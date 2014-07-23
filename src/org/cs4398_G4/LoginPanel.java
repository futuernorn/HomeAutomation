package org.cs4398_G4;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class LoginPanel extends JPanel {

	private LocalInterface view;
	private JTextField password;
	private JTextField textField_1;



	/**
	 * Create the frame.
	 */
	public LoginPanel(final LocalInterface view) {
		this.view = view;

		
	
		this.setBackground(new Color(128, 128, 128));
		this.setLayout(null);
		
		JLabel lblUserId = new JLabel("User ID:");
		lblUserId.setBounds(91, 85, 46, 14);
		this.add(lblUserId);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(91, 113, 64, 14);
		this.add(lblPassword);
		
		//password text field
		password = new JPasswordField();
		password.setBounds(176, 110, 138, 20);
		this.add(password);
		password.setColumns(10);
		
		//user ID text field
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(176, 82, 138, 20);
		this.add(textField_1);
		
		User user = new User();
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if (user.logOn(password.getText()))
//				{
					view.doLogin();
//				}
//				else
//				{
//					textField_1.setText(password.getText());
//				}
			}
		});
		btnOk.setBounds(250, 141, 64, 20);
		this.add(btnOk);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(176, 141, 64, 20);
		this.add(btnClear);		
	}
}
