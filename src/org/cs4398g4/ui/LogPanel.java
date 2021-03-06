package org.cs4398g4.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.cs4398g4.LogEntry;

public class LogPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4083579619628657921L;
	LocalInterface view;
	JScrollPane logScrollPane;
	JLabel lblSystemLog;
	JTextArea logTextArea;

	public LogPanel(LocalInterface view) {
		this.view = view;
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				refreshLogText();
			}

			public void ancestorRemoved(AncestorEvent event) {
			}

			public void ancestorMoved(AncestorEvent event) {
			}

		});
		setLayout(new BorderLayout(0, 0));

		 setPreferredSize(new Dimension(600, 125));
		

		lblSystemLog = new JLabel("System Log");
		logTextArea = new JTextArea(5, 60);
//		 logTextArea.setPreferredSize(new Dimension(600,70));

		logScrollPane = new JScrollPane(logTextArea);

		add(lblSystemLog, BorderLayout.NORTH);
		add(logScrollPane, BorderLayout.CENTER);

	}

	public void refreshLogText() {
		// TODO Auto-generated method stub
		logTextArea.setText("");
		for (LogEntry logEntry : view.getBaseStation().getLog().getLogEntries()) {
			logTextArea.append(logEntry.toString() + "\n");
		}

	}
}
