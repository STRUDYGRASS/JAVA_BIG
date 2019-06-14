package cn.goktech;

public class Bullet extends FlyObject {
	int ySpeed;

	// 子弹是根据英雄机的位置产生，构造方法需要使用到两个参数，表示英雄机的位置
	public Bullet(int x, int y) {
		this.image = ShootGame.bullet;// 游戏开始前画面显示英雄机的图片
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.ySpeed = -3;

		this.x = x;
		this.y = y;
	}

	@Override
	public void move() {
		this.y += ySpeed;

	}

	@Override
	public boolean outOfBounds() {
		if (this.y <= -this.height) {
			return true;
		} else {
			return false;
		}
	}

	public boolean shut(FlyObject flyObject) {
		int x1 = this.x;
		int y1 = this.y;

		int x2 = flyObject.x;
		int y2 = flyObject.y;
		if (x1 >= x2 && x1 <= x2 + flyObject.width) {
			if (y1 >= y2 - flyObject.height && y1 <= y2) {
				return true;
			}
		}
		return false;
	}
}
