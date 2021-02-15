package userinterface;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

import objectgame.*;
import util.Resources;

public class GameScreen extends JPanel implements Runnable, KeyListener {


	public static final int GAME_FISRST_STATE = 0;
	public static final int GAME_PLAY_STATE = 1;
	public static final int GAME_OVER_STATE = 2;
	public static final float GRAVITY = 0.1f;
	public static final float GROUNDY = 150;
	private MainCharacter mainCharacter;
	private ObstacleManager obstacleManager;
	private Resources imageGameOverText;

	private int gameState = GAME_FISRST_STATE;
	private Thread thread;
	private Land land;
	private Clouds clouds;
	private int score = 0;
	private Sound jumpfx = new Sound("data/sound/jump.wav");
	private Sound ripfx = new Sound("data/sound/rip.wav");
	private Sound bgMusic = new Sound("data/sound/background2.wav");
	private Sound menuMusic = new Sound("data/sound/menuloop.wav");


		public GameScreen() {
		thread = new Thread(this);
		mainCharacter = new MainCharacter();
		mainCharacter.setX(50);
		mainCharacter.setY(70);
		land = new Land();
		clouds = new Clouds();
		obstacleManager = new ObstacleManager(mainCharacter,this);

		//game over text
		//imageGameOverText = Resources.getResourceImage("data/...");
	}
	
	public void startGame() {
		thread.start();
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				update();
				repaint(); //recall paint
				Thread.sleep(20); //slower down
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void update(){
		switch (gameState){
			case GAME_FISRST_STATE:
				score = 0;
				break;
			case GAME_PLAY_STATE:
				if (!mainCharacter.isAlive()) {
					gameState = GAME_OVER_STATE;
					ripfx.play(-50);
				}
				mainCharacter.update();
				land.update();
				clouds.update();
				obstacleManager.update(mainCharacter);
				break;
			case GAME_OVER_STATE:
				break;
		}
		//System.out.println(gameState);
	}
	@Override
	public void paint(Graphics g) {
		g.fillRect(0, 0, getWidth(), getHeight());
		switch (gameState) {
			case GAME_FISRST_STATE:
				g.drawImage(Resources.getResourceImage("data/images/"+ GameWindow.theme+"/menu.png"),0,0,g.getClipBounds().width,g.getClipBounds().height,null);
				ripfx.stop();
				menuMusic.loop(-1);
				break;
			case GAME_PLAY_STATE:
				menuMusic.stop();
				land.draw(g);
				mainCharacter.draw(g);
				obstacleManager.draw(g);
				clouds.draw(g);
				g.setColor(Color.WHITE);
				g.drawString("HI "+String.valueOf(score),500,20);
				bgMusic.play(-50);
				break;
			case GAME_OVER_STATE:
				bgMusic.stop();
				land.draw(g);
				mainCharacter.draw(g);
				clouds.draw(g);
				g.drawImage(Resources.getResourceImage("data/images/"+ GameWindow.theme+"/game_over.png"),0,0,g.getClipBounds().width,g.getClipBounds().height,null);
				break;

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		mainCharacter.jump();
		jumpfx.play(100);
		
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	public void resetGame()
	{
		mainCharacter.isAlive(true);
		mainCharacter.setX(50);
		mainCharacter.setY(70);
		obstacleManager.resetObstalcles();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()){
			case KeyEvent.VK_SPACE:
			if (gameState == GAME_FISRST_STATE) gameState = GAME_PLAY_STATE;
				if (gameState == GAME_OVER_STATE) {
					resetGame();
					gameState = GAME_FISRST_STATE;
				}
			break;

		}
	}


}
