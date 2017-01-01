import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.*;
public class RandAlThor{
	private int x,y;
	public int location; 
	Map l;
	public static int followers = 0;
	int score; 
	public static ArrayList <Hogwarts> places = new ArrayList <Hogwarts>();
	public static ArrayList <Hogwarts> tail = new ArrayList <Hogwarts>();
	public static int index;
	public static int lives = 3;
	public RandAlThor(){
		score = 0;
		int location;
		do{
			location = (int) (Math.random() * Hogwarts.coordinates.size());
			Hogwarts l = Hogwarts.coordinates.get(location);
			x = l.x;
			y = l.y;
		}while(y > 0);
		index = location;
	}
	public RandAlThor(int x, int y){
		this.x = x;
		this.y = y;
	}
	public static void moveTail(){
		tail.clear();
		for (int i = 0; i < followers; i ++){
			tail.add(places.get(i));
		}
	}
	
	public void move(String direction){
		if (direction.equals("E")){
			if (Hogwarts.contains(x+1,y) && !(Blinking.contains(x+1,y))){  
				x = x + 1;
				moveTail();
			}
		}
		if (direction.equals("W")){
			if (Hogwarts.contains(x-1,y) && !(Blinking.contains(x-1,y))){
				x = x - 1; 
				moveTail();
			}
		}
	}
	public void subtractY(){
		y = y-1;
	}
	public  boolean moveDown(){
		if (Hogwarts.contains(x, y +1) && !(Blinking.contains(x, y +1))){
			y = y + 1;
			moveTail();
			return true;
		}
		return false;
	}
	public boolean moveDown(int i){
		if (Hogwarts.contains(x, y+1) && !(Blinking.contains(x, y + 1))){
			moveTail();
			return true;
		}
		return false;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public static void addPlaces(Hogwarts hogs){
		Collections.reverse(places);
		places.add(hogs);
		Collections.reverse(places);
	}
	public static boolean checkTail(int x, int y){
		for (int i = 0; i < tail.size(); i ++){
			if (tail.get(i).x == x && tail.get(i).y == y){
				tail= new ArrayList <Hogwarts> (tail.subList(0,i));
				followers = tail.size();
				return true;
			}
		}
		return false;
	}
	public static void loseLife(){
		lives --;
	}
}
