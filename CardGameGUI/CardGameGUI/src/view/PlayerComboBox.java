package view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class PlayerComboBox extends JComboBox<String>{

	private DefaultComboBoxModel<String> playerNames;
	
	/*
	 * Creates a ComboBox and ComboBoxModel storing all the player names
	 */
	public PlayerComboBox() {
		this.playerNames = new DefaultComboBoxModel<String>();
		setModel(playerNames);
	}
	
	// Adds the name to the ComboBoxModel and updates the ComboBox
	public void addPlayer(String name) {
		this.playerNames.addElement(name);
		update();
	}
	
	/*
	 *  Removes the name on the ComboBoxModel based on the index passed in
	 *  and updates the ComboBox
	 */
	public void removePlayer(int index) {
		playerNames.removeElementAt(index);
		update();
	}
	
	// Removes all the names on the ComboBoxModel and updates the ComboBox
	public void deleteAllNames() {
		playerNames.removeAllElements();
		update();
	}
	
	// Sets the combobox to display the same values on the ComboBoxModel.
	public void update() {
		setModel(this.playerNames);
	}
	
	// Returns the string value currently selected on the ComboBox
	public String getPlayerName() {
		return (String) getSelectedItem();
	}
	
}
