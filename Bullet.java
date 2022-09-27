package com.sxt;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * @author ZHAOZONGZHI
 * @date   2022年9月26日
 */
public class Bullet extends GameObject{

	/**
	 * @param img
	 * @param x
	 * @param y
	 * @param gamepanel
	 */
	//大小
	public int width = 4;
	public int height = 4;
	//速度
	private int speed = 5;
	//方向
	Direction direction;
		
	public Bullet(String img, int x, int y ,GamePanel gamepanel, Direction direction) {
		super(img, x, y, gamepanel);
		// TODO Auto-generated constructor stub
		this.direction = direction;
	}
	public void go() {
		switch(direction) {
		case LEFT:
			leftward();
			break;
		case RIGHT:
			rightward();
			break;
		case UP:
			upward();
			break;
		case DOWN:
			downward();
			break;
		}
		this.hitWall();
		this.hitBase();
	}
	public void leftward() {
		x -= speed;
	}
	public void upward() {
		y -= speed;
	}
	public void rightward() {
		x += speed;
	}
	public void downward() {
		y += speed;
	}
	
	public void hitBot() {
		ArrayList<Bot> bots = this.gamePanel.botlist;
		for(Bot bot:bots) {
			if(this.gerRec().intersects(bot.gerRec())) {
				this.gamePanel.botlist.remove(bot);
				this.gamePanel.removelist.add(this);
				break;
			}
		}
	}
	public void hitWall() {
		ArrayList<Wall> walls = this.gamePanel.walllist;
		for(Wall wall:walls) {
			if(this.gerRec().intersects(wall.gerRec())) {
				this.gamePanel.walllist.remove(wall);
				this.gamePanel.removelist.add(this);
				break;
			}
		}
	}
	public void hitBase() {
		ArrayList<Base> bases = this.gamePanel.baselist;
		for(Base base:bases) {
			if(this.gerRec().intersects(base.gerRec())) {
				this.gamePanel.baselist.remove(base);
				this.gamePanel.removelist.add(this);
				break;
			}
		}
	}
	public void moveToBorder() {
		if(x<0||x+width>this.gamePanel.getWidth()) {
			this.gamePanel.removelist.add(this);
		}
		if(y<0||y+height>this.gamePanel.getHeight()) {
			this.gamePanel.removelist.add(this);
		}
	}
	@Override
	public void paintSelf(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img, x, y,null);
		this.go();
		this.hitBot();
	}
	@Override
	public Rectangle gerRec() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,width,height);
	}
}
