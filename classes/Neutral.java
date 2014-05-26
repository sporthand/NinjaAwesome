package classes;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Neutral {	
    public void update();
    public void draw(Graphics g);
	public Rectangle getBounds();
	
    public int getX();
	public int getY();	
}