package GameObject;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import classes.Friendly;
import main.Animation;
import main.Controller;
import main.Game;
import main.Sound;
import main.Sprites;

public class Attack extends GameObj implements Friendly {

	private char direction;
	private int speed = 4; // I put 0 since the swing shall not move
	private int timer = 0, combo;
        private int swingTimer;
	private boolean hit = false;

	private BufferedImage[] swings;
	
	
	BufferedImage image;
	BufferedImage[] e;
	Game game;
	Sprites s;
	Sound boom;
	Animation explosion;
	Controller c;
	
	Animation currentAttack; // to show attack for player!!!
	private int selectPicture =0;

	public Attack(int x, int y, char direction, Game game, Controller c, Sprites s, int combo) {
		super(x, y);
		this.game = game;
		this.c = c;
		this.direction = direction;
		this.s = s;
		this.swingTimer = 0;
                this.combo = combo;
                
		swings = this.s.getAttackSprite();
		currentAttack= new Animation(5,swings[0],250,250, 4, combo);
		e = s.getExplosion();
		
		
		explosion = new Animation(5, image, 32, 32, 1);
		boom = new Sound("/resources/snd_explosion1.wav");
	}

	public void update() {
            swingTimer++;
            if (swingTimer >= 4) {
                c.removeFriendly(this);
            }
		if(!hit){
			if (timer == 40){
				c.removeFriendly(this);
				timer++;
				
				
			}
			/*
			 *
			 *	
			if (direction == 'r'){
			yLoc -= speed;
			xLoc += (speed-2);
			}
			if (direction == 'l'){
				yLoc -= speed;
				xLoc -= (speed-2);
			}
			if (direction == 's') {
				yLoc -= speed;
			}*/
		} else if(hit){
			if(timer >= 30){
				c.removeFriendly(this);
			}
			timer++;
			explosion.runAnimation();
		}
	}

	public void draw(Graphics g) {
		if(!hit){
			currentAttack.setImage(swings[0]);
			currentAttack.runAnimation();
			currentAttack.drawAnimation(g, (int) xLoc, (int) yLoc);
		}else if(hit){
			explosion.drawAnimation(g, xLoc, yLoc);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) xLoc, (int) yLoc, 200, 200);
	}

	// GETTERS
	public int getX() {
		return xLoc;
	}

	public int getY() {
		return yLoc;
	}

	// Setters
	public void SetX(int x) {
		this.xLoc = x;
	}

	public void SetY(int y) {
		this.yLoc = y;
	}
	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public boolean getHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}
	
	public void setDirection(char direction){
		this.direction = direction;
	}
	public char getDirection(){
		return this.direction;
	}
	
	public void setSelectPicture(){ // assign the picture
		this.selectPicture = selectPicture;
	}
	public int getSelectPicture(){ // get the selected picture
		return this.selectPicture;
	}
	

}

