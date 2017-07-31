package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.builder.UserBuilder;
import com.dao.UserStorage;
import com.manager.ConfigKey;
import com.manager.PropertiesLoader;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(RegistrationServlet.class);
	
    public RegistrationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(PropertiesLoader.getProperty(ConfigKey.HOME_PAGE.name()));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String NAME = "name";
		final String EMAIL = "email";
		final String PASSWORD = "pass";
		
		String name = request.getParameter(NAME);
		String password = request.getParameter(PASSWORD);
		String email = request.getParameter(EMAIL);
		
		log.debug("get param ... email : " + email + "/ password : " + password);
		
		UserStorage.addNewUser(new UserBuilder()
									.setUserId()
									.setUserName(name)
									.setUserEmail(email)
									.setUserPassword(password)
									.getUserBuild());
		
		request.setAttribute("message", PropertiesLoader.getProperty(ConfigKey.SUCCESS_REG.name()));
		request.setAttribute("type", PropertiesLoader.getProperty(ConfigKey.GREEN.name()));
		request.getRequestDispatcher(PropertiesLoader.getProperty(ConfigKey.AUTHORIZATION_PAGE.name())).forward(request, response);
	}

}
