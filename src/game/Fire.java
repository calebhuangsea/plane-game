package game;

import java.awt.image.BufferedImage;

/**
 * �ӵ���
 * @author Administrator
 *
 */
public class Fire {
	//ͼƬ
	BufferedImage img;
	//x
	int x;
	//y
	int y;
	//���
	int w;
	//�߶�
	int h;
	//�ٶ�
	int v;
	//��ʼ��
	public Fire(Hero hero){
		//��ʼ��ͼƬ
		img = Tool.getImg("../img/fire.png");
		//���
		w = img.getWidth()/2;
		//�߶�
		h = img.getHeight()/2;
		//x
		x = hero.x - w/2;
		//y
		y = hero.y - hero.h/2 - h;
		//�ٶ�
		v = 10;
		
	}
	//�ӵ��ƶ��ķ���
	public void fireMove(){
		y -= v;
	}
	//�ӵ���л�������ײ
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









