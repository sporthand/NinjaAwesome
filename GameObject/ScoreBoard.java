package GameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import main.Sprites;

public class ScoreBoard extends GameObj {
	
	private int score = 0;
	private int lives;
	private BufferedImage image;
	
	Game game;
	Sprites s;
	
	public ScoreBoard(int x, int y, int lives, int score, Game game, Sprites s){
		super(x,y);
		this.lives =lives;
		this.score = score;
		this.game = game;
		this.s = s;
		this.image = s.getBottomSprite();
	}
	
	
	public void update(){
		
	}
	
	public void draw(Graphics g){
		g.drawImage(this.image, xLoc, yLoc, null);
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getLives() {
		return lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
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
