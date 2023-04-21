package com.paulfranklin.p1.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private final Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

    private static ConnectionFactory connectionFactory;
    private final Properties properties = new Properties();

    private ConnectionFactory() {
        try {
            properties.load(new FileReader("src/main/resources/db.properties"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) connectionFactory = new ConnectionFactory();
        return connectionFactory;
    }

    public Connection getConnection() {
        Connection connection;

        try {
            connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
        }
        catch (SQLException exception) {
            logger.warn("No database connection.");
            throw new RuntimeException();
        }

        return connection;
    }
}
