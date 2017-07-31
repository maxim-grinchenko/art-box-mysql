package com.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;

import com.manager.ConfigKey;
import com.manager.PropertiesLoader;
import com.utils.Utils;

@WebFilter("/registration")
public class RegistrationPasswordFilter implements Filter {
	
	private static final Logger log = Logger.getLogger(RegistrationPasswordFilter.class);

    public RegistrationPasswordFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		final String PASSWORD = "pass";
		final String CONFIRM_PASSWORD = "conf_pass";
		
		String password = request.getParameter(PASSWORD);
		String confirm_password = request.getParameter(CONFIRM_PASSWORD);
		
		if (password.equals(confirm_password) && Utils.passwordVerification(password)) {
			log.debug("password is correct!");
			chain.doFilter(request, response);
		} else {
			log.debug("password is incorrect!");
			request.setAttribute("message_reg_pass", PropertiesLoader.getProperty(ConfigKey.PASS_INCORECT.name()));
			request.setAttribute("type_register_pass", PropertiesLoader.getProperty(ConfigKey.RED.name()));
			request.getRequestDispatcher(PropertiesLoader.getProperty(ConfigKey.REGISTRATION_PAGE.name())).forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
