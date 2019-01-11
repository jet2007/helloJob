package com.helloJob.jobExecutor;


import com.alibaba.fastjson.JSON;
import com.helloJob.model.job.JobBasicInfo;
import com.helloJob.model.job.ScheBasicInfo;
import com.helloJob.utils.ExecDateUtils;
import com.helloJob.utils.job.SshUtils;
import com.helloJob.vto.JobExecResult;

public class ShellJobExecutor extends AbstractJobExecutor{
	public ShellJobExecutor(JobBasicInfo job,ScheBasicInfo scheInfo,String dt) {
		super(job, scheInfo,dt);
	}
	@Override
	public JobExecResult execute(JobBasicInfo job) throws Exception {
		log.info(JSON.toJSONString(job));
		String cmd= ExecDateUtils.execDateVariablesReplace(job.getCommand(), this.dt); 
		System.out.println("####ExecDateUtils.execDateVariablesReplace:"+job.getCommand());
		System.out.println("####ExecDateUtils.execDateVariablesReplace:"+cmd);
		job.setCommand( cmd);
		JobExecResult result = SshUtils.execute(job,this.dt);
		return result;
	}
}
