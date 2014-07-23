package org.cs4398_G4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Log {
	private ArrayList<LogEntry> logEntries;
	private int numLogs;
	
	void addLog(User user, String action)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		logEntries.add(new LogEntry(date, user, action));
		++ numLogs;
	}
	
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
