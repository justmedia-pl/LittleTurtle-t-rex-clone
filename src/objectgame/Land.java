package objectgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.Resources;
import static userinterface.GameScreen.GROUNDY;
import static userinterface.GameWindow.theme;

public class Land {
	private List<ImageLand> listImage;
	private BufferedImage imageLand1, imageLand2, imageLand3;
	private Random random;
	private List<BufferedImage> landImages = new ArrayList<BufferedImage>();
	
	private class ImageLand {
		int posX;
		BufferedImage image;
	}
	
	public Land() {
		random = new Random();
		landImages.add(Resources.getResourceImage("data/images/"+theme+"/bg.jpg"));
		landImages.add(Resources.getResourceImage("data/images/"+theme+"/bg1.jpg"));
		landImages.add(Resources.getResourceImage("data/images/"+theme+"/bg2.jpg"));
		
		listImage = new ArrayList<ImageLand>();
		
		int landTitles = 600 / landImages.get(0).getWidth() + 2;
		for(int i=0;i < landTitles;i++) {
			ImageLand imageLand = new ImageLand();
			imageLand.image = getImageLand(landImages.size());
			imageLand.posX = (int) (i * imageLand.image.getWidth());
			listImage.add(imageLand);
		}
		
	}

	public void update() {
		for(ImageLand imageLand : listImage){
			imageLand.posX = imageLand.posX - 2;
			}
			ImageLand firstLand = listImage.get(0);
			if(firstLand.posX + firstLand.image.getWidth() < 0)
			{
				firstLand.posX = listImage.get(listImage.size()-1).posX + firstLand.image.getWidth();
				listImage.add(firstLand);
				listImage.remove(0);
			}
		
		
	}
	public void draw(Graphics g) {
		for(ImageLand imageLand :listImage) {
		g.drawImage(imageLand.image, imageLand.posX, 200 - imageLand.image.getHeight(),null);
		}
	}
	
	private BufferedImage getImageLand(int size) {
		int i = random.nextInt(5*size)%size;
		return landImages.get(i);
		}

}
	

