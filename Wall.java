package com.sxt;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * @author ZHAOZONGZHI
 * @date   2022年9月26日
 */
public class Wall extends GameObject {

	/**
	 * @param img
	 * @param x
	 * @param y
	 * @param gamepanel
	 */
	int length = 50;
	public Wall(String img, int x, int y, GamePanel gamepanel) {
		super(img, x, y, gamepanel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paintSelf(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img, x, y, gamePanel);
	}

	@Override
	public Rectangle gerRec() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,length,length);
	}

}
