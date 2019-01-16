package com.helloJob.service.job;

import java.util.List;
import java.util.Set;

public interface JobInstanceService {
	
	/**
	 * 判断该作业是否首次执行
	 * **/
	boolean firstExecute(Long jobId); 
	
	/**
	 * 获取上级作业自依赖失败的作业列表
	 * */
	public List<Long> getRelyJobFailInstance (Long jobId,String dt);
	
	public void add(Long jobId,String dt);
	public void add(Long jobId,String dt,String triggerWay);
	
	/**
	 * 判断该作业是否满足自依赖的条件
	 * ***/
	boolean isSelfRely(Long jobId,String dt);

	void delete(Long jobId, String dt);
	
	void delete(Set<Long> jobIds, String dt);
	
	public void setTriggerWay(Long jobId,String dt,String triggerWay);
	
	public String getTriggerWay(Long jobId,String dt);
	
	/**
	 * 是否需要kill作业实例的下游作业实例
	 * @param jobId
	 * @param dt
	 * @return
	 */
	public boolean isKillNextJobsInst(Long jobId,String dt);
	
	/**
	 * 本作业实例是否依赖上游作业实例
	 * @param jobId
	 * @param dt
	 * @return
	 */
	public boolean isRelyPrevJobsInst(Long jobId,String dt);
	
	/**
	 * 本作业实例执行成功后是否触发下游作业实例
	 * @param jobId
	 * @param dt
	 * @return
	 */
	public boolean isTriggerNextJobsInst(Long jobId,String dt);
	
	public void setUpdateTime(Long jobId,String dt);
	
	public boolean isExistsJobInst(Long jobId,String dt);
	
	
	
}
