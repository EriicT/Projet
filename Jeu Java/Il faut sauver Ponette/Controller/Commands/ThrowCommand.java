package Controller.Commands;
import Model.Player;
import Model.Items.*;
/**
 * Write a description of class DropCommand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ThrowCommand extends Command
{
  

    public ThrowCommand()
    {
      
    }

      /**
     * Command Throw : destroys a Item
     */

    public void execute (Player pPlayer)
   {    // Test if the Player throws something
        if (!hasSecondWord()) 
       
            {
                aUserInterface.println("What do you want to throoow ?");
          
            }
        
            
        // Test if the Player owns what he wants to throw
        String SecondWord = getSecondWord();
        
        Item Item  =pPlayer.getInventory().getItem(SecondWord);
        
        if ( SecondWord.equals("squirrel") && aGameModel.getPlayer().getCurrentRoom()== aGameModel.getHashMapRoom().get("den") )
        
        {
          aUserInterface.println("HOOOO ! You killed the wolves with " + SecondWord +" ! Nice shot " );
          aUserInterface.getRealTimeW().stop();
          aUserInterface.showImage("dend.jpg");
          pPlayer.removeItem(SecondWord);
        }

        else if ( ( SecondWord.equals("bat") || SecondWord.equals("squirrel") || SecondWord.equals("poop") || SecondWord.equals("knife") )&& aGameModel.getPlayer().getCurrentRoom()== aGameModel.getHashMapRoom().get("basement") )
        
        { aUserInterface.println( " Shhhh*t " + "the " + SecondWord +" has no effect" ) ;
            pPlayer.removeItem(SecondWord); }
            
         else if ( ( SecondWord.equals("pebble") || SecondWord.equals("poneybrush") || SecondWord.equals("beamer") || SecondWord.equals("trash") )&& aGameModel.getPlayer().getCurrentRoom()== aGameModel.getHashMapRoom().get("basement") )
        
        { aUserInterface.println( " Shhhh*t " + "the " + SecondWord +" has too much effect" ) ;
            pPlayer.removeItem(SecondWord);
        aUserInterface.println("BlackSpot starts to enrage : ");
        aUserInterface.println(" Piece of poop ! You're dead !");
    }
        
         else if ( SecondWord.equals("grapnel"))
         { aUserInterface.println( " You've thrown that grapnel perfectly ! Now use it ! " );
             pPlayer.getInventory().removeItem("grapnel");
             aGameEngine.inventory();
           aGameEngine.setUseGrapnel(true); }
          
        else if ( SecondWord.equals("Biactol") )
          { throwBiactol(); }
            
        else if( Item == null )
            {
            aUserInterface.println("You're looking in your pockets and realize you don't have any "+ SecondWord); 
            }
        else if (pPlayer.getInventory().getItem(SecondWord).equals("rope") )
           {aUserInterface.println( "You've thrown the Rope ! " ); }
        
        // The player drops the Item
         // - Removes the Item from the Inventory
         // - Displays a text to tell the Player he has droped the Item
        else {
            pPlayer.removeItem(SecondWord);
            aUserInterface.println(aGameModel.getPlayer().getCharacterName() +" has thrown "+SecondWord);
            
        
        // Actualises the Item Text
        aGameEngine.inventory();
        
        }
         
        
            
}

 public void throwBiactol()
 { if   (aGameModel.getPlayer().getCurrentRoom()== aGameModel.getHashMapRoom().get("basement"))
     {
        aGameEngine.winGame(); }
            
          else 
           aUserInterface.println("Desperated, you're looking at Biactol and finally decide not to throw him ! ");
 
}
}

