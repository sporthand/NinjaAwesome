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
	private double imageWidth = 800;
	private double imageHeight = 600;

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
		building = s.getBuilding(1);
		anim = new Animation(0, building[0], imageHeight, imageWidth,
				imageNumber);

	}

	public void update() {
		if (xLoc < -1600 || xLoc > 1600) {
			c.removeBuilding(this);

		}

		if (xLoc >= -1600 && xLoc <= 1600) {
			if (game.getPlane().getDirection().equals("left")
					&& game.getPlane().getSpeedX() < 0)
				xLoc += speed;
			else if (game.getPlane().getDirection().equals("right")
					&& game.getPlane().getSpeedX() > 0)
				xLoc -= speed;

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
}
