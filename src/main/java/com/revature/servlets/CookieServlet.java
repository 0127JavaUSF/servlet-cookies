package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/**
		 * You must add this to each servlet you create OR you can isolate this code
		 * to a Filter. (See servlet API filters for details)
		 * 
		 * If you have done it correctly and are STILL getting CORS errors, here
		 * are two important things to check:
		 * 
		 *  * Are your allow headers allowing the right things? Check the error 
		 *  	message on the browser closely.
		 *  
		 *  * Is your URL correct? If you're not getting routed to a servlet
		 *  	then the headers will not be added.
		 */
		
		resp.addHeader("Access-Control-Allow-Headers", "authorization");
		resp.addHeader("Access-Control-Allow-Methods", "GET POST PUT DELETE");
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		
		// TODO Auto-generated method stub
		super.service(req, resp);
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("Initializing");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String myString = null;
//		myString.indexOf(1); // obvious null pointer exception
		
		Cookie[] cookies = request.getCookies();
		for(Cookie c : cookies) {
			System.out.printf("%s:%s%n", c.getName(), c.getValue());
		}
		
		
		Cookie cookie = new Cookie("cookie-quantity", "11");
		response.addCookie(cookie);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie = new Cookie("cookie-type", "chocolate-chip");
		
		response.addCookie(cookie);
	}

}
