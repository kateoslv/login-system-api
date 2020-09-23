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
		
		ArrayList<UserModel> listUserModel = dao.selectData();
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		out.println("<html><body>");
		
		for (UserModel user : listUserModel) {
			
			out.println("<h3>");
			out.println(user.getId() + " " + user.getFirstName() + user.getLastName() + " " + " " + user.getEmail());
			out.println("</h3>");
		}
		
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//invoke insert from DAO class
		
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//invoke update from DAO class	
		
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//invoke delete from DAO class
		
	}

}
