/**
 * Copyright (C) 2020 ShengDa, Inc. All Rights Reserved.
 */
package cn.xxywithpq.domian.cache.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResultDto implements Serializable {

	@Serial
	private static final long serialVersionUID = -647226569887307376L;

	/**
	 * 执行结果
	 */
	private Boolean isSuccess;

	public static ScheduleResultDto success() {
		ScheduleResultDto scheduleResultDto = new ScheduleResultDto();
		scheduleResultDto.setIsSuccess(true);
		return scheduleResultDto;
	}


}
