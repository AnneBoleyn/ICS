import java.util.*;

public class Map {
	public static ArrayList <Map> coordinates = new ArrayList <Map> ();
	public static ArrayList <Map> maxY = new ArrayList <Map> ();
	public int x, y; 
	public Map(){
		this.y = 0;
		int num;
		do {
			num = (int) (Math.random() * 10 + 1);
		} while (contains(num,0));
		this.x = num;
		coordinates.add(this);
		maxY.add(this);
	}
	public Map (int x, int y){
		this.x = x;
		this.y = y;
		coordinates.add(this);
	}
	public static boolean contains(int xvalue, int yvalue){
		for (int i = 0; i < coordinates.size(); i ++){
			if (coordinates.get(i).y == yvalue){
				if (coordinates.get(i).x == xvalue){
				
					return true;
				}
			}
		}
		return false;
	}
	public static void printCo(){
		for (int i = 0; i < coordinates.size(); i ++){
			//System.out.println(coordinates.get(i).x + "," + coordinates.get(i).y);
		}
	}
	public static boolean extend(){
		boolean s = false;
		for (int i = 0; i < maxY.size(); i ++){
			int x = maxY.get(i).x;
			int y = maxY.get(i).y;
			int random = (int) (Math.random() * 6 + 1);
			if (y >= 11){
				scroll();
				s = true;
				return s;				
			}
			if (random < 2){
				x = x -1;
			}
			else if (random < 3){
				x = x + 1;
			}
			else{
				y = y + 1;
			}
			Map map = new Map(x,y);
			maxY.set(i, map);
		}
		return s;
	}
	public static void scroll(){
		for (int i = coordinates.size() -1; i >= 0; i = i -1){
			//System.out.println(coordinates.get(i).x + " " + coordinates.get(i).y);
			if (coordinates.get(i).y < 1){
				coordinates.remove(i);
			}
			else{
				coordinates.get(i).y = coordinates.get(i).y-1;
				//System.out.println(coordinates.get(i).x + "+" + coordinates.get(i).y);
			}
		}
		extend();
	}
}
