package GameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import main.Game;

public class Stages {
	private boolean isStageCompleted = false;
	private int stage = 1;

	public void setStage(boolean isStageCompleted) {
		this.isStageCompleted = isStageCompleted;
		if (isStageCompleted) {
			stage++;
		}
		//else
	
		//if user is dead 
		// display "if you want to continue, Yes or NO"
		// if user hits "Yes" users will start on the beginning of current stage
		// if user hits no it will go back to the title.
		
	}

	public int getStage(){
		return stage;
		
	}
	
	public void setStage(int stage){
		this.stage = stage; 
	}

}
