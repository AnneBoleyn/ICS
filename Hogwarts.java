import java.util.*;

public class Hogwarts {
	public static ArrayList <Hogwarts> coordinates = new ArrayList <Hogwarts> ();
	public static ArrayList <Hogwarts> maxY = new ArrayList <Hogwarts> ();
	public static ArrayList <Hogwarts> walls = new ArrayList <Hogwarts> ();
	public int x, y; 
	boolean extra = false;
	public static boolean scrolling = false;
	public static int max = 0; 
	public static int count = 0;
	public Hogwarts(){
		for (int i = 0; i < 35; i ++){
			for (int a = 0; a < 90; a ++){
				new Hogwarts(i,a);
			}
		}
		createPassage(0, coordinates.size()-1);
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
	public static void createPassage(int start, int end){
		ArrayList <Hogwarts> places = new ArrayList <Hogwarts> (coordinates.subList(start,end));
		int y = places.get(0).y;
		int y2 = places.get(places.size() -1).y;
		for (int i = y + 1; i < y2 + 1; i = i +2){
			int times = (int) (Math.random() * 5 + 4);
			ArrayList <Integer> ints = new ArrayList <Integer> ();
			for (int j = 0; j < times; j ++){
				int x = 0;
				do{
					x = (int) (Math.random() * 30);
				} while (!(contains(x,i)) && !(ints.contains(x)));
				ints.add(x);
			}
			for (int a = 0; a < 35; a ++){
				if (!(ints.contains(a))){
					delete(a,i);
				}
			}
				/*
				int index; 
				int x = 0;
				do{
					x = (int) (Math.random() * 30);
				} while (!(contains(x,i)));
				int side = 0;
				do{
					side = (int) (Math.random() * (maxLength(x, i)) + 5);
				} while (x + side > 35);
				for (int a = x; a < (x + side); a ++){
					delete(a,i);
				}
				*/
		}
	}
	public static int maxLength(int x, int y){
		int length = 0;
		while (contains(x,y)){
			length ++;
			x ++;
		}
		length = length - 10;
		if (length < 0){
			length = 0;
		}
		return length;
	}
	public static boolean checkDeadEnds(int x, int y){
		if (contains(x + 1,y)){
			return checkDeadEnds(x + 1,y);
		}
		else{
			if (contains(x,y + 1)){
				return true;
			}
			else{
				return false;
			}
		}
	}
	public static boolean checkDeadEndsLeft(int x, int y){
		if (contains(x - 1,y)){
			return checkDeadEnds(x - 1,y);
		}
		else{
			if (contains(x,y + 1)){
				return true;
			}
			else{
				return false;
			}
		}
	}
	public static void delete(int x, int y){
		for (int a = 0; a < coordinates.size(); a ++){
			if (coordinates.get(a).x == x && coordinates.get(a).y == y){
				walls.add(coordinates.get(a));
				coordinates.remove(a);
				break;
			}
		}
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
	public static int getMax(){
		int max = coordinates.get(coordinates.size()-1).y;
		return max;
	}
	public static void extend(int f){
		int index1 = coordinates.size();
		int max = getMax() + 1;
		for (int i = 0; i < 35; i ++){
			for (int a = max; a < (max + 10); a ++){
				new Hogwarts(i,a);
			}
		}
		createPassage(index1, coordinates.size() -1);
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
