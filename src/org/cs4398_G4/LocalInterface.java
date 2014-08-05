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

public class LocalInterface {

	JFrame frmHomeAutomationSystem;
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


	/**
	 * Create the application.
	 */
	public LocalInterface(Controller controller) {
		this.controller = controller;
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
		frmHomeAutomationSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmHomeAutomationSystem.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		loginPanel = new LoginPanel(this);
		
		behaviorPanel = new BehaviorPanel(this);

		statusPanel = new ComponentStatusPanel();
		
		behaviorFlowPanel = new BehaviorFlowViewer();
		
		tabbedPane.addTab("Login", null, loginPanel, null);
		
		
		
		
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

	public void refreshComponents(List<Component> components) {
		statusPanel.refreshComponents(components);
		addRemoveTab.updateComponentList();
		behaviorPanel.fillOptions();
		
		
	}
}
