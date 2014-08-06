package org.cs4398g4;

import java.util.ArrayList;
import java.util.List;

import org.cs4398g4.ui.LocalInterface;

/**
 * @author Jeffrey Hogan
 * 
 */
public class BaseStation {
	List<Room> house;
	List<User> users;
	private User currentUser;
	Log log;

	LocalInterface view;

	private ArrayList<Behavior> behaviors;
	BehaviorGraph graph;

	private static User loggedOut = new User("Demo", "", User.AccessLevel.NONE);

	/**
	 * BaseStation constructor
	 */
	public BaseStation() {
		log = new Log(this);
		house = new ArrayList<Room>();
		users = new ArrayList<User>();
		behaviors = new ArrayList<Behavior>();
		graph = new BehaviorGraph();
		currentUser = loggedOut;

	}

	public void addUser(User newUser) {
		users.add(newUser);
	}

	private void setCurrentUser(User user) {
		currentUser = user;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public ArrayList<Behavior> getBehvaiors() {
		return behaviors;
	}

	/**
	 * @returns the house ArrayList
	 */
	public List<Room> getRooms() {
		return house;
	}

	public void addRoom(Room newRoom) {
		house.add(newRoom);
	}

	public void addBehavior(Behavior behavior) {
		behaviors.add(behavior);

	}

	public void removeBehavior(Behavior removedBehavior) {
		removedBehavior.disconnect();
		behaviors.remove(removedBehavior);

	}

	/**
	 * @returns any components of type <T> being used in house
	 */

	public <T> List<T> getComponents(Class<T> cls) {
		List<T> components = new ArrayList<T>();
		for (Room room : house) {
			components.addAll(getComponentsByRoom(cls, room));
		}
		;
		return components;
	}

	public <T> List<T> getComponentsByRoom(Class<T> cls, Room currentRoom) {
		return currentRoom.getComponents(cls);
	}

	public void removeComponent(Component removedComponent) {
		for (Room room : house) {
			room.removeComponent(removedComponent);
		}

	}

	public String getBehvaiorName(Integer v) {
		for (Behavior behavior : getBehvaiors()) {
			if (v == behavior.getId())
				return behavior.toString();
		}
		return "Unknown";
	}

	public BehaviorGraph getGraph() {
		return graph;
	}

	public void setGraph(BehaviorGraph graph) {
		this.graph = graph;
	}

	public void runConnectedBehaviors(int id) {
		System.out.println(graph.g.getIncidentEdges(id));
		for (String edge : graph.g.getIncidentEdges(id)) {
			for (Integer endpoint : graph.g.getEndpoints(edge)) {
				System.out.println("id(" + id + "): edge(" + edge
						+ ") => endpoint(" + endpoint + ")");
				if (endpoint != id)
					runBehaviorID(endpoint);

			}
		}

	}

	private void runBehaviorID(Integer endpoint) {
		for (Behavior behavior : getBehvaiors()) {
			if (endpoint == behavior.getId())
				behavior.Run();
		}

	}

	public void refreshLogDisplay() {
		if (view != null)
			view.refreshLogText();
	}

	public void setView(LocalInterface view) {
		this.view = view;
	}

	public boolean doLogin(String username, String password) {
		for (User user : users) {
			if (user.checkLogin(username, password)) {
				setCurrentUser(user);
				log.addLog(user, "Log in attempt successful.");
				return true;
			}
		}
		log.addLog("Login attempt not successful.");
		return false;

	}

	public void doLogout() {
		log.addLog("Logged out.");
		setCurrentUser(loggedOut);

	}

	public boolean checkUserAccess(User.AccessLevel requiredLevel) {

		return getCurrentUser().checkAccess(requiredLevel);

	}

	public Log getLog() {
		return log;
	}

	public void removeUser(User user) {
		users.remove(user);

	}

	public void removeRoom(Room room) {
		house.remove(room);

	}
}
