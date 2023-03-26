package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * 画板类
 * @author Administrator
 *
 */
public class GamePlean extends JPanel {
	//背景图片
	BufferedImage bg;
	//英雄机
	Hero hero;
	//敌机对象
//	EP ep;
	//飞机场
	List<EP> epList;
	//一颗子弹
//	Fire fire;
	//子弹夹
	List<Fire> fireList;
	//分数
	int score;
	//状态
	boolean gameOver;
	
	Backgrand bgimg;
	
	
	//初始化画板
	public GamePlean(){
		//设置一个背景色
		setBackground(Color.red);
		//初始化背景图片
		bg = Tool.getImg("../img/bg2.jpg");
		//初始化英雄机对象
		hero = new Hero();
		//初始化敌机
//		ep = new EP();
		//初始化飞机场
		epList = new ArrayList<EP>();
		//初始化一颗子弹
//		fire = new Fire(hero);
		//初始化子弹夹
		fireList = new ArrayList<Fire>();
		//初始化分数
		score = 0;
		//初始化状态
		gameOver = false;
		if(score <= 100){
			bgimg = new Backgrand();
		}else if(score > 100 && score <= 200){
			bgimg = new Backgrand();
		}
	}
	
	/**
	 * 画图方法
	 * g就相当于画笔
	 */
	public void paint(Graphics g) {
		//画背景图(图片，x,y,宽度，高度，null)
		
		g.drawImage(bgimg.bg1, bgimg.x1, bgimg.y1, null);
		g.drawImage(bgimg.bg2, bgimg.x2, bgimg.y2, null);
		
		//画英雄机
		g.drawImage(hero.img, hero.x-(hero.w/2),
				hero.y-(hero.h/2), hero.w, hero.h, null);
		//画敌机
		for(int i = 0; i < epList.size();i++){
			EP ep = epList.get(i);
			g.drawImage(ep.img, ep.x, ep.y, null);			
		}
		//画子弹
		for(int i = 0;i < fireList.size();i++){
			Fire fire = fireList.get(i);
			g.drawImage(fire.img, fire.x, fire.y, fire.w,
					fire.h, null);
		}
		//画分数
		Font font = new Font("楷体",Font.BOLD,28);
		g.setFont(font);
		g.setColor(Color.red);
		g.drawString("Score：" + score, 20, 50);
		//画生命值
		g.drawString("Live：" + hero.hp, 20, 80);
		//画游戏结束
		if(gameOver){
			Font font1 = new Font("楷体",Font.BOLD,50);
			g.setFont(font1);
			g.setColor(Color.red);
			g.drawString("GameOver", 160, 350);			
		}
	}
	
	//程序启动后的逻辑
	public void start(GameFram frame) {
//		MouseAdapter mad = new MouseAdapter() {
//			@Override
//			public void mouseMoved(MouseEvent e) {
//				super.mouseMoved(e);
//				int x = e.getX();
//				int y = e.getY();
//				hero.moveTo(x, y);
//			}
//		};
		
		
		//创建键盘监听器
		KeyAdapter adapter = new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				//监听按键
//				char ch = e.getKeyChar();
				int i = e.getKeyCode();
				if(i == e.VK_UP){
					//上移
					hero.moveUp();
//					System.out.println("上");
				}else if(i == e.VK_DOWN){
					//下移
					hero.moveDown();
//					System.out.println("下");
				}else if(i == e.VK_LEFT){
					//左移
					hero.moveLeft();
//					System.out.println("左");
				}else if(i == e.VK_RIGHT){
					//右移
					hero.moveRight();
//					System.out.println("右");
				}
				repaint();//刷新页面
//				System.out.println(i);
			}
			
		};
		//将键盘监听器添加到适配器中
		frame.addKeyListener(adapter);
		//启动线程
		myThread t = new myThread();
		Thread th = new Thread(t);
		th.start();
	}
	
	//制造敌机
	int index = 0;
	public void enterEP(){
		index++;
		//取余的值越大敌机出现频率越高
		if(index % 20 == 0){
			//将敌机添加到集合中
			epList.add(new EP());			
		}
//		if(index == 5){
//			index = 0;
//		}
	}
	
	//制造子弹
	int dir = 0;
	public void enterFire(){
		dir ++;
		if(dir % 10 == 0){
			fireList.add(new Fire(hero));			
		}
	}
	//背景移动
	public void bgMove(){
		
	}
	
	//游戏运行的线程
	class myThread implements Runnable{
		@Override
		public void run() {//将该线程要执行的程序写到run()中
			//让敌机移动
			while(true){
				bgimg.bgMove1();
				bgimg.bgMove2();
				//造子弹
				enterFire();
				//子弹移动
				for(int i = 0; i < fireList.size();i++){
					Fire fire = fireList.get(i);
					fire.fireMove();
					//若子弹飞出界面则移出
					if(fire.y <= -fire.h){
						fireList.remove(fire);
					}
					
				}
				//制造敌机
				enterEP();
				//让敌机起飞（每一架敌机飞）
				for(int i = 0;i < epList.size();i++){
					EP ep = epList.get(i);
					ep.move();
					//如果敌机移出底线则从集合中清除	
					if(ep.y >= 768){
						epList.remove(ep);
					}
				}
				
				//让每一架敌机和每一颗子弹发生碰撞
				for(int i = 0;i < fireList.size();i++){
					Fire fire = fireList.get(i);
					for(int m = 0;m < epList.size();m++){
						EP ep = epList.get(m);
						boolean boo = fire.bang(ep);
						if(boo){
							//子弹销毁
							fireList.remove(fire);
							//敌机血量减1
							ep.hp--;
							if(ep.hp <= 0){
								//销毁敌机
								epList.remove(ep);
								score += 10;
							}
						}
						//每一架敌机和英雄机碰撞
						boolean bo = hero.hit(ep);
						if(bo){
							ep.hp--;
							hero.hp--;
							if(ep.hp <= 0){
								//销毁敌机
								epList.remove(ep);
								score += 10;
							}
							if(hero.hp <= 0){
								//游戏结束
								//清空窗体内的飞行物
								epList.clear();
								fireList.clear();
								gameOver = true;
								return;
							}
						}
					}
				}
				
				try {
					//休眠30毫秒
					Thread.sleep(30);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				repaint();//刷新页面
			}	
		}		
	}
}










