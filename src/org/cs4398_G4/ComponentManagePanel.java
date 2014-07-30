package org.cs4398_G4;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.List;

import javax.swing.JCheckBox;

public class ComponentManagePanel extends JPanel {
	
	private JTextField nameTxt;
	private JComboBox inputCombo;
	private JComboBox outputCombo;
	private JList componentList;
	private DefaultListModel componentListModel;
	private LocalInterface view;
	private DefaultComboBoxModel inputPinComboModel;
	private DefaultComboBoxModel outputPinComboModel;
	private DefaultComboBoxModel roomComboModel;
	private DefaultComboBoxModel typeComboModel;
	private JComboBox roomComboBox;
	private JComboBox typeComboBox;
	private JButton btnSubmit;
	private JCheckBox outputPinCheckBox;
	private JCheckBox inputPinCheckBox;
	private JButton btnRemoveCompontent;

	public ComponentManagePanel(LocalInterface view) {
		this.view = view;
		this.setLayout(new BorderLayout(0, 0));
		
		JPanel addRemoveBtnPanel = new JPanel();
		this.add(addRemoveBtnPanel, BorderLayout.NORTH);
		addRemoveBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAddComponent = new JButton("Add Component");
		addRemoveBtnPanel.add(btnAddComponent);
		
		btnRemoveCompontent = new JButton("Remove Compontent");

		addRemoveBtnPanel.add(btnRemoveCompontent);
		
		JPanel addRemovePanel = new JPanel();
		this.add(addRemovePanel, BorderLayout.SOUTH);
		addRemovePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel addRemoveForm = new JPanel();
		addRemovePanel.add(addRemoveForm, BorderLayout.CENTER);
		addRemoveForm.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblEnabled = new JLabel("Enabled");
		addRemoveForm.add(lblEnabled, "6, 2");
		
		JLabel lblName = new JLabel("Name");
		addRemoveForm.add(lblName, "2, 4, right, default");
		
		nameTxt = new JTextField();
		addRemoveForm.add(nameTxt, "4, 4, fill, top");
		nameTxt.setColumns(10);
		
		JLabel lblInputPin = new JLabel("Input Pin");
		addRemoveForm.add(lblInputPin, "2, 6, right, default");
		
		inputCombo = new JComboBox();
		inputCombo.setEnabled(false);
		
		addRemoveForm.add(inputCombo, "4, 6, fill, default");
		
		inputPinCheckBox = new JCheckBox("");

		addRemoveForm.add(inputPinCheckBox, "6, 6");
		
		JLabel lblOutputPin = new JLabel("Output Pin");
		addRemoveForm.add(lblOutputPin, "2, 8, right, default");
		
		outputCombo = new JComboBox();
		outputCombo.setEnabled(false);
		addRemoveForm.add(outputCombo, "4, 8, fill, default");
		
		outputPinCheckBox = new JCheckBox("");
		addRemoveForm.add(outputPinCheckBox, "6, 8");
		
		JLabel lblRoom = new JLabel("Room");
		addRemoveForm.add(lblRoom, "2, 10, right, default");
		
		roomComboBox = new JComboBox();
		addRemoveForm.add(roomComboBox, "4, 10, fill, default");
		
		JLabel lblType = new JLabel("Type");
		addRemoveForm.add(lblType, "2, 12, right, default");
		
		typeComboBox = new JComboBox();
		addRemoveForm.add(typeComboBox, "4, 12, fill, default");
		
		JPanel addRemoveFormBtnPanel = new JPanel();
		addRemovePanel.add(addRemoveFormBtnPanel, BorderLayout.SOUTH);
		addRemoveFormBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnSubmit = new JButton("Submit");

		addRemoveFormBtnPanel.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		addRemoveFormBtnPanel.add(btnCancel);
		
		JLabel lblAddComponent = new JLabel("Add Component");
		addRemovePanel.add(lblAddComponent, BorderLayout.NORTH);
		
		JPanel addComponentListPanel = new JPanel();
		this.add(addComponentListPanel, BorderLayout.CENTER);
		GridBagLayout gbl_addComponentListPanel = new GridBagLayout();
		gbl_addComponentListPanel.columnWidths = new int[]{225, 0};
		gbl_addComponentListPanel.rowHeights = new int[]{1, 0};
		gbl_addComponentListPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_addComponentListPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		addComponentListPanel.setLayout(gbl_addComponentListPanel);
		
		componentList = new JList();
		GridBagConstraints gbc_componentList = new GridBagConstraints();
		gbc_componentList.fill = GridBagConstraints.BOTH;
		gbc_componentList.gridx = 0;
		gbc_componentList.gridy = 0;
		addComponentListPanel.add(componentList, gbc_componentList);
		
		
		SetupComponentList();
	}
	
	private void SetupComponentList() {
		btnSubmit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Room newRoom =  (Room) roomComboModel.getElementAt(roomComboBox.getSelectedIndex());
				HashMap<String, Pin> inputPinNumbers = new HashMap<String, Pin>();
				if (inputCombo.isEnabled())
					inputPinNumbers.put(nameTxt.getText(), (Pin) inputPinComboModel.getElementAt(inputCombo.getSelectedIndex()));
				HashMap<String, Pin> outputPinNumbers = new HashMap<String, Pin>();
				if (outputCombo.isEnabled())
					outputPinNumbers.put(nameTxt.getText(), (Pin) outputPinComboModel.getElementAt(outputCombo.getSelectedIndex()));
				String type = (String) typeComboModel.getElementAt(typeComboBox.getSelectedIndex());
				Component newComp =  null;
				if (type == "Motion Sensor") {
					newComp = new Sensor("Motion Sensor", inputPinNumbers, outputPinNumbers);
				} else if (type == "LED Light") {
					newComp = new Actuator("LED", inputPinNumbers, outputPinNumbers);
				} else {
//					newComp = new Component(nameTxt.getText(), inputPinNumbers, outputPinNumbers);
				}
				
				view.getController().AddComponent(newComp, newRoom);
				updateComponentList();
			}
		});
		
		inputPinCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (inputCombo.isEnabled()) {
					inputCombo.setEnabled(false);
				} else {
					inputCombo.setEnabled(true);
				}
				SyncInputPinSelectionToOutput();
			}
		});
		
		outputPinCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (outputCombo.isEnabled()) {
					outputCombo.setEnabled(false);
				} else {
					outputCombo.setEnabled(true);
				}
				SyncOutputPinSelectionToInput();
			}
		});
		
		componentListModel = new DefaultListModel();
		componentList.setModel(componentListModel);
		
		
		FillFields();
		updateComponentList();
		
		btnRemoveCompontent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				view.getController().removeComponent();
			    int index = componentList.getSelectedIndex();
			    if (index == -1)
			    	index = 0;
//			    sensorComboBox.addItem(conditionListModel.getElementAt(index));
			    Component removedComponent = ((Component) componentListModel.getElementAt(index));
			    view.getController().RemoveComponent(removedComponent);
			    
			    componentListModel.remove(index);
			    
			    FillFields();
				updateComponentList();
			}
		});
		
		inputCombo.addActionListener (new ActionListener () {
	    public void actionPerformed(ActionEvent e) {
	    	SyncInputPinSelectionToOutput();
	    }
	});
		
		outputCombo.addActionListener (new ActionListener () {
	    public void actionPerformed(ActionEvent e) {
	    	SyncOutputPinSelectionToInput();



	    }
	});
		
	}
	
	public void SyncInputPinSelectionToOutput() {
		int outputIndex = outputCombo.getSelectedIndex();
	    int inputIndex = outputCombo.getSelectedIndex();
	    Object currentOutputSelection =  inputPinComboModel.getElementAt(outputIndex);
	    Object removedPin =  inputPinComboModel.getElementAt(inputIndex);
	    
		if (!inputCombo.isEnabled())
			outputPinComboModel.addElement(removedPin);
		
		if (!outputCombo.isEnabled())
	    	return;
	    

	    

//	    outputPinComboModel.removeAllElements();
//	    Object[] avaiablePins = view.getController().getAvailablePins().toArray();
//	    for (Object avaiablePin : avaiablePins) {
//	    	outputPinComboModel.addElement(avaiablePin);
//	    }
	    outputPinComboModel.removeElement(removedPin);
//	    outputCombo.setSelectedIndex(outputIndex);
//	    outputCombo.setSelectedItem(currentOutputSelection);


    }
	
	public void SyncOutputPinSelectionToInput() {
	    int outputIndex = outputCombo.getSelectedIndex();
	    int inputIndex = outputCombo.getSelectedIndex();
	    Object currentOutputSelection =  inputPinComboModel.getElementAt(outputIndex);
	    Object removedPin =  inputPinComboModel.getElementAt(inputIndex);
	    
		if (!outputCombo.isEnabled())
			inputPinComboModel.addElement(removedPin);
	    if (!inputCombo.isEnabled())
	    	return;

	    

//	    inputPinComboModel.removeAllElements();
	    inputPinComboModel.removeElement(removedPin);
//	    Object[] avaiablePins = view.getController().getAvailablePins().toArray();
//	    for (Object avaiablePin : avaiablePins) {
//	    	inputPinComboModel.addElement(avaiablePin);
//	    }
//	    inputPinComboModel.removeElement(removedPin);
//	    inputCombo.setSelectedItem(currentInputSelection);

    }
	

	public void FillFields() {
		Object[] avaiablePins = view.getController().getAvailablePins().toArray();
		
		System.out.println(view.getController().getAllPossiblePins());
		System.out.println(view.getController().getAvailablePins());
//		String[] rooms = view.getController().getRooms().toArray();
		
		
		inputPinComboModel = new DefaultComboBoxModel(avaiablePins);
		inputCombo.setModel(inputPinComboModel);

		
		
		outputPinComboModel = new DefaultComboBoxModel(avaiablePins);
		outputCombo.setModel(outputPinComboModel);

		
		roomComboModel = new DefaultComboBoxModel(view.getController().getRooms().toArray());
		roomComboBox.setModel(roomComboModel);

		String[] possibleTypes = {"Motion Sensor", "Sound Alarm", "LED Light"};
		typeComboModel = new DefaultComboBoxModel(possibleTypes);
		typeComboBox.setModel(typeComboModel);
		
//		SyncInputPinSelectionToOutput();
		

	}
	
	public void updateComponentList() {
		List<Component> components = view.getController().getComponents();
		componentListModel.removeAllElements();
		for (Component component : components) {
			componentListModel.addElement(component);
		}
		
	    if (componentListModel.getSize() == 0) 
	    	btnRemoveCompontent.setEnabled(false);
	    else
	    	btnRemoveCompontent.setEnabled(true);
	}

}
