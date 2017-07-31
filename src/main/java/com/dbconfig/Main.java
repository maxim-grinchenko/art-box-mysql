package com.dbconfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.model.ArtBoxUser;

public class Main {
	
	private static final Logger log = Logger.getLogger(Main.class);
	private final static String SQL_SELECT_USERS = "SELECT * FROM users;";
	
	
	public static void main(String[] args) {
		try {
			findUser1();
			findUser2();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
	public static List<ArtBoxUser> findUser1 () throws SQLException{
		
		 ConnectionFactoryBuilder connectionFactoryBuilder = new ConnectionFactoryBuilder();
		 List<ArtBoxUser> users = new ArrayList<ArtBoxUser>();
		 
		 ResultSet rs = null;
		 try (Connection connection = connectionFactoryBuilder.getConnection()) {
	            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_USERS);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                String name = rs.getString("NAME");
	                String email = rs.getString("EMAIL");
	                String password = rs.getString("PASSWORD");
	                users.add(new ArtBoxUser(name, email, password));
	                
	                
	            }
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
	        log.debug("find all users in DataBase USERS....");
	        boolean status = rs.isClosed();
            System.out.println("1 = "+status);
	        return users;
	}
	
	public static List<ArtBoxUser> findUser2 () throws SQLException{
		
		 ConnectionFactoryBuilder connectionFactoryBuilder = new ConnectionFactoryBuilder();
		 List<ArtBoxUser> users = new ArrayList<ArtBoxUser>();
		 ResultSet rs = null;
		 
		 try {
			 Connection connection = connectionFactoryBuilder.getConnection();
	            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_USERS);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                String name = rs.getString("NAME");
	                String email = rs.getString("EMAIL");
	                String password = rs.getString("PASSWORD");
	                users.add(new ArtBoxUser(name, email, password));
	                
	               
	            }
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
	        log.debug("find all users in DataBase USERS....");
	        
	        boolean status = rs.isClosed();
            System.out.println("2 = "+status);
	        
	        return users;
	}
	
	
}
