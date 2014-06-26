package Model.Room;

import Model.Items.*;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 * @author  Eric Tran/Esence Thomas
 * @version 11/05/13
 */
public class Room 
{
    private String aDescription;
    private String aLongDescription;
    private HashMap <String,Room> aHashMapExits;
    private HashMap<String, Item> aHashMapItems;
    private String aImageName;
    private boolean aTrapDoor;

    
    

    
    /**
     * Initialize the Rooms with the two HashMaps and the four Paramaters
     */
    public Room(String description,String image, String pLongDescription,boolean pTrapDoor ) 
    { 
      aHashMapExits = new HashMap<String,Room>();
      aHashMapItems = new HashMap<String,Item>();
      this.aDescription = description;
      this.aImageName = image;
      this.aTrapDoor = pTrapDoor;
      this.aLongDescription = pLongDescription;
      
    }
    /**
     * Returns the TrapDoor ( True/False)
     */
    public boolean hasTrapDoor()
    { return aTrapDoor;}
    
    
     
    /**
     * Puts Exits in the HashMap Exits
     */
    public void setExit(String direction,Room neighbor)
    {  aHashMapExits.put(direction,neighbor);  
    }


     /**
     * Returns the Room associated with the String Parameter
     * Parameter : String
     */
    public Room getExit (String direction)
    { return aHashMapExits.get(direction);
    
     }
     
     
     /**
      * Returns the Exits of a Room
      */
     public  String getExitString()
     {  
         StringBuilder exitString = new StringBuilder("Your possible paths are:");
         for ( String vS : aHashMapExits.keySet() )
            exitString.append( " " + vS );
        return exitString.toString();
        
    }

    /**
     *  Returns the long Description of the Room
     */
    public  String getLongDescription()
   { return "\n" + aLongDescription ;
    }
    
    /**
     * Returns the name of the pictures of a the Room
     */
    public String getImageName()
    {
        return aImageName;
    }
    
    /**
     * Returns the short Description of the Room
     */
    public String getShortDescription()
    {
        return  aDescription;
    }
    
    
    
      /**
       * Adds Items to the HashMap Items
       * Parameter : Item
       */
     public void addItem(Item vItem)
     {
      aHashMapItems.put(vItem.getItemName(), vItem);
     }
     
     /**
      * Returns Description of the 
      */
     public String getDescriptionItem(String vName)
      {
       return aHashMapItems.get(vName).getDescriptionItem();
      }

      /**
         * Get the longdescritionof the item.
         *@paramvName
         *@return The long description of the item.
         */
      public String getLongDescriptionItem(String pName)
       {
         return aHashMapItems.get(pName).getLongDescriptionItem();
        }

      /**
      * Get the list of items in the currentRoom.
      * @return Items present in the currentRoom.
      */
     public String getItemList() 
     {
         if   (aHashMapItems.isEmpty())
         return "Items: No items.";
         else
         { StringBuilder ItemListString = new StringBuilder("Items: ");
             String comma ="";
             for  (String itemString :aHashMapItems.keySet())
             { ItemListString.append(comma).append(itemString);comma =", ";}
             return ItemListString.toString();} 
}

    /**
     * Get the list of items and their description in the currentRoom.
     *@return Items and long description of items in the currentRoo.
     */
public String getLongItemList()

{ 
   if (aHashMapItems.isEmpty())
   return "Items: No items.";
    else
    {StringBuilder ItemListString = new StringBuilder("Items: ");
     String comma ="";
    for(String itemString :aHashMapItems.keySet())
    {ItemListString.append(comma).append(aHashMapItems.get(itemString).getLongDescriptionItem()).append("\n");} 
    return ItemListString.toString();} 
}

    public Item getItem(String pItemName)
       { return aHashMapItems.get(pItemName);
        }
        
        
        /**
     * Remove an item from the room.
     * @param itemName
     */
    public void removeItem(String itemName)
    {
        aHashMapItems.remove(itemName);
    }

}


