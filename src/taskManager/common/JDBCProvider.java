package taskManager.common;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCProvider {
    private static BasicDataSource basicDataSource = new BasicDataSource();;
    static{
        basicDataSource.setMaxTotal(10);
        basicDataSource.setUsername("taskmng_ee");
        basicDataSource.setPassword("javatask");
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
        basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
    }

    public static Connection getConnection() throws SQLException{
        return basicDataSource.getConnection();
    }
}