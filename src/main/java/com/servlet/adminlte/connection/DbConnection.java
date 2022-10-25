package com.servlet.adminlte.connection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbConnection {

    public static Connection getConnection(){
        Properties properties=new Properties();
        ClassLoader loader=Thread.currentThread().getContextClassLoader();
        Connection connection=null;
        try {
            InputStream filInputStream=loader.getResourceAsStream("db.properties");
            properties.load(filInputStream);

            Class.forName(properties.getProperty("DB_DRIVER_CLASS"));

            connection= DriverManager.getConnection(properties.getProperty("DB_URL"), properties.getProperty("DB_USERNAME"),properties.getProperty("DB_PASSWORD"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
