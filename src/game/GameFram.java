package game;

import javax.swing.JFrame;

/**
 * ������
 * @author Administrator
 *
 */
public class GameFram extends JFrame {
	
	//��ʼ��
	public GameFram(){
		//���ô�С(��ȣ��߶�)
		setSize(512,768);
		//���ñ���
		setTitle("Plane Game");
		//���þ���
		setLocationRelativeTo(null);
		//���ô��ڲ��ɸı�
		setResizable(false);
		//���ùرմ��ڵ�ͬʱ�Զ���ֹ����
		setDefaultCloseOperation(
				JFrame.EXIT_ON_CLOSE);
		
	}
	
	//����������
	public static void main(String[] args) {
		//����һ������
		GameFram fram = new GameFram();
		//����һ������
		GamePlean panel = new GamePlean();
		//��������ӵ�������
		fram.add(panel);
		//�ô�����ʾ
		fram.setVisible(true);
		//����ʼ����
		panel.start(fram);
	}

}







