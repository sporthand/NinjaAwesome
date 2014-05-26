package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class HiScore {

	public Rectangle main = new Rectangle(Game.WIDTH/2-115, 450,200,50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH/2-65, 550,100,50);
	
	public void draw(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD,50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("HIGH SCORES", Game.WIDTH/3-70, 100);
	
		
		Font fnt1 = new Font("arial", Font.BOLD,30);
		g.setFont(fnt1);

		
		//SCORES
		g.drawString("1. AAA   500", Game.WIDTH/3-20, 200);
		g.drawString("2. BBB   500", Game.WIDTH/3-20, 250);
		g.drawString("2. CCC   500", Game.WIDTH/3-20, 300);
		g.drawString("2. DDD   500", Game.WIDTH/3-20, 350);
		g.drawString("2. EEE   500", Game.WIDTH/3-20, 400);
		
		//BUTTONS
		g.drawString("Main Menu", main.x+22,main.y+ 35);
		g.drawString("Quit", quitButton.x+19, quitButton.y+35);
		
		g.setColor(Color.white);
		g2d.draw(main);
		g2d.draw(quitButton);
	}
}
