package org.cs4398g4.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class LoginPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7124687573646828570L;
	private JTextField txtPassword;
	private JTextField txtUserName;
	private LocalInterface view;
	private JButton btnOk;


	/**
	 * Create the frame with text fields for log in information
	 */
	public LoginPanel(final LocalInterface view) {
		this.view = view;
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("fill:default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default:grow"),}));
		
		JLabel lblUserId = new JLabel("User ID:");
		panel.add(lblUserId, "3, 3");
		
		//user ID text field
		txtUserName = new JTextField();
		panel.add(txtUserName, "5, 3");
		txtUserName.setColumns(10);

		
		
		JLabel lblPassword = new JLabel("Password:");
		panel.add(lblPassword, "3, 5");
		
		//password text field
		txtPassword = new JPasswordField();
		panel.add(txtPassword, "5, 5");
		txtPassword.setColumns(10);
		

		
		
		JPanel loginButtonPanel = new JPanel();
		panel.add(loginButtonPanel, "5, 7, fill, fill");
		loginButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnOk = new JButton("OK");
		loginButtonPanel.add(btnOk);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearFields();
			}
		});
		loginButtonPanel.add(btnClear);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		
		initalize();
		
		
		// for demo only
		txtUserName.setText("Jeffrey");
		txtPassword.setText("626755");
		
	}


	public void doLogin() {
		if (view.getBaseStation().doLogin(txtUserName.getText(), txtPassword.getText())) {
			//create tabs after login here
			view.doLogin();
		} 
	}
	
	public void initalize() {
		
		clearFields();
		view.getFrmHomeAutomationSystem().getRootPane().setDefaultButton(btnOk);
		
	}
	
	private void clearFields() {
		txtUserName.setText("");
		txtPassword.setText("");
	}
}
