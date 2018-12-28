## Hello Job
### 调度系统
hello job 是使用j2ee技术开发的调度系统，提供交互简单的中文操作界面，40秒上手。目前业界有不少调度系统，比如oozie（太难用）、xxl-job（太重量）、airflow（python写的，依赖linux的crontab，只能够部署在linux）， hello job致力于打造一个轻量级的、简单好用的跨平台调度系统，希望可以成为调度界的一股清流。

### TOTO LIST：
1. 增加任务的项目组概念
2. 触发时间改成yyyymmddhhmmss
3. 主机---> 主机组
4. 任务优先级
5. 队列：
   1. 任务使用的队列
   2. 队列资源：任务数，CPU,内存
6. 作业日志+权限过滤
7. 增加任务的邮件



### 开发环境

- 开发工具：eclipse，lombok，maven、tomcat 8.55，mysql5.5+

- lombok 的主要作用是通过注解减少setter 和getter方法的生成，保持代码简洁。

- eclipse 务必要先安装lombok插件

  >  lombok-xxxxxx.jar包
  >
  > 配置文件eclipse.ini的目录上，增加lombok.jar(无xxxxxx)
  >
  > 配置文件eclipse.ini中是否 添加了如下内容: 
  > ​    -javaagent:lombok.jar 
  >
  > ​    -Xbootclasspath/a:lombok.jar 
  >
  > 安装成功，使用不了的原因：没有重启IDE，没有clean项目（project-->clean），lombok版本太低等等

- 



### 部署

##### 部署

- git clone https://github.com/jet2007/helloJob
- 配置
  - 数据库：doc/helloJob.sql
  - 数据库连接配置：src/main/application.properties
  - 作业告警发件邮箱配置：src/main/email.properties
- 在pom.xml所在目录，执行mvn clean package -DskipTests=true，生成target/*.war包
- 将war拷贝到apache-tomcat-8下，启动tomcat
- 部署好项目后，登陆http://localhost:8080/helloJob ，默认账号 admin/test



##### 错误处理

- MYSQL 5.7-only_full_group_by,可能报错

  - 处理方法： 
    -  SHOW VARIABLES like '%sql_mode%' ; 去掉ONLY_FULL_GROUP_BY后配置/etc/my.cnf的[mysqld]，如下；再重启mysql

- > sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION



- 

* 时间调度：底层基于quartz实现，支持cron命令，实现灵活的时间调度方式。
* 作业依赖触发：一个子作业可以依赖多个父作业，一个父作业可以有多个子作业，系统同时做死循环判断，避免作业依赖形成环。
* 手工执行：对任何作业都可以手动触发一次。
2. 调度系统自身并不承担业务逻辑，通过ssh 协议执行远程机器的命令，支持hive、spark、kettle、python、shell等脚本的执行。
3. 本系统实现了邮件告警功能，当作业失败时，第一责任人（创建人）和第二责任人是直接收件人。第三之后的责任人是抄送。在“用户管理”中配置用户邮箱地址。
4. 带有一个名为dt的日期变量（yyyyMMdd格式），可以在“执行命令”中使用${dt}。如“echo ${dt}”。dt的值默认为昨天。所以本系统特别适合用于etl按天增量同步数据的作业的调度。
5. 对于作业有个“自依赖”的选项，自依赖约束该作业在当天dt能够执行，要求前一天dt已经成功执行。
6. 可以部署在windows 或者linux 服务器。

# 系统部分截图
![作业管理](https://github.com/iture123/helloJob/blob/dev/helloJob/doc/job.png)

![添加作业](https://github.com/iture123/helloJob/blob/dev/helloJob/doc/addJob.png)

![作业日志](https://github.com/iture123/helloJob/blob/dev/helloJob/doc/jobLog.png)

# 技术栈：
jdk1.8、spring、springmvc、mybatis、quartz、mysql

# 配置文件
* 数据库：doc/helloJob.sql
* 数据库连接配置：src/main/application.properties
* 作业告警发件邮箱配置：src/main/email.properties

# 开发环境
* 开发工具：eclipse，lombok，maven、tomcat 8.55，mysql5.5+
*  lombok 的主要作用是通过注解减少setter 和getter方法的生成，保持代码简洁。eclipse 务必要先安装lombok插件
*  部署好项目后，登陆http://localhost:8080/helloJob ，默认账号 admin/test

# 未来规划
计划将来融入如下功能：
* 添加本地脚本执行方式，即对于部署脚本和调度系统在同一台机器的作业，不用通过ssh来执行。
* 增加对windows 远程调用的支持，目前考虑使用telnet方式实现，是否有更好的方式呢？
* 如果有其他idea，欢迎您提出来━(*｀∀´*)ノ亻!

# 特别鸣谢
* 本系统的权限控制用的是轩少_开源的spring-shiro-training，在此特别感谢
* [ spring-shiro-training 开源中国地址 ](https://www.oschina.net/p/spring-shiro-training)

# 联系本猿
* 本猿qq：1011699225（默默向上游）

# 腾讯内推
本猿目前在腾讯工作,有需要内推的朋友,可以发简历给我 1011699225@qq.com
