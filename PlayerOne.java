package com.sxt;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;


/**
 * @author ZHAOZONGZHI
 * @date   2022年9月25日
 */
public class PlayerOne extends Tank{

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
	
	public PlayerOne(String img, int x, int y, GamePanel gamepanel, String upImg, String leftImg, String rightImg,
			String downImg) {
		super(img, x, y, gamepanel, upImg, leftImg, rightImg, downImg);
		// TODO Auto-generated constructor stub
	}

	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_SPACE:
			attack();
			break;
		default:
			break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_W:
			up = false;
			break;
		default:
			break;
		}
	}
	
	public void move() {
		if(left) {
			leftward();
		}else if(right) {
			rightward();
		}else if(up) {
			upward();
		}else if(down) {
			downward();
		}
	}
	
	@Override
	public void paintSelf(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img,x,y,null);
		move();
	}

	@Override
	public Rectangle gerRec() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,width,height);
	}

}
