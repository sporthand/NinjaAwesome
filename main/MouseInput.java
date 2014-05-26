package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Game.STATE;

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		/*
		 * public Rectangle playButton = new Rectangle(Game.WIDTH/2-65,
		 * 150,100,50); public Rectangle hiScore = new
		 * Rectangle(Game.WIDTH/2-115, 250,200,50); public Rectangle quitButton
		 * = new Rectangle(Game.WIDTH/2-65, 350,100,50);
		 */
		if (Game.getState() == STATE.MENU) {
			// PlayButton
			if (mx >= Game.WIDTH / 2 - 65 && mx <= Game.WIDTH / 2 + 45) {
				if (my >= 150 && my <= 200) {
					// Pressed Play
					Game.setState(Game.STATE.GAME);
				}

			}
			
			//MultipLayer
			if (mx >= Game.WIDTH / 2 - 65 && mx <= Game.WIDTH / 2 + 45) {
				if (my >= 250 && my <= 300) {
					// Pressed Play
					Game.setState(Game.STATE.MULTI);
				}

			}
			
			//High Scores
			if (mx >= Game.WIDTH / 2 - 65 && mx <= Game.WIDTH / 2 + 45) {
				if (my >= 350 && my <= 400) {
					// Pressed Play
					Game.setState(Game.STATE.SCORE);
				}

			}
			
			
			//QUIT
			if (mx >= Game.WIDTH / 2 - 65 && mx <= Game.WIDTH / 2 + 45) {
				if (my >= 450 && my <= 500) {
					// Quit
					System.exit(1);
				}

			}
		}
		
		if (Game.getState() == STATE.SCORE){
			
			//Main menu
			if (mx >= Game.WIDTH / 2 - 65 && mx <= Game.WIDTH / 2 + 45) {
				if (my >= 450 && my <= 500) {
					//BACK TO MAIN
					Game.setState(Game.STATE.MENU);
				}

			}
			
			//quit
			if (mx >= Game.WIDTH / 2 - 65 && mx <= Game.WIDTH / 2 + 45) {
				if (my >= 550 && my <= 600) {
					System.exit(1);
				}

			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
