package main;

import java.awt.Graphics;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import classes.*;
import GameObject.*;

public class Controller {
	private LinkedList<Island> il = new LinkedList<Island>();
	
	private LinkedList<Friendly> fl = new LinkedList<Friendly>();
	private LinkedList<Hostile>  hl = new LinkedList<Hostile>();
	private LinkedList<Neutral>  nl = new LinkedList<Neutral>();
	
	//Variables
	Friendly f;
	Hostile h;
	Neutral n;
	Random r = new Random();
	Island TempIsland;
	Game game;
	Sprites s;

	public Controller(Game game, Sprites s) throws IOException {
		this.game = game;
		this.s = s;
		for (int x = 1; x <= 3; x++) {
			addIsland(new Island(r.nextInt(640), -r.nextInt(300), x, r, game,s));
		}
	}

	public void update() {
		//FRIENDLY
		for (int i = 0; i < il.size(); i++) {
			TempIsland = il.get(i);
			TempIsland.update();
		}
		for (int i = 0; i < fl.size(); i++) {
			f  = fl.get(i);

			// REMOVE BULLET OUT OF BOUNDS
			if (f.getY() < 0) {
				System.out.println(fl.size());
				removeFriendly(f);
			}
			f.update();
		}
		
		//HOSTILE
		for (int i = 0; i < hl.size(); i++) {
			h = hl.get(i);
			h.update();
		}
		
		//Neutral
		for(int i = 0; i < nl.size(); i++){
			n = nl.get(i);
			n.update();
		}
			
	}

	public void draw(Graphics g) {
		//Neutral
		for(int i = 0; i < nl.size(); i++){
			n = nl.get(i);
			n.draw(g);
		}
		
		//FRIENDLY
		for (int i = 0; i < il.size(); i++) {
			TempIsland = il.get(i);
			TempIsland.draw(g);
		}
		for (int i = 0; i < fl.size(); i++) {
			f = fl.get(i);
			f.draw(g);
		}
		
		//HOSTILE
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
	
	public void addHostile(Hostile h){
		this.hl.add(h);
	}
	
	public void removeHostile(Hostile h){
		this.hl.remove(h);
	}

	public void addIsland(Island i) {
		this.il.add(i);
	}
	
	public void spawnEnemy(int enemy_count) {
		for (int i = 0; i < enemy_count; i++){
			addHostile(new Enemy(700,r.nextInt(500),1,this,this.game,s));
		}
	}
	
	public void spawnEnemy2(int enemy_count) {
		System.out.println("SPAWN 2");
		for (int i = 0; i < enemy_count; i++){
			addHostile(new Enemy(700,r.nextInt(500),-1,this,this.game,s));
		}
	}
	
	public void addBoss(){
		addHostile(new Boss(450,10,this.game,this,s));
	}
	
	public void addNeutral(Neutral n){
		this.nl.add(n);
	}
	public void removeNeutral(Neutral n){
		this.nl.remove(n);
	}
	
	public LinkedList<Friendly> getFriendly(){
		return fl;
	}
	public LinkedList<Hostile> getHostile(){
		return hl;
	}
	public LinkedList<Neutral> getNeutral(){
		return nl;
	}
	
}
