package userinterface;

import static userinterface.GameScreen.GROUNDY;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import objectgame.Land;
import objectgame.MainCharacter;

public class GameScreen extends JPanel implements Runnable, KeyListener {
	
	private Thread thread;
	private Land land;
	public static final float GRAVITY = 0.1f;
	public static final float GROUNDY = 150;
	private MainCharacter mainCharacter;
	
	
	public GameScreen() {
		thread = new Thread(this);
		mainCharacter = new MainCharacter();
		mainCharacter.setX(50);
		land = new Land();
		
	}
	
	public void startGame() {
		thread.start();
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				mainCharacter.update();
				land.update();
				repaint(); //recall paint
				Thread.sleep(20); //slower down
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.red);
		g.drawLine(0,(int)GROUNDY, getWidth(), (int)GROUNDY);
		land.draw(g);
		mainCharacter.draw(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		mainCharacter.jump();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
