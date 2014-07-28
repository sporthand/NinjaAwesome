package GameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.Animation;
import main.Controller;
import main.Game;
import main.Sprites;

public class Road extends GameObj {
	private BufferedImage[] road;
	

	private int ticks = 0;
	private int timer = 0;
	private int Stage = 0; // the building shall be spawn depending on the number stage. 

	// added for new data field for boss.
	private int imageNumber = 1;
	private double imageWidth = 1057;
	private double imageHeight = 350;

	private String direction = "left"; // I make a boss to move aroud easier!
										// New Source Code Attention!
	Animation anim;
	Random r = new Random();
	Game game;
	Sprites s;
	Controller c;

	public Road(int x, int y, Game game, Controller c, Sprites s) {
		super(x, y);
		this.s = s;
		this.c = c;
		this.game = game;
		road = s.getRoad(1);
		anim = new Animation(0, road[0], imageHeight, imageWidth, imageNumber);

	}

	public void update() {

		if (game.getNinja().getDirection().equals("left")){
			xLoc -= game.getNinja().getSpeedX();
			if (xLoc > 0 ){
				
				if (c.getRoad().size()<2)
					c.spawnRoad(-1);
				else if(xLoc>1051)
					c.removeRoad(this);
			}
		} else if (game.getNinja().getDirection().equals("right")
				&& game.getNinja().getSpeedX() > 0) {
			xLoc -=  game.getNinja().getSpeedX();
			if (xLoc<0){
				if (c.getRoad().size()<2)
					c.spawnRoad(1);
				else if(xLoc<-1051)
					c.removeRoad(this);
			}
		}

	}

	public void draw(Graphics g) {
		anim.drawAnimation(g, xLoc, yLoc);

	}

	public Rectangle getBounds() {
		return new Rectangle((int) xLoc, (int) yLoc, 1057, 350);
	}

	public int getX() {
		return this.xLoc;
	}

	public int getY() {
		return this.yLoc;
	}

}
