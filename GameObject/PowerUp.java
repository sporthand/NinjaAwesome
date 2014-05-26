package GameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import classes.Neutral;
import main.Animation;
import main.Game;
import main.Sprites;

public class PowerUp extends GameObj implements Neutral{
	
	private BufferedImage image[] = new BufferedImage[2];
	private int speed = 1;
	Game game;
	Sprites s;
	Animation anim;

	public PowerUp(int x, int y, Game game, Sprites s){
		super(x,y);
		this.game = game;
		this.s =s;
		image[0] = s.getPowerUp();
		anim = new Animation(5,image[0], 32,32,1);
	}

	public void update() {
		yLoc += speed;
		anim.runAnimation();
	}
	public void draw(Graphics g) {
		anim.drawAnimation(g, xLoc, yLoc);
	}
	public Rectangle getBounds(){
		return new Rectangle((int)xLoc,(int)yLoc, 16, 16);
	}
	public int getX() {
		return xLoc;
	}
	public int getY() {
		return yLoc;
	}

	
}
