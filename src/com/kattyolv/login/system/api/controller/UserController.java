package com.kattyolv.login.system.api.controller;

import java.io.IOException;
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
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserModel userModel = new UserModel();
		
		userModel.setFirstName(firstName);
		userModel.setLastName(lastName);
		userModel.setEmail(email);
		userModel.setPassword(password);
		
		dao.insertData(userModel);
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//invoke update from DAO class	
		
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//invoke delete from DAO class
		
	}

}
