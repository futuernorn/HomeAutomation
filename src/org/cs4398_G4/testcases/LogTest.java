package org.cs4398_G4.testcases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.cs4398_G4.BaseStation;
import org.cs4398_G4.Log;
import org.cs4398_G4.LogEntry;
import org.cs4398_G4.User;
import org.junit.Ignore;
import org.junit.Test;

public class LogTest {

	@Test
	public void SearchTests() {
		
		//-----set up testing variables-----
		BaseStation baseStation = new BaseStation();
		Log testLog = baseStation.getLog();
		User testUser = new User();
		
		//test both num variable and size of array
		assertEquals("Number of Logs should be empty", 0, testLog.getNumLogs());
		assertEquals("Number of Logs in array should be empty", 0, testLog.getLogEntries().size());
		
		testLog.addLog(testUser, "test string");
		Date testDate = new Date();
		
		//test both num variable and size of array after adding log
		assertEquals("Number of Logs should be empty", 1, testLog.getNumLogs());
		assertEquals("Number of Logs in array should be empty", 1, testLog.getLogEntries().size());
		
		ArrayList<LogEntry> testUserSearch = new ArrayList<LogEntry>();
		ArrayList<LogEntry> testDateSearch = new ArrayList<LogEntry>();
		
		testUserSearch = testLog.searchLog(testUser);
		testDateSearch = testLog.searchLog(testDate);
		
		assertEquals("Number of Logs found should be 1", 1, testUserSearch.size());
		assertEquals("Number of Logs found should be 1", 1, testDateSearch.size());
	}

}
