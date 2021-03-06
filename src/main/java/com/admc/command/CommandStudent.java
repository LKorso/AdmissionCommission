package com.admc.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admc.loader.StudentChangeInformationPageLoader;
import com.admc.logic.PageConfigurator;

public class CommandStudent implements ICommand {
	private String page = null;
	
	@Override
	public String render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentChangeInformationPageLoader pageLoader = new StudentChangeInformationPageLoader(request, response);
		pageLoader.setAtributes();
		page = PageConfigurator.getConfigurator().getPage(PageConfigurator.CHANGE_STUDENT_INFORMATION_PAGE);
		return page;
	}

}
