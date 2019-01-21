package com.helloJob.constant;

public class JobInstanceTriggerWayConst {
	public static final String AUTO  = "01";
	public static final String MAN_DOWNS  = "11";
	public static final String MAN_ONE_RELY  = "12";
	public static final String MAN_ONE = "13";
	
	// 01:自动(启动作业及下游依赖作业,依赖上游作业)
	// 11:启动作业及下游依赖作业(依赖上游作业且强制kill下游依赖作业)
	// 12:启动作业(依赖上游作业)
	// 13:单个作业(不依赖上游作业)
	
}
