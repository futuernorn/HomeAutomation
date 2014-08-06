package org.cs4398g4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Log {
	private ArrayList<LogEntry> logEntries;
	BaseStation baseStation;

	public Log(BaseStation baseStation) {
		this.baseStation = baseStation;
		logEntries = new ArrayList<LogEntry>();
	}

	public void addLog(String action) {
		addLog(baseStation.getCurrentUser(), action);
	}

	public void addLog(User user, String action) {
		Date date = new Date();
		logEntries.add(new LogEntry(date, user, action));
		baseStation.refreshLogDisplay();
	}

	public ArrayList<LogEntry> searchLog(User user) {

		ArrayList<LogEntry> searchResult = new ArrayList<LogEntry>();

		for (LogEntry entry : logEntries)
			if (entry.equals(user))
				searchResult.add(entry);

		return searchResult;
	}

	public ArrayList<LogEntry> searchLog(Date date) {
		ArrayList<LogEntry> searchResult = new ArrayList<LogEntry>();

		for (LogEntry entry : logEntries)
			if (entry.getDate().equals(date))
				searchResult.add(entry);

		return searchResult;
	}

	public ArrayList<LogEntry> searchLog(String action) {
		ArrayList<LogEntry> searchResult = new ArrayList<LogEntry>();
		for (LogEntry entry : logEntries)
			if (entry.getAction().endsWith(action))
				searchResult.add(entry);

		return searchResult;
	}

	public List<LogEntry> getLogEntries() {
		return logEntries;
	}

	public Object getNumLogEntries() {
		return logEntries.size();
	}
}
