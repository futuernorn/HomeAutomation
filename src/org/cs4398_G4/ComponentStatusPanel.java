package org.cs4398_G4;

import java.awt.BorderLayout;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.FlowLayout;

public class ComponentStatusPanel extends JPanel {
	LocalInterface view;
	public ComponentStatusPanel(LocalInterface view) {
		this.view = view;
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.addAncestorListener ( new AncestorListener () {
	        public void ancestorAdded ( AncestorEvent event )
	        {
	            // Component added somewhere
//	        	System.out.println(this+" added.");
	        	refreshComponents();
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
	


	
	public void refreshComponents() {
		for (Component component : view.getController().getComponents()) {
			this.add(component.getComponentUI());
		}
	}
}
