package game;

import org.lwjgl.opengl.Display;


public class TestState extends PlayState implements GameState {
	
	private static int i; 
	private int[] map;
	private int length;
	
	public TestState(int[] map, int length){
		this.map = map;
		this.length = length;
		cursor = new GameMouse();
		reload = new GameButton((float)Display.getWidth() - 74f, (float)Display.getHeight() - 74f, 64, 64, "reload");
		back = new GameButton(10f, (float)Display.getHeight() - 74f, 64, 64, "back");
		reload.setTexCoord(7, 0, 8, 1);
		back.setTexCoord(6, 0, 7, 1);
		
		generatetest(this.map, this.length);
	}
	
	private void generatetest(int[] map, int length){

		squareList.clear();
		triangleList.clear();
		circleList.clear();
		
		i = 0;
		readMap(map, length);
		
	}

	@Override
	public void render() {
		
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

	@Override
	public void update() {
		
cursor.update(); // updates cursor position
		
		// hover effect for buttons
		reload.hoverEffectGameButtons(cursor);
		back.hoverEffectGameButtons(cursor);
		
		// buttons actions
		if(reload.isClicked(cursor)){
			generatetest(map, length);
		}
		if(back.isClicked(cursor)){
			GameContainer.setCurrentScreen(3);
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
		
	}
	
	public void readMap(int[] map, int length){
		
		while(i < length){
			if(map[i] == 9 || i == length-1){ // if level ends, stop here
				i++;
				System.out.println(i);
				return;
			} 
			if(map[i] == 0){ 	// if type is square, read next 4 digits as square properties
				squareList.add(new GameSquare(map[i+1], map[i+2], map[i+3], map[i+4]));	// adds new square to square array list
				i += 4;	// moves cursor 4 digits forward
			}
			else
			if(map[i] == 1){	// if type is circle, read next 3 digits as circle properties
				circleList.add(new GameCircle(map[i+1], map[i+2], map[i+3]));			// adds new circle to circle array list
				i += 3;	// moves cursor 3 digits forward
			}
			else
//			if(map[i] == 2 && i+3 < map.length){
			if(map[i] == 2){	// if type is triangle, read next 3 digits as triangle properties
				triangleList.add(new GameTriangle(map[i+1], map[i+2], map[i+3]));		// adds new triangle to triangle array list
				i += 3;	// moves cursor 3 digits forward
			}
			i++;		// next digit
		}
	}
	
}
