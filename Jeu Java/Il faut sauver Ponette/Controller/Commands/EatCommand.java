package Controller.Commands;
import Model.Player;
/**
 * Write a description of class EatCommand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EatCommand extends Command
{
   

    /**
     * Constructor for objects of class EatCommand
     */
    public EatCommand()
    {
        
    }
    
    
     /**
     *  Command Eat : Eat something
     */

    public void execute (Player pPlayer)
   { 
       if (!hasSecondWord())
       {
        aUserInterface.println("What do you want to eat ?");
   
        }
       
       String eatSecondWord = getSecondWord();
       
       if(pPlayer.getInventory().getItem(eatSecondWord) == null)
        {aUserInterface.println("You haven't this item");

        }
      
       else
       {
           aUserInterface.println(pPlayer.getCharacterName() +" has eaten a "+eatSecondWord);
           
                if (pPlayer.getInventory().getItem(eatSecondWord).getItemName() == "cookie")
                {
                    aUserInterface.println(pPlayer.getCharacterName() +" has seen the max weight he can carry increased of 15");
                  pPlayer.setCharacterMaxWeight(15);

                } 
             
           aGameModel.getPlayer().getInventory().removeItem(eatSecondWord);
           
        }
      aGameEngine.inventory();
   }
}
