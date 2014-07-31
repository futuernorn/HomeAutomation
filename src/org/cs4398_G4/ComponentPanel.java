package org.cs4398_G4;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class ComponentPanel extends JPanel  implements GpioPinListenerDigital  {
	private JTextField txtCurrentState;
	public ComponentPanel() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("450px"),},
			new RowSpec[] {
				RowSpec.decode("fill:default"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblCurrentState = new JLabel("Current State");
		add(lblCurrentState, "1, 1, right, default");
		
		txtCurrentState = new JTextField();
		txtCurrentState.setText("Current State");
		add(txtCurrentState, "3, 1, fill, default");
		txtCurrentState.setColumns(10);
		
		Border  blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);
	}

	private void setStateText(String newText) {
		txtCurrentState.setText(newText);
	}

	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		setStateText(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
		
	}
}
