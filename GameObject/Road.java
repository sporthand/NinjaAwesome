package GameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.Animation;
import main.Controller;
import main.Game;
import main.Sprites;

public class Road extends GameObj{
	private BufferedImage[] road;
	private int speed = 5;// I changed for jump down speed
	
	private int ticks = 0;
	private int timer = 0;
	private int levelOfStage = 0; // Boss suppose to be changed depend on each
									// level.

	// added for new data field for boss.
	private int imageNumber = 1;
	private double imageWidth = 1057 ;
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
		anim = new Animation(0,road[0], imageHeight,imageWidth,imageNumber);
		
	}

	public void update() {
		
		if (xLoc >= -2600 && xLoc <= 2600) {
			if (game.getPlane().getDirection().equals("left")
					&& game.getPlane().getSpeedX() < 0){
				xLoc += speed;
				if (xLoc >2600)
					c.removeRoad(this);
				//c.addBuilding(this);
			}
			else if (game.getPlane().getDirection().equals("right")
					&& game.getPlane().getSpeedX() > 0){
				xLoc -= speed;
				if (xLoc <-2600)
					c.removeRoad(this);
				//c.addBuilding(this);
			}
		}	
			
			
		
	}
	 
	 public void draw(Graphics g){
	    anim.drawAnimation(g, xLoc, yLoc);
	    	
	    	
	 }
	 public Rectangle getBounds(){
		return new Rectangle((int) xLoc, (int) yLoc, 1057, 350);
	 }
		
	 public int getX(){
	   	return this.xLoc;
	 }
	 public int getY(){
		return this.yLoc;
	 }

}
