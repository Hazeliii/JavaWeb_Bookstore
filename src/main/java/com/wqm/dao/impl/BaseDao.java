package com.wqm.dao.impl;
import com.wqm.utils.jdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//让其他类继承 从而复用代码
public abstract class BaseDao {
    //使用DbUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     *  用于执行insert/update/dalete语句
     * @param sql sql语句
     * @param args 语句中的参数
     * @return -1：操作失败 其他值：影响的行数
     */
    public int update(String sql,Object ... args){
        Connection connection = jdbcUtils.getConnection();
        try {
            return queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回一个javabean的sql语句
     * @param type 返回的对象类型
     * @param sql 执行的对象类型
     * @param args 执行的sql语句
     * @return
     * @param <T> 返回的类型的泛型
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args){
        Connection connection = jdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回多个javabean的sql语句
     * @param type
     * @param sql
     * @param args
     * @return
     * @param <T>
     */
    public <T>List<T> queryForList(Class<T> type, String sql, Object... args){
        Connection connection = jdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(type),args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回一行或者一列
     * @param sql
     * @param args
     * @return
     */
    public Object queryForSingleValue(String sql, Object ... args){
        Connection connection = jdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
