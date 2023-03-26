package game;

import javax.swing.JFrame;

/**
 * 窗体类
 * @author Administrator
 *
 */
public class GameFram extends JFrame {
	
	//初始化
	public GameFram(){
		//设置大小(宽度，高度)
		setSize(512,768);
		//设置标题
		setTitle("Plane Game");
		//设置居中
		setLocationRelativeTo(null);
		//设置窗口不可改变
		setResizable(false);
		//设置关闭窗口的同时自动终止程序
		setDefaultCloseOperation(
				JFrame.EXIT_ON_CLOSE);
		
	}
	
	//主程序的入口
	public static void main(String[] args) {
		//创建一个窗体
		GameFram fram = new GameFram();
		//创建一个画板
		GamePlean panel = new GamePlean();
		//将画板添加到窗体上
		fram.add(panel);
		//让窗口显示
		fram.setVisible(true);
		//程序开始运行
		panel.start(fram);
	}

}







