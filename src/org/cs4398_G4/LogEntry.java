package org.cs4398_G4;

import java.util.ArrayList;
import java.util.Date;

public class LogEntry {
	private String date;
	private User user;
	private String action;
	
	LogEntry(String tempDate, User tempUser, String tempAction)
	{
		date = tempDate;
		user = tempUser;
		action = tempAction;
	}
	
	String viewDate()
	{
		return date;
	}
	
	String viewDateString()
	{
		return date.toString();
	}
	
	String viewUser()
	{
		return user.name();
	}
	
	String viewUserString()
	{
		return user.toString();
	}
	
	String viewAction()
	{
		return action;
	}
}
