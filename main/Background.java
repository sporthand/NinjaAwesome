package main;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import GameObject.*;

public class Background extends GameObj{
	
	private double speed = 10;
	private BufferedImage image;
	//private BufferedImage background;
	
	Game game;
	Sprites s;
	
	public Background(int x, int y, Game game, Sprites s){
		super(x,y);
		this.game = game;
		this.s = s;
		image = s.getBackgroundSprite();
	}
	
	public void update(){
		yLoc+= speed;
	}
	
	public void draw(Graphics g){
		g.drawImage(image,(int)xLoc,(int)yLoc,null);    
}

	public int getX() {
		return xLoc;
	}

	public void setX(int xLoc) {
		this.xLoc = xLoc;
	}

	public int getY() {
		return yLoc;
	}

	public void setY(int yLoc) {
		this.yLoc = yLoc;
	}
	
}