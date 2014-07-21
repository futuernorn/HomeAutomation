package org.txstate.cs4398_sum14.group4;

import java.util.ArrayList;

public class User {
	public
		void User()
		{
			name = "Admin";
			password = "default";
			tier = "Admin";
			loggedIn = false;
		}
	
		void User(String id, String pw, String level)
		{
			name = id;
			pw = password;
			tier = level;
		}
		
		boolean isLoggedOn()
		{
			return loggedIn;
		}
		
		boolean logOn(String pw)
		{
			if (pw == password)
			{
				loggedIn = true;
				return true;
			}
			else
				return false;
		}
		
		boolean hasAccess(String room)
		{
			for (int i = 0; i < limitAccess.length; i++)
			{
				if (limitAccess[i] == room)
					return false;
			}
			
			return true;
		}
		
		void logOut()
		{
			loggedIn = false;
		}
		
		void changePassword(String newPw)
		{
			password = newPw;
		}
		
		String name()
		{
			return name;
		}
		
	private
		String name;
		String password;
		String tier;
		String []limitAccess;
		boolean loggedIn;
		ArrayList<Behavior> behaviors;
}
