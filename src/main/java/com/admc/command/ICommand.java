package com.admc.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {
	String ATTRIBUTE_REDIRECT = "redirect";
	String render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
