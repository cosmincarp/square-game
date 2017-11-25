package game;


import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;


public class EditorState implements GameState {
	
	private static GameMouse cursor;
	
	protected GameButton back;
	
	private static ArrayList<GameObj> gameobjList = new ArrayList<GameObj>();
	
	private static int pen_x;
	private static int pen_y;
	private static boolean hasObjinHand = false;
	private static int next_index = 0;
	
	private static int[] maparray = new int[200];
	private static int mapSize;
	
	public EditorState(){
		
		cursor = new GameMouse();
		back = new GameButton(10f, (float)Display.getHeight() - 74f, 64, 64, "back");
		back.setTexCoord(6, 0, 7, 1);
		
	}

	public void render() {
		drawBG();
		
		back.drawObj();
		
		for(GameObj c : gameobjList){
			c.drawObj();
		}
		
	}

	public void update() {
		
		cursor.update();
		
		back.hoverEffectGameButtons(cursor);
		
		if(back.isClicked(cursor)){
			GameContainer.setCurrentScreen(0);
		}
		
		pen_x = (int)(cursor.x / GameSquare.tile_size);
		pen_y = (int)(cursor.y / GameSquare.tile_size);
		
		// updates positions
		
		for (GameObj o : gameobjList){
//			if(o.canBeMoved && o.isInHand){
			if(o.isInHand){
				o.x = pen_x * GameSquare.tile_size;
				o.y = pen_y * GameSquare.tile_size + 16;
				o.rel_x = pen_x - 4;
				o.rel_y = 7 - pen_y ;
			}
		}
		
		// key listener
		while(Keyboard.next()){
			if(Keyboard.isKeyDown(Keyboard.KEY_BACK)){
				GameContainer.setCurrentScreen(0);
			}
			
			// Create objects
			if(Keyboard.isKeyDown(Keyboard.KEY_1) && !hasObjinHand){
				gameobjList.add(new GameSquare(cursor.x - 48, cursor.y - 48, 0, 0));
				hasObjinHand = true;
				gameobjList.get(next_index).isInHand = true;
				gameobjList.get(next_index).listPosition = next_index;
				next_index++;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_2) && !hasObjinHand){
				gameobjList.add(new GameTriangle(pen_x * GameSquare.tile_size, pen_y * GameSquare.tile_size, 0));
				hasObjinHand = true;
				gameobjList.get(next_index).isInHand = true;
				gameobjList.get(next_index).listPosition = next_index;
				next_index++;
			}			
			if(Keyboard.isKeyDown(Keyboard.KEY_3) && !hasObjinHand){
				gameobjList.add(new GameCircle(pen_x * GameSquare.tile_size, pen_y * GameSquare.tile_size, 0));
				hasObjinHand = true;
				gameobjList.get(next_index).isInHand = true;
				gameobjList.get(next_index).listPosition = next_index;
				next_index++;
			}
			
			
			// change properties
			if(Keyboard.isKeyDown(Keyboard.KEY_D)){
				for(GameObj o : gameobjList){
					if((o.isInHand && o instanceof GameSquare) || (o.isInHand && o instanceof GameTriangle)){						
						o.dir = 0;
						if(o instanceof GameSquare) ((GameSquare)o).setColorTex();
						if(o instanceof GameTriangle) ((GameTriangle)o).setColorTex();
					}
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_S)){
				for(GameObj o : gameobjList){
					if((o.isInHand && o instanceof GameSquare) || (o.isInHand && o instanceof GameTriangle)){						
						o.dir = 1;
						if(o instanceof GameSquare) ((GameSquare)o).setColorTex();
						if(o instanceof GameTriangle) ((GameTriangle)o).setColorTex();
					}
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_A)){
				for(GameObj o : gameobjList){
					if((o.isInHand && o instanceof GameSquare) || (o.isInHand && o instanceof GameTriangle)){						
						o.dir = 2;
						if(o instanceof GameSquare) ((GameSquare)o).setColorTex();
						if(o instanceof GameTriangle) ((GameTriangle)o).setColorTex();
					}
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_W)){
				for(GameObj o : gameobjList){
					if((o.isInHand && o instanceof GameSquare) || (o.isInHand && o instanceof GameTriangle)){						
						o.dir = 3;
						if(o instanceof GameSquare) ((GameSquare)o).setColorTex();
						if(o instanceof GameTriangle) ((GameTriangle)o).setColorTex();
					}
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
				for(GameObj o : gameobjList){
					if((o.isInHand)){						
						hasObjinHand = false;
						o.isInHand = false;
						for(int i = o.listPosition+1; i < gameobjList.size(); i++)
							gameobjList.get(i).listPosition--;
						gameobjList.remove(o.listPosition);							
						next_index--;
						return;
					}
				}
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
				for(GameObj o : gameobjList){
					if((o.isInHand && o instanceof GameSquare) || (o.isInHand && o instanceof GameCircle)){						
						o.color = 0;
						if(o instanceof GameSquare){
							((GameSquare)o).setColorTex();
							((GameSquare)o).backSquare();
						}
						if(o instanceof GameCircle) ((GameCircle)o).setColorTex();
					}
				}				
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_X)){
				for(GameObj o : gameobjList){
					if((o.isInHand && o instanceof GameSquare) || (o.isInHand && o instanceof GameCircle)){						
						o.color = 1;
						if(o instanceof GameSquare){
							((GameSquare)o).setColorTex();
							((GameSquare)o).backSquare();
						}
						if(o instanceof GameCircle) ((GameCircle)o).setColorTex();
					}
				}				
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_C)){
				for(GameObj o : gameobjList){
					if((o.isInHand && o instanceof GameSquare) || (o.isInHand && o instanceof GameCircle)){						
						o.color = 2;
						if(o instanceof GameSquare){
							((GameSquare)o).setColorTex();
							((GameSquare)o).backSquare();
						}
						if(o instanceof GameCircle) ((GameCircle)o).setColorTex();
					}
				}				
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_V)){
				for(GameObj o : gameobjList){
					if((o.isInHand && o instanceof GameSquare) || (o.isInHand && o instanceof GameCircle)){						
						o.color = 3;
						if(o instanceof GameSquare){
							((GameSquare)o).setColorTex();
							((GameSquare)o).backSquare();
						}
						if(o instanceof GameCircle) ((GameCircle)o).setColorTex();
					}
				}				
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
				createMap();
				GameContainer.gameState[4] = new TestState(maparray, mapSize);
				GameContainer.setCurrentScreen(4);
			}
		}
		
		while(Mouse.next()){
			if(Mouse.isButtonDown(0) && hasObjinHand){
				for(GameObj o : gameobjList){
					if(o.isInHand){
						hasObjinHand = false;
						o.isInHand = false;
						o.canBeMoved = false;
					}
				}
			}
			else
			if(Mouse.isButtonDown(0) && !hasObjinHand){
				for(GameObj o : gameobjList){
					if(o.isHovered(cursor)){
						hasObjinHand = true;
						o.isInHand = true;
						o.canBeMoved = true;
					}
				}
			}
		}
		
	}
	
	private static void createMap(){
		ArrayList<Integer> map = new ArrayList<Integer>();
		for(GameObj o : gameobjList){
			switch (o.type) {
			case SQUARE:
				map.add(0);
				map.add(o.rel_x);
				map.add(o.rel_y);
				map.add(o.color);
				map.add(o.dir);
				break;
			case CIRCLE:
				map.add(1);
				map.add(o.rel_x);
				map.add(o.rel_y);
				map.add(o.color);
				break;
			case TRIANGLE:
				map.add(2);
				map.add(o.rel_x);
				map.add(o.rel_y);
				map.add(o.dir);
				break;
			default:
				System.out.println("Read map error");
				break;
			}
		}
		mapSize = map.size();
		for(int i = 0; i < map.size(); i++){
			maparray[i] = map.get(i);
		}

		
	}
	
	private static void drawBG(){
		for(int i = 0; i < 20; i++)
			for(int j = -1; j < 10; j++){
				Draw.drawRect(i * GameSquare.tile_size, j * GameSquare.tile_size + 16, GameSquare.tile_size-1, GameSquare.tile_size-1, 1f, 1f, 1f, 1f);
			}
	}
	
}
