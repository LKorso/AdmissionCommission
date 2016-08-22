package com.admissioncommission.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.admissioncommission.command.CommandAdmin;
import com.admissioncommission.command.CommandApplicant;
import com.admissioncommission.command.CommandChangeInformation;
import com.admissioncommission.command.CommandError;
import com.admissioncommission.command.CommandFaculty;
import com.admissioncommission.command.CommandHeader;
import com.admissioncommission.command.CommandLogin;
import com.admissioncommission.command.CommandNewAdmin;
import com.admissioncommission.command.CommandRegistaration;
import com.admissioncommission.command.CommandRejectedApplications;
import com.admissioncommission.command.CommandStudent;
import com.admissioncommission.command.ICommand;

public class ControllerHelper {
	private static ControllerHelper instance = null;
	private HashMap<String, ICommand> commands = new HashMap<String, ICommand>();
	
	private static final String KEY_COMMAND_ADMIN = "admin";
	private static final String KEY_COMMAND_APPLICANT = "applicant";
	private static final String KEY_COMMAND_ERROR = "error";
	private static final String KEY_COMMAND_FACULTY = "faculty";
	private static final String KEY_COMMAND_LOGIN = "login";
	private static final String KEY_COMMAND_NEW_ADMIN = "newAdministrator";
	private static final String KEY_COMMAND_REGISTRATION = "registration";
	private static final String KEY_COMMAND_STUDENT = "student";
	private static final String KEY_COMMAND_CHANGE_INFORMATION = "changeInformation";
	private static final String KEY_COMMAND_HEADER = "header";
	private static final String KEY_COMMAND_REJECTED_APPLICATIONS = "rejectedApplications";
	private static final String COMMAND_PARAMETER = "command";
	
	private ControllerHelper(){
		commands.put(KEY_COMMAND_ADMIN, new CommandAdmin());
		commands.put(KEY_COMMAND_APPLICANT, new CommandApplicant());
		commands.put(KEY_COMMAND_ERROR, new CommandError());
		commands.put(KEY_COMMAND_FACULTY, new CommandFaculty());
		commands.put(KEY_COMMAND_LOGIN, new CommandLogin());
		commands.put(KEY_COMMAND_NEW_ADMIN, new CommandNewAdmin());
		commands.put(KEY_COMMAND_REGISTRATION, new CommandRegistaration());
		commands.put(KEY_COMMAND_STUDENT, new CommandStudent());
		commands.put(KEY_COMMAND_CHANGE_INFORMATION, new CommandChangeInformation());
		commands.put(KEY_COMMAND_HEADER, new CommandHeader());
		commands.put(KEY_COMMAND_REJECTED_APPLICATIONS, new CommandRejectedApplications());
	}
	
	public ICommand getCommand(HttpServletRequest request){
		ICommand command = commands.get(request.getParameter(COMMAND_PARAMETER));
		if(command == null){
			command = new CommandError();
		}
		return command;
	}
	
	public static synchronized ControllerHelper getHelper() {
		 if (instance == null) {
			 instance = new ControllerHelper();
		 }
		 return instance;
	}
}
