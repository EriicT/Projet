import Controller.*;
import javax.swing.JApplet;
/**
 * Il faut retrouver Ponette ! 
 * @author : Thomas Esence / Eric Tran
 * @version 08/05/13
 */

public class Game extends JApplet
{       private static final long serialVersionUID = 1L;

    private GameEngine aEngine;

    
   public static void main(String[] args)
        {
           new Game();
    }
    
    /**
     * Initialize the GameEngine to run the Game
     */
    public Game() 
    {
        aEngine = new GameEngine ();   
    }
    
  
    
    
}