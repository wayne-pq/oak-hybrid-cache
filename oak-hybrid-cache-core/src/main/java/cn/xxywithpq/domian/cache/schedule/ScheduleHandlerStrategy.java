/**
 * Copyright (C) 2020 ShengDa, Inc. All Rights Reserved.
 */
package cn.xxywithpq.domian.cache.schedule;


import cn.xxywithpq.domian.cache.enums.ScheduleBusinessEnum;
import cn.xxywithpq.domian.cache.schedule.dto.ScheduleParamDto;
import cn.xxywithpq.domian.cache.schedule.dto.ScheduleResultDto;

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
