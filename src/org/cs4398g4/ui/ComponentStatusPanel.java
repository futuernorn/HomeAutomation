package org.cs4398g4.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import net.miginfocom.swing.MigLayout;

import org.cs4398g4.Component;
import org.cs4398g4.Room;

public class ComponentStatusPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7327685188032068212L;
	LocalInterface view;
	private JPanel componentDisplayPanel;
	private JScrollPane scrollPane;
	private JComboBox<Room> roomSelectionBox;
	private JComboBox<String> currentStateCombo;
	private DefaultComboBoxModel<Room> roomSelectionListModel;
	public ComponentStatusPanel(LocalInterface view) {
		this.view = view;
		setLayout(new BorderLayout(0, 0));
		
		addUIElements();
		refreshFields();
		

		
		this.addAncestorListener ( new AncestorListener () {
	        public void ancestorAdded ( AncestorEvent event ) {
	        	refreshComponents();
	        }
	        
	        public void ancestorRemoved ( AncestorEvent event )	        {      }

	        public void ancestorMoved ( AncestorEvent event )   {        }
	        
	    } );
	}
	


	
	public void refreshFields() {
		
		DefaultComboBoxModel<String> currentStateListModel = new DefaultComboBoxModel<String>(new String[]{"---"}); 
		currentStateCombo.setModel(currentStateListModel);

		roomSelectionListModel = new DefaultComboBoxModel<Room>(new Room[]{new Room("ALL")});
		for (Room room : view.getBaseStation().getRooms()) {
			roomSelectionListModel.addElement(room);
		}
		roomSelectionBox.setModel(roomSelectionListModel);
		
		
	}




	public void refreshComponents() {
		System.out.println(new java.util.Date() + "refreshComponents() called");
		componentDisplayPanel.removeAll();
		List<Component> components;
		Object currentRoomSelection = roomSelectionListModel.getSelectedItem();
		
		// If no room selected (i.e., "ALL" string), get all components		
		if (currentRoomSelection.toString() == "ALL")
			components = view.getBaseStation().getComponents(Component.class);
		else
			components = view.getBaseStation().getComponentsByRoom(Component.class, (Room)currentRoomSelection);
		System.out.println(currentRoomSelection.getClass() + " => " + components );
		for (Component component : components) {	
			componentDisplayPanel.add(component.getComponentUI(), "span, wrap");
		}		
		
		
		view.getFrmHomeAutomationSystem().repaint();
	}
	

	
	private void addUIElements() {
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(600, 800));
		
		componentDisplayPanel = new JPanel();
		componentDisplayPanel.setLayout(new MigLayout("", "[grow, fill]", ""));
		scrollPane.setViewportView(componentDisplayPanel);
		

		add(scrollPane, BorderLayout.CENTER);
		
		
		JPanel informationPanel = new JPanel();
		add(informationPanel, BorderLayout.NORTH);
		GridBagLayout gbl_informationPanel = new GridBagLayout();
		gbl_informationPanel.columnWidths = new int[]{0, 0, 0};
		gbl_informationPanel.rowHeights = new int[]{0, 0, 0};
		gbl_informationPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_informationPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		informationPanel.setLayout(gbl_informationPanel);
		
		JLabel lblCurrentState = new JLabel("Current State:");
		GridBagConstraints gbc_lblCurrentState = new GridBagConstraints();
		gbc_lblCurrentState.anchor = GridBagConstraints.EAST;
		gbc_lblCurrentState.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentState.gridx = 0;
		gbc_lblCurrentState.gridy = 0;
		informationPanel.add(lblCurrentState, gbc_lblCurrentState);
		
		currentStateCombo = new JComboBox<String>();
		currentStateCombo.setEnabled(false);
		GridBagConstraints gbc_currentStateCombo = new GridBagConstraints();
		gbc_currentStateCombo.insets = new Insets(0, 0, 5, 0);
		gbc_currentStateCombo.fill = GridBagConstraints.HORIZONTAL;
		gbc_currentStateCombo.gridx = 1;
		gbc_currentStateCombo.gridy = 0;
		informationPanel.add(currentStateCombo, gbc_currentStateCombo);
		
		JLabel lblRoom = new JLabel("Room:");
		GridBagConstraints gbc_lblRoom = new GridBagConstraints();
		gbc_lblRoom.anchor = GridBagConstraints.EAST;
		gbc_lblRoom.insets = new Insets(0, 0, 0, 5);
		gbc_lblRoom.gridx = 0;
		gbc_lblRoom.gridy = 1;
		informationPanel.add(lblRoom, gbc_lblRoom);
		
		roomSelectionBox = new JComboBox<Room>();
		roomSelectionBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	refreshComponents();
		    }
		});
		GridBagConstraints gbc_roomSelectionBox = new GridBagConstraints();
		gbc_roomSelectionBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_roomSelectionBox.gridx = 1;
		gbc_roomSelectionBox.gridy = 1;
		informationPanel.add(roomSelectionBox, gbc_roomSelectionBox);
	}
}
