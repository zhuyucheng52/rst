package com.echo.rst.entity;

/**
 * Created by echo on 16-6-26.
 */
public class ErrorCodes {
	/** 用户相关错误 */
	public static final int USER_NOT_EXISTS         = 1_000_000;

	/** 订单相关错误 */
	public static final int ORDER_NOT_EXISTS        = 2_000_000;

	/** 操作日志相关错误 */
	public static final int OPER_LOG_NOT_EXISTS     = 3_000_000;

	/** 菜单相关错误 */
	public static final int MENU_NOT_EXISTS         = 4_000_000;
}
