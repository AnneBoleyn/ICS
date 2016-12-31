import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * always trying to get in front of the player
 *  to block their path. Pinky is programmed to 
 *  take a route to a position four spaces ahead
 *   of wherever Pac-Man’s moving.
 */

public class Pinky extends Ghost{

	public Pinky(RandAlThor player) {
		super( new ImageIcon("pink.png"), player);
	}

	public void move(RandAlThor player, String dir){
		chase(player, dir);
	}
	
	
	}

