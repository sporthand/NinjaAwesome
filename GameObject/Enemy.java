package GameObject;

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
import GameObject.MyNinja;


public class Enemy extends GameObj implements Hostile {

	Random r = new Random();
	private int speed;
	private BufferedImage[] image;
	private BufferedImage[] e;
	private int ticks;
	private int timer = 0;
	private boolean isDead = false;

	Sound boom;
	Game game;
	Controller c;
	Sprites s;
	Animation anim, explosion;

	public Enemy(int x, int y, int speed, Controller c, Game game, Sprites s) {
		super(x, y);
		this.s = s;
		this.c = c;
		this.speed = (r.nextInt(3) + 2) * speed;
		this.game = game;
		image = s.getEnemySprite();
		e = s.getExplosion2();
		explosion = new Animation(5, e[0], 65, 65, 4);
		anim = new Animation(5, image[0], 200, 200, 4);
		boom = new Sound("/resources/snd_explosion2.wav");
	}

	public void update() {
		ticks++;

		if (isDead) {
			explosion.runAnimation();
			if (timer == 40) {
				c.removeHostile(this);
				setIsDead(false);
				setTimer(0);
				// 1P
				if (game.getState() == game.getState().GAME) {
					game.getNinja().setScore(game.getNinja().getScore() + 5);
				}
				// 2P
				if (game.getState() == game.getState().MULTI) {
					game.getNinja().setScore(game.getNinja().getScore() + 5);
					game.getNinja2().setScore(game.getNinja2().getScore() + 5);
				}
				// INCREMENT KILL COUNTS
				game.setEnemy_killed(game.getEnemy_killed() + 1);
				game.setTotalKilled(game.getTotalKilled() + 1);
				System.out.println("total Killed:" + game.getTotalKilled());
			}
			timer++;
		}

		if (!isDead) { // is where I put no enemy plane spawn!!! I set !isDead
						// --> isDead to test the player
			// MOVEMENT
			if (ticks % 20 >= 8) {
				if (game.getNinja().getX() < this.getX())
					xLoc -= (speed+ game.getNinja().getSpeedX());
				if (game.getNinja().getX() > this.getX())
					xLoc += (speed+ game.getNinja().getSpeedX());
				if (game.getNinja().getY() < this.getY())
					yLoc -= speed;
				if (game.getNinja().getY() > this.getY())
					yLoc += speed;
			}
			// BOUNDS
			if (yLoc >= (Game.HEIGHT + 10)) {
				yLoc = -15;
				xLoc = r.nextInt(700);
			}

			// SHOOTING AI
			if (ticks % 40 == 0) { // delay to shoot until timer 40.
				if (r.nextInt(2) == 1) {
					/*
					 * c.addHostile(new EnemyBullet(this.xLoc, this.yLoc,
					 * this.speed + 1, this.game, this.c, this.s));
					 */
					if (r.nextInt(3) == 1) {
						// c.addHostile(new StrongBullet(this.xLoc, this.yLoc,
						// this.speed + 1, this.game, this.c, this.s));
					}
				}
			}

			for (int i = 0; i < game.fl.size(); i++) {
				Attack tempFriend = (Attack) game.fl.get(i);
				// COLLISION WITH BULLETS
				if (Physics.collision(this, tempFriend)) {
					c.removeFriendly(tempFriend);
					tempFriend.setHit(true);
					// 1P
					if (game.getState() == game.getState().GAME) {
						game.getNinja().setScore(
								game.getNinja().getScore() + 15);
					}
					// 2P
					if (game.getState() == game.getState().MULTI) {
						game.getNinja().setScore(
								game.getNinja().getScore() + 15);
						game.getNinja2().setScore(
								game.getNinja2().getScore() + 15);
					}
					this.setIsDead(true);
					boom.play(false);
				}
			}
		}
		anim.runAnimation();
	}

	public void draw(Graphics g) {
		if (isDead) {
			explosion.drawAnimation(g, xLoc, yLoc + 10);
		} else
			anim.drawAnimation(g, xLoc, yLoc);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) xLoc, (int) yLoc, 32, 32);
	}

	// GETTERS
	public int getX() {
		return xLoc;
	}

	public int getY() {
		return yLoc;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public boolean getIsDead() {
		return isDead;
	}

	public void setIsDead(boolean isDead) {
		this.isDead = isDead;
	}

	public void SetX(int x) {
		this.xLoc = x;
	}

	public void SetY(int y) {
		this.yLoc = y;
	}

	public int getTimer() {
		return timer;
	}
}
