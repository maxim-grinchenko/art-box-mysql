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

import com.controller.RegistrationServlet;
import com.manager.ConfigKey;
import com.manager.PropertiesLoader;
import com.utils.Utils;

@WebFilter("/registration")
public class RegistrationEmailFilter implements Filter {
	
	private static final Logger log = Logger.getLogger(RegistrationServlet.class);

    public RegistrationEmailFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//TODO output of different messages for emailVerification() and checkForUniquenessOfEmail()
		
		final String _REGISTRATION_PAGE = PropertiesLoader.getProperty(ConfigKey.REGISTRATION_PAGE.name());
		
		try {
			
			final String EMAIL = "email";
			String email = request.getParameter(EMAIL);

			if (Utils.emailVerification(email) && Utils.checkForUniquenessOfEmail(email)) {
				log.debug("Success! email is correct!");
				chain.doFilter(request, response);
			} else {
				log.debug("email is not valid!");
				request.setAttribute("message_reg_email", PropertiesLoader.getProperty(ConfigKey.EMAIL_INCORRECT.name()));
				request.setAttribute("type_reg_email", PropertiesLoader.getProperty(ConfigKey.RED.name()));
				request.getRequestDispatcher(_REGISTRATION_PAGE).forward(request, response);
			}

		} catch (NullPointerException e) {
			request.getRequestDispatcher(_REGISTRATION_PAGE).forward(request, response);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
