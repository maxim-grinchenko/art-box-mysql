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

@WebFilter("/authorization")
public class AuthorizationEmailFilter implements Filter {
	
	private static final String ERROR_TYPE = "error_message_reg";
	
	private static final Logger log = Logger.getLogger(AuthorizationEmailFilter.class);

    public AuthorizationEmailFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
			final String EMAIL = "email";
			String email = request.getParameter(EMAIL);

			if (Utils.emailVerification(email)) {
				log.debug("email is correct!");
				chain.doFilter(request, response);
			} else {
				log.debug("email is INcorrect!");
				request.setAttribute("message_reg_email", PropertiesLoader.getProperty(ConfigKey.EMAIL_INCORRECT.name()));
				request.setAttribute("type_reg_email", ERROR_TYPE);
				request.getRequestDispatcher(PropertiesLoader.getProperty(ConfigKey.AUTHORIZATION_PAGE.name())).forward(request, response);
			}

		} catch (NullPointerException e) {
			request.getRequestDispatcher(PropertiesLoader.getProperty(ConfigKey.AUTHORIZATION_PAGE.name())).forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
