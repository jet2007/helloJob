package com.helloJob.jobExecutor;

import java.util.List;
import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSON;
import com.helloJob.commons.utils.StringUtils;
import com.helloJob.constant.JobStateConst;
import com.helloJob.constant.ScheTypeConst;
import com.helloJob.model.job.JobBasicInfo;
import com.helloJob.model.job.ScheBasicInfo;
import com.helloJob.service.job.JobInstanceService;
import com.helloJob.service.job.JobLogService;
import com.helloJob.utils.ApplicationContextUtil;
import com.helloJob.utils.DateUtils;
import com.helloJob.utils.ThreadUtils;
import com.helloJob.utils.job.JobThreadPool;

public class CommonJobEntry {
	public static final Logger log = LoggerFactory.getLogger(CommonJobEntry.class.getName());
	public static void execute(JobBasicInfo job,ScheBasicInfo scheInfo,String dt) {
			log.info("作业被调起："+JSON.toJSONString(job));
			Long beginTime = StringUtils.isNotBlank(scheInfo.getBeginTime())  ? Long.valueOf( scheInfo.getBeginTime()):-1L;
			Long endTime =StringUtils.isNotBlank(scheInfo.getEndTime()) ? Long.valueOf(scheInfo.getEndTime()):99999999999999L;
			Long toDay = Long.valueOf(DateUtils.getNowFormatStr());
			ApplicationContext context = ApplicationContextUtil.getContext();
			JobLogService jobLogService = context.getBean(JobLogService.class);
			//判断是否在有效执行时间内
			if((beginTime !=null && toDay >=beginTime)  || beginTime ==null ) {
				if((endTime !=null && endTime >=toDay)  || endTime ==null ) {
					String scheType = scheInfo.getScheType();
					//有依赖上级作业的
					JobInstanceService jobInstanceService = context.getBean(JobInstanceService.class);
					if(ScheTypeConst.RELY_PRE_JOB.equals(scheType)) {
						if( !jobInstanceService.isExistsJobInst(job.getId(), dt)){
							jobInstanceService.add(job.getId(), dt);
						}else{
							if(jobInstanceService.isRelyPrevJobsInst(job.getId(), dt)){
								String jobState = jobInstanceService.getJobInst(job.getId(), dt).getState();
								if(jobState.equals(JobStateConst.QUEUE)){
									List<Long> relyJobFailInstanceList = jobInstanceService .getRelyJobFailInstance(job.getId(),dt);
									// 依赖的上一级都成功了
									while(relyJobFailInstanceList.size() > 0){
										ThreadUtils.sleeep(10*1000);
										relyJobFailInstanceList = jobInstanceService .getRelyJobFailInstance(job.getId(),dt);
									}
									if (relyJobFailInstanceList.size() == 0) {
										ExecutorService executorService = JobThreadPool.getInstance();
										jobInstanceService.setJobInstState(job.getId(), dt, JobStateConst.RUNNING);
										executorService.execute(new ShellJobExecutor(job,scheInfo,dt));
										jobInstanceService.setJobInstState(job.getId(), dt, JobStateConst.SUCCESS);
									}
								}else{//作业实例已经启动过了
									while(!jobState.equals(JobStateConst.SUCCESS)){
										ThreadUtils.sleeep(10*1000);
										jobState = jobInstanceService.getJobInst(job.getId(), dt).getState();
									}
								}
								
								

							}
						}
						
					}else {
						//根据时间调度或者运行一次
						ExecutorService executorService = JobThreadPool.getInstance();
						jobInstanceService.setJobInstState(job.getId(), dt, JobStateConst.RUNNING);
						executorService.execute(new ShellJobExecutor(job,scheInfo,dt));
						jobInstanceService.setJobInstState(job.getId(), dt, JobStateConst.SUCCESS);
					}
				}else {
					jobLogService.add(job.getId(), dt, JobStateConst.WARNING,"未执行调用作业！<br>超出作业执行结束时间 ！结束时间为："+endTime);
				}
			}else {
				jobLogService.add(job.getId(), dt, JobStateConst.WARNING,"未执行调用作业！<br>未到作业执行开始时间 ！开始时间为："+beginTime);
			}
	}
}
