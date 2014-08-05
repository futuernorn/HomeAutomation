package org.cs4398_G4;

import org.cs4398_G4.examples.*;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class LoginPanel extends JPanel {

	private JPanel contentPane;
	private JTextField password;
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private LocalInterface view;


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
		textField_1 = new JTextField();
		panel.add(textField_1, "5, 3");
		textField_1.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		panel.add(lblPassword, "3, 5");
		
		//password text field
		password = new JPasswordField();
		panel.add(password, "5, 5");
		password.setColumns(10);
		
		JPanel loginButtonPanel = new JPanel();
		panel.add(loginButtonPanel, "5, 7, fill, fill");
		loginButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnOk = new JButton("OK");
		loginButtonPanel.add(btnOk);
		
		JButton btnClear = new JButton("Clear");
		loginButtonPanel.add(btnClear);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if (user.logOn(password.getText()))
//				{
					//create tabs after login here
					view.doLogin();
//				}
			}
		});
		
		final User user = new User();
		
		//new tabs
		
	}
}
