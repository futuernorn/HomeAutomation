package org.cs4398_G4;

import java.util.ArrayList;
import java.util.List;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
 

/**
 * @author Jeffrey Hogan
 *
 */
public class BaseStation {
	ArrayList<Room> house;
	ArrayList<User> users;
	private Security securitySystem;
	private ArrayList<Behavior> behaviors;
//	User mainUser;
	BehaviorGraph graph;
	User currentUser;
	LocalInterface view;
	
	public void setView(LocalInterface view) {
		this.view = view;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	Log log;
	


	public Log getLog() {
		return log;
	}

	/**
	 * BaseStation constructor
	 */
	public BaseStation() {
		log = new Log(this);
		house = new ArrayList<Room>();
		users = new ArrayList<User>();
		behaviors = new ArrayList<Behavior>();
		securitySystem = new Security();
		graph = new BehaviorGraph();
		currentUser = new User();
	}
	
	public ArrayList<Behavior> getBehvaiors() {
		return behaviors;
	}
	
	/**
	 * @returns the house ArrayList
	 */
	public ArrayList<Room> getRooms() {
		return house;
	}

	public void setHouse(ArrayList<Room> house) {
		this.house = house;
	}

	String GetStatus() {
		
		return "Home lookin' p good bro!";
	}
	


	public void AddRoom(Room newRoom) {
		// TODO Auto-generated method stub
		house.add(newRoom);
		
	}
	

	
	/**
	 * @returns sensors being used in house
	 */
	public List<Sensor> GetSensors() {
		List<Sensor> sensors = new ArrayList<Sensor>();
		for (Room room : house) {
			sensors.addAll(room.GetSensors());
		}
		return sensors;
	}
	
	/**
	 * @returns actuators being used in house
	 */
	public List<Actuator> GetActuators() {
	List<Actuator> actuator = new ArrayList<Actuator>();
		for (Room room : house) {
			actuator.addAll(room.GetActuators());
		}
		return actuator;
	}

	public void addBehavior(Behavior behavior) {
		// TODO Auto-generated method stub
		behaviors.add(behavior);
		
	}

	public void removeBehavior(Behavior removedBehavior) {
		// TODO Auto-generated method stub
		removedBehavior.disconnect();
		behaviors.remove(removedBehavior);
		
	}

	public List<Component> getComponents() {
		List<Component> components = new ArrayList<Component>();
		components.addAll(GetSensors());
		components.addAll(GetActuators());
		return components;
	}

	public void removeComponent(Component removedComponent) {
		// TODO Auto-generated method stub
		for (Room room : house) {
			room.removeComponent(removedComponent);
		}
		
	}

	public String getBehvaiorName(Integer v) {
		for (Behavior behavior: getBehvaiors()) {
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


//	public List<? extends Component> GetComponents(Class<?> cls) {
//		List<? extends Component> components = new ArrayList<Component>();
//		for (Room room : house) {
//			components.addAll(room.GetComponents(cls));
//		}
//		return components;
//	}

	public void runConnectedBehaviors(int id) {
		System.out.println(graph.g.getIncidentEdges(id));
		for (String edge : graph.g.getIncidentEdges(id)) {
			for (Integer endpoint : graph.g.getEndpoints(edge)) {
				System.out.println("id("+id+"): edge("+edge+") => endpoint("+endpoint+")");
				if (endpoint != id)
					runBehaviorID(endpoint);
				
			}
		}
		
	}

	private void runBehaviorID(Integer endpoint) {
		for (Behavior behavior: getBehvaiors()) {
			if (endpoint == behavior.getId())
				behavior.Run();
		}
		
	}

	public void refreshLogDisplay() {
		// TODO Auto-generated method stub
		if (view != null)
			view.refreshLogText();
	}


//	public List<? extends Component> GetSensors() {
//		return GetComponentsByType(Sensor.class);
//	}
//
//	public List<Component> GetComponentsByType(Class<?> type) {
//		List<Component> components = new ArrayList<Component>();
//		for (Room room : house) {
//			components.addAll(room.GetComponentsByType(type));
//			
//		}
//		return components;
//	}
}
