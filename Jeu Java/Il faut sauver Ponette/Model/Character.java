package Model;
import Model.Room.*;
import Model.Items.*;


/**
 * Contains methods to create and modify the Character's caracteristics
 * @author Eric Tran/Esence Thomas
 * @version 08/05/13
 */
public class Character
{
       protected String aCharacterName;
       protected int aCharacterCurrentWeight;
       protected int aCharacterMaxWeight;
       protected Room aCurrentRoom;
       protected ItemList aInventory;
          
         /**
          * Creates the Character : Inializes the 5 attributs
          */
            public Character(String pCharacterName, int pCharacterMaxWeight, Room pCurrentRoom)
        {
                  this.aCharacterName = pCharacterName;
                  this.aCharacterMaxWeight = pCharacterMaxWeight;
                  this.aCharacterCurrentWeight = 400 ;
                  this.aCurrentRoom = pCurrentRoom;
                  this.aInventory = new ItemList();
         }
            
          /**
           * Returns the Name of the Character
           */ 
          public String getCharacterName()
           {
                   return this.aCharacterName;
           }
           
          /**
           * Returns the Weight of the Character
           */
           public int getCharacterCurrentWeight()
           {
                   return   this.aCharacterCurrentWeight;
           }
           
           /**
            * Returns the Current Room
            */
           public Room getCurrentRoom()
           {
                   return this.aCurrentRoom;
           }
           
           
           /**
            * Parameter : Room to be set
            * Sets the Current Romm as the Room in Parameter
            */

            public void setCurrentRoom(Room pCurrentRoom)
            {
                   this.aCurrentRoom = pCurrentRoom;
            }
            
            /**
             * Returns the Character Weight that he can carry
             */
            public int getCharacterMaxWeight()
            {
                    return this.aCharacterMaxWeight;
           }
           
           /**
            * Adds more Weight, the Player can now carry more Items
            * Parameter : Int, which we want to implement
            */
           public void setCharacterMaxWeight(int pAddWeight)
            {
                this.aCharacterMaxWeight += pAddWeight;
            }
    
        /**
         * Test if the Item can be taken
         * Parameter : the Item
         * Returns True/False
         */   
        public boolean canBePick(String pItemName)
        {  
           if ( aCurrentRoom.getItem(pItemName) == null)
            return false;
            
            else if(this.aCharacterCurrentWeight + aCurrentRoom.getItem(pItemName).getWeight() > aCharacterMaxWeight)
                    return false;
            else
                    return true;
                
        }
       
      /**
        * Test if the item can be dropped
        * Parameter : the Item
        * Returns True/False
        */
       public boolean canBeDrop(String pItemName)
        {  
            
           if (aInventory.getItem(pItemName) == null)
                   return false;
           else
                   return true;
       }
       
      /**
         * Adds Item to the Inventory of the Character
         * Parameter : the name Item
         */
        public void addItemInventory(String pItemName)
        {  if ( aCurrentRoom.getItem(pItemName) == null)
            {}
           else 
           this.aCharacterCurrentWeight = this.aCharacterCurrentWeight + this.aCurrentRoom.getItem(pItemName).getWeight();
            aInventory.addItem(aCurrentRoom.getItem(pItemName)); 
       }
       /**
         * Adds Item to the Inventory of the Character
         * Parameter : the name of the Item
         */
       public void addItemInventory(Item item)
        {
           this.aCharacterCurrentWeight += item.getWeight();
            aInventory.addItem(item);
        }
        
       /**
        * Removes the Items from the Inventory of the Character
        * Parameter : the name of the Item
        */
        public void removeItem(String pItemName)
        {
            this.aCharacterCurrentWeight = aCharacterCurrentWeight - aInventory.getItem(pItemName).getWeight();
            aInventory.removeItem(aInventory.getItem(pItemName).getItemName());
        }
        
        /**
         * Returns the Inventory
         */
        public ItemList getInventory()
       {
            return aInventory;
      }
      
      
       
}
