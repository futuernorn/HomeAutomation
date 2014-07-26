package org.cs4398_G4.testcases;

import static org.junit.Assert.*;

import org.cs4398_G4.Log;
import org.cs4398_G4.User;
import org.junit.Ignore;
import org.junit.Test;

public class LogTest {

	@Test
	public void test() {
		
		//-----set up testing variables-----
		Log testLog = new Log();
		User testUser = new User();
		
		//test both num variable and size of array
		assertEquals("Number of Logs should be empty", 0, testLog.getNumLogs());
		assertEquals("Number of Logs in array should be empty", 0, testLog.getLogEntries().size());
		
		testLog.addLog(testUser, "test string");
		
		//test both num variable and size of array after adding log
		assertEquals("Number of Logs should be empty", 1, testLog.getNumLogs());
		assertEquals("Number of Logs in array should be empty", 1, testLog.getLogEntries().size());
	}

}
