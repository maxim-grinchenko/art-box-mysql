package com.dbconfig;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.exeption.PropertiesLoaderExeption;

public class ConnectionFactoryBuilder {



	private final Connection connection;

	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static String HOST = "jdbc:mysql://localhost:3306/artbox";

	public ConnectionFactoryBuilder() {
		Connection connection = null;

		try {
			Class.forName(JDBC_DRIVER).newInstance();
			connection = DriverManager.getConnection(HOST, loadProperties());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.connection = connection;
	}

	private Properties loadProperties(){
		Properties properties = new Properties();
		try {
			InputStream inputStream = ConnectionFactoryBuilder.class
					.getClassLoader()
					.getResourceAsStream("database.properties");

			properties.load(inputStream);
			return properties;
		} catch (IOException e) {
			e.printStackTrace();
			throw new PropertiesLoaderExeption(e);
		}

	}
	
	public Connection getConnection() {
		return connection;
	}

}
