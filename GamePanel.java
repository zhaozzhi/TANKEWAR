package com.sxt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;


/**
 * @author ZHAOZONGZHI
 * @date   2022年9月25日
 */
public class GamePanel extends JFrame{
	//定义双缓存图片
	Image offStreemImage = null;
	//窗口长度
	int width = 800;
	int height = 610;
	 //物体集合
    //public List<Tank> tankList = new ArrayList<>();
	//指针图片
	Image select = Toolkit.getDefaultToolkit().getImage("images/selecttanke.png");
	//指针初始纵坐标
	int y = 150;
	//游戏模式 0 游戏未开始，1 单人模式，2 双人模式
	int state = 0;
	private boolean start = false;
	int a = 1;
	int count = 0;
	int enemyCount = 0;
	//游戏元素列表
	ArrayList<Bullet> bulletlist = new ArrayList<Bullet>();
	ArrayList<Bot> botlist = new ArrayList<Bot>();
	ArrayList<Bullet> removelist = new ArrayList<Bullet>();
	ArrayList<Tank> playerlist = new ArrayList<Tank>();
	ArrayList<Wall> walllist = new ArrayList<Wall>(); 
	ArrayList<Base> baselist = new ArrayList<Base>();
	//玩家1 
	PlayerOne playerOne = new PlayerOne("images/playerone/p1tankU.png",125, 510,this,
			"images/playerone/p1tankU.png","images/playerone/p1tankL.png", 
			"images/playerone/p1tankR.png", "images/playerone/p1tankD.png" );
	Base base = new Base("images/star.gif", 375, 560, this);
	//窗口的启动方法
	public void launch() {
	  //标题
		setTitle("坦克大战");
	  //窗口的初始长度
		setSize(width,height);
	  //使屏幕居中
		setLocationRelativeTo(null);
	  //添加关闭事件
		setDefaultCloseOperation(3);
	  //用户不能调整大小
		setResizable(false);
	  //使窗口可见
		setVisible(true);
	  //设置键盘监视器
		this.addKeyListener(new GamePanel.KeyMonitor());
	  //添加围墙
		for(int i = 0;i<14;i++) {
			walllist.add(new Wall("images/walls.gif", 50+i*50, 170, this));
		}
		walllist.add(new Wall("images/walls.gif", 305, 560, this));
		walllist.add(new Wall("images/walls.gif", 305, 500, this));
		walllist.add(new Wall("images/walls.gif", 365, 500, this));
		walllist.add(new Wall("images/walls.gif", 425, 500, this));
		walllist.add(new Wall("images/walls.gif", 425, 560, this));
		baselist.add(base);
	  //重绘
		while(true)
		{
			if(botlist.size()==0&&enemyCount==10) {
				state =5;
			}
			if((playerlist.size()==0&&(state==1||state==2))||baselist.size()==0) {
				state = 4;
			}
			Random random = new Random();
			int rnum = random.nextInt(800);
			if(count%100 == 1 && enemyCount <10 ) {
			//批量生成敌方坦克
				botlist.add(new Bot("images/enemy/enemy1U.gif", rnum, 110, this, "images/enemy/enemy1U.gif",
					"images/enemy/enemy1L.gif", "images/enemy/enemy1R.gif", "images/enemy/enemy1D.gif"));
				
				enemyCount++;
			}
			repaint();
			try {
				Thread.sleep(25);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	//paint方法
	@Override
	public void paint(Graphics g) {
		//创建和窗口一样大小的图片
		if(offStreemImage == null) {
			offStreemImage = this.createImage(width, height);
		}
		Graphics gImage = offStreemImage.getGraphics();
		//获得该图片的画布
		//设置画笔颜色
		gImage.setColor(Color.lightGray);
		//设置实心矩形
		gImage.fillRect(0,0,width,height);
		//改变画笔颜色
		gImage.setColor(Color.blue);
		//设置文字大小和样式
		gImage.setFont(new Font("仿宋",Font.BOLD,50));
		//state等于0，游戏未开始
		if(state ==0) {
		//添加文字
			gImage.drawString("选择游戏模式", 250, 200);
			gImage.drawString("单人模式", 300, 300);
			gImage.drawString("双人模式", 300, 400);
		//绘制指针
			gImage.drawImage(select,260,y+115,null);
		}
		//state等于1或2，游戏开始
		else if(state == 1||state ==2) {
			gImage.setFont(new Font("仿宋", Font.BOLD ,30));
			gImage.setColor(Color.red);
			gImage.drawString("剩余敌人数："+botlist.size(), 10, 60);
			//玩家
			for(Wall wall:walllist) {
				wall.paintSelf(gImage);
			}
			for(Tank player:playerlist) {
				player.paintSelf(gImage);
			}
			//子弹
			for(Bullet bullet:bulletlist) {
				bullet.paintSelf(gImage);
			}
			for(Base base:baselist) {
				base.paintSelf(gImage);
			}
			bulletlist.removeAll(removelist);
			//敌方坦克
			for(Bot bot:botlist) {
			bot.paintSelf(gImage);
			}
			count++;
		}	
		else if(state == 5) {
			gImage.drawString("小子，你胜利了！", 220, 300);
		}
		else if(state == 4)
		{
			gImage.drawString("小子，你真拉跨！", 220, 300);
		}
		g.drawImage(offStreemImage, 0, 0,null);
	}
	
	//键盘监视器
	private class KeyMonitor extends KeyAdapter{
		//按下键盘
		@Override 
		public void keyPressed(KeyEvent e) {
			//返回键值
			int key = e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_1:
				if(!start){
                    y = 150;
                    a = 1;
                }
				break;
			case KeyEvent.VK_2:
				if(!start){
                    y = 250;
                    a = 2;
                }
				break;
			case KeyEvent.VK_ENTER:
				//tankList.add(playerOne);
                state = a;
                start = true;
                playerlist.add(playerOne);
				break;
			default:
				playerOne.keyPressed(e);
				break;
			}
			
		}
		@Override 
		public void keyReleased(KeyEvent e) {
			playerOne.keyReleased(e);
		}
	}
	
	public static void main(String []args) {
		GamePanel gPanel = new GamePanel();
		gPanel.launch();
	}
}
