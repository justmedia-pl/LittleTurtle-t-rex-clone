package userinterface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GameWindow extends JFrame {

	public GameWindow() {
		super("Dino Game");
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]) {
		new GameWindow().setVisible(true);
	}
	
	public void paint(Graphics g) {
		BufferedImage image = null;
		super.paint(g);
		try {
			image = ImageIO.read(new File("data/images/box.png"));
			g.drawImage(image, 100, 100,null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
