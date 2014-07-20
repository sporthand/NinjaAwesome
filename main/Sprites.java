package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Sprites {

	// IMAGES

	private static BufferedImage myninja = null;
	private static BufferedImage attack = null;
	private static BufferedImage attackL = null;
	private static BufferedImage attackR = null;
	private static BufferedImage background = null;
	private static BufferedImage weather1 = null;
	private static BufferedImage weather2 = null;
	private static BufferedImage weather3 = null;
	private static BufferedImage bottom = null;
	private static BufferedImage enemyAttack = null;
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
	public static BufferedImage building01[] = new BufferedImage[2];
	public static BufferedImage building02[] = new BufferedImage[2];
	public static BufferedImage road01[] = new BufferedImage[1];

	public Sprites(Game game) {
		getSprites();
	}

	public void getSprites() {

		BufferedImageLoader loader = new BufferedImageLoader();
		try {

			boss = loader.loadImage("/resources/boss.png");
			powerup = loader.loadImage("/resources/powerup.png");
			// myninja = loader.loadImage("/resources/player.png");
			swings[0] = loader.loadImage("/resources/swingRight.png");
			swings[1] = loader.loadImage("/resources/swingLeft.png");
			attackR = loader.loadImage("/resources/bulletRight.png");
			background = loader.loadImage("/resources/water.png");
			// islands
			weather1 = loader.loadImage("/resources/island1.png");
			weather2 = loader.loadImage("/resources/island2.png");
			weather3 = loader.loadImage("/resources/island3.png");
			bottom = loader.loadImage("/resources/bottom.png");
			enemyAttack = loader.loadImage("/resources/enemybullet1.png");
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

			building01[0] = loader.loadImage("/resources/building01.png");// added
			building02[0] = loader.loadImage("/resources/building02.png");																// and								// Kim's
																			// Implemetation
			road01[0] = loader.loadImage("/resources/road01.png"); // I did it
																	// from Jung
																	// Hwan Kim

			// ///////////////////////////////////////////////////

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getPowerUp() {
		return powerup;
	}

	public BufferedImage getMyNinja() {
		return myninja;
	}

	/****************************************************************************************************
	 * I have erased and repalce the swings so that return swing image.
	 */
	public BufferedImage[] getAttackSprite() {

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

	public BufferedImage getWeatherSprite1() {
		return weather1;
	}

	public BufferedImage getWeatherSprite2() {
		return weather2;
	}

	public BufferedImage getWeatherSprite3() {
		return weather3;
	}

	public BufferedImage getBottomSprite() {
		return bottom;
	}

	public BufferedImage getBackgroundSprite() {
		return background;
	}

	public BufferedImage getEAttack() {
		return enemyAttack;
	}

	public BufferedImage getLife() {
		return life[0];
	}

	public BufferedImage[] getEnemyAttack() {
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

	/*
	 * Boss suppose to be different depending on each level. The tip of
	 * implementation of programming is public BufferedImage[] getEnemySprite()
	 */
	public BufferedImage getBoss() {
		return boss;

	}

	public BufferedImage[] getBuilding(int whatBuilding) {

		switch (whatBuilding) {
		case 0:
			return building01;
		case 1:
			return building02;
			// case 3:
			// return ep2;
		default:
			return ep1;
		}
	}

	public BufferedImage[] getRoad(int i){
		int ran;
		Random r = new Random();
		ran = 1;
		switch(ran){
		case 1:
			return road01;
		case 2:
			return ep1;
		default:
			return ep1;
		}
	}
}
