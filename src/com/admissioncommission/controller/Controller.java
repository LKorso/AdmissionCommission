package com.admissioncommission.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admissioncommission.command.ICommand;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Controller() {
        super();
    }

    protected void doRequest(HttpServletRequest request, HttpServletResponse response) {
    	String page = null;
    	
    	try{
    		ICommand command = ControllerHelper.getHelper().getCommand(request);
    		page = command.render(request, response);
    		if(request.getAttribute("redirect") != null){
    			response.sendRedirect(request.getContextPath() + page);
    		}else {
    			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);	
    		}
    	} catch(ServletException exception){
    		exception.printStackTrace();
    	} catch (IOException exception) {
    		exception.printStackTrace();
		}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

}
