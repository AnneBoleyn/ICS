import java.util.*;
public class Blinking extends Hogwarts{
	public int time;
	public int count = 0;
	public boolean blink;
	public static ArrayList <Blinking> blinkBlocks = new ArrayList <Blinking> ();
	public static ArrayList <Blinking> wall = new ArrayList <Blinking> ();
	public Blinking(int x, int y){
		super(x,y,1);
		blink = false;
		time = (int) Math.random() * 5 + 10;
		blinkBlocks.add(this);
	}
	public void blinking(){
		count ++;
		if (count == time){
			count = 0;
			if (blink){
				blink = false;
				blinkIn();
			}
			else {
				blink = true;
				blinkOut();
			}
		}
	}
	public void blinkIn(){
		wall.add(this);
	}
	public void blinkOut(){
		//System.out.println("blinking");
		for (int a = 0; a < wall.size(); a ++){
			int x = wall.get(a).x;
			int y = wall.get(a).y;
			if (x == this.x && y == this.y){
				wall.remove(a);
				break;
			}
		}
	}
	public static void allBlink(){
		for (int i = 0; i < blinkBlocks.size(); i ++){
			blinkBlocks.get(i).blinking();
		}
	}
	public static boolean contains(int x, int y){
		for (int i = 0; i < wall.size(); i ++){
			if (wall.get(i).x == x && wall.get(i).y == y){
				return true;
			}
		}
		return false;
	}
}
