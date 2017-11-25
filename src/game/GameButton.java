package game;

/*
 * GameButton = new GameButton(x, y, size_on_x_axis, size_on_y_axis, "name");
 * button.setObjColor(float_R, float_G, float_B);
 * button.loadTexture("/path/to/file.png");
 * button.setTexCoord(top_left_x, top_left_y, bottom_right_x, bottom_right_y); 
 */

public class GameButton extends GameObj {
	

	private boolean is_hovered= false;
	private float to_x;
	private float to_y;
	private float speed = 4f;
	protected boolean extended = false;
	

	public GameButton(float x, float y, float sizeX, float sizeY, String name){
		super(x, y, sizeX, sizeY, name, ObjProp.BUTTON);
		to_x = x;
		to_y = y;
		
	}

	public void hoverEffectMenuButtons(GameObj mouse){
		if( this.isHovered(mouse) && !is_hovered){
			this.setTexCoord(tex_x + 3, tex_y, tex_dx + 3, tex_dy);
			is_hovered = true;
		} else if( !this.isHovered(mouse) && is_hovered){			
			this.setTexCoord(tex_x - 3, tex_y, tex_dx - 3, tex_dy);
			is_hovered = false;
		}
	}
	
	public void hoverEffectGameButtons(GameObj mouse){
		if( this.isHovered(mouse) && !is_hovered){
			this.setTexCoord(tex_x, tex_y + 1, tex_dx, tex_dy + 1);
			is_hovered = true;
		} else if( !this.isHovered(mouse) && is_hovered){			
			this.setTexCoord(tex_x, tex_y - 1, tex_dx, tex_dy - 1);
			is_hovered = false;
		}
	}
	
	public void translateTo(float x, float y){
		to_x += x;
		to_y += y;
	}
	
	public void translateEff(){
		if(to_x > x){
			x += speed;
		}
		if(to_x < x){
			x -= speed;
		}
		if(to_y > y){
			y += speed;
		}
		if(to_y < y){
			y -= speed;
		}
	}
}
