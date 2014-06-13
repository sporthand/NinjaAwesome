package GameObject;

import java.io.IOException;
import main.*;


public class Musics {
	private static Sound backgroundMusic = null;
	private static Sound fightMusic = null;
	private static Sound menuMusic = null;
	private static Sound bossMusic = null;
	
	public Musics(Game game) {
		menuMusic = new Sound("/resources/menu.wav");
		fightMusic = new Sound("/resources/background.wav");
		
		
	}
	public void setMusic(int selection){ // select which music would you play?
		if (selection == 0){ // menu Music
			this.backgroundMusic = menuMusic;
		}else if (selection == 1){// battleMusic
			this.backgroundMusic = fightMusic;
			
		}else if (selection == 2){
			this.backgroundMusic = bossMusic;
		}
		
	}
	public Sound getMusic(){
		return backgroundMusic;
	}
	
}
