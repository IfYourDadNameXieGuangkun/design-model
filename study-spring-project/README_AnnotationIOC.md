![图片加入示例](image/wallls.com_120970.jpg)
##BootStrap.java作为容器启动的入口:

###解析注解配置,注册Bean到IOC中
####先看 [AnnotationConfigApplicationContext]的继承关系
![图片加入示例](image/AnnotationConfigApplicationContext继承关系图.png)
#####1.执行 [new AnnotationConfigApplicationContext("aye.spring")]
#####2.