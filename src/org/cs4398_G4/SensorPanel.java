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

	public SensorPanel(Component component) {
		super(component);
	}

	public void setStateText(String newText) {
		txtCurrentState.setText(newText);
	}


}
