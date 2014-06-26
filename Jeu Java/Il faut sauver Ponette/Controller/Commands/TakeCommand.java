package Controller.Commands;
import Model.Player;
/**
 * Command take, adds the item to your inventory and removes it from the Room
 * 
 * @author Eric Tran / Thomas Esence
 * @version 02/05/2013
 */
public class TakeCommand extends Command
{
    /**
     * Constructor
     */
    public TakeCommand()
    { }
    

     /**
      * @paramater : a Player
      * @return : 
      */
       
     public void execute (Player pPlayer)
    {   
        // Test if the player takes something
        if(!hasSecondWord()) 
        { 
           aUserInterface.println("Nothing ? If you wanna..");
           
        }
        // Test if the player tries to take something in the room
     String SecondWord = getSecondWord();
     
         if(aGameModel.getCurrentRoom().getItem(SecondWord) == null)
         {
             aUserInterface.println("LSD is bad for health : Where do you see this Item ?");
           
         }
       // Test if the player can take the item : Can he carry this item ?
       
         else if (pPlayer.canBePick(SecondWord) ==false)
        { 
            aUserInterface.println("IT'S TO HEAVYYYYY");
         
        }
        
        // Means that the player can take the item :
         // - Adds the item to the inventory
         // - Removes the item from the Room
         // - Displays a String to tell the player that he has taken the item
        else 
        {pPlayer.addItemInventory(SecondWord);
        aGameModel.getCurrentRoom().removeItem(SecondWord);
        aUserInterface.println(pPlayer.getCharacterName() +" has taken "+SecondWord);
      
        // Actualises the Item Text
        aGameEngine.inventory(); }

    }
    
    
}
