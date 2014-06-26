package Controller.Commands;

import Model.Player;
/**
 * Write a description of class BackCommand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BackCommand extends Command
{
    public BackCommand ()
    {
    }
    
    
    
    @Override 
     /**
     * Command goBack 
     * Uses command goRoom, with previous Room as next Room
     */
    public void execute (Player pPlayer )
    { 
      
        if (hasSecondWord()) 
     { aGameEngine.getTextView().show("Try again ! " );}
     
     else if ( aGameModel.lookWalkthroughRoom() == true )
      { if ( aGameEngine.gethasBeenTrapped()== true )
          aUserInterface.println(" Actually, you don't wana go back.. " );
          
          else 
          aUserInterface.println("Oooops ! You've just forgotten where you were.. *facepalm* " );
        }
         else 
     {  
         aGameModel.goRoom(aGameModel.getPreviousRoom());
        }
    
       }
}
