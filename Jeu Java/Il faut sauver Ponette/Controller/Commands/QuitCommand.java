package Controller.Commands;
import Model.Player;
public class QuitCommand extends Command
{
  

    /**
     * Constructor for objects of class QuitCommand
     */
    public QuitCommand()
    {
      
    }

     /** 
     * Command quit
     */
    public void execute (Player pPlayer) 
    {
        if(hasSecondWord()) 
        {
            aUserInterface.println("Really ? You really want to quit ? Enter *quit* .. ");
        }
        
        else
        {
            aGameEngine.getTextView().printQuit(); 
            aGameEngine.endGame();
        }
        

    }
}
