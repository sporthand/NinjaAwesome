package GameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.Animation;
import main.Controller;
import main.Game;
import main.Physics;
import main.Sound;
import main.Sprites;
import classes.Hostile;

public class EnemyAttack extends GameObj implements Hostile {

	private BufferedImage[] image;
	private BufferedImage[] e;
	private int speed = 2;
	private boolean isDead;
	public boolean isBullet = true;
	private boolean hit = false;
	private int timer = 0;
	Random r = new Random();
	Game game;
	Sprites s;
	Controller c;
	Animation anim;
	Animation explosion;
	Sound boom;
	
	double imageWidth = 32.0;
	double imageHeight = 32.0; 
	 

	public EnemyAttack(int x, int y, int speed, Game game, Controller c,
			Sprites s) {
		super(x, y);
		this.game = game;
		this.speed = speed;
		this.c = c;
		this.s = s;
		e = s.getExplosion();
		image = s.getEnemyAttack();
		anim = new Animation(10, image[0], imageWidth, imageHeight, 1 );
		explosion = new Animation(5, e[0], imageWidth, imageHeight, 1);
		boom = new Sound("/resources/snd_explosion1.wav");
	}

	public void update() {

		if (!hit) {
			xLoc -= speed;
			yLoc += speed;
			if (yLoc >= (Game.HEIGHT + 10)) {
				c.removeHostile(this);
			}

			if (Physics.collision(game.getNinja(), this)) {
				hit = true;
				boom.play(false);
				game.getNinja().setHEALTH(game.getNinja().getHEALTH() - 15);
			}

			
			if (Physics.collision(game.getNinja2(), this) && game.getState() == game.getState().MULTI) {
				hit = true;
				boom.play(false);
				game.getNinja2().setHEALTH(game.getNinja2().getHEALTH() - 15);	
			}
			anim.runAnimation();
		} else if (hit) {
			if (timer > 40) {
				c.removeHostile(this);
				hit = false;
			}
			explosion.runAnimation();
			timer++;
		}

	}

	public void draw(Graphics g) {
		if(!hit){
		anim.drawAnimation(g, xLoc, yLoc);
		} else if (hit){
			explosion.drawAnimation(g, xLoc, yLoc);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) xLoc, (int) yLoc, 8, 8);
	}

	public int getX() {
		return this.xLoc;
	}

	public int getY() {
		return this.yLoc;
	}

	public void setIsDead(boolean n) {
		isDead = n;
	}

	public boolean getIsDead() {
		return isDead;
	}

}

