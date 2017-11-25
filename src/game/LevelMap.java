package game;

//import java.util.ArrayList;

/************************
 * c - color:
 * red      0
 * blue     1
 * darkblue 2
 * orange   3
 * 
 * d - direction:
 * right 0
 * down  1
 * left  2
 * up    3
 * 
 * t - type:
 * square   0
 * circle   1
 * triangle 2
 ************************/

public class LevelMap extends PlayState {

	/* 1 (start)
	 * type, x, y, c, d
	 * type, x, y, c, d
	 * ...
	 * 9 (end)
	 * 
	 * level nr2
	 * ...
	 * 
	 * x = 3 - center
	 * y = 3 & 4 = center
	 */
	
	public static int i = 1; // current cursor position for reading the array
	public static int search_cursor;
	public static int[] map = 
		{
		1, // first one is ignored
		0, 3, 2, 0, 1,
		0, 1, 1, 0, 1,
		1, 3, 4, 0,
		9,
		0, 3, 2, 1, 1,
		1, 3, 3, 1,
		0, 3, 5, 0, 3,
		1, 3, 4, 0,
		9
		};

	public static void readMap(){
		readMap(map, map.length);
	}
	
	public static void readMap(int[] map, int length){
		
		while(i < length){
			if(map[i] == 9 || i == length-1){ // if level ends, stop here
				level++;
				i++;
				System.out.println(i);
				return;
			} 
			if(map[i] == 0){ 	// if type is square, read next 4 digits as square properties
				squareList.add(new GameSquare(map[i+1], map[i+2], map[i+3], map[i+4]));	// adds new square to square array list
				i += 4;	// moves cursor 4 digits forward
			}
			else
			if(map[i] == 1){	// if type is circle, read next 3 digits as circle properties
				circleList.add(new GameCircle(map[i+1], map[i+2], map[i+3]));			// adds new circle to circle array list
				i += 3;	// moves cursor 3 digits forward
			}
			else
//			if(map[i] == 2 && i+3 < map.length){
			if(map[i] == 2){	// if type is triangle, read next 3 digits as triangle properties
				triangleList.add(new GameTriangle(map[i+1], map[i+2], map[i+3]));		// adds new triangle to triangle array list
				i += 3;	// moves cursor 3 digits forward
			}
			i++;		// next digit
		}
	}
	
	public static void gotoLvl(int searched_lvl){
		
		int counter_lvl = searched_lvl;
		if(searched_lvl == 1){	// if searched level is 1, sets level as one, and moves cursor to first digit
			i = 1;
			level = 1;
		}
		else
		for(search_cursor = 1; search_cursor < map.length; search_cursor++){
			if(map[search_cursor] == 9){	// for every 9 (end level) found,
				counter_lvl--;				// decreases the counter with one
				if(counter_lvl == 1) i = search_cursor + 1; // when the counter = 1, the level was found
				level = searched_lvl;
				return;
			}
		}
		
		System.out.println("No such level");
		
	}
	
}
