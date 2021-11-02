package pl.justmedia.trexframeworkgame.objectgame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Box extends Obstacle {
	private int posX;
	private int posY;
	private Random random;
	private Rectangle rect;
	BufferedImage boxImage;
	private MainCharacter mainCharacter;
	private boolean scored = false;
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(boxImage, posX,posY,null);
	}
	
	public Box(MainCharacter mainCharacter) {
		this.mainCharacter = mainCharacter;
		posX = 200;
		posY = 105;
		rect = new Rectangle();
		
	}
	@Override
	public void update() {
		posX -=2;
		rect.x = posX;
		rect.y = posY;
		rect.width = boxImage.getWidth();
		rect.height = boxImage.getHeight();
	}
	@Override
	public Rectangle getBound() {
		return rect;
	}
	@Override
	public boolean isOutOfScreen()
	{
			return (posX + boxImage.getWidth() < 0 );
	}
	@Override
	public void setX(int x) {
		this.posX = x;
	}
	@Override
	public int getX() {
		return this.posX;
	}
	@Override
	public void setY(int y) {
		this.posY = y;
	}
	@Override
	public int getY() {
		return this.posY;
	}

	public void setImage (BufferedImage image)
	{
		this.boxImage = image;
	}
	@Override
	public boolean isOver(){
		return (mainCharacter.getX() > posX);
	}

	@Override
	public boolean isScored(){
		return scored;
	}

	@Override
	public void setScored(){
		this.scored = true;
	}
}
