package GameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import main.Controller;
import main.Game;
import main.Sprites;

public class BossAttack extends EnemyAttack{
	
	private int damage = 50;
	private int speed = 2;
	//private BufferedImage image;

	public BossAttack(int x, int y, int speed, Game game, Controller c,
			Sprites s) {
		super(x, y, speed, game, c, s);
	}
	public void update() {
		yLoc += speed;
		
		if (yLoc >= (Game.HEIGHT + 10)){
			c.removeHostile(this);
		}
		anim.runAnimation();
	}
	public void draw(Graphics g) {
		anim.drawAnimation(g, xLoc, yLoc);
	}
	public Rectangle getBounds(){
		return new Rectangle((int)xLoc,(int)yLoc, 16, 16);
	}

	public int getX() {
		return this.xLoc;
	}
	public int getY() {
		return this.yLoc;
	}	
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	

}