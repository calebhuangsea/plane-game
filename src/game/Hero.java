package game;

import java.awt.image.BufferedImage;

/**
 * Ӣ�ۻ���
 * @author Administrator
 *
 */
public class Hero {
    //Ӣ�ۻ�ͼƬ
    BufferedImage img;
    //x����
    int x;
    //y����
    int y;
    //���
    int w;
    //�߶�
    int h;
    //Ѫ��
    int hp;
    //��ʼ��
    public Hero() {
        //����Ӣ�ۻ�ͼƬ
        img = Tool.getImg("../img/My_plane.png");
        //��ʼ��x
        x = 200;
        //��ʼ��y
        y = 600;
        //��ȡͼƬ�Ŀ��
        w = img.getWidth();
        //��ȡͼƬ�ĸ߶�
        h = img.getHeight();
        //��ʼ��Ѫ��
        hp = 8;
    }

    //���Ʒ���
    public void moveUp() {
        y -= 10;
        if(y <= h/2){
            y = h/2;
        }
    }

    //����
    public void moveDown() {
        y += 10;
        if(y >= 768 - h/2){
            y = 768 - h/2;
        }
    }

    //����
    public void moveLeft() {
        x -= 10;
        if(x <= w/2){
            x = w/2;
        }
    }

    //����
    public void moveRight() {
        x += 10;
        if(x >= 512 - w/2){
            x = 512 - w/2;
        }
    }

    //Ӣ�ۻ��͵л���ײ
    public boolean hit(EP ep){
		/*
		 * x1 = hx-hw/2-dw
			x2 = hx+hw/2
			x1 <= dx <= x2
			y1 = hy+hh/2
			y2 = hy-hh/2-dh
			y2 <= dy <= y1
		 */

        return ep.x >= x - w/2 - ep.w
                && ep.x <= x + w/2
                && ep.y >= y - h/2 - ep.h
                && ep.y <= y + h/2;
    }


}








