package game;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

public class GameSquare extends GameObj {
	
	protected Texture back_tex = Draw.tex_def;
	
	protected float to_x;
	protected float to_y;
	protected static float speed = 4f;
	protected boolean canBeClicked = true;

	
	public GameSquare(int x, int y, int c, int d){
		this(((Display.getWidth() / 2f) - tile_size*3.5f) + x* tile_size, ((Display.getHeight() / 2) + tile_size*3f) - y*tile_size, c, d);
		rel_x = x;
		rel_y = y;
	}

	public GameSquare(float x, float y, int c, int d){
//		this(x, y, 128, 128, "Square");
		this(x, y, tile_size, tile_size, c, d, "Square");
	}
	
	public GameSquare(float x, float y, float sizeX, float sizeY, int c, int d, String name){
		super(x, y, sizeX, sizeY, name, ObjProp.SQUARE);
		color = c;
		dir = d;
		setColorTex();
		backSquare();
		
		to_x = x;
		to_y = y;
		System.out.println(x);
		System.out.println(y);
	}
	
	public void setColorTex(){
		
		switch(color){
		case 0:
			setTexCoord(8 + (dir*2), 4, 10 + (dir*2), 6);
			break;
		case 1:
			setTexCoord(8 + (dir*2), 6, 10 + (dir*2), 8);
			break;
		case 2:
			setTexCoord(8 + (dir*2), 8, 10 + (dir*2), 10);
			break;
		case 3:
			setTexCoord(8 + (dir*2), 10, 10 + (dir*2), 12);
			break;
		default:
		System.out.println("Error, cannot read color.");		
		}
		
	}
	
	public void setBTexCoord(float btex_x, float btex_y, float btex_dx, float btex_dy){
		
		this.btex_x = btex_x;
		this.btex_y = btex_y;
		this.btex_dx = btex_dx;
		this.btex_dy = btex_dy;
		
		
	}
	
//	public void backSquare(GameCircle dot){
	public void backSquare(){
		backSquare(color);
	}
	public void backSquare(int color){
		
//		if( x == dot.x && y == dot.y){
			
//			switch (dot.color) {
			switch (color) {
			case 0:
				setBTexCoord(0, 6, 2, 8);
				break;
			case 1:
				setBTexCoord(2, 6, 4, 8);
				break;
			case 2:
				setBTexCoord(4, 6, 6, 8);
				break;
			case 3:
				setBTexCoord(6, 6, 8, 8);
				break;
			default:
				setBTexCoord(tex_x, tex_y+2, tex_dx, tex_dy+2);
			System.out.println("Error, cannot read color."+ color);
			
			}
			
//		}
		
		
	}
	
	protected void moveSquare(){
		moveSquare(this, this.dir);
	}

	protected void moveSquare(int dir){
		moveSquare(this, dir);
	}
	
	public static void moveSquare(GameSquare obj, int dir){
		
		obj.canBeClicked = false;
		
		switch (dir) {
		case 0:
			obj.to_x = obj.x + tile_size;
			obj.checkForNeighbour(dir);
			obj.rel_x++;
			break;
		case 1:
			obj.to_y = obj.y - tile_size;
			obj.checkForNeighbour(dir);
			obj.rel_y++;
			break;
		case 2:
			obj.to_x = obj.x - tile_size;
			obj.checkForNeighbour(dir);
			obj.rel_x--;
			break;
		case 3:
			obj.to_y = obj.y + tile_size;
			obj.checkForNeighbour(dir);
			obj.rel_y--;
			break;
		default:
			break;
		}
		
	}
	
	protected void moveTrans(){
			
		if ( x < to_x){
			x+= speed;
		}
		else
		if( x > to_x){
			x-= speed;	
		}
		else
		if (y < to_y){
			y+= speed;
		}
		else
		if( y > to_y){
			y-= speed;
		}
		else
		if( x == to_x && y == to_y && !canBeClicked){
			canBeClicked = true;
		}
	}
	
	protected void checkForNeighbour(int dir){
		
//		boolean found = false;
	
		switch (dir) {
		case 0:
			for(GameSquare s : PlayState.squareList){
				if(this.rel_x + 1 == s.rel_x && this.rel_y == s.rel_y)
					s.moveSquare(dir);
			}
			break;
		case 1:
			for(GameSquare s : PlayState.squareList){
				if(this.rel_x == s.rel_x && this.rel_y + 1 == s.rel_y)
					s.moveSquare(dir);
			}
			break;
		case 2:
			for(GameSquare s : PlayState.squareList){
				if(this.rel_x - 1 == s.rel_x && this.rel_y == s.rel_y)
					s.moveSquare(dir);
			}
			break;
		case 3:
			for(GameSquare s : PlayState.squareList){
				if(this.rel_x == s.rel_x && this.rel_y - 1 == s.rel_y)
					s.moveSquare(dir);
			}
			break;

		default:
			break;
		}
	}
	
	// Get
	public float getBtex_x(){
		return btex_x;
	}
	public float getBtex_y(){
		return btex_y;
	}
	public float getBtex_dx(){
		return btex_dx;
	}
	public float getBtex_dy(){
		return btex_dy;
	}
	
}
