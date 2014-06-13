package GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import classes.Neutral;
import main.Animation;
import main.Controller;
import main.Game;
import main.Physics;
import main.Sound;
import main.Sprites;

public class Building extends GameObj {

	private BufferedImage[] building;
	private int speed = 5;

	private int ticks = 0;
	private int timer = 0;
	private int levelOfStage = 0;

	private int imageNumber = 1;
	private double imageWidth = 0;
	private double imageHeight = 0;
	private int whatBuilding = 0;

	private String direction = "left";
	Animation anim;
	Random r = new Random();
	Game game;
	Sprites s;
	Controller c;

	public Building(int x, int y, Game game, Controller c, Sprites s) {

		super(x, y);
		this.s = s;
		this.c = c;
		this.game = game;
		whatBuilding = r.nextInt(2);
		building = s.getBuilding(whatBuilding);

		if (whatBuilding == 0) {
			this.imageWidth = 800;
			this.imageHeight = 600;

		} else if (whatBuilding == 1) {

			this.imageWidth = 800;
			this.imageHeight = 640;

			yLoc = 100;

		}
		anim = new Animation(0, building[0], imageHeight, imageWidth,
				imageNumber);

	}

	public void update() {

		// building movement when a player press movement key: Jung Hwan Kim

		if (game.getPlane().getDirection().equals("left")
				&& game.getPlane().getSpeedX() < 0) {
			xLoc += speed;
			if (xLoc > 800)
				c.removeBuilding(this);
			// c.addBuilding(this);
		} else if (game.getPlane().getDirection().equals("right")
				&& game.getPlane().getSpeedX() > 0) {
			xLoc -= speed;
			if (xLoc < -800)
				c.removeBuilding(this);
			// c.addBuilding(this);
		}

	}

	public void draw(Graphics g) {
		anim.drawAnimation(g, xLoc, yLoc);

	}

	public Rectangle getBounds() {
		return new Rectangle((int) xLoc, (int) yLoc, 800, 600);
	}

	public int getX() {
		return this.xLoc;
	}

	public int getY() {
		return this.yLoc;
	}

	public void setRandomValue(int whatBuilding) {
		this.whatBuilding = whatBuilding;
	}

	public int getRandomValue() {
		return whatBuilding;
	}
}
