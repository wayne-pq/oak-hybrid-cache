package cn.xxywithpq.domian.cache.schedule;

import cn.hutool.http.HttpUtil;
import cn.xxywithpq.domian.cache.schedule.dto.ScheduleParamDto;
import cn.xxywithpq.domian.cache.schedule.dto.ScheduleResultDto;
import cn.xxywithpq.domian.cache.schedule.exception.RechargeException;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public abstract class AbstractScheduleHandlerStrategy implements ScheduleHandlerStrategy {

	protected ScheduleResultDto notification(ScheduleParamDto paramDto) {
		String executeParams = paramDto.getExecuteParams();
		JSONObject parsedParams = JSON.parseObject(executeParams);

		String url = parsedParams.getString("url");
		String json = parsedParams.getJSONObject("param").toJSONString();

		log.info("[{}] - 重试发起请求，url：[{}]，请求参数：[{}]", type().getDesc(), url, json);        // 实际请求逻辑可以根据需要在这里实现
		String resp = HttpUtil.post(url, json, 3000);
		log.info("[{}] - 重试请求完成，url：[{}]，响应结果：[{}]", type().getDesc(), url, resp);
		JSONObject parsed = JSON.parseObject(resp);
		if (Objects.isNull(parsed.getString("resultCode"))) {
			throw new RechargeException("响应结果异常");
		}

		return ScheduleResultDto.success();
	}
}
