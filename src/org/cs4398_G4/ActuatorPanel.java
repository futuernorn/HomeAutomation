package org.cs4398_G4;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ActuatorPanel extends ComponentPanel {
	private JTextField txtCurrentState;
	public ActuatorPanel() {
		super();
		
		JButton btnNewButton = new JButton("Toggle State");
		add(btnNewButton, "3, 3, fill, center");
	}

}
