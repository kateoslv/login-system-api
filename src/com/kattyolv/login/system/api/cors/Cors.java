package com.kattyolv.login.system.api.cors;

import javax.servlet.http.HttpServletResponse;

public class Cors {

	public static void applyPermissionsHeaders(HttpServletResponse response) {
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		
	}
	
}
