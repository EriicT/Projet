package Controller.Commands;

import Model.Player;
import Model.Room.*;
import Model.GameModel;

/**
 * Il faut retrouver Ponette ! 
 * @author : Thomas Esence / Eric Tran
 * @version 08/05/13
 */

public class GoCommand extends Command
{
    private Room aPreviousRoom;
    private AleaCommand aleaString;
    private boolean hasBeenTrapped;
    
    public GoCommand()
    {
        aleaString = new AleaCommand();
     }
    
    
    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    
    public void execute(Player pPlayer) 
    {   
      // Update the inventory
      aGameEngine.inventory();
      
      // Display a String if there is no second word
      if(!hasSecondWord()) 
      {
           aUserInterface.println( "Go ! But where ??");
       }
     
      // Store the second word in the String attribute "direction" 
      String direction = getSecondWord();
     
      // Store the currentRoom in the Room attribute "aPreviousRoom"
      aPreviousRoom= aGameModel.getCurrentRoom();
     
      // Store the next room in the Room attribute "NextRoom" thanks to the method getExit(direction)
      Room NextRoom = aGameModel.getCurrentRoom().getExit(direction);
      
      if( NextRoom == aGameModel.getHashMapRoom().get("den"))
           {   aUserInterface.getRealTimeW().start();
               aGameEngine.setInDen(true);}
      
      
               
       
      
      //When you're in the Room "tunnel", whatever the direction the player choose, he'll be teleported in a Random Room, unless he use the Alea command
      if ( aPreviousRoom ==GameModel.getHashMapRoom().get("tunnel") )
      {
           //If the player don't use the Alea command, the next room is a Random room
           if ( aleaString.getAleaString() == null )
              NextRoom = RoomRandomizer.nextRoom();
             
           
           //else (so he used the Alea command), he'll go to the Room he asked 
           else 
              NextRoom = aGameModel.getRoom(aleaString.getAleaString());   
       }
      
      
      //If the player can take this way
       if (NextRoom == null)
         aUserInterface.println( "Nooooooooo ! It's tooooo dangerous out there !");
         
         
      else 
      {
          // Add the current Room to the Stack (used by the back command)
          aGameModel.actualiseWalkthroughRoom();
          // Update the current Room
          aGameModel.goRoom(NextRoom);
          // Update the UserInterface
          aUserInterface.println(pPlayer.getCurrentRoom().getLongDescription());
          
          // End the Game if the countdown is finished
          if(aGameEngine.getTime().countDownState() == false )
             aGameEngine.endGame();
          
             
              
       }
            
      // If the player passed the Room "tunnel", the stack is automatically cleared in order not to let him back 
      if (aPreviousRoom == aGameModel.getHashMapRoom().get("tunnel"))
         pPlayer.removeWalkthroughRoom();
         
         
      // Only to print a new String after taking a one way door et and trying to go back.
      hasBeenTrapped = false;
      
      if ( aPreviousRoom.hasTrapDoor() == true  ) 
      {
          pPlayer.removeWalkthroughRoom(); 
          aGameEngine.setHasBeenTrapped(true);
        }
        
      else aGameEngine.setHasBeenTrapped(false); 
      
      if( aGameModel.getPlayer().getCurrentRoom() == aGameModel.getHashMapRoom().get("cave") && aGameEngine.gethasBeenTrapped()==true )
         { aUserInterface.getRealTimeW().stop();
           aUserInterface.println("Oh ! They were so close ! Fortunately you fall in this dark cave ");}
       
     } 

 }
