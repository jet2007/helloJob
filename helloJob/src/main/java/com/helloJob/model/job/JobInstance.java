package com.helloJob.model.job;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;

/**
 * 作业实例,用于作业依赖的（自依赖和上下级依赖）。
 * */
@TableName(value="job_instance")
@Data
public class JobInstance implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 主键id */
	@TableId(type = IdType.INPUT)
	private String id;
	@TableField(value="job_id")
	private Long jobId;
	private String dt;
	private String createTime;
	public void setId(Long jobId, String dt) {
		this.id = jobId+"_"+dt;
	}
	public String triggerWay = "01";
	public String state;
	@TableField(value="update_time")
	public String updateTime;
	
}
