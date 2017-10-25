package com.mtl.util;


import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    public static final String charset = "text/html; charset=utf-8";
    static {
        Properties properties = new Properties();
        try {
//            System.out.println("进入静态代码快");
            properties.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            String dbType = properties.getProperty("dbType");
//            System.out.println("jdbc."+dbType+"driver");
            driver = properties.getProperty("jdbc."+dbType+"driver");
//            System.out.println("driver = " + driver);
            url = properties.getProperty("jdbc."+dbType+"url");
//            System.out.println("url = " + url);
            username = properties.getProperty("jdbc."+dbType+"username");
            password = properties.getProperty("jdbc."+dbType+"password");
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getCon(){
//        System.out.println("url = " + url);
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PreparedStatement getPreStat(Connection connection, String sql){
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setParams(PreparedStatement preparedStatement, Object... params){
        for (int i = 0; i <params.length; i++) {
            try {
                preparedStatement.setObject(i+1, params[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeAll(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
