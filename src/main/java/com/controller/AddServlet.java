package com.controller;

import java.io.IOException;

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
import com.utils.Utils;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS_ATRIBUTE = "success_message";

	private static final Logger log = Logger.getLogger(AddServlet.class);

	public AddServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("home.jsp");
		} else {
			response.sendRedirect(PropertiesLoader.getProperty(ConfigKey.ADD_PAGE.name()));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		final String _ERROR_MESSAGE = PropertiesLoader.getProperty(ConfigKey.ERROR_MASSAGE.name());

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String message;
		String type = PropertiesLoader.getProperty(ConfigKey.RED.name());
		
		try {
			
			final String ARTBOX_NAME = "name";
			final String ARTBOX_AGE = "age";
			final String ARTBOX_COST = "cost";

			String name = request.getParameter(ARTBOX_NAME);
			int age = Integer.parseInt(request.getParameter(ARTBOX_AGE));
			double cost = Double.parseDouble((request.getParameter(ARTBOX_COST)).replaceAll(" ", "").replace(',', '.'));

			log.debug("get params... name - " + name + "; age - " + age + "; cost - " + cost);

			if (Utils.isValidParameters(age, cost)) {
				
				final String _INVALID_MESSAGE = PropertiesLoader.getProperty(ConfigKey.INVALID_MASSAGE.name());
				
				message = _ERROR_MESSAGE + _INVALID_MESSAGE;
				log.warn(message);
			} else {

				ArtBox newArtBox = new ArtBox(name, age, cost);

				ArtBoxStorage artboxStorage = ArtBoxStorage.getInstance();
				artboxStorage.addedBase(newArtBox);

				final String _SUCCESS_MASSAGE_BEGIN = PropertiesLoader.getProperty(ConfigKey.SUCCESS_MASSAGE_BEGIN.name());
				final String _SUCCESS_MASSAGE_END 	= PropertiesLoader.getProperty(ConfigKey.SUCCESS_MASSAGE_END.name());
				
				message = _SUCCESS_MASSAGE_BEGIN + name + _SUCCESS_MASSAGE_END;
				type = SUCCESS_ATRIBUTE;

				log.debug(message);
			}

		} catch (NumberFormatException e) {
			message = _ERROR_MESSAGE;
			log.error(message);
		}

		request.setAttribute("message", message);
		request.setAttribute("type", type);
		request.getRequestDispatcher(PropertiesLoader.getProperty(ConfigKey.ADD_PAGE.name())).forward(request, response);

	}
}
