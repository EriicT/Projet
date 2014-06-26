package Model;

import Model.Room.*;

/**
 * 
 * @author Eric Tran/Thomas Esence
 * @version 08/05/13
 */
public class MovingCharacter extends Character
{
    /**
     * Initalizes the Moving Character with the parameters of the Character
     */
   public MovingCharacter(String pCharacterName, int pCharacterMaxWeight, Room pCurrentRoom)
          {
                  super(pCharacterName, pCharacterMaxWeight, pCurrentRoom);
         }
         
        
      /**
     * Parameter : Room
     * Sets the Character first Room
     */
   public void startRoom( Room pRoom) 
   { setCurrentRoom(pRoom); }
       
   /**
    * Method to move the Character
    * Parameter : direction of the exit
    */
   public void moveCharacter ( String direction)
   { Room vNextRoom= aCurrentRoom.getExit(direction);
     setCurrentRoom(vNextRoom);
    }


}
