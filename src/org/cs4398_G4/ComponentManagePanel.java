package org.cs4398_G4;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Jeffrey Hogan
 *Creates the User Interface tab for adding and removing a component to the system
 */
public class ComponentManagePanel extends JPanel {
	
	private JTextField nameTxt;
	private JComboBox inputCombo;
	private JComboBox outputCombo;
	private JList componentList;
	private DefaultListModel componentListModel;
	private LocalInterface view;
	private DefaultComboBoxModel inputPinComboModel;
	private DefaultComboBoxModel outputPinComboModel;
	private DefaultComboBoxModel roomComboModel;
	private JComboBox roomComboBox;
	private JComboBox typeComboBox;

	public ComponentManagePanel(LocalInterface view) {
		this.view = view;
		this.setLayout(new BorderLayout(0, 0));
		
		JPanel addRemoveBtnPanel = new JPanel();
		this.add(addRemoveBtnPanel, BorderLayout.NORTH);
		addRemoveBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAddComponent = new JButton("Add Component");
		addRemoveBtnPanel.add(btnAddComponent);
		
		JButton btnRemoveCompontent = new JButton("Remove Compontent");
		addRemoveBtnPanel.add(btnRemoveCompontent);
		
		JPanel addRemovePanel = new JPanel();
		this.add(addRemovePanel, BorderLayout.SOUTH);
		addRemovePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel addRemoveForm = new JPanel();
		addRemovePanel.add(addRemoveForm, BorderLayout.CENTER);
		addRemoveForm.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblName = new JLabel("Name");
		addRemoveForm.add(lblName, "2, 2, right, default");
		
		nameTxt = new JTextField();
		addRemoveForm.add(nameTxt, "4, 2, fill, top");
		nameTxt.setColumns(10);
		
		JLabel lblInputPin = new JLabel("Input Pin");
		addRemoveForm.add(lblInputPin, "2, 4, right, default");
		
		inputCombo = new JComboBox();
		addRemoveForm.add(inputCombo, "4, 4, fill, default");
		
		JLabel lblOutputPin = new JLabel("Output Pin");
		addRemoveForm.add(lblOutputPin, "2, 6, right, default");
		
		outputCombo = new JComboBox();
		addRemoveForm.add(outputCombo, "4, 6, fill, default");
		
		JLabel lblRoom = new JLabel("Room");
		addRemoveForm.add(lblRoom, "2, 8, right, default");
		
		roomComboBox = new JComboBox();
		addRemoveForm.add(roomComboBox, "4, 8, fill, default");
		
		JLabel lblType = new JLabel("Type");
		addRemoveForm.add(lblType, "2, 10, right, default");
		
		typeComboBox = new JComboBox();
		addRemoveForm.add(typeComboBox, "4, 10, fill, default");
		
		JPanel addRemoveFormBtnPanel = new JPanel();
		addRemovePanel.add(addRemoveFormBtnPanel, BorderLayout.SOUTH);
		addRemoveFormBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSubmit = new JButton("Submit");
		addRemoveFormBtnPanel.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		addRemoveFormBtnPanel.add(btnCancel);
		
		JLabel lblAddComponent = new JLabel("Add Component");
		addRemovePanel.add(lblAddComponent, BorderLayout.NORTH);
		
		JPanel addComponentListPanel = new JPanel();
		this.add(addComponentListPanel, BorderLayout.CENTER);
		GridBagLayout gbl_addComponentListPanel = new GridBagLayout();
		gbl_addComponentListPanel.columnWidths = new int[]{225, 0};
		gbl_addComponentListPanel.rowHeights = new int[]{1, 0};
		gbl_addComponentListPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_addComponentListPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		addComponentListPanel.setLayout(gbl_addComponentListPanel);
		
		componentList = new JList();
		GridBagConstraints gbc_componentList = new GridBagConstraints();
		gbc_componentList.fill = GridBagConstraints.BOTH;
		gbc_componentList.gridx = 0;
		gbc_componentList.gridy = 0;
		addComponentListPanel.add(componentList, gbc_componentList);
		
		FillFields();
	}
	
	public void FillFields() {
		Object[] avaiablePins = view.getController().getAvailablePins().toArray();
//		String[] rooms = view.getController().getRooms().toArray();
		inputPinComboModel = new DefaultComboBoxModel(avaiablePins);
		outputPinComboModel = new DefaultComboBoxModel(avaiablePins);
		roomComboModel = new DefaultComboBoxModel(view.getController().getRooms().toArray());
		inputCombo.setModel(inputPinComboModel);
		outputCombo.setModel(outputPinComboModel);
		roomComboBox.setModel(roomComboModel);
		componentListModel = new DefaultListModel();
		componentList.setModel(componentListModel);
	}

}
