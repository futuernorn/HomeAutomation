package org.cs4398_G4;

import java.util.ArrayList;



/**
 * This class hold user information for users that are created. 
 *
 */
public class User {
		public User()
		{
			name = "Admin";
			password = "default";
			tier = "Admin";
			loggedIn = false;
		}

		public User(String id, String pw, String level)
		{
			name = id;
			pw = password;
			tier = level;
		}

		public boolean isLoggedOn()
		{
			return loggedIn;
		}

		public boolean logOn(String pw)
		{
			if (password.equals(pw))
			{
				loggedIn = true;
				return true;
			}
			else
				return false;
		}

		public boolean hasAccess(String room)
		{
			for (int i = 0; i < limitAccess.length; i++)
			{
				if (limitAccess[i] == room)
					return false;
			}

			return true;
		}

		public void logOut()
		{
			loggedIn = false;
		}

		public void changePassword(String newPw)
		{
			password = newPw;
		}

		public String name()
		{
			return name;
		}

		private String name;
		private String password;
		private String tier;
		private String []limitAccess;
		private boolean loggedIn;
		private ArrayList<Behavior> behaviors;
}