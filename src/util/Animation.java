package util;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
	private List<BufferedImage> frames = new ArrayList<BufferedImage>();
	private int frameIndex = 0;
	private int intervalTime;
	private long prevTime;
	
	public Animation(int intervalTime) {
		this.intervalTime = intervalTime;
		frames = new ArrayList<BufferedImage>();
	}
	
	public void update() {
			if (System.currentTimeMillis() - prevTime > intervalTime)
			{
			frameIndex++;
			if (frameIndex >= frames.size())
			{
				frameIndex = 0;
			}
			prevTime = System.currentTimeMillis();
		}
		
	}
	public void addFrame(BufferedImage frame) {
		frames.add(frame);
	}
	public BufferedImage getFrame() {
		if (frames.size() > 0)
		{
			return frames.get(frameIndex);
		}
		return null;
	}
}
