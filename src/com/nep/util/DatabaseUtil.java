package com.nep.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL="jdbc:mysql://127.0.0.1:3306/nep";//数据库地址
    private static final String USER="root";//数据库账号
    private static final String PASSWORD="bzy666";  // 输入自己的数据库密码
    private static Connection connection;
    //使用静态代码块注册驱动
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");//注册驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);//获取数据库连接
        }
        return connection;
    }
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();//释放资源
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

