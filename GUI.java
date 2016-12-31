

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JPanel implements ActionListener   {
	
	
    //create A layered pane
    private JLayeredPane layeredPane;
    private JButton playb, exitb, rulesb, scoresb, menub, menub2; 
    JPanel menup, scorep, rulesp;
    GamePanel gamep;
    String[] options = new String[] {"Exit", "No"};
    static int width = 700; 
    static int height = 700; 
    private Timer timer; 
  
    
    public GUI()    {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        //Create and set up the layered pane.
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(width, height));
     
        //buttons
        playb = new JButton("Play");
        setupbutton(playb, width/2 - 125, height - 300	, new ImageIcon("play.png"), 250, 200);
		
        exitb = new JButton("Exit");
        setupbutton(exitb, 100, 0, new ImageIcon("red.png"), 100, 50);
        
  		
        rulesb = new JButton("How To Play");
        setupbutton(rulesb, 200, 0, new ImageIcon("pink.png"), 100, 50);
        
      
        scoresb = new JButton("High Scores");
        setupbutton(scoresb, 300, 0, new ImageIcon("dead.png"), 100, 50);
        
  		
        menub = new JButton("Menu");
        menub.addActionListener(this);
        menub.setLocation (50, 0);
        menub.setSize (100, 50);
  		
        menub2 = new JButton("Menu");
        menub2.addActionListener(this);
        menub2.setLocation (50, 100);
        menub2.setSize (100, 50);
        
        //create panels  and add them to layered pane
        //VALUES FOR MENU AND GAME PANELS SWITCHED. SWITCH 2 AND 3 . 
        	menup = makePanels();
        	layeredPane.add(menup,  new Integer(3));
        	
        	
        	 //add buttons to menu panel
        	menup.add(playb);
        	menup.add(rulesb);
        	menup.add(scoresb);
        	menup.add(exitb);
        	
        
        	gamep = new GamePanel();
        	layeredPane.add(gamep,  new Integer(2));

			
        	//scores panel
        	scorep = makePanels();
        	layeredPane.add(scorep,  new Integer(1));
        	scorep.add(menub2);
        	
        	//rules panel
        	rulesp = makePanels();
        	layeredPane.add(rulesp,  new Integer(0));
        	rulesp.add(menub);
        	
       
        add(layeredPane);
    }


 
    public JPanel getG() 
    {
    	return gamep;
    }
    
    @Override
	public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == timer)
    	{
    		
    	}
    	else 
    	{
    		//buttons switch between panels 
    	JButton b = (JButton)e.getSource();
        if (b.getText().equals("Play"))
        {
        	gamep.setVisible(true);
        	menup.setVisible(false);
        	scorep.setVisible(false);
        	rulesp.setVisible(false);
        }
        else if (b.getText().equals("Exit"))
        {
     	   int response = JOptionPane.showOptionDialog(null, "Are you sure  you want to exit?", "Exit?",
     	            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
     	            null, options, options[0]);
     	   if (response == 0)
     		 System.exit(0);	
        }
        else if (b.getText().equals("How To Play"))
        {
        	rulesp.setVisible(true);
        	menup.setVisible(false);
        	gamep.setVisible(false);
        	scorep.setVisible(false);
        }	
        else if (b.getText().equals("High Scores"))
        {
        	scorep.setVisible(true);
        	menup.setVisible(false);
        	gamep.setVisible(false);
        }	
        else if (b.getText().equals("Menu"))
        {
        	menup.setVisible(true);
        }	
    	}	
		
	}

    //method to create panels
    private JPanel makePanels()
    {
    	JPanel MPanel = new JPanel();
    	MPanel.setOpaque(true);
    	MPanel.setBackground(Color.BLUE);;
    	MPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    	MPanel.setBounds(0, 0, width, height);
    	MPanel.setLayout(null);
    	return MPanel;
    }

    
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new GUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    //method to set up the button 
    private void setupbutton(JButton button, int x, int y, ImageIcon img, int sx, int sy)
    {
    	 button.addActionListener(this);
    	 button.setLocation (x, y);
    	 button.setSize (sx, sy);
    	 button.setBackground(null);
    	 button.setBorder(null);
    	 button.setIcon(img);
    }
    public static void main(String[] args) {
       
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

	
}