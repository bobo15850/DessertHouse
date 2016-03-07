package edu.nju.desserthouse.util;

public final class FinalValue {
	public final static int CONSUME_TO_POINT = 10;// 消费转化为积分
	public final static int POINT_TO_MONEY = 2;// 积分转化为金额

	public final static class Gender {
		public static final int UNKNOW = 0;// 未知
		public static final int MALE = 1;// 男性
		public static final int FEMALE = 2;// 女性

		private static final String[] genderStr = { "未设置", "男", "女" };

		public static String getStrOfGender(final int gender) {
			return (gender >= 0 && gender < genderStr.length) ? genderStr[gender] : genderStr[0];
		}
	}// 性别

	public final static class UserCategory {
		public static final int COMMON_MENBER = 0;// 会员
		public static final int BRANCH_WAITER = 1;// 分店服务员
		public static final int HEAD_WAITER = 2;// 总店服务员
		public static final int MANAGER = 3;// 经理
		public static final int ADMINISTRATOR = 4;// 系统管理员

		private static final String[] categoryStr = { "会员", "分店服务员", "总店服务员", "经理", "系统管理员", "会员" };

		public static String getStrOfUserCategory(final int category) {
			return (category >= 0 && category < categoryStr.length) ? categoryStr[category] : categoryStr[0];
		}
	}// 用户种类

	public final static class UserLevel {
		public static final int BASIC_MEMBER = 0;// 基础会员
		public static final int ADVANCED_MENBER = 1;// 高级会员
		public static final int VIP = 2;// vip会员

		private static final String[] levelStr = { "基础会员", "高级会员", "vip会员" };
		private static final double[] levelBaseline = { 0, 1000, 5000 };
		private static final double[] levelDiscount = { 0.9, 0.8, 0.7 };

		public static String getStrOfUserLevel(final int level) {
			return (level >= 0 && level < levelStr.length) ? levelStr[level] : levelStr[0];
		}

		public static double getBaseLine(final int level) {
			return (level >= 0 && level < levelBaseline.length) ? levelBaseline[level] : levelBaseline[0];
		}

		public static double getDiscount(final int level) {
			return (level >= 0 && level < levelDiscount.length) ? levelDiscount[level] : levelDiscount[0];
		}
	}// 用户等级

	public final static class UserState {
		public static final int INACTIVE = 0;// 未激活
		public static final int NORMAL = 1;// 正常使用
		public static final int SUSPEND = 2;// 暂停
		public static final int STOP = 3;// 停止

		private static final String[] stateStr = { "未激活(充值200元激活)", "正常使用", "暂停", "停止" };

		public static final String getStrOfUserState(final int state) {
			return (state >= 0 && state < stateStr.length) ? stateStr[state] : stateStr[0];
		}
	}// 会员资格

	public final static class RegionType {
		public static final int COUNTRY = 0;// 国家
		public static final int PROVINCE = 1;// 省级行政区
		public static final int CITY = 2;// 市级行政区
		public static final int COUNTY = 3;// 县级行政区
	}// 地区类型

	public final static class ScheduleState {
		public static final int APPROVING = 0;// 正在审核中
		public static final int APPROVE_SUCCEED = 1;// 审核通过
		public static final int APPROVE_FAILED = 2;// 审核未通过
	}// 产品计划状态

	public final static class BookState {
		public static final int NOT_PAY = 0;// 提交了订单但是未付款
		public static final int PAY = 1;// 付款但是未拿货
		public static final int FINISH = 2;// 拿了货
		public static final int CANCLE = 3;// 取消了的订单
	}

	public final static int SALES_RECORD = -1;// 代表销售订单

	public final static class PayMode {
		public static final int NO_CARD_CASH = 0;// 无会员卡现金支付
		public static final int WITH_CARD_CASH = 1;// 有会员卡现金支付
		public static final int CARD_PAY = 2;// 会员卡余额支付
	}
}
