package Controller.Commands;
import Model.Player;
/**
 * Write a description of class ItemsCommand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ItemsCommand extends Command
{
    /**
     * Constructor for objects of class ItemsCommand
     */
    public ItemsCommand()
    {
    }
    
    
     /**
     * Command Items : print the inventory and some other informations
     */
    public void execute (Player pPlayer)
   {
        if(aGameModel.getPlayer().getInventory().InventoryIsEmpty() == true)
            {
            aUserInterface.println("Tired ? But you're only carrying "+ (aGameModel.getPlayer().getCharacterMaxWeight()-aGameModel.getPlayer().getCharacterCurrentWeight())+" pounds");
            aUserInterface.println("Your pockets are empty " +"\n"+ "All your muscles are ready to lift everything you want huhu " );
           
        }
            
        else
        {  
           aUserInterface.println( aGameModel.getPlayer().getInventory().getStringInventory() +"\n"+ "the weight available yet :" + (aGameModel.getPlayer().getCharacterMaxWeight() - aGameModel.getPlayer().getCharacterCurrentWeight()));
           
            
          
        }
    
        }
 
}
