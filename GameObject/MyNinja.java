package GameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import classes.Friendly;
import classes.Hostile;
import classes.Neutral;
import main.Animation;
import main.Controller;
import main.Game;
import main.Physics;
import main.Sound;
import main.Sprites;

public class MyNinja extends GameObj implements Friendly {
	private int speedX, speedY;
	private int lives = 0;
	private Sprites s;
	private BufferedImage[] mp;
	private BufferedImage[] e;
	
	private boolean powerup = false;
	private boolean isDead = false;
	private boolean start = false;
	private int score = 0;

	private int timer = 0;
	private int HEALTH = 150;
	public LinkedList<Life> ll;

	Controller c;
	Game game;
	Sound boom, power;
	Life l1,l2,l3,l4;
	Animation died;
	Animation explosion;
	Animation blinking;
	
	Animation currentMovement;
	
	private String direction ="right"; // setting the direction: facing right or facing left.
	private boolean isRunning = false;

	public MyNinja(int x, int y, Game game, Controller c, Sprites s) {
		super(x, y);
		this.game = game;
		this.c = c;
		this.s = s;
		mp = this.s.getMP(); // got from Sprites.java
		e = this.s.getExplosion2();
		
		explosion = new Animation(5, e[0], 32, 32, 6);
		
		blinking = new Animation(10, mp[0], 32, 32, 3);
		
		boom = new Sound("/resources/snd_explosion2.wav");
		power = new Sound("/resources/powerup.wav");
		
		
		currentMovement = new Animation(6, mp[0], 200,200,4);
		
	}
	

	public void update() {

		// Dead explosion
		if ( getHEALTH() <= 0) {
			explosion.runAnimation();
			if (timer == 30) {
				this.SetX(Game.WIDTH / 2 - 32);
				this.SetY(500);
				setHEALTH(150);
				setIsDead(false);
				this.setPowerup(false);
				this.setStart(true);
				timer = 0;
			}
			if(this.getLives()== 0){
				game.setGameOver(true);
			}
			timer++;
		}

		if (!isDead) {
			//xLoc += speedX;
			yLoc += speedY;

			// SET BOUNDS
			if (xLoc <= 0) {
				xLoc = 0;
			}
			if (xLoc >= 690) {
				xLoc = 690;
			}
			if (yLoc <= 350) {
				yLoc = 350;
			}
			if (yLoc >= 450) {
				yLoc = 450;
			}

			//ENEMY COLLISIONS
			for (int i = 0; i < game.hl.size(); i++) {
				Hostile tempH = game.hl.get(i);

				/*if (Physics.collision(this, tempH)) {
					if (!tempH.getIsDead()) {
						boom.play(false);
						setHEALTH(getHEALTH() - 25);
					}
					tempH.setIsDead(false);
				}*/
			}
			
			//POWER UPS
			for (int i = 0; i< c.getNeutral().size(); i++){
				Neutral tempN = c.getNeutral().get(i);
				
				if(Physics.collision(tempN,this)){
					c.removeNeutral(tempN);
					
					if(!powerup){
						power.play(false);
						setPowerup(true);
					}
					
				}
			}
			

			if (getHEALTH() <= 0) {
				setIsDead(true);
				boom.play(false);
				//setLives(getLives() - 1);
				if (getLives() > 0){
					
				}
			}
		}
		if(timer < 60){
		blinking.runAnimation();
		}
		currentMovement.runAnimation();
		
	}

	public void draw(Graphics g) {
		if (!isDead) {
			if(start){
				if(timer < 60){
					blinking.drawAnimation(g, xLoc, yLoc);
					this.setStart(false);
					timer = 0;
				}
				timer++;
			}
			
			if (direction.equals("left")){
				if (isRunning == false){
					currentMovement.setImage(mp[1]);
					currentMovement.drawAnimation(g, xLoc, yLoc);
				}else {
					currentMovement.setImage(mp[3]);
					currentMovement.drawAnimation(g, xLoc , yLoc);
				}
			}
			if (direction.equals("right")){
				if (isRunning == false){
					currentMovement.setImage(mp[0]);
					currentMovement.drawAnimation(g, xLoc, yLoc);
				}
				else{
					currentMovement.setImage(mp[2]);
					currentMovement.drawAnimation(g, xLoc, yLoc);
				}
			}	 
					
			
		} else
			explosion.drawAnimation(g, xLoc, yLoc);
		
		for(int i = 0; i < this.getLives(); i++){
			//Life tempL = ll.get(i);
			//tempL.draw(g);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) xLoc, (int) yLoc, 64, 64);
	}

	// GETTERS
	public int getX() {
		return xLoc;
	}

	public int getY() {
		return yLoc;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	// Setters
	public void SetX(int x) {
		this.xLoc = x;
	}

	public void SetY(int y) {
		this.yLoc = y;
	}

	public void setSpeedX(int speed) {
		this.speedX = speed;
	}

	public void setSpeedY(int speed) {
		this.speedY = speed;
	}

	public void setIsDead(boolean b) {
		isDead = b;
	}

	public void setLives(int n) {
		this.lives = n;
	}

	public int getLives() {
		return this.lives;
	}

	public boolean isPowerup() {
		return powerup;
	}

	public void setPowerup(boolean powerup) {
		this.powerup = powerup;
	}
	public int getHEALTH() {
		return HEALTH;
	}

	public void setHEALTH(int hEALTH) {
		HEALTH = hEALTH;
	}
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}
	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
	}
	public void setDirection(String direction){
		this.direction = direction;
		
	}
	public String getDirection(){
		return this.direction;
	}

    // add the setter running for running animation
	public void setIsRunning(boolean isRunning){
		this.isRunning = isRunning; 
	}
}
