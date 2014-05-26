package GameObject;

import java.awt.Rectangle;

public class GameObj {
	
	public int xLoc,yLoc;
	public int speed;
	
	public GameObj(int x , int y ){
		this.xLoc = x;
		this.yLoc = y;
	}
	
	public Rectangle getBounds(int width, int height){
		return new Rectangle((int)xLoc,(int)yLoc, width, height);
	}
}
