package com.revature.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SessionServlet extends HttpServlet {
	
	// maps username -> password
	Map<String, String> userMap = new HashMap<>();
	{
		userMap.put("abby", "abbypass");
		userMap.put("billy", "billypass");
		userMap.put("cindy", "cindypass");
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("username", "value"); // Setting request scoped data
		
		// Check if logged in
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("username") == null) {
			response.setStatus(401);
			return;
		}
		
		// Don't do this, make a class.  Mason.
		Map<String, String> map = new HashMap<>();
		map.put("message", "Hello " + session.getAttribute("username"));
		
		ObjectMapper om = new ObjectMapper();
		om.writeValue(response.getWriter(), map);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper om = new ObjectMapper();
		Credentials credentials = om.readValue(request.getReader(), Credentials.class);
		
		// Validate credentials (should be in the service layer)
		String password = userMap.get(credentials.getUsername());
		if (password == null || !password.equals(credentials.getPassword())) {
			response.setStatus(401);
			return;
		}
		
		// They're authenticated, create session, send response
		HttpSession session = request.getSession();
		session.setAttribute("username", credentials.getUsername());
		response.setStatus(201);
		return;
	}

}

class Credentials {
	String username;
	String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Credentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public Credentials() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
