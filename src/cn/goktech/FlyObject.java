package cn.goktech;

import java.awt.image.BufferedImage;

public abstract class FlyObject {

	protected int x;// 英雄机所在的坐标
	protected int y;
	protected BufferedImage image;
	protected int width;
	protected int height;

	public abstract void move();

	public abstract boolean outOfBounds();

}
