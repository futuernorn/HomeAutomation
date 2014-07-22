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

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.SpringLayout;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JSeparator;


public class BehaviorPanel extends JPanel {
	private JTextField sensorDuration;
	private LocalInterface view;
	private JComboBox sensorStateComboBox;
	private JComboBox stateComboBox;
	private JList conditionList;
	private DefaultListModel conditionListModel;
	private JButton addConditionBtn;
	private JButton removeConditionBtn;
	private JTextField txtNewBehavrior;
	private JTextField actuatorDurationTxt;
	private JComboBox actuatorComboBox;
	private JComboBox actuatorStateComboBox;

	/**
	 * Create the panel.
	 */
	public BehaviorPanel(LocalInterface view) {
		this.view = view;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{200, 351, 200, 0};
		gridBagLayout.rowHeights = new int[]{450, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		List<Sensor> sensorOptions = view.GetController().GetSensors();
		System.out.println("Sensor Options: "+sensorOptions);
		
//		String[] stateOptions = {"High", "Low"};
		
		
		JPanel behaviorSensorPanel = new JPanel();
		GridBagConstraints gbc_behaviorSensorPanel = new GridBagConstraints();
		gbc_behaviorSensorPanel.anchor = GridBagConstraints.NORTH;
		gbc_behaviorSensorPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_behaviorSensorPanel.insets = new Insets(0, 0, 5, 5);
		gbc_behaviorSensorPanel.gridx = 0;
		gbc_behaviorSensorPanel.gridy = 0;
		add(behaviorSensorPanel, gbc_behaviorSensorPanel);
		behaviorSensorPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblConditions = new JLabel("Conditions");
		behaviorSensorPanel.add(lblConditions, "4, 2");
		
		JLabel lblSensor = new JLabel("Sensor");
		behaviorSensorPanel.add(lblSensor, "2, 4, right, default");
		ComponentState[] stateOptions = {new ComponentState("High", 1), new ComponentState("Low", 0)};
		sensorStateComboBox = new JComboBox(sensorOptions.toArray());
		behaviorSensorPanel.add(sensorStateComboBox, "4, 4, left, top");
		
		JLabel lblState = new JLabel("State");
		behaviorSensorPanel.add(lblState, "2, 6, right, default");
		stateComboBox = new JComboBox(stateOptions);
		behaviorSensorPanel.add(stateComboBox, "4, 6");
		
		JLabel lblDuration = new JLabel("Duration (seconds)");
		behaviorSensorPanel.add(lblDuration, "2, 8, right, default");
		
		sensorDuration = new JTextField();
		sensorDuration.setText("1");
		behaviorSensorPanel.add(sensorDuration, "4, 8");
		sensorDuration.setColumns(10);
		
		conditionListModel = new DefaultListModel();
		
		JPanel behaviorStatusPanel = new JPanel();
		GridBagConstraints gbc_behaviorStatusPanel = new GridBagConstraints();
		gbc_behaviorStatusPanel.fill = GridBagConstraints.BOTH;
		gbc_behaviorStatusPanel.insets = new Insets(0, 0, 5, 5);
		gbc_behaviorStatusPanel.gridx = 1;
		gbc_behaviorStatusPanel.gridy = 0;
		add(behaviorStatusPanel, gbc_behaviorStatusPanel);
		behaviorStatusPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel behaviorStatusSaveBtnPanel = new JPanel();
		behaviorStatusPanel.add(behaviorStatusSaveBtnPanel, BorderLayout.SOUTH);
		behaviorStatusSaveBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSave = new JButton("Save");
		behaviorStatusSaveBtnPanel.add(btnSave);
		
		JButton btnClear_1 = new JButton("Clear");
		behaviorStatusSaveBtnPanel.add(btnClear_1);
		
		JPanel conditionBtnPanel = new JPanel();
		behaviorStatusPanel.add(conditionBtnPanel, BorderLayout.WEST);
		GridBagLayout gbl_conditionBtnPanel = new GridBagLayout();
		gbl_conditionBtnPanel.columnWidths = new int[]{51, 0};
		gbl_conditionBtnPanel.rowHeights = new int[]{23, 0, 0};
		gbl_conditionBtnPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_conditionBtnPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		conditionBtnPanel.setLayout(gbl_conditionBtnPanel);
		
		addConditionBtn = new JButton(">>");
		addConditionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				sensorComboBox
//				stateComboBox
//				sensorDuration
				Condition condition = new Condition((Sensor) sensorStateComboBox.getSelectedItem(), (ComponentState) stateComboBox.getSelectedItem(), Integer.valueOf(sensorDuration.getText()));
				sensorStateComboBox.removeItem(sensorStateComboBox.getSelectedItem());
				if (sensorStateComboBox.getItemCount() == 0)
					addConditionBtn.setEnabled(false);
				removeConditionBtn.setEnabled(true);
				conditionListModel.addElement(condition);
			}
		});
		GridBagConstraints gbc_button_1_1 = new GridBagConstraints();
		gbc_button_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_button_1_1.anchor = GridBagConstraints.WEST;
		gbc_button_1_1.gridx = 0;
		gbc_button_1_1.gridy = 0;
		conditionBtnPanel.add(addConditionBtn, gbc_button_1_1);
		
				removeConditionBtn = new JButton("<<");
				removeConditionBtn.setEnabled(false);
				removeConditionBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					    int index = conditionList.getSelectedIndex();
					    if (index == -1)
					    	return;
					    sensorStateComboBox.addItem(conditionListModel.getElementAt(index));
					    addConditionBtn.setEnabled(true);
					    conditionListModel.remove(index);

					    int size = conditionListModel.getSize();

					    if (size == 0) { //Nobody's left, disable firing.
					    	removeConditionBtn.setEnabled(false);

					    } else { //Select an index.
					        if (index == conditionListModel.getSize()) {
					            //removed item in last position
					            index--;
					        }

					        conditionList.setSelectedIndex(index);
					        conditionList.ensureIndexIsVisible(index);
					    }
					}
				});
				GridBagConstraints gbc_button_2 = new GridBagConstraints();
				gbc_button_2.anchor = GridBagConstraints.WEST;
				gbc_button_2.gridx = 0;
				gbc_button_2.gridy = 1;
				conditionBtnPanel.add(removeConditionBtn, gbc_button_2);
				
				JPanel actionBtnPanel = new JPanel();
				behaviorStatusPanel.add(actionBtnPanel, BorderLayout.EAST);
				GridBagLayout gbl_actionBtnPanel = new GridBagLayout();
				gbl_actionBtnPanel.columnWidths = new int[]{51, 0};
				gbl_actionBtnPanel.rowHeights = new int[]{23, 0, 0};
				gbl_actionBtnPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
				gbl_actionBtnPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
				actionBtnPanel.setLayout(gbl_actionBtnPanel);
				
				JButton button = new JButton("<<");
				
						actionBtnPanel.add(button);
						
						JButton button_1 = new JButton(">>");
						GridBagConstraints gbc_button_1 = new GridBagConstraints();
						gbc_button_1.gridx = 0;
						gbc_button_1.gridy = 1;
						actionBtnPanel.add(button_1, gbc_button_1);
						
						JPanel behaviorStatusNamePanel = new JPanel();
						behaviorStatusPanel.add(behaviorStatusNamePanel, BorderLayout.NORTH);
						behaviorStatusNamePanel.setLayout(new FormLayout(new ColumnSpec[] {
								ColumnSpec.decode("46px"),
								FormFactory.RELATED_GAP_COLSPEC,
								ColumnSpec.decode("default:grow"),},
							new RowSpec[] {
								RowSpec.decode("14px"),}));
						
						JLabel lblBehaviorName = new JLabel("Name");
						behaviorStatusNamePanel.add(lblBehaviorName, "1, 1, right, top");
						
						txtNewBehavrior = new JTextField();
						txtNewBehavrior.setText("New Behavrior");
						behaviorStatusNamePanel.add(txtNewBehavrior, "3, 1, fill, center");
						txtNewBehavrior.setColumns(10);
						
						JPanel behaviorListStatusPanel = new JPanel();
						behaviorStatusPanel.add(behaviorListStatusPanel, BorderLayout.CENTER);
						GridBagLayout gbl_behaviorListStatusPanel = new GridBagLayout();
						gbl_behaviorListStatusPanel.columnWidths = new int[]{0, 0};
						gbl_behaviorListStatusPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
						gbl_behaviorListStatusPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
						gbl_behaviorListStatusPanel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
						behaviorListStatusPanel.setLayout(gbl_behaviorListStatusPanel);
						
						JLabel lblConditions_1 = new JLabel("Conditions");
						GridBagConstraints gbc_lblConditions_1 = new GridBagConstraints();
						gbc_lblConditions_1.insets = new Insets(0, 0, 5, 0);
						gbc_lblConditions_1.gridx = 0;
						gbc_lblConditions_1.gridy = 0;
						behaviorListStatusPanel.add(lblConditions_1, gbc_lblConditions_1);
						conditionList = new JList(conditionListModel);
						conditionList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
						GridBagConstraints gbc_conditionList = new GridBagConstraints();
						gbc_conditionList.insets = new Insets(0, 0, 5, 0);
						gbc_conditionList.gridx = 0;
						gbc_conditionList.gridy = 1;
						behaviorListStatusPanel.add(conditionList, gbc_conditionList);
						
						JSeparator separator = new JSeparator();
						GridBagConstraints gbc_separator = new GridBagConstraints();
						gbc_separator.insets = new Insets(0, 0, 5, 0);
						gbc_separator.gridx = 0;
						gbc_separator.gridy = 2;
						behaviorListStatusPanel.add(separator, gbc_separator);
						
						JLabel lblActions = new JLabel("Actions");
						GridBagConstraints gbc_lblActions = new GridBagConstraints();
						gbc_lblActions.insets = new Insets(0, 0, 5, 0);
						gbc_lblActions.gridx = 0;
						gbc_lblActions.gridy = 3;
						behaviorListStatusPanel.add(lblActions, gbc_lblActions);
						
		
						JPanel panel = new JPanel();
						GridBagConstraints gbc_panel = new GridBagConstraints();
						gbc_panel.insets = new Insets(0, 0, 5, 0);
						gbc_panel.fill = GridBagConstraints.BOTH;
						gbc_panel.gridx = 2;
						gbc_panel.gridy = 0;
						add(panel, gbc_panel);
						panel.setLayout(new FormLayout(new ColumnSpec[] {
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
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,}));
						
						JLabel lblActuators = new JLabel("Actuators");
						panel.add(lblActuators, "4, 2");
						
						JLabel lblActuator = new JLabel("Actuator");
						panel.add(lblActuator, "2, 4, right, default");
						behaviorSensorPanel.add(lblSensor, "2, 4, right, default");
						
						sensorStateComboBox = new JComboBox(sensorOptions.toArray());
						behaviorSensorPanel.add(sensorStateComboBox, "4, 4, left, top");
						
						JLabel lblActuatorState = new JLabel("State");
						behaviorSensorPanel.add(lblState, "2, 6, right, default");
						stateComboBox = new JComboBox(stateOptions);
						behaviorSensorPanel.add(stateComboBox, "4, 6");
						
						JLabel lblActuatorDuration = new JLabel("Duration (seconds)");
						behaviorSensorPanel.add(lblDuration, "2, 8, right, default");
						
						sensorDuration = new JTextField();
						sensorDuration.setText("1");
						behaviorSensorPanel.add(sensorDuration, "4, 8");
						sensorDuration.setColumns(10);
						
						conditionListModel = new DefaultListModel();
						
						List<Actuator> actuactorOptions = view.GetController().GetActuators();
						actuatorComboBox = new JComboBox(actuactorOptions.toArray());
						panel.add(actuatorComboBox, "4, 4, fill, default");
						
						JLabel lblState_1 = new JLabel("State");
						panel.add(lblState_1, "2, 6, right, default");
						
//						ComponentState[] stateOptions = {new ComponentState("High", 1), new ComponentState("Low", 0)};
						actuatorStateComboBox = new JComboBox(stateOptions);
						panel.add(actuatorStateComboBox, "4, 6, fill, default");
						
						JLabel lblDuration_1 = new JLabel("Duration");
						panel.add(lblDuration_1, "2, 8, right, default");
						
						actuatorDurationTxt = new JTextField();
						actuatorDurationTxt.setText("1");
						panel.add(actuatorDurationTxt, "4, 8, fill, default");
						actuatorDurationTxt.setColumns(10);

	}
}
