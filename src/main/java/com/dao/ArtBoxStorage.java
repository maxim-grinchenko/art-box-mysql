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
import com.model.ArtBox;

public class ArtBoxStorage {

	private static ArtBoxStorage instance;

	private final static String SQL_INSERT_ARTBOX = "INSERT INTO products (NAME, AGE, COST) VALUES (?,?,?);";
	private final static String SQL_SELECT_ARTBOX = "SELECT * FROM products;";
	private final static String SQL_DELETE_ARTBOX = "DELETE FROM products WHERE id= ?;";
	private final static String SQL_SEARCH_ARTBOX = "SELECT * FROM products WHERE name = ?;";

	private static final Logger log = Logger.getLogger(ArtBoxStorage.class);

	private ArtBoxStorage() {
	}

	public static ArtBoxStorage getInstance() {
		if (instance == null) {
			instance = new ArtBoxStorage();
		}
		return instance;
	}

	public void addedBase(ArtBox newArtbox) {

		ConnectionFactoryBuilder connectionFactoryBuilder = new ConnectionFactoryBuilder();
		try (Connection connection = connectionFactoryBuilder.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(SQL_INSERT_ARTBOX, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, newArtbox.getName());
			ps.setInt(2, newArtbox.getAge());
			ps.setDouble(3, newArtbox.getCost());
			ps.execute();
			log.info("new artbox added in DB!");
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	public void removedBase(int id) {
		
		ConnectionFactoryBuilder connectionFactoryBuilder = new ConnectionFactoryBuilder();
		try (Connection connection = connectionFactoryBuilder.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(SQL_DELETE_ARTBOX);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	public List<ArtBox> getList() {

		ConnectionFactoryBuilder connectionFactoryBuilder = new ConnectionFactoryBuilder();
		List<ArtBox> artBoxs = new ArrayList<ArtBox>();
		try (Connection connection = connectionFactoryBuilder.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ARTBOX);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				double cost = rs.getDouble("COST");
				artBoxs.add(new ArtBox(id, name, age, cost));
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		log.debug("find all artbox in DataBase USERS....");
		return artBoxs;
	}

	public List<ArtBox> findArtBoxByName(String artBoxName) {

		ConnectionFactoryBuilder connectionFactoryBuilder = new ConnectionFactoryBuilder();
		List<ArtBox> artBoxs = new ArrayList<ArtBox>();
		try (Connection connection = connectionFactoryBuilder.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(SQL_SEARCH_ARTBOX);
			ps.setString(1, artBoxName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				double cost = rs.getDouble("COST");
				artBoxs.add(new ArtBox(id, name, age, cost));
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return artBoxs.isEmpty() ? null : artBoxs;
	}
}
