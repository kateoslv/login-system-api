package com.kattyolv.login.system.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kattyolv.login.system.api.model.*;
import com.google.gson.Gson;
import com.kattyolv.login.system.api.dao.DAO;


@WebServlet("/user")
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DAO dao = new DAO();

    public UserController() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		
		response.setContentType("application/json");
		
		Gson gson = new Gson();
		
		ArrayList<UserModel> listUserModel = dao.selectData();
		
		String listUserModelJson = gson.toJson(listUserModel);
		
		PrintWriter out = response.getWriter();
		
		out.print(listUserModelJson);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		
		try {
			
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
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

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "PUT");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		
		try {
		
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			
			UserModel userModel = new UserModel();
			
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
						userModel.setEmail(value);
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

		//invoke delete from DAO class
		
	}

}
