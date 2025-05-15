/**
 * Copyright (C) 2020 ShengDa, Inc. All Rights Reserved.
 */
package cn.xxywithpq.domian.cache.schedule.impl;

import com.schengle.recharge.indent.common.enums.ScheduleBusinessEnum;
import com.schengle.recharge.indent.entity.dto.ScheduleParamDto;
import com.schengle.recharge.indent.entity.dto.ScheduleResultDto;
import com.schengle.recharge.indent.service.strategy.schedule.AbstractScheduleHandlerStrategy;
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
