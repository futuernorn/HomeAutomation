package org.cs4398_G4;

import java.util.ArrayList;
import java.util.Date;


/**
 * This class creates a log entry to be logged in the log
 *
 */
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
	
	User viewUser()
	{
		return user;
	}
	
	String viewAction()
	{
		return action;
	}
}
