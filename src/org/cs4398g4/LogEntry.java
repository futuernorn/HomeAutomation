package org.cs4398g4;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogEntry {
	private Date date;
	private User user;
	private String action;

	LogEntry(Date tempDate, User tempUser, String tempAction) {
		date = tempDate;
		user = tempUser;
		action = tempAction;
	}

	public String getFormattedDate() {
		return new SimpleDateFormat("M/dd/yy @ hh:mm").format(date);
	}

	public String toString() {
		return user + " on " + getFormattedDate() + " => " + action;
	}

	public Date getDate() {
		return date;
	}

	public User getUser() {
		return user;
	}

	public String getAction() {
		return action;
	}
}
