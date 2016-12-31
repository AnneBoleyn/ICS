import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class Red extends Ghost{
	
	JPanel panel;
	
	public Red(RandAlThor player) {
		super( new ImageIcon("red.png"), player);
		
	}

	public void move(RandAlThor player){
		chase(player, "");
	}
// chases player

}