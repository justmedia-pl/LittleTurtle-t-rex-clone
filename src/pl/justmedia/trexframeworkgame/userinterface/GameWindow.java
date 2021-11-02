package pl.justmedia.trexframeworkgame.userinterface;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	public static String theme = "turtle";
	private GameScreen gameScreen;
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}
	
	public GameWindow() {
		super("Justmedia * SIMPLE GAME *");
		setSize(583,215);
		setLocation(600,400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameScreen = new GameScreen();
		add(gameScreen);
		addKeyListener(gameScreen);
	}
	
	public void startGame() {
		gameScreen.startGame();
	}
	

	

}
