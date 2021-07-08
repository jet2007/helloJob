package com.helloJob.mapper.job;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.helloJob.model.job.JobInstance;

public interface JobInstanceMapper extends BaseMapper<JobInstance> {

	List<Long> getRelyJobFailInstance(@Param("jobId") Long jobId,@Param("dt") String dt);
	
	void setTriggerWay(@Param("jobId") Long jobId,@Param("dt") String dt,@Param("triggerWay") String triggerWay);

	String getTriggerWay(@Param("jobId") Long jobId,@Param("dt") String dt);
	
	void delete(@Param("jobIds") Set<Long> jobIds,@Param("dt")  String dt);
	
	void setUpdateTime(@Param("jobId") Long jobId,@Param("dt") String dt);

}
