package Controller.Commands;
import Model.Player;
/**
 * Write a description of class AleaCommand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AleaCommand extends Command
{
    private String aleaString;
    
    public AleaCommand()
    {}
    
    public String getAleaString()
    
    { return aleaString ;}
    
    
      public void execute( Player pPlayer)
    {
        if ( hasSecondWord())
        {  aleaString = null ;}
        else
        {   String object = getSecondWord();
            aleaString = object ;
        }
  
    }
    
}
