package game;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Backgrand {
	BufferedImage bg1;
	BufferedImage bg2;
	int x1;
	int y1;
	int x2;
	int y2;
	Random ran = new Random();
	public Backgrand(){
		int i = ran.nextInt(5) + 1;
		bg1 = Tool.getImg("../img/bg"+ i +".jpg");
		bg2 = Tool.getImg("../img/bg"+ i +".jpg");
		x1 = 0;
		y1 = 0;
		x2 = 0;
		y2 = -768;
	}
	public void bgMove1(){
		y1++;
		if(y1 >= 768){
			y1 = -768;
		}
	}
	public void bgMove2(){
		y2++;
		if(y2 >= 768){
			y2 = -768;
		}
	}
	

}
