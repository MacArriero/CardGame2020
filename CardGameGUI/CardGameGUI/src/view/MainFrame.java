package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	

	private CardPanel cardPanel;
	private SummaryPanel sumPanel;
	private ToolBar toolBar;
	private StatusBar statusBar;
	private PullDownMenu pdBar;
	private Dimension minimumDimension;
	
	/*
	 * Creates a resizable frame and all the components for the Card Game Graphical User Interface (GUI),
	 * including the summary panel, card panel, tool bar, pull down menu and status bar.
	 */
	public MainFrame() {	
		super("Card Game");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.minimumDimension = new Dimension(screenSize.width /2, screenSize.height /2);
		setSize(this.minimumDimension);
		setMinimumSize(new Dimension(this.minimumDimension));
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		this.sumPanel = new SummaryPanel();
		this.cardPanel = new CardPanel();
		this.toolBar = new ToolBar();
		this.pdBar = new PullDownMenu();
		this.statusBar = new StatusBar("Waiting for user to perform an Action");
		
		addFrameItems();
		
	}
	
	// Adds all the GUI components to the frame
	public void addFrameItems() {
		
		setJMenuBar(pdBar);
		add(cardPanel, BorderLayout.CENTER);
		add(sumPanel, BorderLayout.EAST);
		add(toolBar, BorderLayout.NORTH);
		add(statusBar, BorderLayout.SOUTH);
	}
	
	// Returns the Card Panel
	public CardPanel getCardPanel() {
		return this.cardPanel;
	}
	
	// Returns the Summary Panel
	public SummaryPanel getSummaryPanel() {
		return this.sumPanel;
	}
	
	// Returns the Tool bar
	public ToolBar getToolBar() {
		return this.toolBar;
	}

	// Returns the Status Bar
	public StatusBar getStatusBar() {
		return this.statusBar;
	}
	
	// Returns the Pull Down Bar
	public PullDownMenu getPullDownBar() {
		return this.pdBar;
	}
	
	
}
