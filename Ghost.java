import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.*;

//parent ghost class 

public class Ghost {

		boolean line = false;
		protected int gx; 
		protected int gy; 
		private int size = 20; 
		private JLabel ghost;
		public int freeze = 0;
		//public Paths shortest = new Paths(); 
		//public Paths path = new Paths();
		
		private  ArrayList <Coordniates>  path = new ArrayList <Coordniates> ();
		private  ArrayList <Coordniates>  shortest = new ArrayList <Coordniates> ();
		
	    //constructor
	public Ghost(ImageIcon img, RandAlThor player) {

		ghost = new JLabel(img); 
		int location; //= (int) (Math.random() * Hogwarts.coordinates.size());
		do{
			location = (int) (Math.random() * Hogwarts.coordinates.size());
			Hogwarts l = Hogwarts.coordinates.get(location);
			gx = l.x;
			gy = l.y ;
		} while (player.getX() == gx || player.getY() == gy);
		
		gx*=size;
		gy*=size;
		ghost.setLocation (gx, gy);
		ghost.setSize (size, size) ;
		
	}
	
	//random movement method
	public void scatter()
	{
		int x = gx/size; 
		int y = gy/size;
		
		int dir = (int)(Math.random()*4);
		//System.out.print(dir);
		
		if (dir == 0 && Hogwarts.contains(x-1,y))
			gx -= size;
		else if (dir == 0 && Hogwarts.contains(x+1,y))
			gx += size; 
		else if (dir == 1 && Hogwarts.contains(x,y-1))
			gy -= size; 
		else if (dir == 2 && Hogwarts.contains(x,y+1))
			gy += size; 
		else if (dir == 3 && Hogwarts.contains(x,y+1))
			gy += size; 
		else if (Hogwarts.contains(x,y+1))
			gy += size; 
	
		
		ghost.setLocation (gx, gy);

	}
	public JLabel getLabel()
	{
		return ghost;
	}
	
	public void checkcol(RandAlThor player) 
	{
		if (player.getX() == gx/size && player.getY() == gy/size)
		{
			System.out.print("lost!!!");
			//INSTEAD OF LOSING 
			
			System.exit(0);
		}
		//if (shortest.size() < 2)
		//{
			//CODE FOR LOOSING
		//	System.exit(0);
		//}
		
		caughtTail();
		
	}
	

	
	public void chase(RandAlThor player, String dir){
		
		
		
		shortest.clear();
		path.clear();
		find(player, gx/size, gy/size);
		//set the ghost location 
		//shortest = sendpath;'
		if (shortest.size() < 2)
		{
			gx = player.getX()*size;
			gy = player.getY()*size;
		}
		else
		{
			gx = shortest.get(1).x * size;
			gy =  shortest.get(1).y*size;
			ghost.setLocation(gx, gy);
		}
			checkcol(player);
	
	
	}//end chase
	
public void scroll(){
			gy-=size; 
			
			//System.out.println(gy);
			if (gy<=0)
			{
				do 
				{
				gy =(int) (Math.random()*20)*size;
				gx =(int) (Math.random()*20)*size;
				}while (!Hogwarts.contains(gx/size, gy/size));
			}
			ghost.setLocation(gx, gy);
		}
			
		
public boolean ccontains(int xvalue, int yvalue){
	for (int i = 0; i < path.size(); i ++){
		if ((path.get(i).y == yvalue) && (path.get(i).x == xvalue)){
			return true;
		}
	}
	return false;
}
	
		
	//method to find the shortest path to character 
	public void find (RandAlThor player, int x, int y){
		checkcol(player);
	//base cases 
		if (path.size() > 0 && shortest.size() > 0 && path.size() > shortest.size())
			return;
		if (!Hogwarts.contains(x, y))
		{
			//System.out.print("out of map");
			return; 
		}
		else if (ccontains(x, y))
		{
		//	System.out.println("foundpath");
			return; 
		}
		else if ((x == player.getX()) && (y == player.getY()))
		{
			//System.out.println(path.size() + "   " + path.get(1).x + " " + path.get(1).y);
			//if (shortest.size() != 0)
				//System.out.println(shortest.size() + "   " + shortest.get(1).x + " " + shortest.get(1).y);
			//else
			//	System.out.println(" 0 0 0 ");
			if (shortest.size() == 0)
			{
				shortest.clear();
				for (int i = 0; i < path.size(); i++)
					shortest.add(path.get(i));
			}
			if (path.size() < shortest.size())
			{
				shortest.clear();
				for (int i = 0; i < path.size(); i++)
					shortest.add(path.get(i));
			}
			return;
			 
		}
		path.add(new Coordniates(x, y)); 
		//path.addcoo(x+1, y);
		find(player, x-1, y); 
		//path.addcoo(x-1, y);
		find(player, x, y-1); 
		//path.addcoo(x, y+1);
		find(player, x+1, y); 
		//path.addcoo(x, y-1);
		find(player, x, y+1); 
		
		path.remove(path.size()-1); 
		return;
		
	}
	 
	
	public void caughtTail(){
		boolean tail = RandAlThor.checkTail(gx/size, gy/size);
		if (tail){
			freeze = 5;
		}
	}
	
	public boolean frozen(){
		if (this.freeze > 0){
			return true;
		}
		else{
			return false;
		}
	}
	public void countDown(){
		//System.out.println("frozen: " + freeze + " " + gx + " " + gy);
		this.freeze --;
	}

	
	}//end class


	
