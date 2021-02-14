package objectgame;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Obstacle {
	public abstract Rectangle getBound();
	public abstract void draw(Graphics g);
	public abstract void update();
	public abstract boolean isOutOfScreen();
	public abstract int getX();
	public abstract int getY();
	public abstract void setX(int x);
	public abstract void setY(int y);
	public abstract boolean isOver();
	public abstract boolean isScored();
	public abstract void setScored();
	
}
