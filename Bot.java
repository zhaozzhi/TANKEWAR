package com.sxt;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

/**
 * @author ZHAOZONGZHI
 * @date   2022年9月26日
 */
public class Bot extends Tank{

	/**
	 * @param img
	 * @param x
	 * @param y
	 * @param gamepanel
	 * @param upImg
	 * @param leftImg
	 * @param rightImg
	 * @param downImg
	 */
	int moveTime = 0; 
	public Bot(String img, int x, int y, GamePanel gamepanel, String upImg, String leftImg, String rightImg,
			String downImg) {
		super(img, x, y, gamepanel, upImg, leftImg, rightImg, downImg);
		// TODO Auto-generated constructor stub
	}
	public Direction getRandomDirection() {
		Random random = new Random();
		int rnum = random.nextInt(4);
		switch (rnum) {
		case 0:
			return Direction.UP;
		case 1:
			return Direction.DOWN;
		case 2:
			return Direction.RIGHT;
		case 3:
			return Direction.LEFT;
		default:
			return null;
		}
	}
	public void go() {
		attack();
		if(moveTime>=20) {
			direction = this.getRandomDirection();
			moveTime = 0;
		}else {
			moveTime++;
		}
		switch (direction) {
		case UP:
			upward();
			break;
		case DOWN:
			downward();
			break;
		case LEFT:
			leftward();
			break;
		case RIGHT:
			rightward();
			break;
		default:
			break;
		}
	}
	public void attack() {
		Point p = getHeadPoint();
		Random random = new Random();
		int rnum = random.nextInt(100);
		if(rnum<4) {
			this.gamePanel.bulletlist.add(new EnemyBullet("images/bullet/bulletYellow.gif", p.x, p.y, this.gamePanel,direction));
		}
	}
	@Override
	public void paintSelf(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img, x, y, null);
		go();
	}

	@Override
	public Rectangle gerRec() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,width,height);
	}

}
