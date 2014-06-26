package Model.Items;
import java.util.HashMap;
import java.util.Set;

public class ItemList
 {
     private HashMap<String, Item> aHashMapInventory ;

   /**
    * Creates the inventory : a HashMap
    */ 
   public ItemList()
   {   this.aHashMapInventory = new HashMap<String, Item>();
                  }

   /**
    * Returns the Item of the String Parameter from the HashMap
    * Parameter : String
    */
   public Item getItem(String pItemName)
       { return aHashMapInventory.get(pItemName);
        }
     /**
      * Returns String Builder for the side JtextArea ( Items )
      */
     public String sGetStringInventory()
         {
              StringBuilder inventoryStringBuilder = new StringBuilder("   You've got in your Inventory : " + "\n" +"\n");
              int vI = 1;
              Set<String> keys =aHashMapInventory.keySet();
                  for (String items : keys)
                        inventoryStringBuilder.append("  Item"+ vI++ + " :"+ items +"\n" + "\n" );
            
              
              return inventoryStringBuilder.toString(); 
                                               }
       /**
        * Returns String Builder ( Items )
        */                                        
       public String getStringInventory()
         {
              StringBuilder inventoryStringBuilder = new StringBuilder("Inventory : ");
                           Set<String> keys =aHashMapInventory.keySet();
                  for (String items : keys)
                        inventoryStringBuilder.append( items +" ");
                      
              
              return inventoryStringBuilder.toString(); 
                                               }
     /**
      * Adds the Item to the Inventory
      * Parameter : Item
      */         
     public void addItem(Item pItem)
     {  aHashMapInventory.put(pItem.getItemName(), pItem);}
     
     /**
      * Removes the Item from the Inventory
      * Parameter : Item
      */
     public void removeItem(String pItem)
     {    aHashMapInventory.remove(pItem);}
      /**
       * Returns true/false to know if the Inventory is empty
       */
     public boolean InventoryIsEmpty()
     {    return aHashMapInventory.isEmpty(); }
     
   
 }
 
 
