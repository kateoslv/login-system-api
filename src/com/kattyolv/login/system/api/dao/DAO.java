package com.kattyolv.login.system.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kattyolv.login.system.api.jdbc.ConnectionJDBC;
import com.kattyolv.login.system.api.model.UserModel;

public class DAO {

	static final String SELECT = "SELECT * FROM users ORDER BY name";
	static final String INSERT = "INSERT INTO users (name, email, password) VALUES (?,?,?)";
	static final String UPDATE = "UPDATE users SET name=?, email=?, password=?";
	static final String DELETE = "DELETE FROM users WHERE name=?";
	
	static Connection connection;
	
	public DAO() {
		ConnectionJDBC connectionJDBC = new ConnectionJDBC();
		connection = connectionJDBC.getConnection();
	}
	
	public void selectData() {
		
		try {
			PreparedStatement stmt = connection.prepareStatement(SELECT);
			
			ResultSet resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("id") + " " + resultSet.getString("name") + " " + resultSet.getString("email"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertData(UserModel userModel) {
		
		try {
			PreparedStatement stmt = connection.prepareStatement(INSERT);
			
			stmt.setString(1, userModel.getName());
			stmt.setString(2, userModel.getEmail());
			stmt.setString(3, userModel.getPassword());
			
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
 	}
	
	
	
}
