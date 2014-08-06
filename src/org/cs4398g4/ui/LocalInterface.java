package org.cs4398g4.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.cs4398g4.BaseStation;
import org.cs4398g4.Controller;
import org.cs4398g4.Room;

public class LocalInterface implements ActionListener {

	JFrame frmHomeAutomationSystem;
	public JFrame getFrmHomeAutomationSystem() {
		return frmHomeAutomationSystem;
	}

	public JTextField motionTxt;
	public JButton btnToggleLed;
	private Controller controller;
	private JTabbedPane tabbedPane;
	private BehaviorPanel behaviorPanel;
	private ComponentManagePanel addRemoveTab;
	private ComponentStatusPanel statusPanel;
	private LoginPanel loginPanel;
	private BehaviorFlowViewer behaviorFlowPanel;
	BaseStation baseStation;
	private LogPanel logPanel;

	private JMenuItem loginMenuItem;
	private JMenu fileMenu;
	private JMenu systemMenu;
	private JMenuItem stateMenuItem;
	private JMenuBar menuBar;
	private JMenu removeRoomMenu;
	private JMenuItem exitMenuItem;


	/**
	 * Create the application.
	 */
	public LocalInterface(Controller controller, BaseStation baseStation)  {
		this.controller = controller;
		this.baseStation = baseStation;

		initialize();
	}
	
	public void ShowInterface() {
		
		frmHomeAutomationSystem.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHomeAutomationSystem = new JFrame();
		frmHomeAutomationSystem.setTitle("Home Automation System");
		frmHomeAutomationSystem.setBounds(100, 100, 711, 481);
		frmHomeAutomationSystem.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frmHomeAutomationSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmHomeAutomationSystem.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		loginPanel = new LoginPanel(this);
		
		behaviorPanel = new BehaviorPanel(this);

		statusPanel = new ComponentStatusPanel(this);
		
		behaviorFlowPanel = new BehaviorFlowViewer(this, baseStation.getGraph());
		
		tabbedPane.addTab("Login", null, loginPanel, null);
		
		logPanel = new LogPanel(this);

		frmHomeAutomationSystem.getContentPane().add(logPanel, BorderLayout.SOUTH);

		
		
		
		
		addRemoveTab = new ComponentManagePanel(this);
		
		initalizeMenuBar();

		
	}
	
	public Controller getController() {
		return controller;
	}
	
	public void displayErrorPrompt(String title, String message) {
		//custom title, error icon
		JOptionPane.showMessageDialog(frmHomeAutomationSystem,
		   message,
		   title,
		    JOptionPane.ERROR_MESSAGE);
	}

	
	public boolean displayConfirmationPrompt(String title, String message) {
	    int response = JOptionPane.showConfirmDialog(frmHomeAutomationSystem, message, title,
	            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION)
          return true;
       
		return false;
	}
	
	public String displayStringEntryDialog(String title, String message) {
		String userEnteredString = "";
		userEnteredString = (String)JOptionPane.showInputDialog(
				frmHomeAutomationSystem,
                message,
                title,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "NewRoom");
		return userEnteredString;
	}
	
	public void doLogin() {
		tabbedPane.remove(loginPanel);
		
		tabbedPane.addTab("Status", null, statusPanel, null);
		tabbedPane.addTab("Add/Remove", null, addRemoveTab, null);		
		tabbedPane.addTab("Behavior", null, behaviorPanel, null);
		tabbedPane.addTab("Flow", null, behaviorFlowPanel, null);
		
		setupLoggedInMenuItems();
		
	    
		getFrmHomeAutomationSystem().getRootPane().setDefaultButton(null);
		
		
	}
	
	private void setupLoggedInMenuItems() {
		fileMenu.remove(loginMenuItem);
		fileMenu.remove(exitMenuItem);
		
		
		
		JMenuItem logoutMenuItem = new JMenuItem("Logout", KeyEvent.VK_L);
		stateMenuItem = new JMenuItem("Set State", KeyEvent.VK_S);
		stateMenuItem.setEnabled(false);
		
		systemMenu = new JMenu("System");
		systemMenu.setMnemonic(KeyEvent.VK_S);
	    
	    
	    JMenu roomMenu = new JMenu("Rooms");
	    roomMenu.setMnemonic(KeyEvent.VK_R);
	    
	    JMenuItem addRoomMenuItem = new JMenuItem("Add", KeyEvent.VK_A);
	    removeRoomMenu = new JMenu("Remove");
	    roomMenu.setMnemonic(KeyEvent.VK_D);
	    
	    updateRemoveRoomMenu();
	    
	    roomMenu.add(addRoomMenuItem);
	    roomMenu.add(removeRoomMenu);
	    
	    systemMenu.add(roomMenu);
	    menuBar.add(systemMenu);
		
	    
		
	    logoutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doLogout();
            }
        });
	    
	    stateMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(0);
            }
        });
	    
	    addRoomMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String newRoomName = displayStringEntryDialog("Add A New Room", "Please enter a name for the new room:");
            	if (newRoomName != "") {
            		if (controller.addRoom(newRoomName)) {
            			updateRemoveRoomMenu();
            			statusPanel.refreshFields();
            		}
            	}
            }
        });
	    
	    
	    fileMenu.add(logoutMenuItem);
	    fileMenu.add(stateMenuItem);
	    fileMenu.add(exitMenuItem);
	}
	
	private void updateRemoveRoomMenu() {
		removeRoomMenu.removeAll();
		for (final Room room : baseStation.getRooms()) {
			JMenuItem newRoomMenuItem = new JMenuItem(room.toString());
			removeRoomMenu.add(newRoomMenuItem);
			
			newRoomMenuItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	if (displayConfirmationPrompt("Confirm Room Deletion", "Are you sure you wish to remove the \"" + room + "\" room? \n WARNING: This will remove all attached components.")) {
	            		if (controller.removeRoom(room)) {
	            			updateRemoveRoomMenu();
	            			statusPanel.refreshFields();
	            		}
	            	}
	            }
	        });
		}
		
	}

	public void doLogout() {
		baseStation.doLogout();
		
		tabbedPane.remove(statusPanel);
		tabbedPane.remove(addRemoveTab);
		tabbedPane.remove(behaviorPanel);
		tabbedPane.remove(behaviorFlowPanel);
		
		tabbedPane.addTab("Login", null, loginPanel, null);
		initalizeMenuBar();
		loginPanel.initalize();
	}
	
	private void initalizeMenuBar() {
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
	    fileMenu.setMnemonic(KeyEvent.VK_F);
	    menuBar.add(fileMenu);

	    loginMenuItem = new JMenuItem("Login", KeyEvent.VK_L);
	    loginMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doLogin();
            }
        });
	    
	    exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_E);
	   
	    exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 System.exit(0);
            }
        });
	    fileMenu.add(loginMenuItem);
	    fileMenu.add(exitMenuItem);

	    getFrmHomeAutomationSystem().setJMenuBar(menuBar);
	}

	public void refreshComponents() {
		statusPanel.refreshComponents();
		addRemoveTab.updateComponentList();
		behaviorPanel.Initialize();
		
		
	}

	public void refresh() {
		// TODO Auto-generated method stub
		addRemoveTab.updateComponentList();
		behaviorPanel.Initialize();
		
	}

	public BaseStation getBaseStation() {
		// TODO Auto-generated method stub
		return baseStation;
	}
	

	


	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getActionCommand() == "log") 
			logPanel.refreshLogText();
		
	}

	public void refreshLogText() {
		logPanel.refreshLogText();
		
	}
	
}
