import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.util.*;
public class GamePanel extends JPanel implements ActionListener,KeyListener{
	public  RandAlThor rand; 
	public Timer myTimer;
	Pinky pinky; 
    Red red; 
    public Hogwarts hogwarts;
    public boolean moveDown = true; 
	public boolean moveSide = false; 
	public String direction; 
	public boolean start = false;
	public GamePanel()
	{  	  
		//added
		this.setLayout(null);
		this.setOpaque(true);
		this.setBackground(Color.BLUE);
    	this.setBorder(BorderFactory.createLineBorder(Color.black));
    	this.setBounds(0, 0, GUI.width, GUI.height);
		hogwarts = new Hogwarts();	
		rand = new RandAlThor();
		//added 
		int loc = rand.location;
		red = new Red(rand); 
		//pinky = new Pinky(rand);
		this.setFocusable(true); 
		this.requestFocusInWindow(); 
		addKeyListener(this);		
		this.play("");  
		//added
		this.add(red.getLabel());
		//this.add(pinky.getLabel());
		myTimer = new Timer(200,this);  
		myTimer.setActionCommand("Timer"); 
		myTimer.start();
		for (int i = 0; i < 2; i ++){
			hogwarts.extend(rand.getY());
		}
		for (int i = 0; i < 20; i ++){
			hogwarts.add();
		}
		
	}   
	public void play(String direction){
		rand.move(direction);
		Hogwarts.map(rand.getY());
		if (Hogwarts.scrolling){
		}
		else{
			Hogwarts.extend(rand.getY());
		}
		//repaint();
	} 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int i = 0; i < Hogwarts.coordinates.size(); i ++){
			int x = Hogwarts.coordinates.get(i).x;
			int y = Hogwarts.coordinates.get(i).y;
			if (!(Blinking.contains(x,y))){
				g.drawRect(x*20,y*20,20,20);
				g.setColor(Color.WHITE);
				g.fillRect(x*20,y*20,20,20); 
				if (Hogwarts.coordinates.get(i).extra){
					g.setColor(Color.RED);
					g.fillOval(x*20, y * 20, 10, 10);
				}  
			}
		}   
		int xr = rand.getX(); 
		int yr = rand.getY();
		boolean extra = Hogwarts.getExtra(xr,yr);
		g.setColor(Color.BLACK);
		g.fillOval(xr*20,yr*20,20,20);
		if (extra){
			rand.followers ++;
		}   
		rand.addPlaces(Hogwarts.getPlace(xr,yr));
		for (int i = 0; i < rand.tail.size(); i ++){
			int x = rand.tail.get(i).x;
			int y = rand.tail.get(i).y;
			g.setColor(Color.BLUE);
			g.fillOval(x*20,y*20,10,10);
		} 
	}     
	//added
	public void ghostmove(){
		red.caughtTail();
		boolean frozen = red.frozen();
		if (frozen){
			red.countDown();
		}  
		else{
			red.move(rand);
		} 
 	}       
	//keyListener 
	public void keyPressed(KeyEvent e){ 
		start = true;
		if (e.getKeyCode() == 37){    
			direction = "W"; 
			moveDown = false; 
			moveSide = true;
		}
		if (e.getKeyCode() == 39){   
			direction = "E"; 
			moveDown = false;
			moveSide = true;
		}
		
	}  
	public void keyReleased(KeyEvent e){
	}
	public void keyTyped(KeyEvent e){		
	}   
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand() == "Timer")
		{ 
			if(rand.lives > 0){
				//added
				if (moveSide){
					play(direction);
					moveSide = false;
				}  
				else{ 
					boolean move;
					Hogwarts.map(rand.getY());
					boolean map = Hogwarts.scrolling;
					if (map){
						move = rand.moveDown(1);
						if (move){
							Hogwarts.scroll();
							red.scroll();
						}
						else{
						}  
					} 
					else{
						move = rand.moveDown();
					}
					Hogwarts.extend(rand.getY());
					Hogwarts.add();
				}
				if (start){
					ghostmove();
				}
				repaint();
				Blinking.allBlink();
			}
		}
	}
} 

