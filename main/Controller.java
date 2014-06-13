package main;

import java.awt.Graphics;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import classes.*;
import GameObject.*;

public class Controller {
	private LinkedList<Island> il = new LinkedList<Island>();
	private LinkedList<Building> bl = new LinkedList<Building>(); // I added for
																	// building
																	// from Jung
																	// Hwan
																	// Kim's
																	// Implementation
	private LinkedList<Friendly> fl = new LinkedList<Friendly>();
	private LinkedList<Hostile> hl = new LinkedList<Hostile>();
	private LinkedList<Neutral> nl = new LinkedList<Neutral>();
	private LinkedList<Road> rl = new LinkedList<Road>();

	// Variables
	Friendly f;
	Hostile h;
	Neutral n;
	Random r = new Random();
	Island TempIsland;
	Building b; // defined from Jung Hwan Kim's Implementation
	Road rd;

	Game game;
	Sprites s;

	public Controller(Game game, Sprites s) throws IOException {
		this.game = game;
		this.s = s;
		for (int x = 1; x <= 3; x++) {
			addIsland(new Island(r.nextInt(640), -r.nextInt(300), x, r, game, s));
		}
	}

	public void update() {
		// FRIENDLY
		for (int i = 0; i < il.size(); i++) {
			TempIsland = il.get(i);
			TempIsland.update();
		}
		for (int i = 0; i < fl.size(); i++) {
			f = fl.get(i);

			// REMOVE BULLET OUT OF BOUNDS
			if (f.getY() < 0) {
				System.out.println(fl.size());
				removeFriendly(f);
			}
			f.update();
		}

		// HOSTILE
		for (int i = 0; i < hl.size(); i++) {
			h = hl.get(i);
			h.update();
		}

		// Neutral
		for (int i = 0; i < nl.size(); i++) {
			n = nl.get(i);
			n.update();
		}
		// Building from Jung Hwan Kim's Implementation
		for (int i = 0; i < bl.size(); i++) {
			b = bl.get(i);
			b.update();
		}

		for (int i = 0; i < rl.size(); i++) {
			rd = rl.get(i);
			rd.update();
		}

	}

	public void draw(Graphics g) {

		// Road from Jung Hwan Kim's Implementation
		for (int i = 0; i < rl.size(); i++) {
			rd = rl.get(i);
			rd.draw(g);
		}
		// Building from Jung Hwan Kim's
		for (int i = 0; i < bl.size(); i++) {
			b = bl.get(i);
			b.draw(g);
		}
		// Neutral
		for (int i = 0; i < nl.size(); i++) {
			n = nl.get(i);
			n.draw(g);
		}

		// FRIENDLY
		for (int i = 0; i < il.size(); i++) {
			TempIsland = il.get(i);
			TempIsland.draw(g);
		}
		for (int i = 0; i < fl.size(); i++) {
			f = fl.get(i);
			f.draw(g);
		}

		// HOSTILE
		for (int i = 0; i < hl.size(); i++) {
			h = hl.get(i);
			h.draw(g);
		}

	}

	public void addFriendly(Friendly block) {
		fl.add(block);
	}

	public void removeFriendly(Friendly block) {
		fl.remove(block);
	}

	public void addHostile(Hostile h) {
		this.hl.add(h);
	}

	public void removeHostile(Hostile h) {
		this.hl.remove(h);
	}

	public void addIsland(Island i) {
		this.il.add(i);
	}

	// add building from Jung Hwan Kim's Implementation
	public void addBuilding(Building b) {
		this.bl.add(b);
	}

	// remove building from Jung Hwan Kim's Implementation
	public void removeBuilding(Building b) {
		this.bl.remove(b);
	}

	public void addRoad(Road rd) {
		this.rl.add(rd);
	}

	public void removeRoad(Road rd) {
		this.rl.remove(rd);
	}

	public void spawnEnemy(int enemy_count) {
		for (int i = 0; i < enemy_count; i++) {
			addHostile(new Enemy(700, 350 + r.nextInt(150), 1, this, this.game,
					s));
		}
	}

	public void spawnEnemy2(int enemy_count) {
		System.out.println("SPAWN 2");
		for (int i = 0; i < enemy_count; i++) {
			addHostile(new Enemy(700, 350 + r.nextInt(150), -1, this,
					this.game, s));
		}
	}

	// spawn buildings from Jung Hwan Kim's Implementation
	public void spawnBuilding(int building_count) {

		if (bl.size() < 2) {
			if(this.game.getPlane().getDirection().equals("left"))
				addBuilding(new Building((-1) * 800, -100, this.game, this, s));
			if  (!this.game.getPlane().getDirection().equals("left")|| !this.game.getPlane().getDirection().equals("right"))
				addBuilding(new Building(0 * 800, -100, this.game, this, s));
			if  (this.game.getPlane().getDirection().equals("right"))
				addBuilding(new Building(1 * 800, -100, this.game, this, s));
		}
	}

	public void spawnRoad(int road_count) {

		if (rl.size() < 2) {
			if  (this.game.getPlane().getDirection().equals("left"))
				addRoad(new Road((-1) * 1057, 450, this.game, this, s));
			if  (!this.game.getPlane().getDirection().equals("left")|| !this.game.getPlane().getDirection().equals("right"))
				addRoad(new Road((0) * 1057, 450, this.game, this, s));
			if  (this.game.getPlane().getDirection().equals("right"))
				addRoad(new Road((1) * 1057, 450, this.game, this, s));
		}
	}

	public void addBoss() {
		addHostile(new Boss(450, 10, this.game, this, s));
	}

	public void addNeutral(Neutral n) {
		this.nl.add(n);
	}

	public void removeNeutral(Neutral n) {
		this.nl.remove(n);
	}

	public LinkedList<Friendly> getFriendly() {
		return fl;
	}

	public LinkedList<Hostile> getHostile() {
		return hl;
	}

	public LinkedList<Neutral> getNeutral() {
		return nl;
	}

	// return LinkedList Building from Jung Hwan Kim's Implemetation
	public LinkedList<Building> getBuilding() {
		return bl;
	}

	public LinkedList<Road> getRoad() {
		return rl;
	}

}
