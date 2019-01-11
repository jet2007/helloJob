## Hello Job
### 调度系统
hello job 是使用j2ee技术开发的调度系统，提供交互简单的中文操作界面，40秒上手。目前业界有不少调度系统，比如oozie（太难用）、xxl-job（太重量）、airflow（python写的，依赖linux的crontab，只能够部署在linux）， hello job致力于打造一个轻量级的、简单好用的跨平台调度系统，希望可以成为调度界的一股清流。

### TOTO LIST：
1. 增加任务的分组概念
2. 触发时间改成yyyymmddhhmmss
3. 主机---> 主机组
4. 任务优先级
5. 队列：
   1. 任务使用的队列
   2. 队列资源：任务数，CPU,内存
6. 作业日志+权限过滤
7. 增加任务的邮件
8. 作业类型的自定义开发





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
- 部署好项目后，登陆http://localhost:8080/war_name ，默认账号 admin/test



### 改进

- > 



#### 作业类型的自定义开发

- 自定义开发作业类型

  - 作业类型增加一个命令列；实现自定义操作不同类型的作业，包括一个参数(%s,用String.format方法实现)；
  - 如示例2，则在job定义内容中，只需要填写业务sql逻辑即可
  - **换行符：在页面上有bug** ,请使用“;”或直接修改数据库

  > 示例内容1： echo "shell 00001---"; %s
  >
  > 示例内容2： hive -e "%s"





#### 触发时间改成yyyymmddhhmmss

- 将原来的yyyymmdd的int类型的execdate触发时间，改成yyyymmddhhmmss的字符串



#### 触发时间的相关参数

- 值与格式（触发时间execdate=20181012131415）：
  - \${sys.exec.dt1}  20181012
  - \${sys.exec.dm1}  20181012131415
  - \${sys.exec.dt2} 2018-10-12
  - \${sys.exec.dm2} 2018-10-12 13:14:15
- 日期加减计算（仿python relativedelta实现日期相加操作）
  - 值+delta计算；delta计算支持的有：year,month,day,hour,minute,second
  - 示例：\${sys.exec.dt1<u>:year=+2,month=8,day=-16</u>}, 【是在exec_date基础上，年份加2，月份为8，日期减16，输出格式为\${sys.exec.dt1}】
  - 示例2：\${sys.exec.dt2:day=1,day=-1} 上月月末 2018-09-30
- 日期加减计算(简易版): 只支持"天"单位的加减
  - 示例: \${sys.exec.dt1<u>-1</u>} , \${sys.exec.dt2<u>+1</u>} 分别对应昨天20181011,2018-10-13
- \${dt1},\${dt2} ，为昨天值，值为20181011，2018-10-11



#### 重启tomcat服务，原来的cron不起作用

- ScheBasicInfoServiceImpl的getScheByTime方法等
- 已完成



#### 作业组

- ScheBasicInfoServiceImpl的getScheByTime方法等
- 增加一个作业组概念
- 简单实现：只在作业基本信息增加一列名称（建议改成id-name,形式）



#### 作业日志+权限过滤

- 作业创建人+责任人
- 已完成



####  主页---？？



#### 主机---> 主机组(没实现)

- 表：host_group_info
- 作业实例/作业日志+host



* 作业依赖触发：一个子作业可以依赖多个父作业，一个父作业可以有多个子作业，系统同时做死循环判断，避免作业依赖形成环。
* 手工执行：对任何作业都可以手动触发一次。
2. 调度系统自身并不承担业务逻辑，通过ssh 协议执行远程机器的命令，支持hive、spark、kettle、python、shell等脚本的执行。
3. 本系统实现了邮件告警功能，当作业失败时，第一责任人（创建人）和第二责任人是直接收件人。第三之后的责任人是抄送。在“用户管理”中配置用户邮箱地址。
4. 带有一个名为dt1,dt2的日期变量（yyyyMMddhhmmss格式），可以在“执行命令”中使用\${dt1}。如“echo \${dt1}”。dt的值默认为昨天。所以本系统特别适合用于etl按天增量同步数据的作业的调度。
5. 对于作业有个“自依赖”的选项，自依赖约束该作业在当天dt能够执行，要求前一天dt已经成功执行。
6. 可以部署在windows 或者linux 服务器。



##### 错误处理

- MYSQL 5.7-only_full_group_by,可能报错

  - 已修复，应当不会发生
  - 处理方法： 
    - SHOW VARIABLES like '%sql_mode%' ; 去掉ONLY_FULL_GROUP_BY后配置/etc/my.cnf的[mysqld]，如下；再重启mysql

- > sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION



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

* 


