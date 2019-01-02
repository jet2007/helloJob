cd /home/hellojob/hj/helloJob

git fetch --all  
git reset --hard origin/dev-201812 
git pull


cd /home/hellojob/hj/helloJob/helloJob
mvn clean package -DskipTests=true
cd /app/apache-tomcat-8.5.15
sh bin/shutdown.sh
rm webapps/shiro.war -rf
rm webapps/shiro -rf
cp /home/hellojob/hj/helloJob/helloJob/target/shiro.war /app/apache-tomcat-8.5.15/webapps/
sh bin/startup.sh