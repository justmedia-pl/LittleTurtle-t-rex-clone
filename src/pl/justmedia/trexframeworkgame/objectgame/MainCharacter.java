package pl.justmedia.trexframeworkgame.objectgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import pl.justmedia.trexframeworkgame.util.Animation;
import pl.justmedia.trexframeworkgame.util.Resources;

import static pl.justmedia.trexframeworkgame.userinterface.GameScreen.GRAVITY;
import static pl.justmedia.trexframeworkgame.userinterface.GameScreen.GROUNDY;
import static pl.justmedia.trexframeworkgame.userinterface.GameWindow.theme;

public class MainCharacter {
	private float x = 0;
	private float y = 0;
	private float speedY = 0;
	private Animation characterRun;
	private Rectangle rect;
	private boolean isAlive = true;
	
	public MainCharacter() {

		characterRun = new Animation(200);
		characterRun.addFrame(Resources.getResourceImage("data/images/"+theme+"/ch_1.png"));
		characterRun.addFrame(Resources.getResourceImage("data/images/"+theme+"/ch_2.png"));
		characterRun.addFrame(Resources.getResourceImage("data/images/"+theme+"/ch_3.png"));
		characterRun.addFrame(Resources.getResourceImage("data/images/"+theme+"/ch_4.png"));

		rect = new Rectangle();

		
	}
	
	public void update() {
		characterRun.update();
		if (y >= GROUNDY - characterRun.getFrame().getHeight()) {
			speedY = 0;
			y = GROUNDY - characterRun.getFrame().getHeight();
			
		} else {
		speedY += GRAVITY;
		y+=speedY;
		}
		rect.x = (int) x;
		rect.y = (int) y;
		rect.width = characterRun.getFrame().getWidth();
		rect.height = characterRun.getFrame().getHeight();
	}
	public Rectangle getBound()	{
		return rect;
	}
	
	public void draw(Graphics g) {
		if (isAlive){
		g.setColor(Color.BLACK);
		g.drawImage(characterRun.getFrame(), (int)x,(int)y, null);
		}  else {
			setY(GROUNDY-Resources.getResourceImage("data/images/"+theme+"/ch_rip.png").getHeight());
			g.drawImage(Resources.getResourceImage("data/images/"+theme+"/ch_rip.png"),(int)x,(int)y, null);
		}
	}
	public void jump() {
		speedY = -4;
		y += speedY;

		}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getSpeedY() {
		return speedY;
	}
	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}
	public boolean isAlive() {return isAlive;}
	public void isAlive(boolean all) { isAlive = all;}
	
	
	
}
