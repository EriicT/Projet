package Controller.Commands;
import Model.Player;
import Model.Items.*;
/**
 * Write a description of class DropCommand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DropCommand extends Command
{
  

    public DropCommand()
    {
      
    }

      /**
     * Command Drop
     */

    public void execute (Player pPlayer)
   {    // Test if the Player drops something
        if (!hasSecondWord()) 
       
            {
                aUserInterface.println("What do you want to drop ?");
          
            }
        
            
        // Test if the Player owns what he wants to drop
        String SecondWord = getSecondWord();
        
        Item Item  =pPlayer.getInventory().getItem(SecondWord);
 
        if(aGameModel.getPlayer().canBeDrop(SecondWord) ==false)
            {
            aUserInterface.println("You're looking in your pockets and realize you don't have any "+ SecondWord); 
            }
            
        // The player drops the Item
         // - Adds the Item in the Current Room
         // - Removes the Item from the Inventory
         // - Displays a text to tell the Player he has droped the Item
            else 
        {   aGameModel.getCurrentRoom().addItem(Item);
            pPlayer.removeItem(SecondWord);
            aUserInterface.println(aGameModel.getPlayer().getCharacterName() +" has droped "+SecondWord);
         
        // Actualises the Item Text
        aGameEngine.inventory(); }

        
   }
}
