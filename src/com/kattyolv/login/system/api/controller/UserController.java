package com.kattyolv.login.system.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kattyolv.login.system.api.model.*;
import com.google.gson.Gson;
import com.kattyolv.login.system.api.cors.Cors;
import com.kattyolv.login.system.api.dao.DAO;


@WebServlet("/user")
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DAO dao = new DAO();

    public UserController() {
    	super();
    }
    
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	super.doOptions(request, response);
    	
    	Cors.applyPermissionsHeaders(response);
		
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Cors.applyPermissionsHeaders(response);
		
		response.setContentType("application/json");
		
		Gson gson = new Gson();
		
		ArrayList<UserModel> listUserModel = dao.selectData();
		
		String listUserModelJson = gson.toJson(listUserModel);
		
		PrintWriter out = response.getWriter();
		
		out.print(listUserModelJson);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cors.applyPermissionsHeaders(response);
		
		try { 
			
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = URLDecoder.decode(request.getParameter("email"), "UTF-8");
			String password = request.getParameter("password");
			
			UserModel userModel = new UserModel();
			
			userModel.setFirstName(firstName);
			userModel.setLastName(lastName);
			userModel.setEmail(email);
			userModel.setPassword(password);
			
			boolean hasInserted = dao.insertData(userModel);
			
			if (hasInserted == true) {
				response.setStatus(200);
			}
			else {
				response.setStatus(400);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cors.applyPermissionsHeaders(response);
		
		try {
			
			UserModel userModel = new UserModel();
		
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			
			String data = bufferedReader.readLine();
				
			String[] dataSplited = data.split("&");
			
			for (String parameters : dataSplited) {
				
				String[] splitedParameters = parameters.split("=");
				
				String key = splitedParameters[0];
				String value = null;
				
				if (splitedParameters.length > 1) {
					value = splitedParameters[1];
				}
				
				switch (key) {
					case "id":
						if (value != null) {
							int id = Integer.parseInt(value);
							userModel.setId(id);
						}
						break;
					case "firstName":
						userModel.setFirstName(value);
						break;
					case "lastName":
						userModel.setLastName(value);
						break;
					case "email":
						userModel.setEmail(URLDecoder.decode(value, "UTF-8"));
						break;
					case "password":
						userModel.setPassword(value);
						break;
				}
			}
			
			if (userModel.getId() == 0) {
				response.setStatus(400);
				response.getWriter().println("ID is Required!");
				return;
			}
			
			boolean hasUpdated = dao.updateData(userModel);
			
			if (hasUpdated == true) {
				response.setStatus(200);
			}
			else {
				response.setStatus(400);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cors.applyPermissionsHeaders(response);
		
		try {
			
			UserModel userModel = new UserModel();
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));

			String data = bufferedReader.readLine();
			
			String[] dataSplited = data.split("=");
			
			if(dataSplited[0].equalsIgnoreCase("id")) {
				
				int idValue = Integer.parseInt(dataSplited[1]);
				
				userModel.setId(idValue);
				
				boolean hasDeleted = dao.deleteData(userModel);
				
				if(hasDeleted == true) {
					response.setStatus(200);
				}
				else {
					response.setStatus(400);
				}
			}
			else {
				response.setStatus(422);
			}
			
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			response.setStatus(422);
		}		
		catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
		}
		
	}

}
