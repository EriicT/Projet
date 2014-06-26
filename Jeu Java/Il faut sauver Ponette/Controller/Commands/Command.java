package Controller.Commands;

import Model.GameModel;
import Controller.GameEngine;
import View.UserInterface;
import Model.Player;

/**

 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public abstract class Command
{
    private String secondWord;
    protected static GameModel aGameModel;
    protected static GameEngine aGameEngine;
    protected static UserInterface aUserInterface;

    /**
     * Create a command object. First and second word must be supplied, but
     * either one (or both) can be null.
     * @param firstWord The first word of the command. Null if the command
     *                  was not recognised.
     * @param secondWord The second word of the command.
     */
    public Command()
    {
        this.secondWord = null;
       
    }

  

    /**
     * @return The second word of this command. Returns null if there was no  second word.
     */
    public String getSecondWord()
    {
        return secondWord;
    }
    
    /**
     * Sets the parameter as Second Word 
     */
    public void setSecondWord( String pString)
    { 
        secondWord=pString;
    }
    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
    
    public static void setGameModel(final GameModel pGameModel)
    { 
       aGameModel=pGameModel;
    }
    
     public static void setGameEngine( final GameEngine pGameEngine)
    { 
       aGameEngine=pGameEngine;
    }
    
    
     public static void setUserInterface(final UserInterface pUserInterface)
    { 
       aUserInterface=pUserInterface;
    }
    
    public abstract void execute(Player player);
    
}
