package edu.nju.desserthouse.util;

public final class FinalValue {
	public final class Gender {
		public static final int MALE = 0;// 男性
		public static final int FEMALE = 1;// 女性
	}// 性别

	public final class UserCategory {
		public static final int COMMON_MENBER = 0;// 普通会员
		public static final int BRANCH_WAITER = 1;// 分店服务员
		public static final int HEAD_WAITER = 2;// 总店服务员
		public static final int MANAGER = 3;// 经理
		public static final int ADMINISTRATOR = 4;// 系统管理员
	}// 用户种类

	public final class UserLevel {
		public static final int BASIC_MEMBER = 0;// 基础会员
		public static final int ADVANCED_MENBER = 1;// 高级会员
		public static final int VIP = 2;// vip会员
	}// 用户等级

	public final class UserState {
		public static final int NORMAL = 0;// 正常使用
		public static final int SUSPEND = 1;// 暂停
		public static final int STOP = 2;// 停止
	}// 会员资格

	public final class RegionType {
		public static final int COUNTRY = 0;// 国家
		public static final int PROVINCE = 1;// 省级行政区
		public static final int CITY = 2;// 市级行政区
		public static final int COUNTY = 3;// 县级行政区
	}// 地区类型
}
