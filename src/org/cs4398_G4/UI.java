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

public class UI extends JFrame {

	private JPanel contentPane;
	private JTextField password;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UI() {
		setBackground(UIManager.getColor("ComboBox.selectionBackground"));
		setTitle("Home Automation System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 424, 252);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		tabbedPane.addTab("Log In", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblUserId = new JLabel("User ID:");
		lblUserId.setBounds(91, 85, 46, 14);
		panel.add(lblUserId);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(91, 113, 64, 14);
		panel.add(lblPassword);
		
		//password text field
		password = new JPasswordField();
		password.setBounds(176, 110, 138, 20);
		panel.add(password);
		password.setColumns(10);
		
		//user ID text field
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(176, 82, 138, 20);
		panel.add(textField_1);
		
		final User user = new User();
		user.User();
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (user.logOn(password.getText()))
				{
					JPanel panel_1 = new JPanel();
					panel_1.setBackground(new Color(128, 128, 128));
					tabbedPane.addTab("Lights", null, panel_1, null);
					panel_1.setLayout(null);
					
					JPanel panel_2 = new JPanel();
					panel_2.setBackground(new Color(128, 128, 128));
					tabbedPane.addTab("Locks", null, panel_2, null);
					panel_2.setLayout(null);
					
					JPanel panel_3 = new JPanel();
					panel_3.setBackground(new Color(128, 128, 128));
					tabbedPane.addTab("HVAC", null, panel_3, null);
					panel_3.setLayout(null);
				}
				else
				{
					textField_1.setText(password.getText());
				}
			}
		});
		btnOk.setBounds(250, 141, 64, 20);
		panel.add(btnOk);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(176, 141, 64, 20);
		panel.add(btnClear);		
	}
}
