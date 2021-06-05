package com.design.jdbc.sqlhelp;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public final class DBConnectionPool extends Pool {
    private int checkOut;//正在使用的连接数
    private Vector<Connection> freeConnections = new Vector<>();//存放产生的连接对象容器

    private String passWord = null;
    private String url = null;
    private String userName = null;
    private static int num = 0;//空闲连接数
    private static int numActive = 0;//当前可用连接数
    private static DBConnectionPool pool = null;//连接池示例变量

    public static synchronized DBConnectionPool getInstance() {
        if (pool == null) {
            pool = new DBConnectionPool();
        }
        return pool;
    }

    private DBConnectionPool() {
        try {
            init();
            for (int i = 0; i < normalConnect; i++) {
                Connection c = newConnection();
                if (c != null) {
                    freeConnections.addElement(c);
                    num++;//记录总连接数
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //创建一个新连接
    private Connection newConnection() {
        Connection con = null;
        try {
            if (userName == null || passWord == null) {
                con = DriverManager.getConnection(url);
            } else {
                con = DriverManager.getConnection(url, userName, passWord);
            }
            System.out.println("连接池创建一个新的连接");
        } catch (Exception e) {
            System.out.println("无法创建这个URL的连接" + url);
            return null;
        }
        return con;
    }


    private void init() throws IOException {
        InputStream is = DBConnectionPool.class.getResourceAsStream(propertiesName);
        Properties p = new Properties();
        p.load(is);
        this.userName = p.getProperty("userName");
        this.passWord = p.getProperty("passWord");
        this.driverName = p.getProperty("driverName");
        this.url = p.getProperty("url");
        this.maxConnect = Integer.parseInt(p.getProperty("maxConnect"));
        this.normalConnect = Integer.parseInt(p.getProperty("normalConnect"));

    }


    @Override
    protected void createPool() {
        pool = new DBConnectionPool();
        if (pool != null) {
            System.out.println("创建连接池成功");
        }
    }

    @Override
    public synchronized Connection getConnection() {
        Connection con = null;
        if (freeConnections.size() > 0) {
            num--;
            con = freeConnections.firstElement();
            freeConnections.removeElementAt(0);

            try {
                if (con.isClosed()) {
                    System.out.println("从连接池中删除一个无效连接");
                    con = getConnection();
                }
            } catch (Exception e) {
                System.out.println("从链接池中删除一个无效链接");
                con = getConnection();
            }
        } else if (maxConnect == 0 || checkOut < maxConnect) {
            con = newConnection();
        }
        if (con != null) {
            checkOut++;
        }
        numActive++;
        return con;
    }

    @Override
    public synchronized Connection getConnection(long timeout) {
        long startTime = new Date().getTime();
        Connection con;
        while ((con = getConnection()) == null) {
            try {
                wait(timeout);
            } catch (Exception e) {

            }
            if ((new Date().getTime() - startTime) >= timeout) {
                return null;
            }
        }
        return con;
    }

    //关闭所有连接
    @Override
    public synchronized void release() {
        try {
            Enumeration allConnections = freeConnections.elements();
            while (allConnections.hasMoreElements()) {
                Connection con = (Connection) allConnections.nextElement();
                try {
                    con.close();
                    num--;
                } catch (SQLException e) {
                    System.out.println("无法关闭连接池中的连接");
                }
            }
            freeConnections.removeAllElements();
            numActive = 0;
        }finally {
            super.release();
        }
    }

    //如果不在使用某个连接对象,可调用此方法将该对象释放到连接池
    @Override
    public synchronized void freeConnection(Connection con) {
        freeConnections.addElement(con);
        num++;
        checkOut--;
        numActive--;
        notifyAll();
    }

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public int getNumActive() {
        return numActive;
    }
}
