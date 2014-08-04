package org.cs4398_G4;

import java.awt.BorderLayout;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;

public class ComponentStatusPanel extends JPanel {
	public ComponentStatusPanel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}
	


	
	public void refreshComponents(List<Component> components) {
		for (Component component : components) {
			this.add(component.getComponentUI());
		}
	}
}
