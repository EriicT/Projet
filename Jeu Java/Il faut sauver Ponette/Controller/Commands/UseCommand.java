package Controller.Commands;
import Model.Player;
import Model.Items.*;
/**
 * Write a description of class UseCommand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UseCommand extends Command
{
  

    /**
     * Constructor for objects of class UseCommand
     */
    public UseCommand()
    {
        
    }

    /**
    * Command Use
    */ 
   
   public void execute (Player pPlayer)
   {
      if(!hasSecondWord())
      { aUserInterface.println ("You're using ! You're using.. BUT WHAT ?"); }
      
       String object = getSecondWord(); 
       if( object.equals("grapnel") &&  aGameEngine.getUsedGrapnel()== true)
        { useGrapnel() ;}
        
       else if ( object.equals("rope") &&( aGameModel.getPlayer().getInventory().getItem("hook")) != null ) 
        { useRopeHook(); }
        
       else if ( object.equals("rope") && aGameModel.getPlayer().getInventory().getItem("hook") == null )
        { aUserInterface.println("You're playing with the rope! It's so coooool ! ") ; }
        
       else if ( object.equals("hook") && aGameModel.getPlayer().getInventory().getItem("rope") != null)
        { useRopeHook(); }
        
       else if ( object.equals("hook") && aGameModel.getPlayer().getInventory().getItem("rope") == null)
        { aUserInterface.println("Stop playing with it ! It's dangerous ! "); }
      
      
      
      else if (aGameModel.getPlayer().getInventory().getItem(object) == null && aGameEngine.getUsedGrapnel() !=true)
      { aUserInterface.println("You should take this before using it ") ;}
       
      else if ( object.equals("beamer"))
       { useBeamer();   }
       
      else 
       { aUserInterface.println (" Use that for.. what ? "); }
       

   }
   public void useRopeHook()
    { 
      aUserInterface.println("Combining the rope and the hook gives you a super powerfull tool that helps you to climb everything you want to : a Grapnel ! ");
      aGameModel.getPlayer().removeItem("rope");
      aGameModel.getPlayer().removeItem("hook");
      aGameModel.getPlayer().addItemInventory(aGameModel.getGrapnel());
      aGameEngine.inventory();
      
        
    }
   
    public void useGrapnel()
    {
        if  (aGameModel.getPlayer().getCurrentRoom().equals("fall") )
         aUserInterface.println("You're using that grapnel to climb ! Great Idea ! ");
         aGameModel.getPlayer().setCurrentRoom(aGameModel.getGrapnelRoom());
         aUserInterface.showImage("coast.jpg");
         aUserInterface.println(aGameModel.getLongLocationInfo());
         aGameModel.getPlayer().removeWalkthroughRoom();
         
        
    }
   
   public void useBeamer()
   {
      Beamer beamer = (Beamer)
      aGameModel.getPlayer().getInventory().getItem("beamer");
      
      if ( beamer.getCharged() == false )
      {
        beamer.setSavedRoom(aGameModel.getPlayer().getCurrentRoom());
        beamer.setCharge(true);
        aUserInterface.println ("The Talisman starts to shine !") ;
      }
      
      else 
      { aGameModel.getPlayer().setCurrentRoom(beamer.getSavedRoom());
        beamer.setCharge(false);  
        aGameModel.getPlayer().removeWalkthroughRoom();
        aUserInterface.println("The floor starts  to quake and you're falling in a horrible colorful spatiotemporal tunnel" + "\n" + " Pooof! You're back to the real World.. and the Talisman has just stopped to shine");
        aGameEngine.getTextView().printLocationInfo();
        
        aUserInterface.showImage(aGameModel.getCurrentRoom().getImageName());

       
    }
   }
}
