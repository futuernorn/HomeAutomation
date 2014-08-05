package org.cs4398_G4;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class ComponentPanel extends JPanel  implements GpioPinListenerDigital  {
	protected JTextField txtCurrentState;
	final protected Component component;
	BaseStation baseStation;
	public ComponentPanel(final Component component, BaseStation baseStation) {
		this.baseStation = baseStation;
		this.component = component;
		if (component.getInputPins() != null)
			component.getInputPins().addListener(this);
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("450px"),},
			new RowSpec[] {
				RowSpec.decode("fill:default"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblComponentName = new JLabel(component.toString());
		add(lblComponentName, "1, 1, right, default");
		
		JLabel lblCurrentState = new JLabel("Current State");
		add(lblCurrentState, "1, 2, right, default");
		
		txtCurrentState = new JTextField();
		txtCurrentState.setText("Current State");
		add(txtCurrentState, "3, 1, fill, default");
		txtCurrentState.setColumns(10);
		
		Border  blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);
		
		this.addAncestorListener ( new AncestorListener () {
	        public void ancestorAdded ( AncestorEvent event )
	        {
	            // Component added somewhere
//	        	System.out.println(component+" added.");
	        	refreshStateText();
	        }
	        
	        public void ancestorRemoved ( AncestorEvent event )
	        {
	            // Component removed from container
	        }

	        public void ancestorMoved ( AncestorEvent event )
	        {
	            // Component container moved
	        }
	        
	    } );
	}
	

	public void refreshStateText() {
    	if (component.getInputPins() != null)
    		setStateText(component.getInputPins().getState().toString());
    	else if (component.getOutputPins() != null)
    		setStateText(component.getOutputPins().getState().toString());
	}
	public void setStateText(String newText) {
		txtCurrentState.setText(newText);
	}

	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		// make log entry here
//		System.out.println();
		baseStation.getLog().addLog("--> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
		refreshStateText();
		
	}
}
