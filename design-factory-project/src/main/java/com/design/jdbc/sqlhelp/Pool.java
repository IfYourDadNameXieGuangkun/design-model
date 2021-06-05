package com.design.jdbc.sqlhelp;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 自定义getInstance(),返回Pool的唯一实例,第一次调用时将执行构造函数
 * 构造函数Pool()调用驱动装载load
 */
public abstract class Pool {
    public String propertiesName = "connection-INF.properties";
    private static Pool instance = null;
    protected int maxConnect = 100;
    protected int normalConnect = 10;
    protected String driverName = null;
    protected Driver driver = null;

    protected Pool() {
        try {
            init();
            loadDriver(driverName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        InputStream is = Pool.class.getResourceAsStream(propertiesName);
        Properties p = new Properties();
        p.load(is);
        this.driverName = p.getProperty("driverName");
        this.maxConnect = Integer.parseInt(p.getProperty("maxConnect"));
        this.normalConnect = Integer.parseInt(p.getProperty("normalConnect"));
    }

    private void loadDriver(String driverClassName) {
        try {
            driver = (Driver) Class.forName(driverClassName).newInstance();
            DriverManager.registerDriver(driver);
            System.out.println("成功注册JDBC驱动程序" + driverClassName);
        } catch (Exception e) {
            System.out.println("成功注册JDBC驱动程序" + driverClassName + ",错误:" + e);
        }
    }

    //创建连接池
    protected abstract void createPool();

    /**
     * 单例模式
     */

    public static synchronized Pool getInstance() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (instance == null) {
            instance.init();
            instance = (Pool) Class.forName("com.design.jdbc.sqlhelp.Pool").newInstance();
        }
        return instance;
    }

    public abstract Connection getConnection();
    public abstract Connection getConnection(long time);
    public abstract void freeConnection(Connection con);

    public abstract int getNum();
    public abstract int getNumActive();

    protected synchronized void release(){
        try {
            DriverManager.deregisterDriver(driver);
            System.out.println("撤销JDBC驱动程序"+driver.getClass().getName());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("无法撤销JDBC驱动程序"+driver.getClass().getName());
        }
    }


}
