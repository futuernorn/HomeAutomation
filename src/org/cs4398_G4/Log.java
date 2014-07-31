package org.cs4398_G4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * This class creates a log off all actions taken by the user of the Home Automation System (HAS)
 *
 */
public class Log {
	private ArrayList<LogEntry> logEntries;
	private int numLogs;
	
	/**
	 * Adds a log entry to the log 
	 * @param user: The user that performed the action being logged 
	 * @param action: The action the user performed
	 */
	void addLog(User user, String action)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		logEntries.add(new LogEntry(date, user, action));
		++ numLogs;
	}
	
	/**
	 * Searches all log entries by user
	 * @param user
	 * @return the entries logged by the requested user
	 */
	ArrayList<LogEntry> searchLog(User user)
	{
		ArrayList<LogEntry> tempEntries = null;
		for (int i = 0; i < logEntries.size(); i++)
		{
			LogEntry temp = logEntries.get(i);
			if (temp.viewUser() == user)
			{
				tempEntries.add(logEntries.get(i));
			}
		}
		return tempEntries;
	}
	
	/** 
	 * Searches the log entries by date of entry
	 * @param date
	 * @return the entries logged on the requested date
	 */
	ArrayList<LogEntry> searchLog(Date date)
	{
		ArrayList<LogEntry> tempEntries = null;
		for (int i = 0; i < logEntries.size(); i++)
		{
			LogEntry temp = logEntries.get(i);
			if (temp.viewDate() == date)
			{
				tempEntries.add(logEntries.get(i));
			}
		}
		return tempEntries;
	}
	
	/** 
	 * Searches all log entries by the action performed
	 * @param action
	 * @return the entries logged of the requested action
	 */
	ArrayList<LogEntry> searchLog(String action)
	{
		ArrayList<LogEntry> tempEntries = null;
		for (int i = 0; i < logEntries.size(); i++)
		{
			LogEntry temp = logEntries.get(i);
			if (temp.viewAction() == action)
			{
				tempEntries.add(logEntries.get(i));
			}
		}
		return tempEntries;
	}
}
