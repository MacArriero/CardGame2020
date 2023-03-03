package controller;

import model.interfaces.GameEngine;
import view.CardPanel;
import view.MainFrame;
import view.PlayerComboBox;
import view.PullDownMenu;
import view.StatusBar;
import view.SummaryPanel;
import view.ToolBar;

public class ControllerRegister {
	
	private GameEngine engine;
	private MainFrame mainFrame;
	
	private CardPanel cardPanel;
	private ToolBar toolBar;
	private PlayerComboBox playerCombobox;
	private PullDownMenu pullMenu;
	private StatusBar status;
	private SummaryPanel sumPanel;
	private int delay;
	
	// Creates register that will add listeners to the GUI components for event handling.
	public ControllerRegister(GameEngine engine, MainFrame mainFrame, int delay) {
		this.engine = engine;
		this.mainFrame = mainFrame;
		this.delay = delay;
		setGuiComponents();
	}
	
	// References all the GUI components 
	public void setGuiComponents() {
		this.cardPanel = this.mainFrame.getCardPanel();
		this.toolBar = this.mainFrame.getToolBar();
		this.playerCombobox = this.toolBar.getPlayerComboBox();
		this.pullMenu = this.mainFrame.getPullDownBar();
		this.status = this.mainFrame.getStatusBar();
		this.sumPanel = this.mainFrame.getSummaryPanel();
	}
	
	public void registerControllers() {
		// Adds an MenuController as an action listener for each menu item in the menu.
		for (int i = 0; i <= this.pullMenu.getMenuCount(); ++i) {
			this.pullMenu.getMenu().getItem(i).addActionListener(new MenuController(this.engine, this.sumPanel, this.toolBar, this.cardPanel));
		}

		// Adds a RemovePlayerController as an action listener on the remove player button.
		this.toolBar.getRemovePlayerButton().addActionListener(new RemovePlayerController(this.engine, this.toolBar, this.sumPanel, this.cardPanel));
		
		// Adds a PlayerNameComboBoxController as an action listener to the player combo box.
		this.toolBar.getPlayerComboBox().addActionListener(new PlayerNameComboBoxController(this.engine, this.toolBar, this.status, this.cardPanel, this.sumPanel));
		
		// Adds a BetController as an action listener for the bet button.
		this.toolBar.getBetButton().addActionListener(new BetController(this.engine, this.toolBar, this.sumPanel));
		
		// Adds a DealPlayerController as an action listener for the deal button.
		this.toolBar.getDealButton().addActionListener(new DealPlayerController(this.engine, this.delay, this.toolBar, this.status, this.cardPanel)); 
		
		// Adds a DealHouseController as property change listener for the status in the status bar.
		this.status.getStatus().addPropertyChangeListener(new DealHouseController(this.engine, this.delay, this.status, this.cardPanel));
		
		// Adds a ZeroPointsRemoverController as a property change listener for the status in the status bar.
		this.status.getStatus().addPropertyChangeListener(new ZeroPointsRemoverController(this.engine, this.sumPanel, this.cardPanel, this.toolBar));
	}
	
	// Returns the game engine
	public GameEngine getEngine() {
		return this.engine;
	}

	// Returns the Main Frame
	public MainFrame getMainFrame() {
		return this.mainFrame;
	}

	// Returns the Card Panel
	public CardPanel getCardPanel() {
		return this.cardPanel;
	}

	// Returns the Player combo box
	public PlayerComboBox getPlayerCombobox() {
		return this.playerCombobox;
	}

	// Returns the pull down menu
	public PullDownMenu getPullMenu() {
		return this.pullMenu;
	}

	// Returns the Status bar
	public StatusBar getStatus() {
		return this.status;
	}

	// Returns the Summary Panel
	public SummaryPanel getSumPanel() {
		return this.sumPanel;
	}

	// Returns the Tool Bar
	public ToolBar getToolBar() {
		return this.toolBar;
	}
	
}
