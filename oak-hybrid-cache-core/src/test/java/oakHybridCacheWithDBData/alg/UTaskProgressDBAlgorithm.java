package oakHybridCacheWithDBData.alg;

import com.alibaba.fastjson2.JSON;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Properties;

/**
 * 实现自定义COMPLEX分片策略
 * 声明算法时，ComplexKeysShardingAlgorithm接口可传入一个泛型，这个泛型就是分片键的数据类型。
 * 这个泛型只要实现了Comparable接口即可。
 * 但是官方不建议声明一个明确的泛型出来，建议是在doSharding中再做类型转换。这样是为了防止分片键类型与算法声明的类型不符合。
 *
 * @auth roykingw
 */
public class UTaskProgressDBAlgorithm implements StandardShardingAlgorithm<Comparable<?>> {
    Logger log = LoggerFactory.getLogger(UTaskProgressDBAlgorithm.class);
    private Properties props;

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Comparable<?>> preciseShardingValue) {
        Comparable<?> value = preciseShardingValue.getValue();
        log.info("String preciseShardingValue {},collection {}", JSON.toJSONString(preciseShardingValue), JSON.toJSONString(collection));
        return "ds_" + ((Number) value).longValue() % collection.size();
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Comparable<?>> rangeShardingValue) {
        return collection;
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
