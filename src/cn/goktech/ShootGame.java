package cn.goktech;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShootGame extends JPanel {
	public static int width = 400;
	public static int height = 625;

	// 表示当前的游戏状态
	public int state = 3;
	// 表示这个游戏过程中存在的四种状态
	public final static int START = 0;
	public final static int RUNNING = 1;
	public final static int PAUSE = 2;
	public final static int GAMEOVER = 3;

	// 静态资源
	public static BufferedImage background;
	public static BufferedImage start;
	public static BufferedImage pause;
	public static BufferedImage gameover;
	public static BufferedImage hero0;
	public static BufferedImage hero1;
	public static BufferedImage airplane;
	public static BufferedImage bee;
	public static BufferedImage bullet;

	Hero hero = new Hero();
	// 创建List分别存放当前面板中存在敌机、小蜜蜂、子弹
	List<FlyObject> flyings = new ArrayList<FlyObject>();
	List<Bullet> bullets = new ArrayList<Bullet>();

	// 静态块：只会执行一次
	static {
		// 初始化静态图片
		try {
			background = ImageIO.read(new File("iamge/background.png"));
			start = ImageIO.read(new File("iamge/start.png"));
			pause = ImageIO.read(new File("iamge/pause.png"));
			gameover = ImageIO.read(new File("iamge/gameover.png"));
			hero0 = ImageIO.read(new File("iamge/hero0.png"));
			hero1 = ImageIO.read(new File("iamge/hero1.png"));
			airplane = ImageIO.read(new File("iamge/airplane.png"));
			bee = ImageIO.read(new File("iamge/bee.png"));
			bullet = ImageIO.read(new File("iamge/bullet.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 此方法重点！！！
	// 实现监听的配置与游戏中对象的生成与动画效果
	public void action() {
		// 1、要为JPanel创建监听
		// 鼠标点击与移动
		MouseAdapter adapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 点击时要更改游戏状态
				switch (state) {
				case START:
					state = RUNNING;
					repaint();// 重新再调用一次paint方法
					break;
				case RUNNING:
					state = PAUSE;
					repaint();
					break;
				case PAUSE:
					state = RUNNING;
					repaint();
					break;
				case GAMEOVER:
					state = START;
					repaint();
					break;
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 游戏进行时，鼠标移出窗体，游戏暂停
				if (state == RUNNING) {
					state = PAUSE;
					repaint();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// 游戏暂停时，鼠标移入窗体，游戏开始
				if (state == PAUSE) {
					state = RUNNING;
					repaint();
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// 实现实时获取鼠标的坐标，让英雄机跟着鼠标移动
				// 获取鼠标的坐标
				if (state == RUNNING) {// 判断是否是游戏中
					int x = e.getX();
					int y = e.getY();
					// 将鼠标的坐标赋值给英雄机的坐标

					hero.x = x - hero.width / 2;// 让鼠标停留在飞机的机身中央位置
					hero.y = y - hero.height / 2;
					repaint();
				}
			}
		};

		// 为面板绑定监听
		this.addMouseListener(adapter);
		this.addMouseMotionListener(adapter);

		// 定时器周期性产生敌机与小蜜蜂、子弹

		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {

			@Override
			public void run() {
				if (state == RUNNING) {
					// 1、负责敌机与小蜜蜂的产生
					flyObjectAction();
					// 2、负责敌机与小蜜蜂、子弹、英雄机的移动
					flyobjectMove();
					// 3、负责子弹的产生
					bulletAction();
					// 4、负责子弹的移动
					bulletMove();
					// 5、判断子弹、小蜜蜂、敌机是否越界
					removeObject();
					// 6、判断子弹是否与敌机、小蜜蜂碰撞
					flyObjectShutByBullet();

					// 7、判断英雄机和敌机、小蜜蜂的碰撞
					// 生命值-1，火力值恢复一发
					// 8、判断英雄机的生命值改变游戏状态
					if (hero.life == 0) {
						state = GAMEOVER;
					}
					repaint();
				}
			}

		};

		// 启动定时任务
		timer.schedule(timerTask, 10, 10);

	}

	int flyObjectIndex;

	// 1、负责敌机与小蜜蜂的产生
	private void flyObjectAction() {
		// 1、通过概率控制小蜜蜂生成的数量要比敌机少
		flyObjectIndex++;
		if (flyObjectIndex % 30 == 0) {
			Random random = new Random();
			int number = random.nextInt(100);
			if (number < 20) {
				// 生成蜜蜂
				Bee bee = new Bee();
				flyings.add(bee);
			} else {
				AirPlane airPlane = new AirPlane();
				flyings.add(airPlane);
			}
		}
	}

	int moveIndex;

	// 2、负责敌机与小蜜蜂的移动
	public void flyobjectMove() {
		if (moveIndex++ % 2 == 0) {
			for (FlyObject flyObject : flyings) {
				flyObject.move();
			}

		}
		hero.move();
	}

	int bulletIndex;

	// 3、负责子弹的产生
	public void bulletAction() {
		// 控制子弹发射的频率
		bulletIndex++;
		if (bulletIndex % 20 == 0) {
			// 英雄机产生的子弹集合（1发/2发）
			List<Bullet> bulletList = hero.shutBullet();
			// 将新产生的子弹添加到面板的子弹集合中
			bullets.addAll(bulletList);
		}
	}

	int bulletMoveIndex;

	// 4、子弹的移动
	public void bulletMove() {

		for (Bullet bullet : bullets) {
			bullet.move();
		}

	}

	// 5、
	public void removeObject() {
		// 循环迭代List时，不允许在迭代过程中删除元素
		// 等待迭代完在进行批量移除
		// 保存越界的小蜜蜂敌机\子弹
		List<FlyObject> tempFlyObjet = new ArrayList<FlyObject>();
		List<Bullet> tempBullet = new ArrayList<Bullet>();
		// 判断小蜜蜂与敌机是否越界
		for (FlyObject flyObject : flyings) {
			if (flyObject.outOfBounds()) {
				tempFlyObjet.add(flyObject);
			}
		}
		// 判断子弹是否越界
		for (Bullet bullet : bullets) {
			if (bullet.outOfBounds()) {
				tempBullet.add(bullet);
			}
		}
		flyings.removeAll(tempFlyObjet);
		bullets.removeAll(tempBullet);

		// System.out.println("flyings=" + flyings.size());
		// System.out.println("bullets=" + bullets.size());

	}

	// 6、判断子弹是否与敌机、小蜜蜂碰撞
	public void flyObjectShutByBullet() {
		// 1、碰撞之后子弹与小蜜蜂要消失
		// 2、碰撞之后要判断是否+分，+生命值，+火力值
		List<FlyObject> tempFlyObjet = new ArrayList<FlyObject>();
		List<Bullet> tempBullet = new ArrayList<Bullet>();
		for (Bullet bullet : bullets) {
			// 实现每一颗子弹遍历窗体中的所有蜜蜂+敌机
			// 碰撞之后，两者消失在面板，
			for (FlyObject flyObject : flyings) {
				if (bullet.shut(flyObject)) {
					tempFlyObjet.add(flyObject);
					tempBullet.add(bullet);
					// 判断撞击的目标是小蜜蜂还是敌机？
					if (flyObject instanceof AirPlane) {
						AirPlane airPlane = (AirPlane) flyObject;
						// 获取敌机的得分
						hero.score += airPlane.getScore();
					} else if (flyObject instanceof Bee) {
						Bee bee = (Bee) flyObject;
						// 获取奖励
						bee.getAward(hero);
					}
					break;
				}

			}
		}

		// 移除掉击中的对象
		flyings.removeAll(tempFlyObjet);
		bullets.removeAll(tempBullet);

	}

	@Override
	public void paint(Graphics g) {
		// 1、绘制背景图片
		g.drawImage(background, 0, 0, null);

		// 3、绘制敌机与小蜜蜂
		paintAirPlaneAndBee(g);
		// 4、绘制子弹
		paintBullets(g);
		// 5、绘制游戏状态前景图
		paintForeground(g);

		Font font = new Font("微软雅黑", Font.BOLD, 22);
		g.setColor(Color.red);
		g.setFont(font);
		g.drawString("Score:" + hero.score, 10, 30);
		g.drawString("Life:" + hero.life, 10, 60);
		// 2、绘制英雄机
		g.drawImage(hero.image, hero.x, hero.y, null);
	}

	private void paintForeground(Graphics g) {
		switch (state) {
		case START:
			g.drawImage(start, 0, 0, null);
			break;
		case RUNNING:

			break;
		case PAUSE:
			g.drawImage(pause, 0, 0, null);
			break;
		case GAMEOVER:
			g.drawImage(gameover, 0, 0, null);
			break;
		}

	}

	// 实现子类的绘制
	private void paintBullets(Graphics g) {
		for (Bullet bullet : bullets) {
			g.drawImage(bullet.image, bullet.x, bullet.y, null);
		}

	}

	// 实现敌机与小蜜蜂的绘制
	private void paintAirPlaneAndBee(Graphics g) {
		for (FlyObject flyObject : flyings) {
			g.drawImage(flyObject.image, flyObject.x, flyObject.y, null);
		}

	}

	public static void main(String[] args) {
		ShootGame game = new ShootGame();
		JFrame jframe = new JFrame("飞机大战");
		jframe.add(game);
		jframe.setSize(ShootGame.width, ShootGame.height);
		jframe.setLocationRelativeTo(null);
		jframe.setResizable(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		game.action();
	}

}
