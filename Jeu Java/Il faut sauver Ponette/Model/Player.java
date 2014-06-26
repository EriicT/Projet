package Model;
import Model.Room.*;
import Model.Items.*;
import java.util.Stack;

public class Player extends MovingCharacter
 {
   
     private Stack<Room> walkthroughRoom ;
          

     /**
      * Initializes the Player with the paramater of the Moving Character
      */
       public Player(String pPlayerName,int pPlayerMaxWeight, Room pCurrentRoom)
      {   
        super ( pPlayerName, pPlayerMaxWeight, pCurrentRoom);
        this.walkthroughRoom = new Stack<Room>();
        
     }
     
 
      
     /**
      * Returns the Stack
      */

      public Stack<Room> getWalkthroughRoom()
      {    return this.walkthroughRoom;}
      


      /**
       * Adds the Current Room in the Stack
       */
        public void actualiseWalkthroughRoom()
       {   walkthroughRoom.push(aCurrentRoom); }
       /**
        * Returns et removes the last Room of the Stack
        */
       public Room getPreviousRoom()
       {   return this.walkthroughRoom.pop();   }
       /**
        * Removes the Rooms until the Stack is empty
        */
       public void removeWalkthroughRoom()
       { while (this.walkthroughRoom.empty() == false )
           this.walkthroughRoom.pop();
        }
        
        /**
         * Returns the last Room of the Stack
         */
      public Room lookPreviousRoom()
       
       { 
         return this.walkthroughRoom.peek();  
        }
        
     

    
                                    
       
       
       
}
