package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dao.UserStorage;
import com.manager.ConfigKey;
import com.manager.PropertiesLoader;
import com.model.ArtBoxUser;

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String HIDDEN = "hidden";
	private static final String BLOCK_MESSAGE_REGISTER = "block_message_register";
	private static final Logger log = Logger.getLogger(AuthorizationServlet.class);
	
	public AuthorizationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(PropertiesLoader.getProperty(ConfigKey.HOME_PAGE.name()));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String EMAIL = "email";
		final String PASSWORD = "pass";
		
		String email = request.getParameter(EMAIL);
		String password = request.getParameter(PASSWORD);

		log.debug("get param ... email : " + email + "/ password : " + password);

		boolean successAuthorization = false;
		
		for (ArtBoxUser user : UserStorage.findUser()) {
			if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
				log.debug("user success authorization!");
				successAuthorization = true;
				
				Cookie cookie = new Cookie("ArtBoxCookie", email);
				response.addCookie(cookie);
				log.debug("add cookie : " + cookie);
				
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(3600*3600);
				session.setAttribute("user", "Hello "+user.getName() + " | ");
				session.setAttribute("hidden", "hidden");
				session.setAttribute("logout", "logout");
				request.setAttribute("user", "Hello " + user.getName() + " | ");
				request.setAttribute("logout", "logout");
				log.debug(session.getId());
				break;
			}
		}

		if (successAuthorization) {
			
			final String _SUCCCESS_AUTH = PropertiesLoader.getProperty(ConfigKey.SUCCCESS_AUTH.name());
			
			log.debug(_SUCCCESS_AUTH);
			request.setAttribute("success_message_register", _SUCCCESS_AUTH);
			request.setAttribute("block_message_register", BLOCK_MESSAGE_REGISTER);
			request.setAttribute("green", PropertiesLoader.getProperty(ConfigKey.GREEN.name()));
			request.setAttribute("hidden", HIDDEN);
			request.getRequestDispatcher(PropertiesLoader.getProperty(ConfigKey.HOME_PAGE.name())).forward(request, response);
		} else {
			log.debug("ERROR AUTHORIZATION!");
			request.setAttribute("message", PropertiesLoader.getProperty(ConfigKey.ERROR_AUTH.name()));
			request.setAttribute("type", PropertiesLoader.getProperty(ConfigKey.RED.name()));
			request.getRequestDispatcher(PropertiesLoader.getProperty(ConfigKey.AUTHORIZATION_PAGE.name())).forward(request, response);
		}
	}

}
