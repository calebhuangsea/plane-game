package game;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * �л���
 * @author Administrator
 *
 */
public class EP {
	//ͼƬ
	BufferedImage img;
	//���
	int w;
	//�߶�
	int h;
	//x
	int x;
	//y
	int y;
	//�ٶ�
	int v;
	//�������
	Random ran = new Random();
	//Ѫ��
	int hp;
	
	//��ʼ��
	public EP(){
		int i = ran.nextInt(15) + 1;
		String path = i < 10 ? "0" + i : i + "";
//		if(i < 10){
//			path = "0" + i;
//		}else{
//			path = i + "";
//		}	
		//��ʼ��һ�ܵл�
		img = Tool.getImg("../img/ep"+ path +".png");
		//��ʼ�����
		w = img.getWidth();
		//��ʼ���߶�
		h = img.getHeight();
		//��ʼ��x
		x = ran.nextInt(512 - w);
		//��ʼ��y
		y = -h;
//		y = 100;
		//��ʼ���ٶ�
		if(i == 15){
			v = 30;
		}else{
			v = ran.nextInt(10) + 1;				
		}
		//��ʼ��Ѫ��
		if(i <= 5){
			hp = 1;
		}else if(i > 5 && i <= 10){
			hp = 3;
		}else if(i > 10 && i <= 15){
			hp = 5;
		}
	}
	
	//�ƶ��ķ���
	public void move(){
		y += v;
	}

}










