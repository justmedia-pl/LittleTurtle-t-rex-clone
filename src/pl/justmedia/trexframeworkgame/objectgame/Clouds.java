package pl.justmedia.trexframeworkgame.objectgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.justmedia.trexframeworkgame.util.Resources;

import static pl.justmedia.trexframeworkgame.userinterface.GameWindow.theme;

public class Clouds {
	
	private BufferedImage cloudImage;
	private List<Cloud> clouds;
	private List<BufferedImage> cloudImages = new ArrayList<BufferedImage>();
	private int cloudsDensity = 4;
	
	public Clouds()
	{
		
		cloudImages.add(Resources.getResourceImage("data/images/"+theme+"/paralax1.png"));
		cloudImages.add(Resources.getResourceImage("data/images/"+theme+"/paralax2.png"));
		cloudImages.add(Resources.getResourceImage("data/images/"+theme+"/paralax3.png"));
		cloudImages.add(Resources.getResourceImage("data/images/"+theme+"/paralax4.png"));



		Random randomShape = new Random();
		clouds = new ArrayList<Cloud>();
		float lastposX = 0.00f;
		float lastposY = 0.00f;
		int i;
		
		for (i=0 ;i <= cloudsDensity; i++)
		{
			int id = randomShape.nextInt(10*cloudImages.size())%cloudImages.size();
			cloudImage = cloudImages.get(id);
			if (clouds.size() > 0 && lastposX > 0.00f ) lastposX = clouds.get(clouds.size()).posX; 
			if (clouds.size() > 0 && lastposY > 0.00f) lastposY = clouds.get(clouds.size()).posY;
			clouds.add(new Cloud(lastposX,lastposY,cloudImage));
			System.out.println("new on initialize");
		}
	
	}
	public void update() {
		Random randomShape = new Random();
		float lastposX = 0.00f;
		float lastposY = 0.00f;
		
		//ranodmize top down ?
		for (Cloud cloud: clouds) {
			cloud.posX = cloud.posX -5;
		}
		
		
			Cloud firstCloud= clouds.get(0);
			
			if (clouds.size() > 0 && lastposX > 0.00f ) lastposX = clouds.get(clouds.size()).posX; 
			if (clouds.size() > 0 && lastposY > 0.00f) lastposY = clouds.get(clouds.size()).posY;
			
			if(firstCloud.posX + cloudImages.get(0).getWidth() < 0)
			{
				int i = randomShape.nextInt(5*cloudImages.size())%cloudImages.size();
				cloudImage = cloudImages.get(i);
				clouds.add(new Cloud(lastposX,lastposY,cloudImage));
				clouds.remove(0);
				System.out.println("new after remove");
			}
		
	}
	
	public void draw (Graphics g) {
		for (Cloud cloud : clouds) {
		g.drawImage(cloud.cloudImage,(int)cloud.posX, (int)cloud.posY, null);
		}
	}
	
	private class Cloud {
		
		BufferedImage cloudImage;
		float posX;
		float posY;
	
		public Cloud(float lastposX,float lastposY,BufferedImage cloudImage)
		{
			Random randomPos = new Random();
			this.posX = lastposX + randomPos.nextInt(1000)+100*cloudsDensity;
			//this.posY = lastposY + randomPos.nextInt(100);
			this.posY = lastposY;
			this.cloudImage = cloudImage;
			System.out.println(this.posX);
			
		}
	}
}
