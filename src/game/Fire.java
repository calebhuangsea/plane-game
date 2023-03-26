package game;

import java.awt.image.BufferedImage;

/**
 * 子弹类
 * @author Administrator
 *
 */
public class Fire {
	//图片
	BufferedImage img;
	//x
	int x;
	//y
	int y;
	//宽度
	int w;
	//高度
	int h;
	//速度
	int v;
	//初始化
	public Fire(Hero hero){
		//初始化图片
		img = Tool.getImg("../img/fire.png");
		//宽度
		w = img.getWidth()/2;
		//高度
		h = img.getHeight()/2;
		//x
		x = hero.x - w/2;
		//y
		y = hero.y - hero.h/2 - h;
		//速度
		v = 10;
		
	}
	//子弹移动的方法
	public void fireMove(){
		y -= v;
	}
	//子弹与敌机发生碰撞
	public boolean bang(EP ep){
		/*
		 * x1 = dx-zw
			x2 = dx+dw
			x1<x<x2
			y1 = dy+dh 
			y2 = dy-zh
			y2<y<y1
		 */
		
		return ep.x-w <= x 
				&& x <= ep.x + ep.w
				&& y >= ep.y - h
				&& y <= ep.y + ep.h;
	}
	
	

}









