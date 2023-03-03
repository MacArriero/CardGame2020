package view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class SummaryPanel extends JPanel{
	
	private JTable summary;
	private DefaultTableModel playersInfo;
	private static final int playerNameColumnIndex = 0;
	private static final int playerPointsColumnIndex = 1;
	private static final int betColumnIndex = 2;
	private static final int resultColumnIndex = 3;
	private static final int historyIndex = 4;
	private static final String headers[] = {"Name", "Points(P)" , "Bet", "Result(R)", "History"};
	
	
	/*
	 *  Creates a Summary panel with a scrollable table and a model table
	 *  where the player information are stored
	 */
	public SummaryPanel() {
		
		setLayout(new BorderLayout());
		Border border = BorderFactory.createTitledBorder("Summary");
		setBorder(border);		
		
		this.playersInfo = new DefaultTableModel(headers, 0);
		this.summary = new JTable(playersInfo);
		JScrollPane scroller = new JScrollPane(this.summary);
		add(scroller, BorderLayout.CENTER);
		scroller.setCorner(JScrollPane.UPPER_RIGHT_CORNER, null);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		summary.setEnabled(false);
		
		
	}

	// Adds the passed in player information into the model table, based row passed in.
	public void addPlayerInfo(int rowNum, String[] newPlayerInfo) {
		this.playersInfo.insertRow(rowNum, newPlayerInfo);
		refreshSummary();
	}
	
	// Deletes the row passed in, on the model table.
	public void removePlayerInfo(int row) {
		this.playersInfo.removeRow(row);
		refreshSummary();
	}
	
	// Deletes all the rows on the model table
	public void deleteAllPlayerInfo() {
		int numOfRows = this.playersInfo.getRowCount();
		if (numOfRows > 0) {
			for (int i = numOfRows - 1; i >= 0; --i) {
				playersInfo.removeRow(i);
			}
		}
		this.summary.setModel(playersInfo);
	}
	
	// Sets visible table on the GUI to display the model table
	public void refreshSummary() {
		this.summary.setModel(playersInfo);
	}
	
	/*
	 *  Changes the bet information to the string passed in, 
	 *  based on the row passed in.
	 */
	public void updateBet(int row, String bet) {
		this.playersInfo.setValueAt(bet, row, betColumnIndex);
	}
	
	/*
	 *  Changes the result information to the string passed in,
	 *  based on the row passed in.
	 */
	public void updateResult(int row, String result) {
		this.playersInfo.setValueAt(result, row, resultColumnIndex);
	}
	
	/*
	 * Returns the string from the results column on the model table,
	 * based on the row passed in.
	 */
	public String getPlayerResult(int row) {
		return (String) playersInfo.getValueAt(row, resultColumnIndex);
	}
	
	// Returns the table model, with the current player information.
	public DefaultTableModel getAllPlayersInfo() {
		return this.playersInfo;
	}
	
	// Changes all the String value on the bet column to "0".
	public void resetAllBets() {
		String zeroBetString = "0";
		for (int row = 0; row < getTableNumRows(); ++row) {
			this.playersInfo.setValueAt(zeroBetString, row, betColumnIndex);
		}
		
	}
	
	/*
	 *  Changes the cell value on the history column, to the string passed in,
	 *  based on the row passed in.
	 */
	public void updateHistory(int row, String outcome) {
		this.playersInfo.setValueAt(outcome, row, historyIndex);
	}
	
	/*
	 *  Changes the cell value on the points column, to the string passed in,
	 *  based on the row passed in.
	 */
	public void updatePlayerPoints(int row, String points) {
		this.playersInfo.setValueAt(points, row, playerPointsColumnIndex);
	}
	
	// Changes all the String value on the result column to "0".
	public void resetAllResults() {
		String zeroResultString = "0";
		for (int row = 0; row < getTableNumRows(); ++row) {
			this.playersInfo.setValueAt(zeroResultString, row, resultColumnIndex);
		}
	}
	
	// Returns the number of rows on the results column with a value greater than zero.
	public int getNumOfPlayersDealt() {
		int playersDealt = 0;
		for (int i = 0; i < getAllPlayersInfo().getRowCount(); ++i) {
			if (Integer.valueOf(getPlayerResult(i)) > 0) {
				++playersDealt;
			}
		}
		return playersDealt;
	}
	
	// Returns cell value on the points column, based on the row value passed in.
	public String getPlayerPoints(int row) {
		return (String) playersInfo.getValueAt(row, playerPointsColumnIndex);
	}
	
	// Return cell value on the player name column, based on the row value passed in.
	public String getPlayerName(int row) {
		return (String) playersInfo.getValueAt(row, playerNameColumnIndex);
	}
	
	// Returns the Summary table
	public JTable getSummaryTable() {
		return this.summary;
	}
	
	// Returns the number of rows on the model table
	public int getTableNumRows() {
		return this.playersInfo.getRowCount();
	}
	
	// Returns the player points column index
	public int getPointsColumnIndex() {
		return playerPointsColumnIndex;
	}

}
