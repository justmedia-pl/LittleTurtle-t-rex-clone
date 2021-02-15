

package userinterface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GameWindow extends JFrame {
	public static String theme = "turtle";
	//public static String theme = "knight";
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
	
	public static void main(String args[]) {
		GameWindow gw = new GameWindow();
		gw.setVisible(true);
		gw.startGame();
	}
	

}
