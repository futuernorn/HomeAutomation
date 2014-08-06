package org.cs4398g4;

import org.cs4398g4.utilities.BCrypt;

public class User {
	public enum AccessLevel {
		ADMIN, POWER_USER, USER, NONE
	}

	private String name;
	private String password;
	private AccessLevel accessLevel;

	// private List<Room> limitAccess;

	public User() {
		name = "Admin";
		password = "default";
		accessLevel = AccessLevel.USER;
	}

	public User(String name, String password, AccessLevel accessLevel) {
		this.name = name;
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
		this.accessLevel = accessLevel;
	}

	public boolean checkLogin(String username, String password) {
		boolean loggedIn = false;
		if ((username.equalsIgnoreCase(name)) && (BCrypt.checkpw(password, this.password)))
			loggedIn = true;

		return loggedIn;
	}

	public void setPassword(String newPassword) {
		password = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public String toString() {
		return name + " [" + accessLevel + "] ";
	}

	public boolean checkAccess(AccessLevel requiredLevel) {
		boolean hasAccess = false;
		if (accessLevel.compareTo(requiredLevel) <= 0)
			hasAccess = true;

		return hasAccess;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof User))
			return false;
		if (obj == this)
			return true;

		User rhs = (User) obj;

		// assuming name as primary key
		if (name.equalsIgnoreCase(rhs.name))
			return true;
		return false;
	}

}