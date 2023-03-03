package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class PullDownMenu extends JMenuBar{
	
	private JMenu menu;

	/*
	 * Creates a JMenu with two JMenuItems - Add player and Exit options.
	 */
	public PullDownMenu() {
		this.menu = new JMenu("Menu");

		JMenuItem addPlayer = new JMenuItem("Add Player");
		JMenuItem exitGame = new JMenuItem("Exit");
		
		this.menu.add(addPlayer);
		this.menu.add(exitGame);
		this.menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(this.menu);
	}

	// Returns the JMenu
	public JMenu getMenu() {
		return this.menu;
	}

}
