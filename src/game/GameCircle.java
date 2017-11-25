package game;

import org.lwjgl.opengl.Display;

public class GameCircle extends GameObj {
	
	protected boolean complete = false;

	public GameCircle(int x, int y, int c){
		this(((Display.getWidth() / 2f) - tile_size*3.5f) + x* tile_size, ((Display.getHeight() / 2) + tile_size*3f) - y*tile_size, c);
		rel_x = x;
		rel_y = y;
	}
	
	public GameCircle(float x, float y, int c){
		this(x, y, 80, 80, "circle", c);
	}
	
	public GameCircle(float x, float y, float sizeX, float sizeY, String name, int c){
		super(x, y, tile_size, tile_size, "circle", ObjProp.CIRCLE);
		color = c;
		setColorTex();
	}
	
	public void setColorTex(){
		
		switch(color){
		case 0:
			setTexCoord(0, 8, 2, 10);
			break;
		case 1:
			setTexCoord(2, 8, 4, 10);
			break;
		case 2:
			setTexCoord(4, 8, 6, 10);
			break;
		case 3:
			setTexCoord(6, 8, 8, 10);
			break;
		default:
		System.out.println("Error, cannot read color.");		
		}
		
	}
	
	public void checkForBgChange(GameSquare obj){
		
		if(areOnSameTile(this, obj)){
			if(this.color == obj.color) complete = true;
			else
			complete = false;
			obj.backSquare(this.color);
		} else {
			obj.backSquare();
		}
		
	}
	
	public static boolean areOnSameTile(GameCircle obj1, GameSquare obj2){
		
		if(obj1.x == obj2.x && obj1.y == obj2.y)
			return true;
		
		return false;
	}
	
}
