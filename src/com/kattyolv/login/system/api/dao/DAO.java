package com.kattyolv.login.system.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kattyolv.login.system.api.jdbc.ConnectionJDBC;
import com.kattyolv.login.system.api.model.UserModel;

public class DAO {

	static final String SELECT = "SELECT * FROM users ORDER BY first_name";
	static final String INSERT = "INSERT INTO users (first_name, last_name, email, password) VALUES (?,?,?,?)";
	static final String UPDATE = "UPDATE users SET first_name=?, last_name=?, email=?, password=? WHERE id=?";
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
				userModel.setFirstName(resultSet.getString("first_name"));
				userModel.setLastName(resultSet.getString("last_name"));
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
	
	public boolean insertData(UserModel userModel) {
		
		try {
			PreparedStatement stmt = connection.prepareStatement(INSERT);
			
			stmt.setString(1, userModel.getFirstName());
			stmt.setString(2, userModel.getLastName());
			stmt.setString(3, userModel.getEmail());
			stmt.setString(4, userModel.getPassword());
			
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
	
	public boolean updateData(UserModel userModel) {
		
		try {
			PreparedStatement stmt = connection.prepareStatement(UPDATE);
			
			stmt.setString(1, userModel.getFirstName());
			stmt.setString(2, userModel.getLastName());
			stmt.setString(3, userModel.getEmail());
			stmt.setString(4, userModel.getPassword());
			stmt.setInt(5, userModel.getId());
		
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
	
	public boolean deleteData(UserModel userModel) {
		
		try {
			PreparedStatement stmt = connection.prepareStatement(DELETE);
			
			stmt.setInt(1, userModel.getId());
			
			int rowsAffected = stmt.executeUpdate();
			
			if (rowsAffected > 0) {
				return true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
