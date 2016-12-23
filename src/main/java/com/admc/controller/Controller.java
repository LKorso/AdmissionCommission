package com.admc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.admc.command.ICommand;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(Controller.class);
       
    public Controller() {
    }

    protected void doRequest(HttpServletRequest request, HttpServletResponse response) {
    	String page = null;
    	try{
    		ICommand command = ControllerHelper.getHelper().getCommand(request);
    		page = command.render(request, response);
    		request.getSession().setAttribute("currentPage", page);
    		if(request.getAttribute("redirect") != null){
    			response.sendRedirect(request.getContextPath() + page);
    		}else {
    			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);	
    		}
    	} catch(ServletException exception){
    		LOGGER.error("Error while doing request", exception);
    	} catch (IOException exception) {
    		LOGGER.error("Error while doing request", exception);
		}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

}
