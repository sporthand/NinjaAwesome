package GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import classes.Hostile;
import main.Animation;
import main.Controller;
import main.Game;
import main.Physics;
import main.Sound;
import main.Sprites;

public class Boss extends GameObj implements Hostile {

	private int HEALTH = 150;
	private boolean isDead = false;
	private BufferedImage image;
	private BufferedImage[] e;
	private int bulletSpeed = 11;
	private int speed = 11;// I changed for jump down speed
	//private int xSpeed = 2;
	private int bossAction =0;
	private int attackSpeed = 0; // Jung added the more datafield
	private int ticks = 0;
	private int timer = 0;
	private boolean isJumpDown = false;
	private int levelOfStage = 0; // Boss suppose to be changed depend on each
									// level.

	// added for new data field for boss.
	private int imageNumber = 1;
	private double imageWidth = 0.0;
	private double imageHeight = 0.0;

	private String direction = "down"; // I make a boss to move aroud easier!
										// New Source Code Attention!
	Animation explosion, anim;
	Random r = new Random();
	Sound boom;
	Game game;
	Sprites s;
	Controller c;

	public Boss(int x, int y, Game game, Controller c, Sprites s) {
		super(x, y);
		this.s = s;
		this.c = c;
		this.game = game;
		image = s.getBoss();
		e = s.getExplosion2();
		boom = new Sound("/resources/snd_explosion2.wav");
		explosion = new Animation(5, image, imageWidth, imageHeight,
				imageNumber);
	}

	public void update() {
		if (isDead) {
			explosion.runAnimation();
			if (timer == 40) {
				game.setGameOver(true);
				game.setWin(true);
				setTimer(0);
				// 1P
				if (game.getState() == game.getState().GAME) {
					game.getPlane().setScore(game.getPlane().getScore() + 150);
				}
				// 2P
				if (game.getState() == game.getState().MULTI) {
					game.getPlane().setScore(game.getPlane().getScore() + 150);
					game.getPlane2()
							.setScore(game.getPlane2().getScore() + 150);
				}
				c.removeHostile(this);
			}
			timer++;
		}
		/* The boss movement is here and action!
		 *  ****************************************************************************************************************/
		
		if (!isDead) {
			
			if (ticks %60 == 0)
				bossAction = r.nextInt(2);
			
			if (!isJumpDown){
				yLoc += speed;// y
				if (yLoc >= 450) {
					isJumpDown = true;
					this.setDirection("up");
					
				}
			}
			else if (bossAction == 0){
				if (direction.equals("up")) {
					this.setSpeed(-1);
					if (yLoc <=50)
						this.setDirection("down");
	
				} else if (direction.equals("down")) {
					//System.out.println("Boss " + direction + "\n"); // for degug purpose;
					this.setSpeed(1);
					
					if (yLoc >= 200) {// x
						this.setDirection("up");
					}
	
				}
				
			}else if (bossAction == 1){
				if (!(direction.equals("right")))
					this.setDirection("left");
				this.setSpeed(0);
				if(direction.equals("left")){
					//System.out.println ("Boss" + direction + "\n");
					this.setAttackSpeed(-10);
					if (xLoc <=30)
						this.setDirection("right");
					
				}
				else if (direction.equals("right")){
					this.setAttackSpeed(10);
					if (xLoc>=390){
						this.setDirection("up");
						this.setAttackSpeed(0);
						bossAction =0;
					}
						
					
				}
				
			}
		/**********************************************************************************************************************/	
			yLoc += this.getSpeed();
			xLoc += this.getAttackSpeed();
			// SHOOTING AI
			if (ticks % 40 == 0) { // this might be helpful for delay!
				if (r.nextInt(2) == 1) {
					c.addHostile(new EnemyBullet(this.xLoc - 40,
							this.yLoc + 50, this.bulletSpeed + 1, this.game, this.c,
							this.s));
					c.addHostile(new EnemyBullet(this.xLoc - 20,
							this.yLoc + 50, this.bulletSpeed + 1, this.game, this.c,
							this.s));
					c.addHostile(new EnemyBullet(this.xLoc + 20,
							this.yLoc + 50, this.bulletSpeed + 1, this.game, this.c,
							this.s));
					c.addHostile(new EnemyBullet(this.xLoc + 40,
							this.yLoc + 50, this.bulletSpeed + 1, this.game, this.c,
							this.s));
					if (r.nextInt(3) == 1) {
						// c.addHostile(new StrongBullet(this.xLoc, this.yLoc,
						// this.speed + 1, this.game, this.c, this.s));
					}
					if (ticks % 90 == 0) {
						c.spawnEnemy(4);
					}
				}
			}

			// COLLISSION WITH BULLETS
			for (int i = 0; i < game.fl.size(); i++) {
				Bullet tempFriend = (Bullet) game.fl.get(i);
				if (Physics.collision(this, tempFriend)) {
					// 1P
					if (game.getState() == game.getState().GAME) {
						if (!tempFriend.getHit()) {
							setHEALTH(this.getHEALTH() - 5);
							System.out.println("Enemy Health: " + getHEALTH());
						}
					}

					// 2P
					if (game.getState() == game.getState().MULTI) {
						setHEALTH(this.getHEALTH() - 5);
					}
					tempFriend.setHit(true);
					if (getHEALTH() <= 0) {
						this.setIsDead(true);
					}
					if (tempFriend.getTimer() == 0) {
						boom.play(false);
					}
				}
			}
		}

		ticks++;
	}

	public void draw(Graphics g) {
		if (!isDead) {
			g.setColor(Color.red);
			g.fillRect(5, 20, 150, 20);

			if (getHEALTH() >= 0) {
				g.setColor(Color.green);
				g.fillRect(5, 20, this.getHEALTH(), 20);
			}

			g.setColor(Color.gray);
			g.drawRect(5, 20, 150, 20);

			g.drawImage(image, xLoc, yLoc, null);
		} else if (isDead) {
			// explosion.drawAnimation(g, xLoc + 20, yLoc, 0);
			// explosion.drawAnimation(g, xLoc + 52, yLoc, 0);
			// explosion.drawAnimation(g, xLoc + 84, yLoc, 0);
			// explosion.drawAnimation(g, xLoc + 30, yLoc + 40, 0);
			// explosion.drawAnimation(g, xLoc + 72, yLoc + 40, 0);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) xLoc, (int) yLoc, 160, 90);
	}

	public int getX() {
		return xLoc;
	}

	public int getY() {
		return yLoc;
	}

	public void setIsDead(boolean n) {
		isDead = n;
	}

	public boolean getIsDead() {
		return isDead;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setAttackSpeed(int attackSpeed){
		this.attackSpeed = attackSpeed;
	}
	public int getAttackSpeed(){
		return attackSpeed;
	}
	

	public int getHEALTH() {
		return HEALTH;
	}

	public void setHEALTH(int hEALTH) {
		HEALTH = hEALTH;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public void setTimer(int n) {
		timer = n;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDirection() {
		return this.direction;
	}
}