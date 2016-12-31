import javax.swing.ImageIcon;

public class Cyan extends Ghost{

//	scatters until it 'sees' player, chases him 


	boolean c = false;
	public Cyan(RandAlThor player) {
		super( new ImageIcon("cyan.png"), player);
	}
	
	public void move(RandAlThor player)
	{
	if (player.getX() == gx || player.getY() == gy)	
		chase(player, ""); 
	if (Math.abs(player.getX() - gx) + Math.abs(player.getY() - gy) > 10)
		scatter(); 
	
	}	
	
	}

