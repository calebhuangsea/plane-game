package game;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 敌机类
 * @author Administrator
 *
 */
public class EP {
	//图片
	BufferedImage img;
	//宽度
	int w;
	//高度
	int h;
	//x
	int x;
	//y
	int y;
	//速度
	int v;
	//随机函数
	Random ran = new Random();
	//血量
	int hp;
	
	//初始化
	public EP(){
		int i = ran.nextInt(15) + 1;
		String path = i < 10 ? "0" + i : i + "";
//		if(i < 10){
//			path = "0" + i;
//		}else{
//			path = i + "";
//		}	
		//初始化一架敌机
		img = Tool.getImg("../img/ep"+ path +".png");
		//初始化宽度
		w = img.getWidth();
		//初始化高度
		h = img.getHeight();
		//初始化x
		x = ran.nextInt(512 - w);
		//初始化y
		y = -h;
//		y = 100;
		//初始化速度
		if(i == 15){
			v = 30;
		}else{
			v = ran.nextInt(10) + 1;				
		}
		//初始化血量
		if(i <= 5){
			hp = 1;
		}else if(i > 5 && i <= 10){
			hp = 3;
		}else if(i > 10 && i <= 15){
			hp = 5;
		}
	}
	
	//移动的方法
	public void move(){
		y += v;
	}

}










