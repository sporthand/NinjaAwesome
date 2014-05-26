package main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import classes.Friendly;
import classes.Hostile;
import GameObject.*;

public class Game extends Canvas implements Runnable {

	// FIELDS
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 740;
	public static final int HEIGHT = 620;
	public static final int SCALE = 2;
	public final String TITLE = "2D Plane Shooter Game";

	private Menu menu;
	private HiScore hiScore;

	private boolean running = false;
	private Thread thread;

	private boolean shooting = false;
	private boolean shooting2 = false;
	private boolean bossSpawn = true;
	private boolean win = false;
	private boolean gameOver = false;

	private int ticks = 0;
	private int move = 0, speed = 1;
	private int enemy_count = 2;
	private int enemy_killed = 0;
	private int totalKilled = 0;

	public LinkedList<Friendly> fl;
	public LinkedList<Hostile> hl;

	// ///SOUND////////
	public Sound bgMusic;

	// ///MENU//////
	public static enum STATE {
		MENU, GAME, MULTI, SCORE
	};

	public static STATE State = STATE.MENU;
	// //////////////

	// IMAGES
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);

	// GAME OBJECTS
	private MyPlane mp;
	private MyPlane mp2;
	private Controller c;
	private ScoreBoard sb;
	// private Background gb;
	private Sprites s;

	public void init() {
		// req
		requestFocus();
		setBackground(Color.white);

		// listeners
		this.addMouseListener(new MouseInput());
		addKeyListener(new KeyInput(this));

		// init
		bgMusic = new Sound("/resources/background.wav");
		menu = new Menu();
		hiScore = new HiScore();
		s = new Sprites(this);
		try {
			c = new Controller(this, s);
		} catch (IOException e) {

		}
		sb = new ScoreBoard(30, 0, 3, 0, this, s);
		mp = new MyPlane(WIDTH / 4 + 32, 500, this, c, s);
		mp2 = new MyPlane(WIDTH - 100, 500, this, c, s);
		c.spawnEnemy(enemy_count);
		fl = c.getFriendly();
		hl = c.getHostile();
	}

	private synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread();
		thread.start();
	}

	private synchronized void stop() {
		if (!running) {
			return;
		}

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	public void run() {
		// Initialize clock
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		bgMusic.play(true);

		// GAME LOOP
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			draw();
			frames++;

			// SLEEP
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				break;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	private void update() {
		if (!gameOver) {
			if (getState() == STATE.GAME) {
				c.update();
				sb.update();
				mp.update();

				// TIMELINE HANDLING
				if (totalKilled <= 30) {
					if (enemy_killed >= enemy_count) {
						enemy_count += 2;
						enemy_killed = 0;
						c.spawnEnemy(enemy_count);
					}
				}
				if (totalKilled == 2 && bossSpawn) {
					bossSpawn = false;
					System.out.println("BOSS HAS SPAWNED!");
					c.addBoss(); // boss spawned at this source code!
				}

				if (ticks % 1000 == 0) {
					c.addNeutral(new PowerUp(c.r.nextInt(670), -15, this,
							this.s));
				}
				if (ticks % 1500 == 0) {
					c.addNeutral(new Life(c.r.nextInt(670), -64, 1, this, c, s));
				}
			}

			if (getState() == STATE.MULTI) {
				c.update();
				sb.update();
				mp.update();
				mp2.update();

				// TIMELINE
				if (totalKilled < 30) {
					if (enemy_killed >= enemy_count) {
						enemy_count += 2;
						enemy_killed = 0;
						c.spawnEnemy(enemy_count);
					}
				}
				if (totalKilled >= 30 && bossSpawn) {
					bossSpawn = false;
					System.out.println("BOSS HAS SPAWNED!");
					c.addBoss();
				}
				if (ticks % 1000 == 0) {
					c.addNeutral(new PowerUp(c.r.nextInt(670), -15, this,
							this.s));
				}
			}

			ticks++;
		}
	}

	private void draw() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

		// draw background//
		Graphics2D g2 = (Graphics2D) g;
		this.drawBackGroundWithTileImage(WIDTH, HEIGHT, g2);

		if (!gameOver) {
			// //////////////draw here//////////////////////////
			if (getState() == STATE.GAME) {

				// Objects
				c.draw(g);
				sb.draw(g);
				mp.draw(g);

				// HEALTH BAR////
				g.setColor(Color.gray);
				g.fillRect(0, 0, 150, 15);

				if (mp.getHEALTH() >= 0) {
					g.setColor(Color.green);
					g.fillRect(0, 0, mp.getHEALTH(), 15);
				}

				g.setColor(Color.white);
				g.drawRect(0, 0, 150, 15);
				// //////////////

				// Score//
				Font fnt0 = new Font("arial", Font.PLAIN, 20);
				g.setFont(fnt0);
				g.setColor(Color.yellow);
				g.drawString(Integer.toString(mp.getScore()), 285, 580);

			} else if (getState() == STATE.MULTI) {

				// Objects
				c.draw(g);
				sb.draw(g);
				mp.draw(g);
				mp2.draw(g);

				// HEALTH BAR////
				// P1
				g.setColor(Color.gray);
				g.fillRect(62, 598, 150, 15);

				if (mp.getHEALTH() >= 0) {
					g.setColor(Color.green);
					g.fillRect(62, 598, mp.getHEALTH(), 15);
				}

				g.setColor(Color.white);
				g.drawRect(62, 598, 150, 15);

				// P2
				g.setColor(Color.gray);
				g.fillRect(372, 598, 150, 15);

				if (mp2.getHEALTH() >= 0) {
					g.setColor(Color.green);
					g.fillRect(372, 598, mp2.getHEALTH(), 15);
				}

				g.setColor(Color.white);
				g.drawRect(372, 598, 150, 15);
				// //////////////END HEALTH

				// Score P1//
				Font fnt0 = new Font("arial", Font.PLAIN, 20);
				g.setFont(fnt0);
				g.setColor(Color.yellow);
				g.drawString("P1:" + Integer.toString(mp.getScore()), 285, 580);
				g.drawString("P2:" + Integer.toString(mp2.getScore()), 285, 610);

			} else if (getState() == STATE.MENU) {
				menu.draw(g);
			} else if (getState() == STATE.SCORE) {
				hiScore.draw(g);
			}
			// ///////////////////////////////////////////////////////////////////////
			g.dispose();
			bs.show();
		} else if (gameOver) {

			// SHOW SCORE
			if (win) {
				// Score//
				Font fnt0 = new Font("arial", Font.PLAIN, 50);
				g.setFont(fnt0);
				g.setColor(Color.yellow);
				g.drawString("YOU WON!!", WIDTH / 3, 250);
				g.drawString("SCORE:" + Integer.toString(mp.getScore()),
						WIDTH / 3, 350);
			} else if (!win) {
				Font fnt0 = new Font("arial", Font.PLAIN, 50);
				g.setFont(fnt0);
				g.setColor(Color.red);
				g.drawString("YOU LOSE!!", WIDTH / 3, 250);
				g.drawString("SCORE:" + Integer.toString(mp.getScore()),
						WIDTH / 3, 350);
			}

			g.dispose();
			bs.show();
		}
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (getState() == STATE.GAME) {
			if (key == KeyEvent.VK_RIGHT) {
				mp.setSpeedX(5);
				mp.setIsRunning(true);
				mp.setDirection("right");
			}
			else if (key == KeyEvent.VK_LEFT) {
				mp.setSpeedX(-5);
				mp.setIsRunning(true);
				mp.setDirection("left");
			}
			if (key == KeyEvent.VK_DOWN) {
				mp.setSpeedY(5);
			}
			if (key == KeyEvent.VK_UP) {
				mp.setSpeedY(-5);
			}
			if (key == KeyEvent.VK_SPACE && !shooting) {
				shooting = true;
				if (mp.isPowerup()) {
					c.addFriendly(new Bullet(mp.getX() + 16, mp.getY() - 5,'s', this, c, s));
					c.addFriendly(new Bullet(mp.getX() + 16, mp.getY() - 5,'r', this, c, s));
					c.addFriendly(new Bullet(mp.getX(), mp.getY() - 5, 'l',
							this, c, s));
				}
				if (!mp.isPowerup()) {
					c.addFriendly(new Bullet(mp.getX() + 20, mp.getY(),
							's', this, c, s));
				}
			}
		}

		if (getState() == STATE.MULTI) {
			if (key == KeyEvent.VK_RIGHT) {
				mp2.setSpeedX(5);
			} else if (key == KeyEvent.VK_LEFT) {
				mp2.setSpeedX(-5);
			} else if (key == KeyEvent.VK_DOWN) {
				mp2.setSpeedY(5);
			} else if (key == KeyEvent.VK_UP) {
				mp2.setSpeedY(-5);
			} else if (key == KeyEvent.VK_SPACE && !shooting2) {
				shooting = true;
				if (mp2.isPowerup()) {
					c.addFriendly(new Bullet(mp2.getX() + 16, mp2.getY() - 5,
							's', this, c, s));
					c.addFriendly(new Bullet(mp2.getX() + 16, mp2.getY() - 5,
							'r', this, c, s));
					c.addFriendly(new Bullet(mp2.getX() - 16, mp2.getY() - 5,
							'l', this, c, s));
				} else if (!mp2.isPowerup()) {
					c.addFriendly(new Bullet(mp2.getX() + 16, mp2.getY() - 5,
							's', this, c, s));
				}
			} else if (key == KeyEvent.VK_D) {
				mp.setSpeedX(5);
			} else if (key == KeyEvent.VK_A) {
				mp.setSpeedX(-5);
			} else if (key == KeyEvent.VK_S) {
				mp.setSpeedY(5);
			} else if (key == KeyEvent.VK_W) {
				mp.setSpeedY(-5);
			} else if (key == KeyEvent.VK_SHIFT && !shooting) {
				shooting = true;
				if (mp.isPowerup()) {
					c.addFriendly(new Bullet(mp.getX() + 16, mp.getY() - 5,
							's', this, c, s));
					c.addFriendly(new Bullet(mp.getX() + 16, mp.getY() - 5,
							'r', this, c, s));
					c.addFriendly(new Bullet(mp.getX() - 16, mp.getY() - 5,
							'l', this, c, s));
				}
				if (!mp.isPowerup()) {
					c.addFriendly(new Bullet(mp.getX() + 16, mp.getY() - 5,
							's', this, c, s));
				}
			}
		}

	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();
		if (Game.State == Game.State.GAME) {
			if (key == KeyEvent.VK_RIGHT) {
				mp.setIsRunning(false);
				mp.setSpeedX(0);
				
			} else if (key == KeyEvent.VK_LEFT) {
				mp.setIsRunning(false);
				mp.setSpeedX(0);
				
			} else if (key == KeyEvent.VK_DOWN) {
				mp.setSpeedY(0);
			} else if (key == KeyEvent.VK_UP) {
				mp.setSpeedY(0);
			} else if (key == KeyEvent.VK_SPACE) {
				shooting = false;
			}
		} else if (Game.State == Game.State.MULTI) {
			if (key == KeyEvent.VK_D) {
				mp.setSpeedX(0);
			} else if (key == KeyEvent.VK_A) {
				mp.setSpeedX(0);
			} else if (key == KeyEvent.VK_S) {
				mp.setSpeedY(0);
			} else if (key == KeyEvent.VK_W) {
				mp.setSpeedY(0);
			} else if (key == KeyEvent.VK_SHIFT) {
				shooting = false;
			} else if (key == KeyEvent.VK_RIGHT) {
				mp2.setSpeedX(0);
			} else if (key == KeyEvent.VK_LEFT) {
				mp2.setSpeedX(0);
			} else if (key == KeyEvent.VK_DOWN) {
				mp2.setSpeedY(0);
			} else if (key == KeyEvent.VK_UP) {
				mp2.setSpeedY(0);
			} else if (key == KeyEvent.VK_SPACE) {
				shooting2 = false;
			}
		}

	}

	public static void main(String[] args) {
		Game game = new Game();

		// SET DIMENSIONS
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));

		// SET JFRAME
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
		game.run();
	}

	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}

	public MyPlane getPlane() {
		return this.mp;
	}

	public MyPlane getPlane2() {
		return this.mp2;
	}

	public int getTotalKilled() {
		return totalKilled;
	}

	public void setTotalKilled(int totalKilled) {
		this.totalKilled = totalKilled;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	// func: drawBackGroudWithTileImage
	// produce the background
	
	public void drawBackGroundWithTileImage(int w, int h, Graphics2D g2) {
		Image sea;
		sea = s.getBackgroundSprite();
		int TileWidth = 800;
		int TileHeight =800;


		// Image Buffer = createImage(NumberX * TileWidth, NumberY *
		// TileHeight);

		
		g2.drawImage(sea,0+ (move % TileWidth), 0, TileWidth, TileHeight, this);
			
		move=0;
	}

	public static STATE getState() {
		return State;
	}

	public static void setState(STATE state) {
		State = state;
	}

	public void setWin(boolean n) {
		win = n;
	}
}