package Controller.Commands;

import Model.Player;
/**
 * Write a description of class HelpCommand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HelpCommand extends Command
{


    /**
     * Constructor for objects of class HelpCommand
     */
    public HelpCommand()
    {
 
    }

    public void execute( Player pPlayer )
    
    { aUserInterface.println( aGameModel.getHelpString()); 
      aUserInterface.println ( aGameEngine.getParser().showCommands() ) ;
      

      
    }
}
