package game;

import java.awt.image.BufferedImage;

/**
 * 英雄机类
 * @author Administrator
 *
 */
public class Hero {
    //英雄机图片
    BufferedImage img;
    //x坐标
    int x;
    //y坐标
    int y;
    //宽度
    int w;
    //高度
    int h;
    //血量
    int hp;
    //初始化
    public Hero() {
        //加载英雄机图片
        img = Tool.getImg("../img/My_plane.png");
        //初始化x
        x = 200;
        //初始化y
        y = 600;
        //获取图片的宽度
        w = img.getWidth();
        //获取图片的高度
        h = img.getHeight();
        //初始化血量
        hp = 8;
    }

    //上移方法
    public void moveUp() {
        y -= 10;
        if(y <= h/2){
            y = h/2;
        }
    }

    //下移
    public void moveDown() {
        y += 10;
        if(y >= 768 - h/2){
            y = 768 - h/2;
        }
    }

    //左移
    public void moveLeft() {
        x -= 10;
        if(x <= w/2){
            x = w/2;
        }
    }

    //右移
    public void moveRight() {
        x += 10;
        if(x >= 512 - w/2){
            x = 512 - w/2;
        }
    }

    //英雄机和敌机碰撞
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








