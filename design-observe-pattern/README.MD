#案例说明:
当“小伙伴们”在GPer生态圈中提问的时候，如果设置了指定老师回
答，对应的老师就会收到邮件通知，这就是观察者模式的一种应用场
景。有些“小伙伴”可能会想到MQ、异步队列等，其实JDK本身就提供
这样的API。我们用代码来还原一下这个应用场景，创建GPer类