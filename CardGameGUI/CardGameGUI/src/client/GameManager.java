package client;

import javax.swing.SwingUtilities;

import controller.ControllerRegister;
import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;
import view.MainFrame;

// Initiates and runs the program for the Card Game.
public class GameManager {

	public static void main(String[] args) {
		GameEngine engine = new GameEngineImpl();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame frame = new MainFrame();
				ControllerRegister register = new ControllerRegister(engine, frame, 100);
				frame.addFrameItems();
				engine.addGameEngineCallback(new GameEngineCallbackImpl());
				engine.addGameEngineCallback(new GameEngineCallbackGUI(frame.getSummaryPanel(), frame.getCardPanel(), frame.getStatusBar(), frame.getToolBar()));
				register.registerControllers();
			}
		});

	}
}

