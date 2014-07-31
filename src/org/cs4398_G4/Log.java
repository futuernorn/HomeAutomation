package org.cs4398_G4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Log {
	public ArrayList<LogEntry> logEntries;
	private int numLogs;
	
	public Log() {
		numLogs = 0;
		logEntries = new ArrayList<LogEntry>();
	}
	
	public void addLog(User user, String action)
	{
		String date =  new SimpleDateFormat("MM/dd/yy").format(Calendar.getInstance().getTime());
		
		logEntries.add(new LogEntry(date, user, action));
		++ numLogs;
	}
	
	public ArrayList<LogEntry> searchLogUser(User user)
	{
		ArrayList<LogEntry> tempEntries = new ArrayList<LogEntry>();

		for (int i = 0; i < logEntries.size(); i++)
		{
			LogEntry temp = logEntries.get(i);
			
			if (temp.viewUserString().equals(user.toString()))
			{
				tempEntries.add(logEntries.get(i));
			}
		}
		return tempEntries;
	}
	
	public ArrayList<LogEntry> searchLogDate(String date)
	{

		ArrayList<LogEntry> tempEntries = new ArrayList<LogEntry>();
		
		for (int i = 0; i < logEntries.size(); i++)
		{
			LogEntry temp = logEntries.get(i);
			
			if (date.equals(temp.viewDate()))
			{
				tempEntries.add(logEntries.get(i));
			}
		}
		return tempEntries;
	}
	
	public ArrayList<LogEntry> searchLogAction(String action)
	{
		ArrayList<LogEntry> tempEntries = new ArrayList<LogEntry>();
		for (int i = 0; i < logEntries.size(); i++)
		{
			LogEntry temp = logEntries.get(i);
			
			if (action.equals(temp.viewAction()))
			{
				tempEntries.add(logEntries.get(i));
			}
		}
		return tempEntries;
	}
	
	public int getNumLogs() {
		return numLogs;
	}
	
	public ArrayList<LogEntry> getLogEntries() {
		return logEntries;
	}
}
