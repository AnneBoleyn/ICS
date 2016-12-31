import java.util.*;

public class Hogwarts {
	public static ArrayList <Hogwarts> coordinates = new ArrayList <Hogwarts> ();
	public static ArrayList <Hogwarts> maxY = new ArrayList <Hogwarts> ();
	public int x, y; 
	boolean extra = false;
	public static boolean scrolling = false;
	public static int max = 0; 
	public Hogwarts(){
		y = 0;
		int num;
		do {
			num = (int) (Math.random() * 30 + 1);
		} while (contains(num,0));
		int side;
		do {
			side = (int) (Math.random() * 10 + 5);
		} while ((side + num) > 35);
		makeSquare(num,y,side);
	}
	public Hogwarts (int x, int y){
		this.x = x;
		this.y = y;
		if (!(contains(x,y))){
			coordinates.add(this);
		}
	}
	public Hogwarts (int x, int y, boolean a){
		this.x = x;
		this.y = y;
		if (!(contains(x,y))){
			coordinates.add(this);
			maxY.add(this);
		}
	}
	public static void makeSquare(int x, int y, int side){
		max ++;
		if (max >= 3){
			maxY.clear();
		}
		for (int i = 0; i <= side; i ++){
			new Hogwarts(x, y + i);
			new Hogwarts((x+side), (y + i));
			new Hogwarts((x + i), y);
			new Hogwarts((x + i), (y + side), true);
		}
		
	}
	public static void negSquare(int x, int y, int side){
		max++;
		if (max >= 3){
			maxY.clear();
		}
		for (int i = 0; i <= side; i ++){
			new Hogwarts(x, y + i);
			new Hogwarts((x - side), (y + i));
			new Hogwarts((x - i), y);
			new Hogwarts((x - i), (y + side), true);
		}
	}
	/*
	public void addLines(){
		int index = (int) (Math.random() * maxY.size() - 1);
		Hogwarts place1 = maxY.get(index);
		ArrayList <Hogwarts> list = new ArrayList <Hogwarts> (coordinates.subList()
	}
	*/
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
	public static void extend(int a){
		boolean s = false;
		map(a);
		int place;
		do {
			place = (int)(Math.random() * (maxY.size()-1));
		} while (maxY.get(place).x > 30);
		int top = (int)(Math.random() * 3 + 1);
		int x = maxY.get(place).x;
		int y = maxY.get(place).y - top;
		int pos = (int) (Math.random() * 2 + 1);
		int side;
		if (x >= 30){
			pos = 2;
		}
		if (x <= 5){
			pos = 1;
		}
		if (pos == 1){
			do {
				side = (int) (Math.random() * 10 + 5);
			} while ((side + x) > 35); 
		}
		else{
			do {
				side = (int) (Math.random() * 10 + 5);
			} while ((x - side ) < 1);
		}
		for (int i = maxY.size() -1; i >= 0; i --){
			if (maxY.get(i).y == y){
				maxY.remove(i);
			}
		}
		if (pos == 1){
			makeSquare(x,y,side);
		}
		else{
			negSquare(x,y,side);
		}
		
	}
	public static void map(int y){
		if (y >= 20){
			scrolling = true;
		}
	}
	public static void scroll(){
		for (int i = coordinates.size() -1; i >= 0; i = i -1){
			if (coordinates.get(i).y < -100){
				coordinates.remove(i);
			}
			else{
				coordinates.get(i).y = coordinates.get(i).y-1;
			}
		}
	}
	public static void add(){
		int num;
		do{
			num = (int) (Math.random() * coordinates.size());
		} while (coordinates.get(num).extra == true);
		coordinates.get(num).extra = true; 
	} 
	public static boolean getExtra(int x, int y){ 
		for (int i = 0; i < coordinates.size(); i ++){
			if (coordinates.get(i).x == x && coordinates.get(i).y == y){
				if (coordinates.get(i).extra){
					coordinates.get(i).extra = false;
					return true;
				} 
				else{
					return false; 
				}
			}
		}
		return false;
	}
	public static Hogwarts getPlace(int x, int y){
		Hogwarts hogs = coordinates.get(0);
		for (int i = 0; i < coordinates.size(); i ++){
			if (coordinates.get(i).x == x && coordinates.get(i).y == y){
				hogs = coordinates.get(i);
				return hogs;
			}
		}
		return hogs;
	}
}
