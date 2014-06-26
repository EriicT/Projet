package Model.Items;

import Model.*;
import Model.Room.*;
public class Beamer extends Item
{

  private Room aSavedRoom;
  private boolean aCharge;
  /**
   * Creates a Beamer with Item's Parameters
   */
  public Beamer(String pNameItem, String pDescription, int pWeight)
  {
     super(pNameItem, pDescription, pWeight);
     this.aCharge= false;
   }
   /**
    * Returns the charge of the Beamer
    */
   public boolean getCharged()
   { 
    return this.aCharge;
    }
   /**
    * Sets charge of the Beamer
    */ 
   public void setCharge(final boolean pCharge)
   { 
     this.aCharge = pCharge ; 
    }
    /**
     * Saves the Room of the charge
     */
   public void setSavedRoom ( final Room pSavedRoom)
    { 
      this.aSavedRoom = pSavedRoom ;
    } 
   /**
    * Returns the Room Saved
    */ 
   public Room getSavedRoom()
   {
      return this.aSavedRoom;
    }
    
   
    
    
    
}
