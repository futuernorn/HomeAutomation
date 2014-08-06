package org.cs4398g4.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.cs4398g4.Actuator;
import org.cs4398g4.BaseStation;
import org.cs4398g4.Component;

import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class ActuatorPanel extends ComponentPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8004438556424129471L;
	private JButton btnToggle;

	public ActuatorPanel(final Component component, BaseStation baseStation) {
		super(component, baseStation);

		btnToggle = new JButton("Toggle State");
		btnToggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				((Actuator) component).toggle();
				updateToggleBtnText(component.getOutputPin());
			}
		});

		add(btnToggle, "3, 5");
	}

	protected void updateToggleBtnText(GpioPinDigitalOutput outputPins) {
		String currentState = component.getOutputPinState() ? "Off" : "On";
		btnToggle.setText("Toggle " + currentState);
		baseStation.getLog().addLog(component + " toggled state to: " + currentState);
		refreshStateText();

	}

}
