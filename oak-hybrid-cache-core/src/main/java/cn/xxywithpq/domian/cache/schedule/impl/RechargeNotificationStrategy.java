/**
 * Copyright (C) 2020 ShengDa, Inc. All Rights Reserved.
 */
package cn.xxywithpq.domian.cache.schedule.impl;

import cn.xxywithpq.domian.cache.enums.ScheduleBusinessEnum;
import cn.xxywithpq.domian.cache.schedule.AbstractScheduleHandlerStrategy;
import cn.xxywithpq.domian.cache.schedule.dto.ScheduleParamDto;
import cn.xxywithpq.domian.cache.schedule.dto.ScheduleResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RechargeNotificationStrategy extends AbstractScheduleHandlerStrategy {

	@Override
	public ScheduleBusinessEnum type() {
		return ScheduleBusinessEnum.RECHARGE_NOTIFICATION;
	}

	@Override
	public ScheduleResultDto handle(ScheduleParamDto req) throws Exception {
		return notification(req);
	}
}
