package com.sxt;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * @author ZHAOZONGZHI
 * @date   2022年9月26日
 */
public class EnemyBullet extends Bullet{

	/**
	 * @param img
	 * @param x
	 * @param y
	 * @param gamepanel
	 * @param direction
	 */
	public EnemyBullet(String img, int x, int y, GamePanel gamepanel, Direction direction) {
		super(img, x, y, gamepanel, direction);
		// TODO Auto-generated constructor stub
	}

	public void hitPlayer() {
		ArrayList<Tank> players = this.gamePanel.playerlist;
		for(Tank player:players) {
			if(this.gerRec().intersects(player.gerRec())) {
				this.gamePanel.playerlist.remove(player);
				this.gamePanel.removelist.add(this);
				break;
			}
		}
	}
	public void paintSelf(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img, x, y,null);
		this.go();
		this.hitPlayer();
	}

	public Rectangle gerRec() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,width,height);
	}
}
