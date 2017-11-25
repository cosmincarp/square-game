package game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;


public class MenuState implements GameState {

	private GameButton play;
	private GameButton levels;
	private GameButton editor;
	private GameButton exit;
	private GameButton reset;
	private GameMouse cursor;
	
	// sets default button sizes
	private float button_w = 192f;
	private float button_h = 64f;
	private float offset_d = 100f;
	
	
	public MenuState(){
		
		// initiates cursor & buttons
		cursor = new GameMouse();
		play   = new GameButton(Display.getWidth()/2 - button_w/2, Display.getHeight()/2 - button_h/2 + 80 - offset_d, button_w, button_h, "Play");
		reset  = new GameButton(Display.getWidth()/2 - button_w/2, Display.getHeight()/2 - button_h/2 + 80 - offset_d, button_w, button_h, "Reset");		
		levels = new GameButton(Display.getWidth()/2 - button_w/2, Display.getHeight()/2 - button_h/2 - offset_d     , button_w, button_h, "Level");
		editor = new GameButton(Display.getWidth()/2 - button_w/2, Display.getHeight()/2 - button_h/2 - 80 - offset_d, button_w, button_h, "Level");
		exit   = new GameButton(Display.getWidth()/2 - button_w/2, Display.getHeight()/2 - button_h/2 -80*2- offset_d, button_w, button_h, "Exit");
		
		// sets texture coordinates for buttons
		play.setTexCoord(0, 0, 3, 1);
		levels.setTexCoord(0, 1, 3, 2);
		editor.setTexCoord(0, 3, 3, 4);
		exit.setTexCoord(0, 2, 3, 3);
		reset.setTexCoord(6, 2, 9, 3);
		
	}
	
	public void render(){
		
		// draws buttons
		reset.drawObj();
		play.drawObj();
		levels.drawObj();
		editor.drawObj();
		exit.drawObj();

	}
	
	public void update(){
		
		// updates cursor
		cursor.update();
		
		// hover effect for buttons
		play.hoverEffectMenuButtons(cursor);
		reset.hoverEffectMenuButtons(cursor);
		levels.hoverEffectMenuButtons(cursor);
		editor.hoverEffectMenuButtons(cursor);
		exit.hoverEffectMenuButtons(cursor);
		
		// actions if buttons are clicked
		if ( play.isClicked(cursor)){
			System.out.println("Screen changed to Play");
			GameContainer.setCurrentScreen(1); // sets screen to PlayState
		}
		if( play.isRClicked(cursor)){		// toggles reset button /// TODO: should reset after the user leaves the menu
			if(play.rel_x == reset.rel_x){
				reset.rel_x++;
				reset.extended = true;
				reset.translateTo(192, 0);
			}else
			if(play.rel_x < reset.rel_x){
				reset.rel_x--;
				reset.extended = false;
				reset.translateTo(-192, 0);
			}
		}
		if ( levels.isClicked(cursor)){
			System.out.println("Screen changed to Level");
			GameContainer.setCurrentScreen(2); // sets screen to LevelState
		}
		if ( editor.isClicked(cursor)){
			System.out.println("Screen changed to Editor");
			GameContainer.setCurrentScreen(3); // sets screen to EditorState
		}
		if ( exit.isClicked(cursor)){
			System.out.println("Game terminated");
			Main.exitGame(); // exits game
		}
		if (reset.isClicked(cursor) && reset.extended){
			LevelMap.i=1;
			GameContainer.initPlay(); // resets PlayState
		}
		reset.translateEff(); // translate effect for the reset button
		while(Keyboard.next()){
			if(Keyboard.isKeyDown(Keyboard.KEY_E)){
				GameContainer.setCurrentScreen(3);
			}
		}
	}
	
	
	
}
