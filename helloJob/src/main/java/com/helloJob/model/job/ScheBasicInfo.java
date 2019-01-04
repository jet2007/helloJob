package com.helloJob.model.job;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.helloJob.utils.DateUtils;

import lombok.Data;
@TableName("sche_basic_info")
@Data
public class ScheBasicInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.INPUT,value="job_id")
	private Long jobId;
	private Long creater;//创建人
	private String scheType;
	private String  cron;//时间调度
	private String isSelfRely;//是否自依赖
	private String beginTime;
	private String endTime;
	private Integer tryCount = 0;//默认重试次数
	private Integer tryInterval = 1;//默认重试间隔分钟
	/*private String receiver;//收件人
*/	private String createTime;
	private String period;
	
	
	/**
	 * 调度的作业是否是有效的
	 */
	public boolean isAvailable(){
		Long beginTime = this.beginTime !=null ? Long.valueOf( this.beginTime):-1L;
		Long endTime =this.endTime !=null? Long.valueOf(this.endTime):99999999999999L;
		Long toDay = Long.valueOf(DateUtils.getNowFormatStr());
		if(beginTime<=toDay && toDay<=endTime){
			return true;
		}else{
			return false;
		}
	}
	
}
