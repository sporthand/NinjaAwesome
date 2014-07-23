package GameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import classes.Neutral;
import main.Controller;
import main.Game;
import main.Physics;
import main.Sprites;

public class Life extends GameObj implements Neutral {

	private BufferedImage image;
	private int speed = 1;
	Game game;
	Sprites s;
	Controller c;

	public Life(int x, int y, int speed, Game game, Controller c, Sprites s) {
		super(x, y);
		this.speed = speed;
		this.game = game;
		this.c = c;
		this.s = s;
		image = s.getLife();
	}

	public void update() {
		yLoc += speed;
		if (game.State == game.State.GAME) {
			if (Physics.collision(this, game.getNinja())) {
				game.getNinja().setLives(game.getNinja().getLives() + 1);
				c.removeNeutral(this);
			}

		} else if (game.State == game.State.MULTI) {
			if (Physics.collision(this, game.getNinja())) {
				game.getNinja().setLives(game.getNinja().getLives() + 1);
				c.removeNeutral(this);
			}

			if (Physics.collision(this, game.getNinja2())) {
				game.getNinja2().setLives(game.getNinja2().getLives() + 1);
				c.removeNeutral(this);
			}
		}

	}

	public void draw(Graphics g) {
		g.drawImage(image, xLoc, yLoc, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) xLoc, (int) yLoc, 16, 16);
	}

	public int getX() {
		return xLoc;
	}

	public int getY() {
		return yLoc;
	}

}

