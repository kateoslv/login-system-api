package com.kattyolv.login.system.api.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kattyolv.login.system.api.cors.Cors;
import com.kattyolv.login.system.api.dao.DAO;
import com.kattyolv.login.system.api.model.UserModel;

@WebServlet("/sign-in")
public class SignInController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cors.applyPermissionsHeaders(response);
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		DAO dao = new DAO();
		UserModel userModel = dao.selectUserByEmail(email, password);
		
		if (userModel != null) {
			response.setStatus(200);
		}
		else {
			response.setStatus(401);
		}
	}
	
}
