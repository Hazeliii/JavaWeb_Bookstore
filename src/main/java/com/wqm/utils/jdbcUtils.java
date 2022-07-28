package com.wqm.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.util.JdbcUtils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class jdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();
    static {
        Properties properties = new Properties();
        try {
            //读取属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            //System.out.println(dataSource.getConnection()); com.mysql.cj.jdbc.ConnectionImpl@6e0dec4a
        } catch (Exception e) {
           e.printStackTrace();
        }
    }


    /**
     * 获取数据库连接池中的连接
     * @return 如果返回Null则表示获取连接失败，有值表示成功
     */
    public static Connection getConnection(){
        Connection connection = conns.get();
        if(connection==null){
            try{
                connection = dataSource.getConnection();
                connection.setAutoCommit(false);
                //保存到threadlocal对象中
                conns.set(connection);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void commitAndClose(){
        Connection connection = conns.get();
        if(connection!=null){
            try {
                connection.commit();

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //必须移除，否则会出错，因为tomcat底层使用了线程池
        conns.remove();
    }

    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if(connection!=null){
            try {
                connection.rollback();

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //必须移除，否则会出错，因为tomcat底层使用了线程池
        conns.remove();

    }

    /**
     * 关闭连接，放回数据库连接池
     * @param connection
     */
    public static void close(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
