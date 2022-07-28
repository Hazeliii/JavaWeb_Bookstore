package com.wqm.test;
import com.wqm.utils.jdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class jdbcUtilsTest {
    @Test
    public void test1() throws SQLException {
        for(int i=0;i<100;i++){
            Connection connection = jdbcUtils.getConnection();
            System.out.println(connection);
            jdbcUtils.close(connection);  //记得要释放
        }

    }
}
