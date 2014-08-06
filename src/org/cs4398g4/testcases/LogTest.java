package org.cs4398g4.testcases;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.cs4398g4.BaseStation;
import org.cs4398g4.Log;
import org.cs4398g4.LogEntry;
import org.cs4398g4.User;
import org.junit.Test;

public class LogTest {

	@Test
	public void SearchTests() {
		
		BaseStation baseTest = new BaseStation();
		
		//-----set up testing variables-----
		Log testLog = new Log(baseTest);
		User testUser = new User();
		
		//test both num variable and size of array
		assertEquals("Number of Logs should be empty", 0, testLog.getNumLogEntries());
		assertEquals("Number of Logs in array should be empty", 0, testLog.getLogEntries().size());
		
		testLog.addLog(testUser, "test string");
		Date testDate = new Date();
		
		//test both num variable and size of array after adding log
		assertEquals("Number of Logs should be empty", 1, testLog.getNumLogEntries());
		assertEquals("Number of Logs in array should be empty", 1, testLog.getLogEntries().size());
		
		ArrayList<LogEntry> testUserSearch = new ArrayList<LogEntry>();
		ArrayList<LogEntry> testDateSearch = new ArrayList<LogEntry>();
		
		testUserSearch = testLog.searchLog(testUser);
		testDateSearch = testLog.searchLog(testDate);
		
		assertEquals("Number of Logs found should be 1", 1, testUserSearch.size());
		assertEquals("Number of Logs found should be 1", 1, testDateSearch.size());
	}

}
