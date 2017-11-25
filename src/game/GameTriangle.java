package game;

import org.lwjgl.opengl.Display;

public class GameTriangle extends GameObj {

	public GameTriangle(int x, int y, int d){
		this(((Display.getWidth() / 2f) - tile_size*3.5f) + x* tile_size, ((Display.getHeight() / 2) + tile_size*3f) - y*tile_size, d);
		rel_x = x;
		rel_y = y;
	}
	
	public GameTriangle(float x, float y, int d){
		this(x, y, 96, 96, d);
	}
	
	public GameTriangle(float x, float y, float sizeX, float sizeY, int d){
		super(x, y, sizeX, sizeY, "triangle", ObjProp.TRIANGLE);
		dir = d;
		setColorTex();
	}
	
	public void setColorTex(){
		
		setTexCoord(0 + dir*2, 10, 2+dir*2, 12);
		
	}
	
	public void checkForDirChange(GameSquare obj){
		
		if(areOnSameTile(this, obj)){
			obj.dir = this.dir;
			obj.setColorTex();
		}
		
	}
	
	public static boolean areOnSameTile(GameTriangle obj1, GameSquare obj2){
		
		if(obj1.x == obj2.x && obj1.y == obj2.y)
			return true;
		
		return false;
	}
	
}
