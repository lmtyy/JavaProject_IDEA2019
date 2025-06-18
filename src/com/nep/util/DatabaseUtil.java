package com.nep.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseUtil {
    private static final Logger logger = LogUtil.getLogger(DatabaseUtil.class);
    private static final String URL="jdbc:mysql://127.0.0.1:3306/nep?useSSL=false";  // 数据库地址
    private static final String USER="root";  // 数据库账号
    private static final String PASSWORD="bzy666";  // 输入自己的数据库密码
    private static Connection connection;

    //使用静态代码块注册驱动
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");  // 注册驱动
            logger.config("JDBC驱动注册成功");
        } catch (ClassNotFoundException e) {
            logger.severe("JDBC驱动注册失败: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);  // 获取数据库连接
            logger.fine("数据库连接建立成功");
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();  // 释放资源
                logger.fine("数据库连接关闭成功");
            }
        } catch (SQLException e) {
            logger.severe("关闭数据库连接时出错: " + e.getMessage());
        }
    }
}

