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

public class LocalInterface {

	JFrame frmHomeAutomationSystem;
	private JTextField nameTxt;
	public JTextField motionTxt;
	public JButton btnToggleLed;
	private Controller controller;
	private JTabbedPane tabbedPane;
	private BehaviorPanel behaviorPanel;
	private JPanel addRemoveTab;
	private JPanel statusPanel;
	private LoginPanel loginPanel;


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

		statusPanel = new JPanel();
		
		tabbedPane.addTab("Login", null, loginPanel, null);
		
		
		statusPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel statusForm = new JPanel();
		statusPanel.add(statusForm, BorderLayout.CENTER);
		statusForm.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblLedLight = new JLabel("LED Light");
		statusForm.add(lblLedLight, "2, 2, right, default");
		
		btnToggleLed = new JButton("Toggle LED");

		statusForm.add(btnToggleLed, "4, 2");
		
		JSeparator separator = new JSeparator();
		statusForm.add(separator, "4, 4");
		
		JLabel lblMotionSensor = new JLabel("Motion Sensor");
		statusForm.add(lblMotionSensor, "2, 6, right, default");
		
		motionTxt = new JTextField();
		motionTxt.setEditable(false);
		statusForm.add(motionTxt, "4, 6, fill, center");
		motionTxt.setColumns(10);
		
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
		
	}
}
