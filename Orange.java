import javax.swing.ImageIcon;

/* Chases if more than 8 places away 
 * Onces 8 or less, goes back to the corner
 */

public class Orange extends Ghost{

	boolean c = false;
	public Orange(RandAlThor player) {
		super( new ImageIcon("orange.png"), player);
	}
	
	public void move(RandAlThor player)
	{
		if (Math.abs(player.getX() - gx) + Math.abs(player.getY() - gy) > 8)
			chase(player, "");
		else 
			scatter();
	}

}
