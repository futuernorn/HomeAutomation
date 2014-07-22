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


public class BehaviorPanel extends JPanel {
	private JTextField sensorDuration;
	private LocalInterface view;
	private JComboBox sensorComboBox;
	private JComboBox stateComboBox;
	private JList conditionList;
	private DefaultListModel conditionListModel;

	/**
	 * Create the panel.
	 */
	public BehaviorPanel(LocalInterface view) {
		this.view = view;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel behaviorSensorPanel = new JPanel();
		add(behaviorSensorPanel);
		behaviorSensorPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("89px:grow"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("22px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblSensor = new JLabel("Sensor");
		behaviorSensorPanel.add(lblSensor, "2, 2, right, default");
		
		List<Sensor> sensorOptions = view.GetController().GetSensors();
		System.out.println("Sensor Options: "+sensorOptions);
		sensorComboBox = new JComboBox(sensorOptions.toArray());
		behaviorSensorPanel.add(sensorComboBox, "4, 2, left, top");
		
		JLabel lblState = new JLabel("State");
		behaviorSensorPanel.add(lblState, "2, 4, right, default");
		
//		String[] stateOptions = {"High", "Low"};
		ComponentState[] stateOptions = {new ComponentState("High", 1), new ComponentState("Low", 0)};
		stateComboBox = new JComboBox(stateOptions);
		behaviorSensorPanel.add(stateComboBox, "4, 4");
		
		JLabel lblDuration = new JLabel("Duration (seconds)");
		behaviorSensorPanel.add(lblDuration, "2, 6, right, default");
		
		sensorDuration = new JTextField();
		sensorDuration.setText("1");
		behaviorSensorPanel.add(sensorDuration, "4, 6");
		sensorDuration.setColumns(10);
		
		JButton btnClear = new JButton("Clear");
		behaviorSensorPanel.add(btnClear, "4, 8");
		
		JPanel behaviorStatusPanel = new JPanel();
		add(behaviorStatusPanel);
		behaviorStatusPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		behaviorStatusPanel.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSave = new JButton("Save");
		panel.add(btnSave);
		
		JButton btnClear_1 = new JButton("Clear");
		panel.add(btnClear_1);
		
		conditionListModel = new DefaultListModel();
		conditionList = new JList(conditionListModel);
		
		
		behaviorStatusPanel.add(conditionList, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		behaviorStatusPanel.add(panel_1, BorderLayout.WEST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{51, 0};
		gbl_panel_1.rowHeights = new int[]{23, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JButton button_1 = new JButton(">>");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				sensorComboBox
//				stateComboBox
//				sensorDuration
				Condition condition = new Condition((Sensor) sensorComboBox.getSelectedItem(), (ComponentState) stateComboBox.getSelectedItem(), Integer.valueOf(sensorDuration.getText()));
				conditionListModel.addElement(condition);
			}
		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 5, 0);
		gbc_button_1.anchor = GridBagConstraints.WEST;
		gbc_button_1.gridx = 0;
		gbc_button_1.gridy = 0;
		panel_1.add(button_1, gbc_button_1);
		
		JButton button = new JButton("<<");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.WEST;
		gbc_button.gridx = 0;
		gbc_button.gridy = 1;
		panel_1.add(button, gbc_button);
		
		JPanel behaviorActuatorPanel = new JPanel();
		add(behaviorActuatorPanel);
		behaviorActuatorPanel.setLayout(new BorderLayout(0, 0));

	}

}
