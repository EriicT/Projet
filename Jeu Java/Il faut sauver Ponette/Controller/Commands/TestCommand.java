package Controller.Commands;
import java.util.Scanner;
import Model.Player;
/**
 * Write a description of class TestCommand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCommand extends Command
{


    /**
     * Constructor for objects of class TestCommand
     */
    public TestCommand()
    {

    }
    
     /**
    * Only Used for Test
    */  
        public void execute (Player pPlayer)
        
       { Scanner vSc = new Scanner( this.getClass().getClassLoader().getResourceAsStream("test01.txt") );
        while ( vSc.hasNextLine() == true )
       {   
          aGameEngine.interpretCommand(vSc.nextLine());
     
       }

    }
}
