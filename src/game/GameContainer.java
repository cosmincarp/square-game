package game;

import org.newdawn.slick.opengl.Texture;


public class GameContainer {

	public static GameState[] gameState = new GameState[5];

	private final static int menuScreen = 0;
	private final static int playScreen = 1;
	private final static int levelScreen = 2;
	private final static int editorScreen = 3;
//	private final static int testScreen = 4;
	
	@SuppressWarnings("unused")
	private static Texture bg = Draw.loadTextureR("bg");;

	private static int currentScreen = menuScreen;
	
	
	public GameContainer(){
		// intiates menu
		initMenu();
	}
	
	public static void initMenu(){
		gameState[menuScreen] = new MenuState();
	}
	public static void initPlay(){
		gameState[playScreen] = new PlayState();		
	}
	public static void initLevel(){
		gameState[levelScreen] = new LevelState();		
	}
	public static void initEditor(){
		gameState[editorScreen] = new EditorState();		
	}
	
	public void render(){
		// renders current gameState
//		Draw.renderBG(bg);
		gameState[currentScreen].render();
		
	}
	
	public void update(){
		// updates current gameState
		gameState[currentScreen].update();
		
	}
	
	// sets
	public static void setCurrentScreen(int screen){
		currentScreen = screen;
		switch (screen) {
		case menuScreen:
			if(gameState[menuScreen] == null) initMenu();
			break;
		case playScreen:
			initPlay();
			break;
		case levelScreen:
			initLevel();
			break;
		case editorScreen:
			initEditor();
			break;
		default:
			break;
		}
	}

	// gets
	public int getCurrentScreen(){
		return currentScreen;
	}
	
}
