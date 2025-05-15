/**
 * Copyright (C) 2020 ShengDa, Inc. All Rights Reserved.
 */
package cn.xxywithpq.domian.cache.schedule;

import com.schengle.recharge.indent.common.enums.ScheduleBusinessEnum;
import com.schengle.recharge.indent.entity.dto.ScheduleParamDto;
import com.schengle.recharge.indent.entity.dto.ScheduleResultDto;

/**
 * @author CS
 * @title: RechargeStrategy
 * @projectName recharge-indent
 * @date 2023/1/10
 */
public interface ScheduleHandlerStrategy {

	/**
	 * 当前策略对应业务模式
	 */
	ScheduleBusinessEnum type();

	/**
	 * 具体的业务逻辑
	 */
	ScheduleResultDto handle(ScheduleParamDto req) throws Exception;
}
