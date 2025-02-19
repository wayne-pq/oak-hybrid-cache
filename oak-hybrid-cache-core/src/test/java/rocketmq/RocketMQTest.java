package rocketmq;

import oakHybridCache.OakHybridCacheTest;
import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientConfigurationBuilder;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.consumer.ConsumeResult;
import org.apache.rocketmq.client.apis.consumer.FilterExpression;
import org.apache.rocketmq.client.apis.consumer.FilterExpressionType;
import org.apache.rocketmq.client.apis.consumer.SimpleConsumer;
import org.apache.rocketmq.client.apis.message.Message;
import org.apache.rocketmq.client.apis.message.MessageBuilder;
import org.apache.rocketmq.client.apis.message.MessageView;
import org.apache.rocketmq.client.apis.producer.Producer;
import org.apache.rocketmq.client.apis.producer.SendReceipt;
import org.apache.rocketmq.client.java.message.MessageBuilderImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


/**
 * @author qian.pan on 2024/1/17.
 */
public class RocketMQTest {

    Logger log = LoggerFactory.getLogger(OakHybridCacheTest.class);

    @Test
    public void receiveMessage01() throws ClientException, InterruptedException {
        //消费示例：使用PushConsumer消费普通消息。
        ClientServiceProvider provider = ClientServiceProvider.loadService();
        String topic = "FIFOTopic";
        // 接入点地址，需要设置成Proxy的地址和端口列表，一般是xxx:8080;xxx:8081
        String endpoint = "123.123.8.2:8080";
        String consumerGroup = "FIFOGroup";
        FilterExpression filterExpression = new FilterExpression("*", FilterExpressionType.TAG);
        provider.newPushConsumerBuilder()
                //设置消费者分组。
                .setConsumerGroup(consumerGroup)
                //设置接入点。
                .setClientConfiguration(ClientConfiguration.newBuilder().setEndpoints(endpoint).build())
                //设置预绑定的订阅关系。
                .setSubscriptionExpressions(Collections.singletonMap(topic, filterExpression))
                //设置消费监听器。
                .setMessageListener(messageView -> {
                    System.out.println(messageView);
                    //消费消息并返回处理结果。
                    return ConsumeResult.SUCCESS;
                }).build();

        Thread.sleep(1000000);
    }

    /**
     * 基础测试
     */
    @Test
    public void receiveMessage02() throws ClientException {
        //消费示例二：使用SimpleConsumer消费顺序消息，主动获取消息进行消费处理并提交消费结果。
        //需要注意的是，同一个MessageGroup的消息，如果前序消息没有消费完成，再次调用Receive是获取不到后续消息的。
        String topic = "FIFOTopic";
        // 接入点地址，需要设置成Proxy的地址和端口列表，一般是xxx:8080;xxx:8081
        String endpoint = "123.123.8.2:8080";
        String consumerGroup = "FIFOGroup01";

        ClientServiceProvider provider = ClientServiceProvider.loadService();
        ClientConfigurationBuilder builder = ClientConfiguration.newBuilder().setEndpoints(endpoint);
        ClientConfiguration configuration = builder.build();
        // 订阅消息的过滤规则，“*”表示订阅多有tag消息
        FilterExpression filterExpression = new FilterExpression("*", FilterExpressionType.TAG);
        SimpleConsumer simpleConsumer = provider.newSimpleConsumerBuilder()
                .setConsumerGroup(consumerGroup)// 设置消费者分组
                .setClientConfiguration(configuration)
                .setSubscriptionExpressions(Collections.singletonMap(topic, filterExpression))// 设置预绑定的订阅关系
                .setAwaitDuration(Duration.ofSeconds(30))// 设置从服务端接收消息的最大等待时间
                .build();

        List<MessageView> messageViewList = null;
        try {
            messageViewList = simpleConsumer.receive(10, Duration.ofSeconds(30));
            messageViewList.forEach(messageView -> {
                System.out.println(messageView);
                //消费处理完成后，需要主动调用ACK提交消费结果。
                try {
                    simpleConsumer.ack(messageView);
                } catch (ClientException e) {
                    e.printStackTrace();
                }
            });
        } catch (ClientException e) {
            //如果遇到系统流控等原因造成拉取失败，需要重新发起获取消息请求。
            e.printStackTrace();
        }
    }

    @Test
    public void sendMessage() throws ClientException {
        // 接入点地址，需要设置成Proxy的地址和端口列表，一般是xxx:8080;xxx:8081
        String endpoint = "123.123.8.2:8080";
        // 消息发送的目标Topic名称，需要提前创建。
        String topic = "FIFOTopic";
        ClientServiceProvider provider = ClientServiceProvider.loadService();
        ClientConfigurationBuilder builder = ClientConfiguration.newBuilder().setEndpoints(endpoint);
        ClientConfiguration configuration = builder.build();
        // 初始化Producer时需要设置通信配置以及预绑定的Topic。
        Producer producer = provider.newProducerBuilder()
                .setTopics(topic)
                .setClientConfiguration(configuration)
                .build();

        //顺序消息发送。
        MessageBuilder messageBuilder = new MessageBuilderImpl();
        Message message = messageBuilder.setTopic(topic)
                //设置消息索引键，可根据关键字精确查找某条消息。
                .setKeys(UUID.randomUUID().toString())
                //设置消息Tag，用于消费端根据指定Tag过滤消息。
                .setTag("messageTag")
                //设置顺序消息的排序分组，该分组尽量保持离散，避免热点排序分组。
                .setMessageGroup("fifoGroup001")
                //消息体。
                .setBody("messageBody".getBytes())
                .build();
        try {
            //发送消息，需要关注发送结果，并捕获失败等异常
            SendReceipt sendReceipt = producer.send(message);
            System.out.println(sendReceipt.getMessageId());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }


}
