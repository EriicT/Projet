package Model.Items;
 /**
 * Classe ITEM
 */
public class Item
{

      private String aItemName;
      private int aWeight;
      private String aDescription ;
      
      /**
      * Constructor with 3 parameters
      */
    public Item(String pItemName, String pDescription, int pWeight)
        {
          this.aItemName = pItemName;
          this.aWeight= pWeight;
          this.aDescription= pDescription;
        }
    /**
     * Sets and returns the Name of an Item
     */    
    public String setNameItem(String pItemName)
       {
         this.aItemName= pItemName;
         return aItemName;
       }
   /**
    * Sets Weights and returns Weights
    */    
   public int setWeight(int pWeight)
       {
         this.aWeight= pWeight;
         return aWeight;
        }       
   /**
    * Returns the Name of the Item
    */     
    public String getItemName()
        { 
          return aItemName;
         }
    /**
     * Sets and returns Description
     */ 
    public String setDescriptionItem(String pDescription)
       {
        this.aDescription= pDescription;
        return aDescription;
       }     
    /**
     * Returns Weight of the item
     */   
    public int getWeight()
        {
         return aWeight;
        }
   /**
    * Returns Description of the item
    */      
   public String getDescriptionItem()
        {
         return aDescription;
        }
        
        /**
         * Returns Long Description of the Item for method examine
         */
   public String getLongDescriptionItem()
       {
       String aDescriptionItem = ("You're looking closer to this "+aItemName+", "+"it's a "+aDescription+".."+ "\n"+"And it weighs about "+aWeight+" pounds");
       return aDescriptionItem;
       }
}

    
