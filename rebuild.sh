cd /home/hellojob/hj/helloJob

git fetch --all  
git reset --hard origin/dev-201812 
git pull


cd /home/hellojob/hj/helloJob/helloJob
mvn clean package -DskipTests=true
cd /app/apache-tomcat-8.5.15
sh bin/shutdown.sh
ps -ef|grep tomcat|grep ^hellojob|grep -v grep|awk '{print $2}' |xargs kill -9  | echo 1

rm webapps/shiro.war -rf
rm webapps/shiro -rf
cp /home/hellojob/hj/helloJob/helloJob/target/shiro.war /app/apache-tomcat-8.5.15/webapps/
sh bin/startup.sh