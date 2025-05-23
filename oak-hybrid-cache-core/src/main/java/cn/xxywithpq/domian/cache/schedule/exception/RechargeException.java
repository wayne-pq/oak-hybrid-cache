/**
 * Copyright (C) 2020 ShengDa, Inc. All Rights Reserved.
 */
package cn.xxywithpq.domian.cache.schedule.exception;

import lombok.Data;

@Data
public class RechargeException extends RuntimeException {

	private static final long serialVersionUID = -2187172886767508228L;

	private static final String errorCode = "100000";
	/**
	 *业务返回值
	 */
	private String code;

	/**
	 *业务返回值描述
	 */
	private String msg;

	public RechargeException(String code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public RechargeException(String msg) {
		this.code = errorCode;
		this.msg = msg;
	}

}
