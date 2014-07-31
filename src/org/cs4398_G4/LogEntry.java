package org.cs4398_G4;

import java.util.ArrayList;
import java.util.Date;

public class LogEntry {
	private Date date;
	private User user;
	private String action;
	
	LogEntry(Date tempDate, User tempUser, String tempAction)
	{
		date = tempDate;
		user = tempUser;
		action = tempAction;
	}
	
	Date viewDate()
	{
		return date;
	}
	
	String viewDateString()
	{
		return date.toString();
	}
	
	User viewUser()
	{
		return user;
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
