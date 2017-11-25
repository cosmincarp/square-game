package game;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;




import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Draw extends GameObj {

	public static float sprite_unit = 64f; // the textures are (sprite_unit * 2) x (sprite_unit * 2)
//	public static float sprite_unit = 8f;
	private static float unit_nr;
	
	@SuppressWarnings("unused")
	private static Texture bg = loadTextureR("bg");;
	
//	private int[] pixels = new int[bg.getImageWidth() * bg.getImageHeight()];
	
	public Draw(){
		
	} 
	
	
	// draw quads without texture
	
	public static void drawRect(float x, float y, float width, float height){
		drawRect(x, y, width, height, 1f, 1f, 1f, 1f);
	}
	
	public static void drawRect(float x, float y, float width, float height, float colorf1, float colorf2, float colorf3, float alpha){
		glColor4f(colorf1, colorf2, colorf3, alpha);
		glPopMatrix();
			glBegin(GL_QUADS);
			{
				glVertex2f(x, y+height);
				glVertex2f(x+width, y+height);
				glVertex2f(x+width, y);
				glVertex2f(x, y);
			}
			glEnd();
		glPushMatrix();
		glColor3f(1f, 1f, 1f);
	}
	
	/*
	 * 	The texture is drawn from top-left -> bottom-left -> bottom-right -> top-right
	 * 
	 * 	tex_x and tex_y   are the coordinates measured from top left, in sprite_units of the top-left corner of the texture
	 * 	tex_dx and tex_dy are the coordinates measured from top left, in sprite_units of the lower-right corner of the texture 
	 */
	public static void drawTex(float x, float y, float sx, float sy, Texture tex, float tex_x, float tex_y, float tex_dx, float tex_dy) {
		
		Color.white.bind();
		
		unit_nr = tex.getImageHeight() / sprite_unit; // the texture is divided in a grid (sprite_unit x sprite_unit)
//		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		
		glEnable(GL_TEXTURE_2D);
		tex.bind();

		glBegin(GL_QUADS);
		
			glTexCoord2f(1/unit_nr * tex_x, 1/unit_nr * tex_y);
			glVertex2f(x, y + sy);
			
			glTexCoord2f(1/unit_nr * tex_x, 1/unit_nr * tex_dy);
			glVertex2f(x, y);
			
			glTexCoord2f(1/unit_nr * tex_dx, 1/unit_nr * tex_dy);
			glVertex2f(x + sx, y);
			
			glTexCoord2f(1/unit_nr * tex_dx, 1/unit_nr * tex_y);
			glVertex2f(x + sx, y + sy);
			
		glEnd();
		glDisable(GL_TEXTURE_2D);
		
	}
	
	// simplified drawTex for drawing buttons fast
	public void drawButton(){
		drawTex(this.x, this.y, this.sizeX, this.sizeY, this.tex, this.tex_x, this.tex_y, this.tex_dx, this.tex_dy);
	}
	
	// draw a quad with a custom texture
	public static void texQuad(float x, float y, float sx, float sy, Texture tex){
		
		tex.bind();
		glBegin(GL_QUADS);
		
			glTexCoord2f(0f, 0f);
			glVertex2f(x *1f, y *1f + sy);
			
			glTexCoord2f(0f, 1f);
			glVertex2f(x*1f, y*1f);
			
			glTexCoord2f(1f, 1f);
			glVertex2f(x*1f + sx, y*1f);
			
			glTexCoord2f(1f, 0f);
			glVertex2f(x*1f +sx, y*1f + sy);
			
		glEnd();
	}
	
	
	/// TODO: background draw
	public static void renderBG(Texture bg){

//		glColor3f(0.8f, 0.8f, 0.8f);
		
		texQuad(100, 100, 188f, 178f, bg);
		
//				glBegin(GL_QUADS);
//			
//					glTexCoord2f(0, 0);
//					glVertex2f(0f, 800f - 178f);
//					
//					glTexCoord2f(1, 0);
//					glVertex2f(188f, 800f - 178f);
//					
//					glTexCoord2f(1, 1);
//					glVertex2f(188f, 800f - (178f*2));
//					
//					glTexCoord2f(0, 1);
//					glVertex2f(0f, 800f - (178f*2));
//			
//				glEnd();
		
}
	
	public void drawSquare(){
		drawTex(this.x, this.y, this.sizeX, this.sizeY, this.tex, this.tex_x, this.tex_y, this.tex_dx, this.tex_dy);
	}
	
	// texture loader
	public static Texture loadTextureR(String s){
		
		try {
			// load just with the name of the file, all files must be png and in res/, or use the bottom one. Or do whatever you want. It's yours to play with.
			return TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/" + s + ".png")); 
//			return TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(s));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
