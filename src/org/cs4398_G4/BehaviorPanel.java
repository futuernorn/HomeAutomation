package org.cs4398_G4;

import javax.swing.JPanel;
import javax.swing.JSpinner;

import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.JComboBox;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.SpringLayout;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JSeparator;


public class BehaviorPanel extends JPanel {
	private JTextField sensorDuration;
	private LocalInterface view;
	private JComboBox sensorComboBox;
	private JComboBox sensorStateComboBox;
	private JList conditionList;
	private DefaultListModel conditionListModel;
	private JButton addConditionBtn;
	private JButton removeConditionBtn;
	private JTextField actuatorDurationTxt;
	private JComboBox actuatorComboBox;
	private JComboBox actuatorStateComboBox;
	private JButton addActionBtn;
	private JButton removeActionBtn;
	private DefaultComboBoxModel sensorListModel;
	private DefaultComboBoxModel stateListModel;
	private DefaultComboBoxModel actuatorListModel;
	private DefaultListModel actionListModel;
	private JList actionList;
	private JTextField txtNewBehavior;
	private JButton btnSave;
	private JList behaviorList;
	private DefaultListModel behaviorListModel;
	private JButton btnBehaviorRemove;
	private JButton btnClearBehavior;

	/**
	 * Create the panel.
	 */
	public BehaviorPanel(LocalInterface view) {
		this.view = view;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{351, 0};
		gridBagLayout.rowHeights = new int[]{450, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		

		
		JPanel behaviorStatusPanel = new JPanel();
		GridBagConstraints gbc_behaviorStatusPanel = new GridBagConstraints();
		gbc_behaviorStatusPanel.fill = GridBagConstraints.BOTH;
		gbc_behaviorStatusPanel.insets = new Insets(0, 0, 5, 0);
		gbc_behaviorStatusPanel.gridx = 0;
		gbc_behaviorStatusPanel.gridy = 0;
		add(behaviorStatusPanel, gbc_behaviorStatusPanel);
		behaviorStatusPanel.setLayout(new BorderLayout(0, 0));
						
						JPanel behaviorListStatusPanel = new JPanel();
						behaviorStatusPanel.add(behaviorListStatusPanel, BorderLayout.WEST);
						GridBagLayout gbl_behaviorListStatusPanel = new GridBagLayout();
						gbl_behaviorListStatusPanel.columnWidths = new int[]{0, 0, 250, 0, 0};
						gbl_behaviorListStatusPanel.rowHeights = new int[]{0, 74, 0, 62, 0, 0, 0, 0};
						gbl_behaviorListStatusPanel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
						gbl_behaviorListStatusPanel.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
						behaviorListStatusPanel.setLayout(gbl_behaviorListStatusPanel);
						
						JLabel lblConditions_1 = new JLabel("Conditions");
						GridBagConstraints gbc_lblConditions_1 = new GridBagConstraints();
						gbc_lblConditions_1.insets = new Insets(0, 0, 5, 5);
						gbc_lblConditions_1.gridx = 2;
						gbc_lblConditions_1.gridy = 0;
						behaviorListStatusPanel.add(lblConditions_1, gbc_lblConditions_1);
						

						
//		String[] stateOptions = {"High", "Low"};
						
						
						JPanel behaviorSensorPanel = new JPanel();
						GridBagConstraints gbc_behaviorSensorPanel = new GridBagConstraints();
						gbc_behaviorSensorPanel.anchor = GridBagConstraints.EAST;
						gbc_behaviorSensorPanel.fill = GridBagConstraints.VERTICAL;
						gbc_behaviorSensorPanel.insets = new Insets(0, 0, 5, 5);
						gbc_behaviorSensorPanel.gridx = 0;
						gbc_behaviorSensorPanel.gridy = 1;
						behaviorListStatusPanel.add(behaviorSensorPanel, gbc_behaviorSensorPanel);
						behaviorSensorPanel.setLayout(new FormLayout(new ColumnSpec[] {
								FormFactory.RELATED_GAP_COLSPEC,
								ColumnSpec.decode("right:30dlu"),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								ColumnSpec.decode("89px:grow"),},
							new RowSpec[] {
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								RowSpec.decode("22px"),
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,}));
						
						JLabel lblConditions = new JLabel("Sensors");
						behaviorSensorPanel.add(lblConditions, "4, 2");
						
						JLabel lblSensor = new JLabel("Sensor");
						behaviorSensorPanel.add(lblSensor, "2, 4, right, default");
						
						sensorComboBox = new JComboBox();
						behaviorSensorPanel.add(sensorComboBox, "4, 4, fill, default");
						
						JLabel lblState = new JLabel("State");
						behaviorSensorPanel.add(lblState, "2, 6, right, default");
						
						sensorStateComboBox = new JComboBox();
						behaviorSensorPanel.add(sensorStateComboBox, "4, 6, fill, default");
						
	
						
						JLabel lblDuration = new JLabel("Duration");
						behaviorSensorPanel.add(lblDuration, "2, 8, right, default");
						
						sensorDuration = new JTextField();
						sensorDuration.setText("1");
						behaviorSensorPanel.add(sensorDuration, "4, 8, fill, default");
						sensorDuration.setColumns(10);
						behaviorSensorPanel.add(lblSensor, "2, 4, right, default");
						behaviorSensorPanel.add(lblState, "2, 6, right, default");
						behaviorSensorPanel.add(lblDuration, "2, 8, right, default");
						
						sensorDuration = new JTextField();
						sensorDuration.setText("1");
						behaviorSensorPanel.add(sensorDuration, "4, 8");
						sensorDuration.setColumns(10);
						
						JPanel conditionBtnPanel = new JPanel();
						GridBagConstraints gbc_conditionBtnPanel = new GridBagConstraints();
						gbc_conditionBtnPanel.insets = new Insets(0, 0, 5, 5);
						gbc_conditionBtnPanel.gridx = 1;
						gbc_conditionBtnPanel.gridy = 1;
						behaviorListStatusPanel.add(conditionBtnPanel, gbc_conditionBtnPanel);
						GridBagLayout gbl_conditionBtnPanel = new GridBagLayout();
						gbl_conditionBtnPanel.columnWidths = new int[]{51, 0};
						gbl_conditionBtnPanel.rowHeights = new int[]{23, 0, 0};
						gbl_conditionBtnPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
						gbl_conditionBtnPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
						conditionBtnPanel.setLayout(gbl_conditionBtnPanel);
						
						addConditionBtn = new JButton(">>");
						GridBagConstraints gbc_addConditionBtn = new GridBagConstraints();
						gbc_addConditionBtn.insets = new Insets(0, 0, 5, 0);
						gbc_addConditionBtn.gridx = 0;
						gbc_addConditionBtn.gridy = 0;
						conditionBtnPanel.add(addConditionBtn, gbc_addConditionBtn);
						
								removeConditionBtn = new JButton("<<");
								
												GridBagConstraints gbc_button_2 = new GridBagConstraints();
												gbc_button_2.anchor = GridBagConstraints.WEST;
												gbc_button_2.gridx = 0;
												gbc_button_2.gridy = 1;
												conditionBtnPanel.add(removeConditionBtn, gbc_button_2);
						
						conditionList = new JList();
						GridBagConstraints gbc_behaviorList = new GridBagConstraints();
						gbc_behaviorList.insets = new Insets(0, 0, 5, 5);
						gbc_behaviorList.fill = GridBagConstraints.BOTH;
						gbc_behaviorList.gridx = 2;
						gbc_behaviorList.gridy = 1;
						behaviorListStatusPanel.add(conditionList, gbc_behaviorList);
						
						JLabel lblActions = new JLabel("Actions");
						GridBagConstraints gbc_lblActions = new GridBagConstraints();
						gbc_lblActions.insets = new Insets(0, 0, 5, 5);
						gbc_lblActions.gridx = 2;
						gbc_lblActions.gridy = 2;
						behaviorListStatusPanel.add(lblActions, gbc_lblActions);
						
		
						JPanel behaviorActuatorPanel = new JPanel();
						GridBagConstraints gbc_behaviorActuatorPanel = new GridBagConstraints();
						gbc_behaviorActuatorPanel.anchor = GridBagConstraints.EAST;
						gbc_behaviorActuatorPanel.fill = GridBagConstraints.VERTICAL;
						gbc_behaviorActuatorPanel.insets = new Insets(0, 0, 5, 5);
						gbc_behaviorActuatorPanel.gridx = 0;
						gbc_behaviorActuatorPanel.gridy = 3;
						behaviorListStatusPanel.add(behaviorActuatorPanel, gbc_behaviorActuatorPanel);
						behaviorActuatorPanel.setLayout(new FormLayout(new ColumnSpec[] {
								FormFactory.RELATED_GAP_COLSPEC,
								ColumnSpec.decode("right:30dlu"),
								FormFactory.RELATED_GAP_COLSPEC,
								ColumnSpec.decode("default:grow"),},
							new RowSpec[] {
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,}));
						
						JLabel lblActuators = new JLabel("Actuators");
						behaviorActuatorPanel.add(lblActuators, "4, 2");
						
						JLabel lblActuator = new JLabel("Actuator");
						behaviorActuatorPanel.add(lblActuator, "2, 4, right, default");
						
				
						
						actuatorComboBox = new JComboBox();
						behaviorActuatorPanel.add(actuatorComboBox, "4, 4, fill, default");
						
						JLabel lblState_1 = new JLabel("State");
						behaviorActuatorPanel.add(lblState_1, "2, 6, right, default");
						
//						ComponentState[] stateOptions = {new ComponentState("High", 1), new ComponentState("Low", 0)};
						actuatorStateComboBox = new JComboBox();
						behaviorActuatorPanel.add(actuatorStateComboBox, "4, 6, fill, default");
						
						JLabel lblDuration_1 = new JLabel("Duration");
						behaviorActuatorPanel.add(lblDuration_1, "2, 8, right, default");
						
						actuatorDurationTxt = new JTextField();
						actuatorDurationTxt.setText("1");
						behaviorActuatorPanel.add(actuatorDurationTxt, "4, 8, fill, default");
						actuatorDurationTxt.setColumns(10);
						
						JPanel actionBtnPanel = new JPanel();
						GridBagConstraints gbc_actionBtnPanel = new GridBagConstraints();
						gbc_actionBtnPanel.insets = new Insets(0, 0, 5, 5);
						gbc_actionBtnPanel.gridx = 1;
						gbc_actionBtnPanel.gridy = 3;
						behaviorListStatusPanel.add(actionBtnPanel, gbc_actionBtnPanel);
						GridBagLayout gbl_actionBtnPanel = new GridBagLayout();
						gbl_actionBtnPanel.columnWidths = new int[]{51, 0};
						gbl_actionBtnPanel.rowHeights = new int[]{23, 0, 0};
						gbl_actionBtnPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
						gbl_actionBtnPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
						actionBtnPanel.setLayout(gbl_actionBtnPanel);
						
						addActionBtn = new JButton(">>");
						
								actionBtnPanel.add(addActionBtn);
								
								removeActionBtn = new JButton("<<");
								
														GridBagConstraints gbc_removeActionBtn = new GridBagConstraints();
														gbc_removeActionBtn.gridx = 0;
														gbc_removeActionBtn.gridy = 1;
														actionBtnPanel.add(removeActionBtn, gbc_removeActionBtn);
						
			
						
						actionList = new JList();
						GridBagConstraints gbc_actionList = new GridBagConstraints();
						gbc_actionList.insets = new Insets(0, 0, 5, 5);
						gbc_actionList.fill = GridBagConstraints.BOTH;
						gbc_actionList.gridx = 2;
						gbc_actionList.gridy = 3;
						behaviorListStatusPanel.add(actionList, gbc_actionList);
						
			
						
						JPanel behaviorNameEntryPanel = new JPanel();
						GridBagConstraints gbc_behaviorNameEntryPanel = new GridBagConstraints();
						gbc_behaviorNameEntryPanel.insets = new Insets(0, 0, 5, 5);
						gbc_behaviorNameEntryPanel.fill = GridBagConstraints.BOTH;
						gbc_behaviorNameEntryPanel.gridx = 2;
						gbc_behaviorNameEntryPanel.gridy = 5;
						behaviorListStatusPanel.add(behaviorNameEntryPanel, gbc_behaviorNameEntryPanel);
						behaviorNameEntryPanel.setLayout(new FormLayout(new ColumnSpec[] {
								FormFactory.RELATED_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.RELATED_GAP_COLSPEC,
								ColumnSpec.decode("default:grow"),},
							new RowSpec[] {
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,}));
						
						JLabel lblName = new JLabel("Name");
						behaviorNameEntryPanel.add(lblName, "2, 2, right, default");
						
						txtNewBehavior = new JTextField();
						txtNewBehavior.setText("New Behavior");
						behaviorNameEntryPanel.add(txtNewBehavior, "4, 2, fill, default");
						txtNewBehavior.setColumns(10);
						
						JPanel behaviorStatusSaveBtnPanel = new JPanel();
						GridBagConstraints gbc_behaviorStatusSaveBtnPanel = new GridBagConstraints();
						gbc_behaviorStatusSaveBtnPanel.insets = new Insets(0, 0, 0, 5);
						gbc_behaviorStatusSaveBtnPanel.gridx = 2;
						gbc_behaviorStatusSaveBtnPanel.gridy = 6;
						behaviorListStatusPanel.add(behaviorStatusSaveBtnPanel, gbc_behaviorStatusSaveBtnPanel);
						behaviorStatusSaveBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						
						btnSave = new JButton("Save");
						
						behaviorStatusSaveBtnPanel.add(btnSave);
						
						btnClearBehavior = new JButton("Clear");
	
						behaviorStatusSaveBtnPanel.add(btnClearBehavior);
						
						JPanel behaviorListPanel = new JPanel();
						behaviorStatusPanel.add(behaviorListPanel, BorderLayout.CENTER);
						GridBagLayout gbl_behaviorListPanel = new GridBagLayout();
						gbl_behaviorListPanel.columnWidths = new int[]{243, 0};
						gbl_behaviorListPanel.rowHeights = new int[]{0, 0, 0, 0};
						gbl_behaviorListPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
						gbl_behaviorListPanel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
						behaviorListPanel.setLayout(gbl_behaviorListPanel);
						
						JLabel lblBehaviors = new JLabel("Behaviors");
						GridBagConstraints gbc_lblBehaviors = new GridBagConstraints();
						gbc_lblBehaviors.insets = new Insets(0, 0, 5, 0);
						gbc_lblBehaviors.gridx = 0;
						gbc_lblBehaviors.gridy = 0;
						behaviorListPanel.add(lblBehaviors, gbc_lblBehaviors);
						
						behaviorList = new JList();
						GridBagConstraints gbc_behaviorList1 = new GridBagConstraints();
						gbc_behaviorList1.insets = new Insets(0, 0, 5, 0);
						gbc_behaviorList1.fill = GridBagConstraints.BOTH;
						gbc_behaviorList1.gridx = 0;
						gbc_behaviorList1.gridy = 1;
						behaviorListPanel.add(behaviorList, gbc_behaviorList1);
						
						JPanel behaviorButtonsPanel = new JPanel();
						GridBagConstraints gbc_behaviorButtonsPanel = new GridBagConstraints();
						gbc_behaviorButtonsPanel.fill = GridBagConstraints.BOTH;
						gbc_behaviorButtonsPanel.gridx = 0;
						gbc_behaviorButtonsPanel.gridy = 2;
						behaviorListPanel.add(behaviorButtonsPanel, gbc_behaviorButtonsPanel);
						
						btnBehaviorRemove = new JButton("Remove");

						behaviorButtonsPanel.add(btnBehaviorRemove);
						
						JLabel lblActuatorState = new JLabel("State");
						
						JLabel lblActuatorDuration = new JLabel("Duration (seconds)");
						
		
						Initialize();
	}
	
	private void Initialize() {
		
		ComponentState[] stateOptions = {new ComponentState("High", 1), new ComponentState("Low", 0)}; // sensorStateComboBox
		stateListModel =  new DefaultComboBoxModel(stateOptions); 
		btnClearBehavior.setEnabled(false);
		SetupBehaviorUI();
		SetupConditionUI();
		SetupActionUI();
		
	}
	
	private void SetupConditionUI(){
		List<Sensor> sensorOptions = view.getController().GetSensors();
		sensorListModel = new DefaultComboBoxModel(sensorOptions.toArray()); 
		sensorComboBox.setModel(sensorListModel);
		sensorStateComboBox.setModel(stateListModel);
		conditionListModel = new DefaultListModel();
		conditionList.setModel(conditionListModel);
		addConditionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				sensorComboBox
//				stateComboBox
//				sensorDuration
				Condition condition = new Condition((Sensor) sensorComboBox.getSelectedItem(), (ComponentState) sensorStateComboBox.getSelectedItem(), Integer.valueOf(sensorDuration.getText()));
				sensorComboBox.removeItem(sensorComboBox.getSelectedItem());
				if (sensorComboBox.getItemCount() == 0)
					addConditionBtn.setEnabled(false);
				removeConditionBtn.setEnabled(true);
				btnClearBehavior.setEnabled(true);
				conditionListModel.addElement(condition);
			}
		});
		removeConditionBtn.setEnabled(false);
		removeConditionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeCondition();
			}
		});
	}
	
	private void SetupActionUI() {
		List<Actuator> actuatorOptions = view.getController().GetActuators();
		actuatorListModel = new DefaultComboBoxModel(actuatorOptions.toArray()); 
		actuatorComboBox.setModel(actuatorListModel);
		actuatorStateComboBox.setModel(stateListModel);
		actionListModel = new DefaultListModel();
		actionList.setModel(actionListModel);
		addActionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				sensorComboBox
//				stateComboBox
//				sensorDuration
				Action action = new Action((Actuator) actuatorComboBox.getSelectedItem(), (ComponentState) actuatorStateComboBox.getSelectedItem(), Integer.valueOf(actuatorDurationTxt.getText()));
				actuatorComboBox.removeItem(actuatorComboBox.getSelectedItem());
				if (actuatorComboBox.getItemCount() == 0)
					addActionBtn.setEnabled(false);
				removeActionBtn.setEnabled(true);
				btnClearBehavior.setEnabled(true);
				actionListModel.addElement(action);
			}
		});
		removeActionBtn.setEnabled(false);
		removeActionBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				removeAction();

			}
		
		});
	}
	
	private void SetupBehaviorUI() {
		behaviorListModel = new DefaultListModel();
		behaviorList.setModel(behaviorListModel);
		btnBehaviorRemove.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				List<Action> actions = new ArrayList<Action>();
				if ((actionListModel.getSize() == 0) || (conditionListModel.getSize() == 0)) {
					view.displayErrorMessage("Missing Behavior Components", "All new behaviors require at least one condition and at least one action to be created");
					return;
				}
				for (int i = 0; i < actionListModel.getSize(); i++) {
					actions.add((Action) actionListModel.getElementAt(i));
				}
				List<Condition> conditions = new ArrayList<Condition>();
				for (int i = 0; i < conditionListModel.getSize(); i++) {
					conditions.add((Condition) conditionListModel.getElementAt(i));
				}
				Behavior behavior = new Behavior(txtNewBehavior.getText(),conditions, actions);
				view.getController().addBehavior(behavior);
				behaviorListModel.addElement(behavior);
				btnBehaviorRemove.setEnabled(true);
				clearBehaviorSettings();	
			}
		});
		
		btnBehaviorRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    int index = behaviorList.getSelectedIndex();
			    if (index == -1)
			    	return;

			    Behavior removedBehavior = ((Behavior) behaviorListModel.getElementAt(index));
			    behaviorListModel.remove(index);
			    
			    int size = behaviorListModel.getSize();
			    if (size == 0) { 
			    	btnBehaviorRemove.setEnabled(false);
			    } else { 
			        if (index == behaviorListModel.getSize()) {
			            index--;
			        }

			        behaviorList.setSelectedIndex(index);
			        behaviorList.ensureIndexIsVisible(index);
			    }
				
				
			}
		});
		
		btnClearBehavior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearBehaviorSettings();				
			}
		});
	}
	
	private void clearBehaviorSettings() {
		for (int i = 0; i < actionListModel.getSize(); i++) {
			removeAction();
		}
		for (int i = 0; i < conditionListModel.getSize(); i++) {
			removeCondition();
		}
		
	}
	private void removeCondition() {
	    int index = conditionList.getSelectedIndex();
	    if (index == -1)
	    	index = 0;
//	    sensorComboBox.addItem(conditionListModel.getElementAt(index));
	    Sensor removedSensor = ((Condition) conditionListModel.getElementAt(index)).getSensor();
	    sensorListModel.addElement(removedSensor);
	    addConditionBtn.setEnabled(true);
	    conditionListModel.remove(index);
	    
	    int size = conditionListModel.getSize();
//	    System.out.println("Removing condition, conditionListModel.getSize()=>" + conditionListModel.getSize());
	    if (size == 0) { //Nobody's left, disable firing.
	    	removeConditionBtn.setEnabled(false);
	    	if (actionListModel.getSize() == 0)
	    		btnClearBehavior.setEnabled(false);
	    } 
	    else { //Select an index.
	        if (index == conditionListModel.getSize()) {
	            //removed item in last position
	            index--;
	        }

	        conditionList.setSelectedIndex(index);
	        conditionList.ensureIndexIsVisible(index);
	    }
		
	}
	
	private void removeAction() {
	    int index = actionList.getSelectedIndex();
	    if (index == -1)
	    	index = 0;
	    Actuator removedActuator = ((Action) actionListModel.getElementAt(index)).getActuator();
	    actuatorListModel.addElement(removedActuator);
	    addActionBtn.setEnabled(true);
	    actionListModel.remove(index);

	    int size = actionListModel.getSize();
//	    System.out.println("Removing action, actionListModel.getSize()=>" + size);

	    if (size == 0) { //Nobody's left, disable firing.
	    	removeActionBtn.setEnabled(false);
	    	if (conditionListModel.getSize() == 0)
	    		btnClearBehavior.setEnabled(false);
	    } else { //Select an index.
	        if (index == actionListModel.getSize()) {
	            //removed item in last position
	            index--;
	        }

	        actionList.setSelectedIndex(index);
	        actionList.ensureIndexIsVisible(index);
	    }
		
	}
	
}
