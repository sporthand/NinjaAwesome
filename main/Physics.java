package main;

import classes.Friendly;
import classes.Hostile;
import classes.Neutral;

import java.util.LinkedList;

public class Physics {

	public static boolean collision(Friendly f, Hostile h) {
		if (f.getBounds().intersects(h.getBounds())) {
			return true;
		}
		return false;
	}
	
	public static boolean collision(Hostile h,Friendly f) {
		if (h.getBounds().intersects(f.getBounds())) {
			return true;
		}
		return false;
	}
	
	public static boolean collision(Neutral n,Friendly f) {
		if (n.getBounds().intersects(f.getBounds())) {
			return true;
		}
		return false;
	}


	public static boolean collision(Hostile h, LinkedList<Friendly> fl) {
		for (int i = 0; i < fl.size(); i++) {
			if (h.getBounds().intersects(fl.get(i).getBounds())) {
				return true;
			}
		}
		return false;
	}

}
