package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dbconfig.ConnectionFactoryBuilder;
import com.model.ArtBoxUser;

public class UserStorage {
	
	private static UserStorage instance;
	private static final Logger log = Logger.getLogger(UserStorage.class);
	
	private final static String SQL_INSERT_USERS = "INSERT INTO users(NAME, EMAIL, PASSWORD) VALUES (?,?,?);";
	private final static String SQL_SELECT_USERS = "SELECT * FROM users;";
	private final static String SQL_FIND_USER = "SELECT * FROM users WHERE email = ?;";
	
	public UserStorage(){
	}
	
	public static UserStorage getInstance (){
		if(instance == null) instance = new UserStorage();
		return instance;
	}

	public static void addNewUser(ArtBoxUser user) {
		
        ConnectionFactoryBuilder connectionFactoryBuilder = new ConnectionFactoryBuilder();
        try (Connection connection = connectionFactoryBuilder.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_USERS, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.execute();
            log.debug("new user added in DB!");
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
	}
	
	public static List<ArtBoxUser> findUser(){
		
		 ConnectionFactoryBuilder connectionFactoryBuilder = new ConnectionFactoryBuilder();
		 List<ArtBoxUser> users = new ArrayList<ArtBoxUser>();
		 
		 try (Connection connection = connectionFactoryBuilder.getConnection()) {
	            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_USERS);
	            ResultSet rs = ps.executeQuery();
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
	        return users;
	}
	
	public static List<ArtBoxUser> searchUserByEmail(String searchEmail){
		
		ConnectionFactoryBuilder connectionFactoryBuilder = new ConnectionFactoryBuilder();
		 List<ArtBoxUser> users = new ArrayList<ArtBoxUser>();
		 
		 try (Connection connection = connectionFactoryBuilder.getConnection()) {
	            PreparedStatement ps = connection.prepareStatement(SQL_FIND_USER);
	            ps.setString(1, searchEmail);
	            ps.execute();
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                String email = rs.getString("EMAIL");
	                String password = rs.getString("PASSWORD");
	                log.debug("find user with email : " + email);
	                users.add(new ArtBoxUser(email, password));
	            }
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
	        return users;
	}
}
