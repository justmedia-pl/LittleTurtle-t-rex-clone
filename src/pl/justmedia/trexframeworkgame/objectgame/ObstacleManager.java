package pl.justmedia.trexframeworkgame.objectgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.justmedia.trexframeworkgame.userinterface.GameScreen;
import pl.justmedia.trexframeworkgame.util.Resources;

import static pl.justmedia.trexframeworkgame.userinterface.GameWindow.theme;

public class ObstacleManager {
	private List<Obstacle> obstacles;
	private Random random;
	BufferedImage boxImage;
	private List<BufferedImage> boxImages = new ArrayList<BufferedImage>();
	float lastposX = 0.00f;
	private int obstacleDensity = 10;
	private MainCharacter mainCharacter;
	private GameScreen gameScreen;
	
	
	public ObstacleManager(MainCharacter mainCharacter, GameScreen gameScreen) {
		this.mainCharacter = mainCharacter;
		this.gameScreen = gameScreen;
		obstacles = new ArrayList<Obstacle>();
		boxImages.add(Resources.getResourceImage("data/images/"+theme+"/obstacle1.png"));
		boxImages.add(Resources.getResourceImage("data/images/"+theme+"/obstacle2.png"));

		Box box = new Box(mainCharacter);
		
		random = new Random();
		if (obstacles.size() > 0 && lastposX > 0.00f ) lastposX = obstacles.get(obstacles.size()).getX();
		obstacles.add(getRandomBox((int)lastposX + random.nextInt(1000)+100*obstacleDensity));

	}
	public void update(MainCharacter mainCharacter) {
		for (Obstacle o : obstacles) {
			o.update();
			if (o.isOver() && !(o.isScored())) {
				gameScreen.setScore(gameScreen.getScore()+10);
				o.setScored();
			}
			if (o.getBound().intersects(mainCharacter.getBound())) {
				System.out.println("collision");
				mainCharacter.isAlive(false);
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
	public void resetObstalcles(){
		obstacles.clear();
		obstacles.add(getRandomBox((int)lastposX + random.nextInt(1000)+100*obstacleDensity));
	}
	
	public Box getRandomBox(int posx) {
		Box box;
		box = new Box(mainCharacter);
		box.setX(posx);
		random = new Random();
		boxImage = boxImages.get(random.nextInt(5*boxImages.size())%boxImages.size());	
		box.boxImage = boxImage;
		return box;
	}
}
