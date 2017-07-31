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

@WebServlet("/dashboard")
public class DisplayServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String PRODUCTS = "products";
	private static final String ERROR_MESSAGE_ATRIBUTE = "error_message";
	
	private static final Logger log = Logger.getLogger(DisplayServlet.class);
	
	public DisplayServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect(PropertiesLoader.getProperty(ConfigKey.HOME_PAGE.name()));
		} else {

			ArtBoxStorage artboxStorage = ArtBoxStorage.getInstance();
			List<ArtBox> collectionsArtBox = artboxStorage.getList();
			String typeAtribute = ERROR_MESSAGE_ATRIBUTE;

			if (collectionsArtBox.isEmpty() || collectionsArtBox == null) {
				
				final String _LIST_IS_EMPTY = PropertiesLoader.getProperty(ConfigKey.LIST_IS_EMPTY.name());
				
				request.setAttribute("message", _LIST_IS_EMPTY);
				log.debug(_LIST_IS_EMPTY);
			} else {
				request.setAttribute(PRODUCTS, collectionsArtBox);
				log.debug("Display collections ArtBox...");
			}

			request.setAttribute("type", typeAtribute);
			request.getRequestDispatcher(PropertiesLoader.getProperty(ConfigKey.DASHBOARD_PAGE.name())).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
