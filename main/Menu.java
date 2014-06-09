package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton = new Rectangle(Game.WIDTH/2-65, 150,100,50);
	public Rectangle multiButton = new Rectangle(Game.WIDTH/2-115, 250,200,50);
	public Rectangle hiScore = new Rectangle(Game.WIDTH/2-115, 350,200,50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH/2-65, 450,100,50);
	
	
	public void draw(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD,50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Ninja Awesome", Game.WIDTH/2 - 140, 100);
		
		Font fnt1 = new Font("arial", Font.BOLD,30);
		g.setFont(fnt1);
		g.drawString("Play", playButton.x+19, playButton.y+35);
		g.drawString("Multiplayer", multiButton.x+22, multiButton.y+35);
		g.drawString("High Scores", hiScore.x+16,hiScore.y+ 35);
		g.drawString("Quit", quitButton.x+19, quitButton.y+35);
		
		g.setColor(Color.yellow);
		g2d.draw(playButton);
		g2d.draw(multiButton);
		g2d.draw(hiScore);
		g2d.draw(quitButton);
	}
}
