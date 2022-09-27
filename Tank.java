package com.sxt;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;

/**
 * @author ZHAOZONGZHI
 * @date   2022年9月25日
 */
public abstract class Tank extends GameObject {

	//尺寸
	public int width = 40;
	public int height = 50;
	//速度
	private int speed = 5;
	//方向
	public Direction direction = Direction.UP;
	//四个方向的图片
	private String upImg;
	private String leftImg;
	private String rightImg;
	private String downImg;
	//攻击冷却状态
	private boolean attackCoolDown = true;
	//攻击冷却时间毫秒间隔1000毫秒
	private int attackCoolDownTime = 500;
	
	public Tank (String img,int x,int y,GamePanel gamepanel,
			String upImg,String leftImg,String rightImg,String downImg) {
		super(img,x,y,gamepanel);
		this.upImg = upImg;
		this.leftImg = leftImg;
		this.rightImg = rightImg;
		this.downImg = downImg;
		
	}
	
	public void leftward() {
		if( !hitWall(x-speed,y)&&!moveToBorder(x-speed,y)) {
		x -= speed;
		}
		setImg(leftImg);
		direction = Direction.LEFT;
	}
	public void upward() {
		if( !hitWall(x,y-speed)&&!moveToBorder(x,y-speed)) { 
		y -= speed;
		}
		setImg(upImg);
		direction = Direction.UP;
	}
	public void rightward() {
		if( !hitWall(x+speed,y)&&!moveToBorder(x+speed,y)) { 
		x += speed;
		}
		setImg(rightImg);
		direction = Direction.RIGHT;
	}
	public void downward() {
		if( !hitWall(x,y+speed)&&!moveToBorder(x,y+speed)) {
		y += speed;
		}
		setImg(downImg);
		direction = Direction.DOWN;
	}
	public void setImg(String img) {
		this.img = Toolkit.getDefaultToolkit().getImage(img);
	}
	
	public boolean hitWall(int x,int y) {
		ArrayList<Wall> walls = this.gamePanel.walllist;
		Rectangle next = new Rectangle(x,y,width,height);
		for(Wall wall:walls) {
			if(next.intersects(wall.gerRec())) {
				return true;
			}
		}
		return false;
	}
	public boolean moveToBorder(int x,int y) {
		if(x<0) {
			return true;
		}else if(x+width>this.gamePanel.getWidth()) {
			return true;
		}else if(y<0) {
			return true;
		}else if(y+height>this.gamePanel.getHeight()) {
			return true;
		}
		return false;
	}
	public void attack() {
		if(attackCoolDown) {
		Point p = this.getHeadPoint();
		Bullet bullet = new Bullet("images/bullet/bulletGreen.gif", p.x, p.y, this.gamePanel,direction);
		this.gamePanel.bulletlist.add(bullet);
		//线程开始
		new AttackCD().start();
		}
	}
	//新线程
	class AttackCD extends Thread {
		public void run() {
			//将攻击功能设置为冷却状态
			attackCoolDown = false;
			//休眠1秒
			try {
				Thread.sleep(attackCoolDownTime);
			}catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			//解除冷却状态
			attackCoolDown = true;
			//线程终止
			this.stop();
		}
	}
	
	public Point getHeadPoint() {
		switch(direction) {
		case LEFT:
			return new Point(x,y+height/2);
		case RIGHT:
			return new Point(x+width,y+height/2);
		case UP:
			return new Point(x+width/2,y);
		case DOWN:
			return new Point(x+width/2,y+height);
			default:
				return null;
		}
	}
	
	@Override
	public abstract void paintSelf(Graphics g);
	@Override
	public abstract Rectangle gerRec();
}
