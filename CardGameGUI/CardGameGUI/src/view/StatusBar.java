package view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class StatusBar extends JPanel{

	private JLabel status;
	
	// Creates a status bar with a single JLabel to display the status
	public StatusBar(String initialStatus) {
		
		setLayout(new BorderLayout());
		Border border = BorderFactory.createTitledBorder("Status");
		setBorder(border);

		setLayout(new BorderLayout());
		this.status = new JLabel(initialStatus,JLabel.CENTER);
		this.add(status);
		
	}
	
	/*
	 *  Changes the text on the JLabel displaying the status,
	 *  to the string passed in.
	 */
	public void setStatus(String status) {
		this.status.setText(status);
	}
	
	// Returns the JLabel that displays the status
	public JLabel getStatus() {
		return this.status;
	}
}

