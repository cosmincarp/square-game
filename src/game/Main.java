/*	Display res: 1440 x 810 (standard 16:9)
 * 	Display res this case 1440 x 800
 * 
 * 
 */
package game;

import org.lwjgl.*;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.Display;
import org.lwjgl.input.Keyboard;

import static org.lwjgl.opengl.GL11.*;

public class Main {
	
	private final static int WIDTH = 1440;
	//private final static int HEIGHT = WIDTH/16*9;
	private final static int HEIGHT = 800;
	
	private static String NAME = "Menu";

	private GameContainer game;
	
	
	public void initLWJGL(){
		
		// initiates OpenGL. Not LWJGL. I messed it up. Whatever. It's not clean, I know, but whatever
		glMatrixMode(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 3);
		glMatrixMode(GL_MODELVIEW);
		glMatrixMode(GL_PROJECTION);
		glEnable(GL_TEXTURE_2D);
		glEnable (GL_BLEND);
		glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glClearColor(0.84f, 0.84f, 0.84f, 1f);

	}
	
	
	public void initGame(){
		game = new GameContainer();
	}
	
	public void initDisplay(){
		try{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(NAME);
			Display.create();
			Display.setVSyncEnabled(false);
		} catch(LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}
		
		initLWJGL();
		initGame();
		
		while(!Display.isCloseRequested()){
			// clears everything
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			getKey(); // used just for exit the game if pressed escape
			update(); // updates game
			render(); // renders game
			
			Display.update();
			Display.sync(120);
		}
		
		Display.destroy();
		System.exit(0);
	}
	
	private void update(){
		game.update();
	}
	
	private void render(){
		game.render();
	}

	public static void main(String[] args) {
		
		Main gameHnd = new Main();
		gameHnd.initDisplay();

	}
	
	public static void exitGame(){
		Display.destroy();
		System.exit(0);
	}
	
	
	public static void getKey(){
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			exitGame();
		}
	}
	
}
