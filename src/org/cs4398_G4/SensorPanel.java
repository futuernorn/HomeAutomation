package org.cs4398_G4;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class SensorPanel extends ComponentPanel  implements GpioPinListenerDigital  {
	private JTextField txtCurrentState;
	public SensorPanel() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("450px"),},
			new RowSpec[] {
				RowSpec.decode("fill:default"),}));
		
		JLabel lblCurrentState = new JLabel("Current State");
		add(lblCurrentState, "1, 1, right, default");
		
		txtCurrentState = new JTextField();
		txtCurrentState.setText("Current State");
		add(txtCurrentState, "3, 1, fill, default");
		txtCurrentState.setColumns(10);
	}

	private void setStateText(String newText) {
		txtCurrentState.setText(newText);
	}

	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		setStateText(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
		
	}
}
