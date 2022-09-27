package com.sxt;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * @author ZHAOZONGZHI
 * @date   2022年9月25日
 */
public abstract class GameObject {

	//图片
	public Image img;
	//坐标
	public int x;
	public int y;
	//界面
	public GamePanel gamePanel;
	
	public GameObject(String img,int x,int y,GamePanel gamepanel) {
		this.img = Toolkit.getDefaultToolkit().getImage(img);
		this.x = x;
		this.y = y;
		this.gamePanel = gamepanel; 
	}
	public abstract void paintSelf(Graphics g);
	public abstract Rectangle gerRec();
}
