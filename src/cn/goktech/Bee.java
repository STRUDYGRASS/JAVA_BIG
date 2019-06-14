package cn.goktech;

import java.util.Random;

import cn.goktech.intf.Award;

public class Bee extends FlyObject implements Award {
	int xSpeed;
	int ySpeed;
	int awardType;

	public Bee() {
		this.image = ShootGame.bee;// 游戏开始前画面显示英雄机的图片
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.ySpeed = 2;
		this.xSpeed = 1;
		Random random = new Random();

		this.x = random.nextInt(ShootGame.width - this.width);
		this.y = -this.height;
		// 随机产生[0,2）的整数 = 0 ，1
		this.awardType = random.nextInt(2);
	}

	@Override
	public void move() {
		this.y += ySpeed;
		this.x += xSpeed;
		// 判断小蜜蜂是否与左右边框相撞
		if (this.x + this.width >= ShootGame.width) {
			this.xSpeed = -2;
		}

		if (this.x <= 0) {
			this.xSpeed = 1;
		}

	}

	@Override
	public boolean outOfBounds() {
		if (this.y >= ShootGame.height) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void getAward(Hero hero) {
		switch (this.awardType) {
		case 0:
			hero.life += 1;
			break;
		case 1:
			hero.doubleFire += 40;
			break;
		}

	}

}
