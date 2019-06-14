package cn.goktech;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Hero extends FlyObject {
	int life;
	int score;
	int doubleFire;
	BufferedImage[] images;// 存放英雄机的两张图片
	int index;// 让其0、1来回切换，根据0或者1获取数组images的图片进行显示

	public Hero() {
		this.x = 170;
		this.y = 400;
		this.image = ShootGame.hero0;// 游戏开始前画面显示英雄机的图片
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.life = 3;
		this.score = 0;
		this.images = new BufferedImage[] { ShootGame.hero0, ShootGame.hero1 };
		this.index = 0;
		this.doubleFire = 0;
	}

	@Override
	public void move() {
		// 实现两张图片切换的效果
		index++;
		// 更改当前飞机的图片
		this.image = images[index % 2];

	}

	public List<Bullet> shutBullet() {
		List<Bullet> list = new ArrayList<Bullet>();
		// 判断英雄当前的火力值是否可以发射双发，
		// 获取英雄的1/4宽度
		int tempWidth = this.width / 4;
		// 判断火力值
		if (this.doubleFire > 0) {
			Bullet bullet1 = new Bullet(this.x + tempWidth * 1, this.y);
			Bullet bullet2 = new Bullet(this.x + tempWidth * 3, this.y);
			list.add(bullet1);
			list.add(bullet2);
			this.doubleFire -= 2;
		} else {
			Bullet bullet = new Bullet(this.x + tempWidth * 2, this.y);
			list.add(bullet);
		}
		return list;
	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return false;
	}

}
