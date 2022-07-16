package com.tweetapp.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class JDBCConnection {
	public static Connection getConnection() {
		Properties p=new Properties();
		try {  
		     
		    FileInputStream reader=new FileInputStream(new File("D:\\git\\tweet\\TweetAppComponent1\\src\\main\\resources\\db.properties"));
		    p.load(reader);
			Class.forName(p.getProperty("driver-class"));
		} catch (ClassNotFoundException ex) {
			System.out.println("Couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find db.properties file");
		} catch (IOException e) {
			System.out.println("Unable to load db.properties file");
			return null;
			
		}

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+p.getProperty("database"), p.getProperty("username"), p.getProperty("password"));
			if (conn != null) {
				return conn;
			} else {
				System.out.println("Failed to Establish connection!");
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Sorry!! MySQL Connection Failed!");
			e.printStackTrace();
			return null;
	}
	}
}
