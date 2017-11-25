package game;

import org.lwjgl.opengl.Display;

public class LevelState implements GameState {

	private GameButton back;
	private GameMouse cursor;
	

	public LevelState(){
		
		cursor = new GameMouse();
		back = new GameButton(40, Display.getHeight()-100, Draw.sprite_unit*3, Draw.sprite_unit*1, "Back");
		back.setTexCoord(0, 3, 3, 4);
		
	}
	
	public void render() {
		back.drawObj();
		
	}

	public void update() {
		
		cursor.update();
		
		back.hoverEffectMenuButtons(cursor);
		
		if(back.isClicked(cursor)){
			System.out.println("Screen changed to Menu");
			GameContainer.setCurrentScreen(0);
		}
		
		
	}
	
	
	
}
