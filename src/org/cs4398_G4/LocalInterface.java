package org.cs4398_G4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.FlowLayout;

import javax.swing.JProgressBar;
import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class LocalInterface implements ActionListener {

	JFrame frmHomeAutomationSystem;
	public JFrame getFrmHomeAutomationSystem() {
		return frmHomeAutomationSystem;
	}

	private JTextField nameTxt;
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
	private JPanel logPanel;
	private JScrollPane logScrollPane;
    private JTextArea textArea;
    private JLabel lblSystemLog;


	/**
	 * Create the application.
	 */
	public LocalInterface(Controller controller, BaseStation baseStation)  {
		this.controller = controller;
		this.baseStation = baseStation;
		textArea = new JTextArea(5, 60);
//		textArea.setPreferredSize(new Dimension());
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
//		frmHomeAutomationSystem.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frmHomeAutomationSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmHomeAutomationSystem.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		loginPanel = new LoginPanel(this);
		
		behaviorPanel = new BehaviorPanel(this);

		statusPanel = new ComponentStatusPanel(this);
		
		behaviorFlowPanel = new BehaviorFlowViewer(this, baseStation.getGraph());
		
		tabbedPane.addTab("Login", null, loginPanel, null);
		
		logPanel = new JPanel();
		logPanel.addAncestorListener ( new AncestorListener () {
	        public void ancestorAdded ( AncestorEvent event )
	        {
	            // Component added somewhere
//	        	System.out.println(component+" added.");
	        	refreshLogText();
//	        	textArea.getColumnWidth()
//	        	textArea.setColumns(frmHomeAutomationSystem.getContentPane().getSize().width / textArea.getColumnWidth());
//	        	textArea.getCol
	        }
	        


			public void ancestorRemoved ( AncestorEvent event )
	        {
	            // Component removed from container
	        }

	        public void ancestorMoved ( AncestorEvent event )
	        {
	            // Component container moved
	        }
	        
	    } );
		frmHomeAutomationSystem.getContentPane().add(logPanel, BorderLayout.SOUTH);
		logPanel.setLayout(new BorderLayout(0, 0));
		
		
		logScrollPane = new JScrollPane(textArea);
		logPanel.add(logScrollPane);
		
		lblSystemLog = new JLabel("System Log");
		logPanel.add(lblSystemLog, BorderLayout.NORTH);
		
		
		
		
		addRemoveTab = new ComponentManagePanel(this);

		
	}
	
	public Controller getController() {
		return controller;
	}
	
	public void displayErrorMessage(String title, String message) {
		//custom title, error icon
		JOptionPane.showMessageDialog(frmHomeAutomationSystem,
		   message,
		   title,
		    JOptionPane.ERROR_MESSAGE);
	}

	public void doLogin() {
		tabbedPane.remove(loginPanel);
		
		
		tabbedPane.addTab("Add/Remove", null, addRemoveTab, null);
		tabbedPane.addTab("Status", null, statusPanel, null);
		tabbedPane.addTab("Behavior", null, behaviorPanel, null);
		tabbedPane.addTab("Flow", null, behaviorFlowPanel, null);
		
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
	

	
    public void refreshLogText() {
		// TODO Auto-generated method stub
    	textArea.setText("");
    	for (LogEntry logEntry : baseStation.getLog().getLogEntries()) {
    		textArea.append(logEntry.toString() + "\n");
    	}
		
	}

	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getActionCommand() == "log") 
			refreshLogText();
		
	}
	
}
