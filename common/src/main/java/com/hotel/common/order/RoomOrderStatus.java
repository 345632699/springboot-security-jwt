package com.hotel.common.order;

import lombok.Getter;

/**
 * @author xu
 */

@Getter
public enum RoomOrderStatus {
	/**
	 * 待支付
	 */
	WAIT_TO_PAY(0, "待支付"),
	/**
	 * 已入住
	 */
	CHECKIN(1, "已入住"),
	/**
	 * 待退房
	 */
	WAIT_CHECKOUT(2, "待退房"),
	/**
	 * 已完成
	 */
	HAS_DONE(3, "已完成"),
	/**
	 * 已取消
	 */
	HAS_CANCELED(4, "已取消");
	/**
	 * 状态码
	 */
	private Integer code;

	/**
	 * 返回信息
	 */
	private String message;

	RoomOrderStatus(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public static RoomOrderStatus fromCode(Integer code) {
		RoomOrderStatus[] statuses = RoomOrderStatus.values();
		for (RoomOrderStatus status : statuses) {
			if (status.getCode()
				.equals(code)) {
				return status;
			}
		}
		return WAIT_TO_PAY;
	}
}
