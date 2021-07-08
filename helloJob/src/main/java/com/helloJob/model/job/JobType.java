package com.helloJob.model.job;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;

@TableName("job_type")
@Data
public class JobType implements Serializable{
	/**
	 * 增加一个cmd,用于自定义设置执行的命令
	 */
	private static final long serialVersionUID = 1L;
	/** 主键id */
	@TableId(type = IdType.AUTO)
	private Long id;
	private String name;
	private int seq;
	private String createTime;
	private String cmd;
	
}
