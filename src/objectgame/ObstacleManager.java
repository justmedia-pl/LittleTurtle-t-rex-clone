package objectgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.Resources;

public class ObstacleManager {
	private List<Obstacle> obstacles;
	private Random random;
	BufferedImage boxImage;
	private List<BufferedImage> boxImages = new ArrayList<BufferedImage>();
	float lastposX = 0.00f;
	private int obstacleDensity = 10;
	
	
	public ObstacleManager() {
		obstacles = new ArrayList<Obstacle>();
		boxImages.add(Resources.getResourceImage("data/images/box.png"));
		boxImages.add(Resources.getResourceImage("data/images/box.png"));
		boxImages.add(Resources.getResourceImage("data/images/box.png"));
		Box box = new Box();
		
		random = new Random();
		obstacles.add(getRandomBox((int)lastposX + random.nextInt(1000)+100*obstacleDensity));
		if (obstacles.size() > 0 && lastposX > 0.00f ) lastposX = obstacles.get(obstacles.size()).getX(); 
		
	}
	public void update(MainCharacter mainCharacter) {
		for (Obstacle o : obstacles) {
			o.update();
			if (o.getBound().intersects(mainCharacter.getBound())) {
				System.out.println("collision");
			}
		}
		Obstacle firstObstacle = obstacles.get(0);
		Random randomPos = new Random();
		
		if (obstacles.get(0).isOutOfScreen()) {
			obstacles.remove(firstObstacle);
			obstacles.add(getRandomBox((int)lastposX + (randomPos.nextInt(100)+60)*obstacleDensity));
		}
	}
	public void draw(Graphics g) {
		for (Obstacle o : obstacles) {
			o.draw(g);
		}
	}
	
	public Box getRandomBox(int posx) {
		Box box;
		box = new Box();
		box.setX(posx);
		random = new Random();
		boxImage = boxImages.get(random.nextInt(5*boxImages.size())%boxImages.size());	
		box.boxImage = boxImage;
		return box;
	}
}
