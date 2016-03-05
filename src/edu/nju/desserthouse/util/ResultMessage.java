package edu.nju.desserthouse.util;

public class ResultMessage {
	public static final ResultMessage SUCCESS = new ResultMessage();// 成功
	public static final ResultMessage FAILURE = new ResultMessage();// 失败
	public static final ResultMessage IS_EXIST = new ResultMessage();// 已存在
	public static final ResultMessage NOT_EXIST = new ResultMessage();// 不存在
}
