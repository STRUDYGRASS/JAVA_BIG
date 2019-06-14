package cn.goktech;

import java.util.Random;

import cn.goktech.intf.Score;

public class AirPlane extends FlyObject implements Score {
	int ySpeed;

	public AirPlane() {
		// 保证敌机的在顶部位置随机产生（0~窗体的宽度-图片的宽度）
		// 随机数工具类
		//
		this.image = ShootGame.airplane;// 游戏开始前画面显示英雄机的图片
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.ySpeed = 1;
		Random random = new Random();

		this.x = random.nextInt(ShootGame.width - this.width);
		this.y = -this.height;
	}

	@Override
	public void move() {
		this.y += ySpeed;

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
	public int getScore() {
		// TODO Auto-generated method stub
		return 5;
	}

}
