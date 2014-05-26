package GameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import main.Game;
import main.Sprites;

public class Island extends GameObj{

	private int speed = 10;
	private BufferedImage image;
	private Random gen;
	
	Sprites s;
	Game game;
	

	public Island(int x, int y, int num, Random gen, Game game, Sprites s) {
		super(x,y);
		this.gen = gen;
		this.game= game;
		if (num == 1) {
			this.image = s.getIslandSprite1();
		}
		if (num == 2) {
			this.image = s.getIslandSprite2();
		}
		if (num == 3) {
            this.image = s.getIslandSprite3();
		}
	}

	public void update() {
		xLoc -= speed;
		if (yLoc >= (Game.HEIGHT * Game.SCALE + 15)) {
			yLoc = -100;
			xLoc = Math.abs(gen.nextInt() % (Game.WIDTH - 30));
		}
	}

	public void draw(Graphics g) {
		g.drawImage(image, (int) xLoc, (int) yLoc, null);
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
}