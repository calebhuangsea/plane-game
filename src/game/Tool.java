package game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * get image
 * @author Caleb
 *
 */
public class Tool {
	// get image
	public static BufferedImage getImg(String path){
		BufferedImage img = null;
		try {
			img = ImageIO.read(
							Tool.class.getResource(path));	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

}








