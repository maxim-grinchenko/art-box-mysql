package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.dao.ArtBoxStorage;
import com.manager.ConfigKey;
import com.manager.PropertiesLoader;
import com.model.ArtBox;


@WebServlet("/find")
public class SearchServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String PRODUCTS = "products";
	
	private static final Logger log = Logger.getLogger(SearchServlet.class);
       
    public SearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = null;
		String typeAtribute = PropertiesLoader.getProperty(ConfigKey.RED.name());

		try {
			
			final String ARTBOX_NAME = "name";
			
			String name = request.getParameter(ARTBOX_NAME);
			log.debug("get search parameter : " + name);
			
			ArtBoxStorage artboxStorage = ArtBoxStorage.getInstance();
			List<ArtBox> findArtBoxCollections = artboxStorage.findArtBoxByName(name);
			boolean notFound = true;

			if (findArtBoxCollections != null) {
				log.info("collections arbox not null");
				
				request.setAttribute(PRODUCTS, findArtBoxCollections);

				message = PropertiesLoader.getProperty(ConfigKey.SUCCESS_FOUND.name()) + name;
				typeAtribute = PropertiesLoader.getProperty(ConfigKey.GREEN.name());
				
				notFound = false;
			}

			if (notFound){
				
				final String _ERROR_NO_DB_BEGIN = PropertiesLoader.getProperty(ConfigKey.ERROR_NO_DB_BEGIN.name());
				final String _ERROR_NO_DB_AFTER = PropertiesLoader.getProperty(ConfigKey.ERROR_NO_DB_AFTER.name());
				
				message = _ERROR_NO_DB_BEGIN + name + _ERROR_NO_DB_AFTER;
			}
				
			log.debug("Displaying of found parameters...");

		} catch (NullPointerException e) {
			message = PropertiesLoader.getProperty(ConfigKey.ERROR_WRONG.name());
			log.error(message, e);
			
			response.sendRedirect(PropertiesLoader.getProperty(ConfigKey.DASHBOARD_PAGE.name()));
		}

		request.setAttribute("message", message);
		request.setAttribute("type", typeAtribute);
		request.getRequestDispatcher(PropertiesLoader.getProperty(ConfigKey.DASHBOARD_PAGE.name())).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
