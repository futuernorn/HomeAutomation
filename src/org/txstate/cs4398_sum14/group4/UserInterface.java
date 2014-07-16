package org.txstate.cs4398_sum14.group4;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * From http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/TabbedPaneDemoProject/src/components/TabbedPaneDemo.java mostly
 * @author Jeffrey Hogan
 *
 */
public class UserInterface {

    private JPanel controlPanel;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JFrame mainWindow;

	public UserInterface() {
		mainWindow = new JFrame("Home Automation System");
		mainWindow.setSize(400,400);
		mainWindow.setLayout(new GridLayout(3, 1));
	      mainWindow.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });   
        
	      headerLabel = new JLabel("", JLabel.CENTER);        
	      statusLabel = new JLabel("",JLabel.CENTER);    

	      statusLabel.setSize(350,100);  
	      
        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon = createImageIcon("images/middle.gif");
        
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        SetupButtons();
//        JButton addBtn = new JButton("Add");
//        JButton removeBtn = new JButton("Remove");
        tabbedPane.addTab("Component Management", icon, controlPanel, "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        JComponent panel2 = makeTextPanel("Panel #2");
        tabbedPane.addTab("Tab 2", icon, panel2,
                "Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        JComponent panel3 = makeTextPanel("Panel #3");
        tabbedPane.addTab("Tab 3", icon, panel3,
                "Still does nothing");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        
        JComponent panel4 = makeTextPanel(
                "Panel #4 (has a preferred size of 410 x 50).");
        panel4.setPreferredSize(new Dimension(410, 50));
        tabbedPane.addTab("Tab 4", icon, panel4,
                "Does nothing at all");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        
        //Add the tabbed pane to this panel.
        mainWindow.add(tabbedPane);
        
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        mainWindow.add(headerLabel);
        mainWindow.add(statusLabel);
        mainWindow.setVisible(true);  
    }
	
	   private static ImageIcon createImageIcon(String path, 
			      String description) {
			      java.net.URL imgURL = UserInterface.class.getResource(path);
			      if (imgURL != null) {
			         return new ImageIcon(imgURL, description);
			      } else {            
			         System.err.println("Couldn't find file: " + path);
			         return null;
			      }
			   }   
	   
	   private void ShowAddPanel() {
		   final JPanel addPanel = new JPanel();
		   addPanel.setLayout(new FlowLayout());
		      JButton javaButton = new JButton("Submit");
		      JButton cancelButton = new JButton("Cancel");
		      cancelButton.setHorizontalTextPosition(SwingConstants.LEFT);   
		      javaButton.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {
			            statusLabel.setText("Submit Button clicked.");
			            mainWindow.remove(addPanel);
			         }
			      });

			      cancelButton.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {
			            statusLabel.setText("Cancel Button clicked.");
			            mainWindow.remove(addPanel);
			         }
			      });
			      
			      JTextField textField = new JTextField(20);
			      JLabel l = new JLabel();
			      l.setText("Component Name");
			      addPanel.add(l);
			      addPanel.add(textField);
			      addPanel.add(javaButton);
			      addPanel.add(cancelButton); 
			      mainWindow.add(addPanel);
	   }
	   private void SetupButtons(){

		 



		      JButton addButton = new JButton("Add");        
		
		      

		      addButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		            statusLabel.setText("Add Button clicked.");
		            ShowAddPanel();
		         }          
		      });



		      controlPanel.add(addButton);
      
 
		   }
		
    
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
    
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = UserInterface.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    


}