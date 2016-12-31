import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

//parent ghost class 

public class Ghost {

		boolean line = false;
		protected int gx; 
		protected int gy; 
		private int size = 20; 
		private JLabel ghost;
		public  ArrayList <Paths> coo = new ArrayList <Paths> ();
		public int freeze = 0;
		
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
			System.out.print("lost!!!");
		caughtTail();
	}
	
	
	public void chase(RandAlThor player, String dir){
	//px and py are square numbers
		
	int rx = gx/size; 
	int ry = gy/size;

	//System.out.println(player.getX() + "  " + rx);
	//horizontal

	
	if (player.getX() == rx)
	{
	
		if (ry < player.getY())
		{
			if (Hogwarts.contains(gx/size, gy/size+1))
				gy += size; 
		}
		else if (ry>player.getY())
		{
			if (Hogwarts.contains(gx/size, gy/size-1))
				gy -= size; 
		}
	}
	else if (player.getY() == ry)
	{
	
		//ghost on top of player 
		if (rx > player.getX())
		{
			if (Hogwarts.contains(gx/size-1, gy)) 
				gx -= size; 
	}
		else if (rx < player.getX())
		{
			if (Hogwarts.contains(gx/size+1, gy)) 
				gx += size; 
		}
	}
	else
		findpath(player, dir);

	

	ghost.setLocation (gx,  gy);
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
			
		
	
	
	public void findpath(RandAlThor player, String dir){
		
		int rx = gx/size; 
		int ry = gy/size; 
		int px = player.getX();
		int py = player.getY(); 
		
		if (dir.equals("E"))
			px -= 4; 
		if (dir.equals("W"))
			px += 4;
			
		
		//if ghost is up and left 
		if (rx < px && ry< py)
		{
			 if (Hogwarts.contains(rx, ry+1))
				gy+=size;
			 else if (Hogwarts.contains(rx+1, ry))
				gx+=size;	
		}
		//if ghost is down left
		else if (rx<px && ry>py)
		{
			if (Hogwarts.contains(rx+1, ry))
				gx+=size;
			else if (Hogwarts.contains(rx, ry-1))
				gy-=size;
		}
		//if ghost is right and up 
		else if (rx>px && ry<py)
		{
			if (Hogwarts.contains(rx, ry+1))
				gy+=size;
			else if (Hogwarts.contains(rx-1, ry))
				gx-=size;
		}
		//if ghost is right and down 
		else if (rx > px && ry> py)
		{
			 if (Hogwarts.contains(rx, ry-1))
				gy-=size;
			 else if (Hogwarts.contains(rx+1, ry))
				gx+=size;	
		}
		else ;
		
			
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


	
