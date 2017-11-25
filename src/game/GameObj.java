/*	Obj type
 * 	0 - square
 * 	1 - triangle
 *  2 - circle
 */
package game;

import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class GameObj {
	
	protected static float tile_size = 96;
	
	protected float x;
	protected float y;
	
	protected int rel_x;
	protected int rel_y;
	
	protected float sizeX;
	protected float sizeY;
	
	protected ObjProp type;
	protected String name;
	protected boolean destroyed;
	
	protected float colorf1;
	protected float colorf2;
	protected float colorf3;
	protected float colorf4;
	
	protected static Texture tex_def = Draw.loadTextureR("sprite");
//	protected static Texture tex_def = Draw.loadTextureR("16bit_sprites");
	protected Texture tex = tex_def;
	protected float tex_x;
	protected float tex_y;
	protected float tex_dx;
	protected float tex_dy;
	
	protected float btex_x;
	protected float btex_y;
	protected float btex_dx;
	protected float btex_dy;
	
	protected int color;
	protected int dir;
	
	private boolean click_pre = false;
	private boolean click_act = false;
	private boolean rclick_pre = false;
	private boolean rclick_act = false;
	
	// for level editor
	protected boolean canBeMoved = true;
	protected boolean isInHand = false;
	protected int listPosition;
	
	
	// Empty obj
	public GameObj(){		
		this(0, 0, 0, 0, "inv", ObjProp.NULL, 1f, 1f, 1f, 1f);
	}
	
	public GameObj(float x, float y, float sizeX, float sizeY, String name, ObjProp type){
		this(x, y, sizeX, sizeY, name, type, 1f, 1f, 1f, 1f);
	}

	
	// rand Quad
	public GameObj(float x, float y, float sizeX, float sizeY, String name, ObjProp type, float colorf1, float colorf2, float colorf3, float colorf4){
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.type = type;
		this.name = name;
		this.colorf1 = colorf1;
		this.colorf2 = colorf2;
		this.colorf3 = colorf3;
		this.colorf4 = colorf4;
		tex = tex_def;
		
		if(type == ObjProp.NULL) destroyed = true;
		else destroyed = false;
	}
	
	public void loadTexture(String s){
		
		try {
//			tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/" + s + ".png"));
			tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(s));
			
			System.out.println(tex.getTextureRef());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void drawObj(){
		if(!destroyed)
		{
			switch(type){
			case SQUARE:
				Draw.drawTex(x, y, sizeX, sizeY, tex, btex_x, btex_y, btex_dx, btex_dy);
				Draw.drawTex(x, y, sizeX, sizeY, tex, tex_x, tex_y, tex_dx, tex_dy);
				break;
			case BUTTON:
//				Draw.drawRect(x, y, sizeX, sizeY, colorf1, colorf2, colorf3);
				Draw.drawTex(x, y, sizeX, sizeY, tex, tex_x, tex_y, tex_dx, tex_dy);
				break;
			case CIRCLE:
				Draw.drawTex(x, y, sizeX, sizeY, tex, tex_x, tex_y, tex_dx, tex_dy);
				break;
			case TRIANGLE:
				Draw.drawTex(x, y, sizeX, sizeY, tex, tex_x, tex_y, tex_dx, tex_dy);
				break;
			case MOUSE:
				Draw.drawRect(x, y, sizeY, sizeX);
				break;
			default:
				System.out.print("Cannot draw object ");
				System.out.println(name);
			}
		}
		
	}
	
	public static boolean collisionDet(GameObj obj1, GameObj obj2){
		// For obj1:
		float x1  = obj1.getObjX();		// get obj1 y
		float y1  = obj1.getObjY();		// get obj1 y
		float sx1 = obj1.getObjSizeX();	// get obj1 sizeX
		float sy1 = obj1.getObjSizeY();	// get obj1 sizeY
		
		return collisionDet(x1, y1, sx1, sy1, obj2);
	}
	
	public static boolean collisionDet(float x1, float y1, float sx1, float sy1, GameObj obj2){
		
		// For obj2:                                             
		float x2  = obj2.getObjX();      // get obj2 y           
		float y2  = obj2.getObjY();      // get obj2 y           
		float sx2 = obj2.getObjSizeX();  // get obj2 sizeX       
		float sy2 = obj2.getObjSizeY();  // get obj2 sizeY       
		                                                         
		if( ( x1 < x2 && x1+sx1 > x2 ) && ( y1 < y2 && y1+sy1 > y2 ) ){
			return true;
		}
		
		if( ( x2 < x1 && x2+sx2 > x1 ) && ( y2 < y1 && y2+sy2 > y1 ) ){
			return true;
		}
		
		if( ( x1 < x2+sx2 && x2+sx2 < x1+sx1 ) && ( y1 < y2 && y2 < y1+sy1 ) ){
			return true;
		}
		
		if( ( x2 < x1+sx1 && x1+sx1 < x2+sx2 ) && ( y2 < y1 && y1 < y2+sy2 ) ){
			return true;
		}
		
		return false;
	}
	
//	public static boolean areOnSameTile(GameObj obj1, GameObj obj2){
//		
//		if(obj1.x == obj2.x && obj1.y == obj2.y)
//			return true;
//		
//		return false;
//	}
	
	
	public boolean isClicked(GameObj cursor){
		
		
		if(Mouse.isButtonDown(0) && collisionDet(this, cursor)){
			click_pre = click_act;
			click_act = true;
			
		} else {                                                                     
			click_pre = click_act;
			click_act = false;
		}
		                                                                                 
		if( click_pre && !click_act && collisionDet(this, cursor)){
			click_pre = false;
			click_act = false;
			return true;
		}

		return false;

	}
	
	public boolean isRClicked(GameObj cursor){
		
		
		if(Mouse.isButtonDown(1) && collisionDet(this, cursor)){
			rclick_pre = rclick_act;
			rclick_act = true;
			
		} else {                                                                     
			rclick_pre = rclick_act;
			rclick_act = false;
		}
		                                                                                 
		if( rclick_pre && !rclick_act && collisionDet(this, cursor)){
			rclick_pre = false;
			rclick_act = false;
			return true;
		}

		return false;

	}
	
	public boolean isHovered(GameObj mouse){
		
		if(collisionDet(this, mouse)) {
			return true;
		}
		
		return false;
	}

	// Set
	public void setObjX(float x){
		this.x = x;
	}

	public void setObjY(float y){
		this.y = y;
	}

	public void setObjXY(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void setObjColor4f(float colorf1, float colorf2, float colorf3, float colorf4){
		this.colorf1 = colorf1;
		this.colorf2 = colorf2;
		this.colorf3 = colorf3;
		this.colorf4 = colorf4;
	}

	public void destroyObj(){
		destroyed = true;
	}
	
	public void setTexCoord(float tex_x, float tex_y, float tex_dx, float tex_dy){
		this.tex_x = tex_x;		// x (from) top-left
		this.tex_y = tex_y;		// y (from) to-left
		this.tex_dx = tex_dx;	// x (from) bottom-right
		this.tex_dy = tex_dy;	// y (from) bottom-left
	}
	
	public void setDir(byte dir){
		this.dir = dir;
	}
	
	public void setColor(byte color){
		this.color = color;
	}

	// Return
	public float getObjX(){
		return x;
	}
	
	public float getObjY(){
		return y;
	}
	
	public float getObjSizeX(){
		return sizeX;
	}

	public float getObjSizeY(){
		return sizeY;
	}
	
	public ObjProp getObjType(){
		return type;
	}
	
	
}
