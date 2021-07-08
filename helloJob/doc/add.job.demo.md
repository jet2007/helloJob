####系统简介：


```mysql



set @var=2200;    -- 100递增   
set @time11=300 ; -- 每个作业sleep(0~time11)
set @groupname='DayDemo';  -- groupname
set @jobname='DayJob'; --  jobname
set @cron='0 0 6 * * ?'; -- 定时
set @period='YYYYMMDD';  -- YYYYMMDDMIHHSS,YYYYMMDDMIHH,YYYYMMDDMI,年月建议采用天形式

DELETE FROM job_basic_info
  WHERE `id`>=10041+@var AND `id`<=10051 +@var; 

INSERT INTO job_basic_info
(
  `id`
 ,job_group
 ,job_name
 ,command
 ,sche_type
 ,job_type
 ,creater
 ,host_id
 ,remark
 ,create_time
)
 SELECT   @var + `id`
 ,job_group
 ,job_name
 ,command
 ,sche_type
 ,job_type
 ,creater
 ,host_id
 ,remark
 ,create_time
  FROM job_basic_info 
  WHERE `id`>=10041 AND `id`<=10051  ;  -- DAYDEMO


UPDATE job_basic_info
  set job_group=CONCAT(@groupname,10000+@var ) 
      ,job_name= CONCAT(@groupname,10000+@var ,'.',@jobname,`id`) 
   WHERE `id`>=10041+@var AND `id`<=10051 +@var;  




UPDATE job_basic_info 
SET
 command =  concat(
  'echo "####################业务脚本开始..."\n'
  ,'echo "执行日期[sys.exec.dm1:${sys.exec.dm1}]"\n'
  ,'echo "执行日期[sys.exec.dt1:${sys.exec.dt1}]"\n'
  ,'uname -a\necho "',
  job_name,'"\nsleep '
  , round( RAND() * @time11   )  
  ,'\necho "####################业务脚本结束..."'
  )    
   WHERE `id`>=10041+@var AND `id`<=10051 +@var;  

   
   --- 
   
DELETE FROM sche_basic_info
  WHERE `job_id`>=10041+@var AND `job_id`<=10051 +@var; 
   
INSERT INTO sche_basic_info
(
  job_id
 ,creater
 ,sche_type
 ,cron
 ,is_self_rely
 ,begin_time
 ,end_time
 ,try_count
 ,try_interval
 ,receiver
 ,create_time
 ,period
) 
select   job_id+@var 
 ,creater
 ,sche_type
 ,RTRIM( case when sche_type='timeSche' then @cron
       else replace(cron,'100', cast( round(100+@var/100) as char(10)  ) ) 
       END)  as cron
 ,is_self_rely
 ,begin_time
 ,end_time
 ,try_count
 ,try_interval
 ,receiver
 ,create_time
 ,case when sche_type='timeSche' then @period
       else period
       end as period
from  sche_basic_info 
  WHERE `job_id`>=10041 AND `job_id`<=10051  ;   
  
  
DELETE FROM sche_rely_job
  WHERE `pid`>=10041+@var AND `pid`<=10051 +@var; 
  
  INSERT INTO sche_rely_job
(
  pid
 ,job_id
)
select   pid+@var 
,job_id+@var 

from sche_rely_job
  WHERE `pid`>=10041 AND `pid`<=10051  ;  


```



