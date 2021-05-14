##BootStrap作为容器启动的入口:
###[1]解析XML中的Bean信息
####1.以 **ClassPathXmlApplicationContext** 加载配置文件为例进行IOC流程(入口):
####2.获取配置路径:
    1)调用 **super(parent)** 父类构造器为容器设置好Bean资源加载器
    2)调用父类AbstractRefreshableConfigApplicationContext的setConfigLocations(configLocations) 方法设置Bean配置信息的定位路径
####3.容器开始启动,IOC容器对Bean配置资源的载入是从**refresh()** 方法开始,它是一个模板方法
    1)调用父类AbstractApplicationContext的refresh()方法启动整个IOC容器
    2)IOC容器载入Bean配置信息从其子类容器refreshBeanFactory()方法启动,所以整个refresh()方法中的**ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory()**以后的代码都是在注册容器的信息源和生命周期时间,
    3)refresh() 方法的主要作用是:再创建IOC容器前,如果已经有容器存在,需要把已有的容器销毁和关闭,以保证再refresh()方法之后使用的是新创建的IOC容器,再新创建的容器中对容器进行初始化,对Bean配置资源进行载入
    4)obtainFreshBeanFactory()方法正真调用的是AbstractRefreshableApplicationContext实现的refreshBeanFactory()
    5)接着在refreshBeanFactory()方法中调用lodBeanDefinitions()方法转载Bean定义,其实现是在AbstractXmlApplicationContext子类中,继续跟代码,方法中调用lodBeanDefinitions最终实现在AbstractBeanDefinitionReader中
    6)在loadBeanDefinition中Resource[] resources = ((ResourcePatternResolver) resourceLoader).getResources(location); getResources最终在ClassPathXmlApplicationContext的父类DefaultResourceLoader ->org.springframework.core.io.DefaultResourceLoader.getResource中进行解析
    7)跟踪代码loadBeanDefinitions最终实现在XmlBeanDefinition中,最终doLoadBeanDefinitions中进行具体实现,Document doc = doLoadDocument(inputSource, resource);将Bean配置文件转换成文档对象
####4.将Bean配置文件解析成为文档对象之后,下一步将文档对象解析成为Spring IOC管理的Bean对象并将其注册到容器中--->分配解析策略:
    1)在Document doc = doLoadDocument(inputSource, resource)之后,执行int count = registerBeanDefinitions(doc, resource); 方法,进行Bean配置信息的解析&&载入
    2)接着doRegisterBeanDefinitions(doc.getDocumentElement())-->parseBeanDefinitions(root, this.delegate)--->parseDefaultElement(ele, delegate);-->最终对xml中的元素标签 <import>、<alias>、<bean>、<beans>进行解析
                                                                                                                               if (delegate.nodeNameEquals(ele, IMPORT_ELEMENT)) {
                                                                                                                                              			importBeanDefinitionResource(ele);
                                                                                                                                              		}
                                                                                                                                              		else if (delegate.nodeNameEquals(ele, ALIAS_ELEMENT)) {
                                                                                                                                              			processAliasRegistration(ele);
                                                                                                                                              		}
                                                                                                                                              		else if (delegate.nodeNameEquals(ele, BEAN_ELEMENT)) {
                                                                                                                                              			processBeanDefinition(ele, delegate);
                                                                                                                                              		}
                                                                                                                                              		else if (delegate.nodeNameEquals(ele, NESTED_BEANS_ELEMENT)) {
                                                                                                                                              			// recurse
                                                                                                                                              			doRegisterBeanDefinitions(ele);
                                                                                                                                              		}
    3)<bean>的解析在parseDefaultElement()--->processBeanDefinition(ele, delegate);-->BeanDefinitionHolder bdHolder = delegate.parseBeanDefinitionElement(ele);
    4)解析成为 BeanDefinitionHolder 后 想容器中进行注册-->registry.registerBeanDefinition(beanName, definitionHolder.getBeanDefinition());
    5)registerBeanDefinition 的实现是在 DefaultListableBeanFactory中实现????为啥
    6)先不管第五步,我们继续走 注册BeanDefinition的具体实现在 DefaultListableBeanFactory 中的 registerBeanDefinition(String beanName, BeanDefinition beanDefinition) 方法,将BeanDefinition注册到 beanDefinitionMap 方法中 ,完成BeanDefinition的注册
    
    
    
###[2]注解模式加载Bean信息 