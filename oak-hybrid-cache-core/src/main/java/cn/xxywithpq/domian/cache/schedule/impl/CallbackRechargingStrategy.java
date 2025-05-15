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

/**
 * @author CS
 * @title: CardRechargeStrategy
 * @projectName recharge-indent
 * @description: 卡密类充值
 * @date 2023/1/10
 */
@Slf4j
@Service
public class CallbackRechargingStrategy extends AbstractScheduleHandlerStrategy {

	@Override
	public ScheduleBusinessEnum type() {
		return ScheduleBusinessEnum.CALLBACK_RECHARGING;
	}

	@Override
	public ScheduleResultDto handle(ScheduleParamDto req) throws Exception {
		return notification(req);
	}

}
