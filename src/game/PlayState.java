package game;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;


public class PlayState implements GameState {
	
	protected GameMouse cursor;
	protected GameButton reload;
	protected GameButton back;
	
	protected static int level= 1; // next level
	protected static int circles_nr;
	
	protected static ArrayList<GameSquare> squareList = new ArrayList<GameSquare>();
	protected static ArrayList<GameTriangle> triangleList = new ArrayList<GameTriangle>();
	protected static ArrayList<GameCircle> circleList = new ArrayList<GameCircle>();
	

	public PlayState(){
		
		// initiates cursor & buttons
		cursor = new GameMouse();
		reload = new GameButton((float)Display.getWidth() - 74f, (float)Display.getHeight() - 74f, 64, 64, "reload");
		back = new GameButton(10f, (float)Display.getHeight() - 74f, 64, 64, "back");
		reload.setTexCoord(7, 0, 8, 1);
		back.setTexCoord(6, 0, 7, 1);
		// generate lvl
		generateLvl();
	}
	
	protected void generateLvl(){
		// clear all arrayLists of precedent objects
		squareList.clear();
		triangleList.clear();
		circleList.clear();
		// reads level array map
		LevelMap.readMap();
		
	}
	
	public void render(){
		// draw buttons
		reload.drawObj();
		back.drawObj();
		// draw all game objects
		for(GameCircle c : circleList)
			c.drawObj();
		for(GameSquare s : squareList)
			s.drawObj();
		for(GameTriangle t : triangleList)
			t.drawObj();
		
	}
	
	public void update(){
		
		cursor.update(); // updates cursor position
		
		// hover effect for buttons
		reload.hoverEffectGameButtons(cursor);
		back.hoverEffectGameButtons(cursor);
		
		// buttons actions
		if(reload.isClicked(cursor)){
			LevelMap.gotoLvl(level-1);
			generateLvl();
		}
		if(back.isClicked(cursor)){
			level--;
			GameContainer.setCurrentScreen(0);
		}
		// check for updates
		for(GameSquare s : squareList){
			if(s.isClicked(cursor) && s.canBeClicked){ 
				s.moveSquare(); // gives square a target x/y for a new position
			}
			s.moveTrans(); // if square has a target, translate to that
			for(GameCircle c : circleList){
				c.checkForBgChange(s); // checks to see if is required to change a square's background (if square is on circle)
			}
			for(GameTriangle t : triangleList){
				t.checkForDirChange(s); // check for direction change (if square is on triangle)
			}
		}
		// check for win
		circles_nr = circleList.size();
		for(GameCircle c : circleList)
			if(c.complete) circles_nr--;
		if(circles_nr == 0){
			LevelMap.gotoLvl(level);
			generateLvl();
			return;
		}
			
		/// TODO: clear
		while(Keyboard.next()){			
			if(Keyboard.isKeyDown(Keyboard.KEY_R)){
				LevelMap.gotoLvl(level-1);
				generateLvl();
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				LevelMap.gotoLvl(level-1);
				generateLvl();
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				LevelMap.gotoLvl(level);
				generateLvl();
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_1)){
				LevelMap.gotoLvl(1);
				generateLvl();
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_2)){
				LevelMap.gotoLvl(2);
				generateLvl();
			}
		}
		
	}
	
	
	
	
}
