package cn.xxywithpq.domian.cache.schedule;

import cn.xxywithpq.domian.cache.enums.ScheduleBusinessEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class ScheduleHandlerStrategyFactory {

    /**
     * 卡密发券的策略实现类
     */
    @Resource
    private List<ScheduleHandlerStrategy> scheduleHandlerStrategies;

    public ScheduleHandlerStrategy getStrategy(ScheduleBusinessEnum businessModeEnum) {
        Optional<ScheduleHandlerStrategy> getCreateCradOrderStrategy = scheduleHandlerStrategies.stream().filter(t -> t.type() == businessModeEnum).findAny();
        return getCreateCradOrderStrategy.orElseThrow(() -> new IllegalArgumentException("暂不支持的业务模式"));
    }


}
