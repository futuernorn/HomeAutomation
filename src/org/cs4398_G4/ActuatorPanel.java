package org.cs4398_G4;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ActuatorPanel extends ComponentPanel {
	private JTextField txtCurrentState;
	private JButton btnToggle;
	public ActuatorPanel(final Component component) {
		super(component);
		
		btnToggle = new JButton("Toggle State");
		btnToggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((Actuator) component).toggle();
				updateToggleBtnText(component.getOutputPins());
			}
		});
		add(btnToggle, "3, 3, fill, center");
	}
	protected void updateToggleBtnText(GpioPinDigitalOutput outputPins) {
		// TODO Auto-generated method stub
		btnToggle.setText("Toggle ["+((Actuator) component).isOn()+"]");
		refreshStateText();
		
	}

}
