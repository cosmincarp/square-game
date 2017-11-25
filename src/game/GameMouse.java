package game;

import org.lwjgl.input.Mouse;

public class GameMouse extends GameObj {
	
	public GameMouse(){
		this(0, 0);
	}

	public GameMouse(float x, float y){
		super(x, y, 0, 0, "Cursor", ObjProp.MOUSE);
	}
	
	public void update(){
		x = Mouse.getX();
		y = Mouse.getY();
	}
	
}
