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
 * ������
 * @author Administrator
 *
 */
public class GamePlean extends JPanel {
	//����ͼƬ
	BufferedImage bg;
	//Ӣ�ۻ�
	Hero hero;
	//�л�����
//	EP ep;
	//�ɻ���
	List<EP> epList;
	//һ���ӵ�
//	Fire fire;
	//�ӵ���
	List<Fire> fireList;
	//����
	int score;
	//״̬
	boolean gameOver;
	
	Backgrand bgimg;
	
	
	//��ʼ������
	public GamePlean(){
		//����һ������ɫ
		setBackground(Color.red);
		//��ʼ������ͼƬ
		bg = Tool.getImg("../img/bg2.jpg");
		//��ʼ��Ӣ�ۻ�����
		hero = new Hero();
		//��ʼ���л�
//		ep = new EP();
		//��ʼ���ɻ���
		epList = new ArrayList<EP>();
		//��ʼ��һ���ӵ�
//		fire = new Fire(hero);
		//��ʼ���ӵ���
		fireList = new ArrayList<Fire>();
		//��ʼ������
		score = 0;
		//��ʼ��״̬
		gameOver = false;
		if(score <= 100){
			bgimg = new Backgrand();
		}else if(score > 100 && score <= 200){
			bgimg = new Backgrand();
		}
	}
	
	/**
	 * ��ͼ����
	 * g���൱�ڻ���
	 */
	public void paint(Graphics g) {
		//������ͼ(ͼƬ��x,y,��ȣ��߶ȣ�null)
		
		g.drawImage(bgimg.bg1, bgimg.x1, bgimg.y1, null);
		g.drawImage(bgimg.bg2, bgimg.x2, bgimg.y2, null);
		
		//��Ӣ�ۻ�
		g.drawImage(hero.img, hero.x-(hero.w/2),
				hero.y-(hero.h/2), hero.w, hero.h, null);
		//���л�
		for(int i = 0; i < epList.size();i++){
			EP ep = epList.get(i);
			g.drawImage(ep.img, ep.x, ep.y, null);			
		}
		//���ӵ�
		for(int i = 0;i < fireList.size();i++){
			Fire fire = fireList.get(i);
			g.drawImage(fire.img, fire.x, fire.y, fire.w,
					fire.h, null);
		}
		//������
		Font font = new Font("����",Font.BOLD,28);
		g.setFont(font);
		g.setColor(Color.red);
		g.drawString("Score��" + score, 20, 50);
		//������ֵ
		g.drawString("Live��" + hero.hp, 20, 80);
		//����Ϸ����
		if(gameOver){
			Font font1 = new Font("����",Font.BOLD,50);
			g.setFont(font1);
			g.setColor(Color.red);
			g.drawString("GameOver", 160, 350);			
		}
	}
	
	//������������߼�
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
		
		
		//�������̼�����
		KeyAdapter adapter = new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				//��������
//				char ch = e.getKeyChar();
				int i = e.getKeyCode();
				if(i == e.VK_UP){
					//����
					hero.moveUp();
//					System.out.println("��");
				}else if(i == e.VK_DOWN){
					//����
					hero.moveDown();
//					System.out.println("��");
				}else if(i == e.VK_LEFT){
					//����
					hero.moveLeft();
//					System.out.println("��");
				}else if(i == e.VK_RIGHT){
					//����
					hero.moveRight();
//					System.out.println("��");
				}
				repaint();//ˢ��ҳ��
//				System.out.println(i);
			}
			
		};
		//�����̼�������ӵ���������
		frame.addKeyListener(adapter);
		//�����߳�
		myThread t = new myThread();
		Thread th = new Thread(t);
		th.start();
	}
	
	//����л�
	int index = 0;
	public void enterEP(){
		index++;
		//ȡ���ֵԽ��л�����Ƶ��Խ��
		if(index % 20 == 0){
			//���л���ӵ�������
			epList.add(new EP());			
		}
//		if(index == 5){
//			index = 0;
//		}
	}
	
	//�����ӵ�
	int dir = 0;
	public void enterFire(){
		dir ++;
		if(dir % 10 == 0){
			fireList.add(new Fire(hero));			
		}
	}
	//�����ƶ�
	public void bgMove(){
		
	}
	
	//��Ϸ���е��߳�
	class myThread implements Runnable{
		@Override
		public void run() {//�����߳�Ҫִ�еĳ���д��run()��
			//�õл��ƶ�
			while(true){
				bgimg.bgMove1();
				bgimg.bgMove2();
				//���ӵ�
				enterFire();
				//�ӵ��ƶ�
				for(int i = 0; i < fireList.size();i++){
					Fire fire = fireList.get(i);
					fire.fireMove();
					//���ӵ��ɳ��������Ƴ�
					if(fire.y <= -fire.h){
						fireList.remove(fire);
					}
					
				}
				//����л�
				enterEP();
				//�õл���ɣ�ÿһ�ܵл��ɣ�
				for(int i = 0;i < epList.size();i++){
					EP ep = epList.get(i);
					ep.move();
					//����л��Ƴ�������Ӽ��������	
					if(ep.y >= 768){
						epList.remove(ep);
					}
				}
				
				//��ÿһ�ܵл���ÿһ���ӵ�������ײ
				for(int i = 0;i < fireList.size();i++){
					Fire fire = fireList.get(i);
					for(int m = 0;m < epList.size();m++){
						EP ep = epList.get(m);
						boolean boo = fire.bang(ep);
						if(boo){
							//�ӵ�����
							fireList.remove(fire);
							//�л�Ѫ����1
							ep.hp--;
							if(ep.hp <= 0){
								//���ٵл�
								epList.remove(ep);
								score += 10;
							}
						}
						//ÿһ�ܵл���Ӣ�ۻ���ײ
						boolean bo = hero.hit(ep);
						if(bo){
							ep.hp--;
							hero.hp--;
							if(ep.hp <= 0){
								//���ٵл�
								epList.remove(ep);
								score += 10;
							}
							if(hero.hp <= 0){
								//��Ϸ����
								//��մ����ڵķ�����
								epList.clear();
								fireList.clear();
								gameOver = true;
								return;
							}
						}
					}
				}
				
				try {
					//����30����
					Thread.sleep(30);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				repaint();//ˢ��ҳ��
			}	
		}		
	}
}










