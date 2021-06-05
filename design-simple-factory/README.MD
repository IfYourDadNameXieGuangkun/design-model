简单工厂模式,将创建对象的过程丢入工厂对象中创建,要被创建的对象本身不需要关注创建过程
java中的简单工程模式:
logback:
private static Logger logger = LoggerFactory.getLogger(XXX.class);

缺点:不易扩展