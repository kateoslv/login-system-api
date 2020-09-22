package com.kattyolv.login.system.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kattyolv.login.system.api.jdbc.ConnectionJDBC;
import com.kattyolv.login.system.api.model.UserModel;

public class DAO {

	static final String SELECT = "SELECT * FROM users ORDER BY name";
	static final String INSERT = "INSERT INTO users (name, email, password) VALUES (?,?,?)";
	static final String UPDATE = "UPDATE users SET name=?, email=?, password=? WHERE id=?";
	static final String DELETE = "DELETE FROM users WHERE id=?";
	
	static Connection connection;
	
	public DAO() {
		ConnectionJDBC connectionJDBC = new ConnectionJDBC();
		connection = connectionJDBC.getConnection();
	}
	
	public ArrayList<UserModel> selectData() {
		
		try {
			PreparedStatement stmt = connection.prepareStatement(SELECT);
			
			ResultSet resultSet = stmt.executeQuery();
			
			ArrayList<UserModel> listUserModel = new ArrayList<UserModel>();
			
			while(resultSet.next()) {
				
				UserModel userModel = new UserModel();
				
				userModel.setId(resultSet.getInt("id"));
				userModel.setName(resultSet.getString("name"));
				userModel.setEmail(resultSet.getString("email"));
				
				listUserModel.add(userModel);				
			}
			
			return listUserModel;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
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
	
	public boolean updateData(UserModel userModel) {
		
		try {
			PreparedStatement stmt = connection.prepareStatement(UPDATE);
			
			stmt.setString(1, userModel.getName());
			stmt.setString(2, userModel.getEmail());
			stmt.setString(3, userModel.getPassword());
			stmt.setInt(4, userModel.getId());
		
			int rowsAffected = stmt.executeUpdate();
			
			if (rowsAffected > 0) {
				return true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public int deleteData(UserModel userModel) {
		
		try {
			PreparedStatement stmt = connection.prepareStatement(DELETE);
			
			stmt.setInt(1, userModel.getId());
		
			int rowsAffected = stmt.executeUpdate();
			
			return rowsAffected;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
}
