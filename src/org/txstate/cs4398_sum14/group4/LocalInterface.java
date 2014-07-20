package org.txstate.cs4398_sum14.group4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LocalInterface {

	private JFrame frmHomeAutomationSystem;
	private JTextField nameTxt;
	private JTextField roomTxt;
	public JTextField motionTxt;
	public JButton btnToggleLed;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LocalInterface window = new LocalInterface();
					window.frmHomeAutomationSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LocalInterface() {
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmHomeAutomationSystem.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel statusPanel = new JPanel();
		tabbedPane.addTab("Status", null, statusPanel, null);
		statusPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel statusForm = new JPanel();
		statusPanel.add(statusForm, BorderLayout.CENTER);

		
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
		
		JPanel addRemoveTab = new JPanel();
		tabbedPane.addTab("Add/Remove", null, addRemoveTab, null);
		addRemoveTab.setLayout(new BorderLayout(0, 0));
		
		JPanel addRemoveBtnPanel = new JPanel();
		addRemoveTab.add(addRemoveBtnPanel, BorderLayout.NORTH);
		addRemoveBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAddComponent = new JButton("Add Component");
		addRemoveBtnPanel.add(btnAddComponent);
		
		JButton btnRemoveCompontent = new JButton("Remove Compontent");
		addRemoveBtnPanel.add(btnRemoveCompontent);
		
		JPanel addRemovePanel = new JPanel();
		addRemoveTab.add(addRemovePanel, BorderLayout.SOUTH);
		addRemovePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel addRemoveForm = new JPanel();
		addRemovePanel.add(addRemoveForm, BorderLayout.CENTER);
	
		
		JLabel lblName = new JLabel("Name");
		addRemoveForm.add(lblName, "2, 2, right, default");
		
		nameTxt = new JTextField();
		addRemoveForm.add(nameTxt, "4, 2, fill, top");
		nameTxt.setColumns(10);
		
		JLabel lblInputPin = new JLabel("Input Pin");
		addRemoveForm.add(lblInputPin, "2, 4, right, default");
		
		JComboBox inputCombo = new JComboBox();
		addRemoveForm.add(inputCombo, "4, 4, fill, default");
		
		JLabel lblOutputPin = new JLabel("Output Pin");
		addRemoveForm.add(lblOutputPin, "2, 6, right, default");
		
		JComboBox outputCombo = new JComboBox();
		addRemoveForm.add(outputCombo, "4, 6, fill, default");
		
		JLabel lblRoom = new JLabel("Room");
		addRemoveForm.add(lblRoom, "2, 8, right, default");
		
		roomTxt = new JTextField();
		addRemoveForm.add(roomTxt, "4, 8, fill, default");
		roomTxt.setColumns(10);
		
		JPanel addRemoveFormBtnPanel = new JPanel();
		addRemovePanel.add(addRemoveFormBtnPanel, BorderLayout.SOUTH);
		addRemoveFormBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSubmit = new JButton("Submit");
		addRemoveFormBtnPanel.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		addRemoveFormBtnPanel.add(btnCancel);
		
		JLabel lblAddComponent = new JLabel("Add Component");
		addRemovePanel.add(lblAddComponent, BorderLayout.NORTH);
	}
}
