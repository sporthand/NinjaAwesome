package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Sprites {

	// IMAGES

	private static BufferedImage myplane = null;
	private static BufferedImage bullet = null;
	private static BufferedImage bulletL = null;
	private static BufferedImage bulletR = null;
	private static BufferedImage background = null;
	private static BufferedImage island1 = null;
	private static BufferedImage island2 = null;
	private static BufferedImage island3 = null;
	private static BufferedImage bottom = null;
	private static BufferedImage enemyBullet = null;
	private static BufferedImage powerup = null;
	private static BufferedImage boss = null;

	// ANIMATION ARRAYS
	public static BufferedImage at[] = new BufferedImage[2];
	public static BufferedImage swings[] = new BufferedImage[2];
	public static BufferedImage eBullet[] = new BufferedImage[2];
	public static BufferedImage explosion[] = new BufferedImage[6];
	public static BufferedImage explosion2[] = new BufferedImage[7];
	public static BufferedImage mp[] = new BufferedImage[4];
	public static BufferedImage ep[] = new BufferedImage[1];
	public static BufferedImage ep1[] = new BufferedImage[1];
	// public static BufferedImage ep2[] = new BufferedImage[1];
	// public static BufferedImage ep3[] = new BufferedImage[1];
	public static BufferedImage strBullet[] = new BufferedImage[2];
	private static BufferedImage life[] = new BufferedImage[2];

	public Sprites(Game game) {
		getSprites();
	}

	public void getSprites() {

		BufferedImageLoader loader = new BufferedImageLoader();
		try {

			boss = loader.loadImage("/resources/boss.png");
			powerup = loader.loadImage("/resources/powerup.png");
			// myplane = loader.loadImage("/resources/player.png");
			swings[0] = loader.loadImage("/resources/swingRight.png");
			swings[1] = loader.loadImage("/resources/swingLeft.png");
			bulletR = loader.loadImage("/resources/bulletRight.png");
			background = loader.loadImage("/resources/water.png");
			// islands
			island1 = loader.loadImage("/resources/island1.png");
			island2 = loader.loadImage("/resources/island2.png");
			island3 = loader.loadImage("/resources/island3.png");
			bottom = loader.loadImage("/resources/bottom.png");
			enemyBullet = loader.loadImage("/resources/enemybullet1.png");
			// Enemy Bullet
			eBullet[0] = loader.loadImage("/resources/enemybullet1.png");
			eBullet[1] = loader.loadImage("/resources/enemybullet2.png");
			// Explosion
			explosion[0] = loader.loadImage("/resources/explosion1_strip6.png");

			explosion2[0] = loader
					.loadImage("/resources/explosion2_strip7.png");

			// for ninja's animation for player
			// for right standing
			mp[0] = loader.loadImage("/resources/standingRight.png");

			// for left standing
			mp[1] = loader.loadImage("/resources/standingLeft.png");

			// ninja moves right
			mp[2] = loader.loadImage("/resources/runningRight.png");

			// ninja moves left
			mp[3] = loader.loadImage("/resources/runningLeft.png");

			// ninja attacks right side's animation
			at[0] = loader.loadImage("/resources/attackRight.png");
			// ninja attacks left side's animation
			at[1] = loader.loadImage("/resources/attackLeft.png");

			// EnemyPlane
			ep[0] = loader.loadImage("/resources/robotLeft.png");

			ep1[0] = loader.loadImage("/resources/robotRight.png");

			// ep2[0] = loader.loadImage("/resources/enemy2_strip3.png");

			// ep3[0] = loader.loadImage("/resources/enemy4_strip3.png");

			life[0] = loader.loadImage("/resources/life.png");

			// ///////////////////////////////////////////////////

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getPowerUp() {
		return powerup;
	}

	public BufferedImage getMyPlane() {
		return myplane;
	}

	/****************************************************************************************************
	 * I have erased and repalce the swings so that return swing image.
	 */
	public BufferedImage[] getBulletSprite() {

		return swings; // return all ninja attacks left and right!
	}

	public BufferedImage[] getEnemySprite() {
		int ran;
		Random r = new Random();
		ran = r.nextInt(2);
		switch (ran) {
		case 1:
			return ep;
		case 2:
			return ep1;
			// case 3:
			// return ep2;
		default:
			return ep1;
		}
	}

	public BufferedImage getIslandSprite1() {
		return island1;
	}

	public BufferedImage getIslandSprite2() {
		return island2;
	}

	public BufferedImage getIslandSprite3() {
		return island3;
	}

	public BufferedImage getBottomSprite() {
		return bottom;
	}

	public BufferedImage getBackgroundSprite() {
		return background;
	}

	public BufferedImage getEBullet() {
		return enemyBullet;
	}

	public BufferedImage getLife() {
		return life[0];
	}

	public BufferedImage[] getEnemyBullet() {
		return eBullet;
	}

	public BufferedImage[] getExplosion() {
		return explosion;
	}

	public BufferedImage[] getMP() {
		return mp;
	}

	public BufferedImage[] getExplosion2() {
		return explosion2;
	}

	public BufferedImage getBoss() {
		return boss;
	}
}
