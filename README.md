# EagleEyes

# 使用如下命令打包跳过测试环节
mvn clean package -Dmaven.test.skip=true

# 启动时默认激活开发环境，使用如下命令以生产环境启动
java -jar eagleeyes-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
