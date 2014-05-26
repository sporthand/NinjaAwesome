package classes;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Hostile {	
	abstract public void update();
	abstract public void draw(Graphics g);
	public Rectangle getBounds();
	
	abstract public int getX();
	abstract public int getY();	
	
	abstract public void setIsDead(boolean n);
	abstract public boolean getIsDead();
}