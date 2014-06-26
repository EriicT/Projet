package Controller.Commands;
import Model.Player;
/**
 * Write a description of class ExamineCommand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExamineCommand extends Command
{
 

    /**
     * Constructor for objects of class ExamineCommand
     */
    public ExamineCommand()
    {
       
    }
    
     /**
     * Command Examine : look closer to a object
     */
     public void execute (Player pPlayer)
     {
       if(!hasSecondWord())
       { aUserInterface.println("Examine what ?");}
       
       String object = getSecondWord();
       
       if (aGameModel.getPlayer().getInventory().getItem(object) == null )
      { aUserInterface.println("You should take this before examinining it ") ;}
       
      else aUserInterface.println((aGameModel.getPlayer().getInventory().getItem(object).getLongDescriptionItem()));
   
      
     }
 
}
