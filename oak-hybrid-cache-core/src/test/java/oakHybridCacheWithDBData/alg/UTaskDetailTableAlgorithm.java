package oakHybridCacheWithDBData.alg;

import com.alibaba.fastjson2.JSON;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 实现自定义COMPLEX分片策略
 * 声明算法时，ComplexKeysShardingAlgorithm接口可传入一个泛型，这个泛型就是分片键的数据类型。
 * 这个泛型只要实现了Comparable接口即可。
 * 但是官方不建议声明一个明确的泛型出来，建议是在doSharding中再做类型转换。这样是为了防止分片键类型与算法声明的类型不符合。
 *
 * @auth roykingw
 */
public class UTaskDetailTableAlgorithm implements ComplexKeysShardingAlgorithm<Comparable<?>> {

    Logger log = LoggerFactory.getLogger(UTaskProgressDBAlgorithm.class);
    private Properties props;

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Comparable<?>> shardingValue) {
        log.info("availableTargetNames {} , shardingValue {}", JSON.toJSONString(availableTargetNames), JSON.toJSONString(shardingValue));
        Collection<Comparable<?>> userIds = shardingValue.getColumnNameAndShardingValuesMap().get("user_id");
//        Collection<Comparable<?>> createTimes = shardingValue.getColumnNameAndShardingValuesMap().get("create_time");
        Integer tableNum = Integer.valueOf(props.getProperty("tableNum"));


//        Range<Comparable<?>> createTimesRange = shardingValue.getColumnNameAndRangeValuesMap().get("create_time");
//        HashSet<Integer> yearRangeSet = new HashSet<>();

//        if (!Objects.isNull(createTimesRange)) {
//            DateTime lowerDateTime = DateTime.of((String) createTimesRange.lowerEndpoint(), "yyyy-MM-dd HH:mm:ss");
//            DateTime upperDateTime = DateTime.of((String) createTimesRange.upperEndpoint(), "yyyy-MM-dd HH:mm:ss");
//            int lowerYear = Integer.parseInt(lowerDateTime.toString("yyyy"));
//            int upperYear = Integer.parseInt(upperDateTime.toString("yyyy"));
//
//            for (int year = lowerYear; year <= upperYear; year++) {
//                yearRangeSet.add(year);
//            }
//        }
//
//        if (!Objects.isNull(createTimes)) {
//            for (Comparable<?> createTime : createTimes) {
//                yearRangeSet.add(((Timestamp) createTime).toLocalDateTime().getYear());
//            }
//        }

        HashSet<Long> userIdsSet = new HashSet<>();
        if (!Objects.isNull(userIds)) {
            for (Comparable<?> userId : userIds) {
                userIdsSet.add(((Number) userId).longValue());
            }
        }

        HashSet<String> availableTarget = new HashSet<>();
        if (!CollectionUtils.isEmpty(userIdsSet)) {
            for (Long userId : userIdsSet) {
                int index = (int) ((tableNum - 1) & (userId % 1000));
                String patternString = "u_task_detail_" + LocalDate.now().getYear() + "_" + index;
                Pattern pattern = Pattern.compile(patternString);
                for (String availableTargetName : availableTargetNames) {
                    Matcher matcher = pattern.matcher(availableTargetName);
                    if (matcher.matches()) {
                        availableTarget.add(availableTargetName);
                    }
                }
            }
        }

        return availableTarget;
    }

    public Properties getProps() {
        return this.props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    public void init(Properties props) {
        this.props = props;
    }

}
