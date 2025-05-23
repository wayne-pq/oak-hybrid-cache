package cn.xxywithpq.domian.cache.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ScheduleBusinessEnum {
	CALLBACK_RECHARGING("callbackRecharging", "充值中-回调h5"),
	RECEIVE_NOTIFICATION("receiveNotification", "卡密充值-回调h5"),
	RECHARGE_NOTIFICATION("rechargeNotification", "直充-回调h5"),
	;

	private final String code;
	private final String desc;

	public static ScheduleBusinessEnum getEnumByCode(String code) {
		for (ScheduleBusinessEnum value : ScheduleBusinessEnum.values()) {
			if (value.code.equals(code)) {
				return value;
			}
		}
		return null;
	}
}
