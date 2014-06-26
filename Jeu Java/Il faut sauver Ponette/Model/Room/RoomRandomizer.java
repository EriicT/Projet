package Model.Room;
import java.util.HashMap;
import java.util.Random;

/**
 * 
 * 
 * @author Eric Tran/Esence Thomas WITH THE HELP OF NAJI ASTIER AND YOHAN ROBIN
 * @version 11/05/13
 */
public  class RoomRandomizer
{
    
    private static  Room[] aRoomList;

    /**
     * Creates a list of Rooms from a HashMap Parameter
     * Parameter : HashMap
     */
    public RoomRandomizer(final HashMap <String,Room> pHashMapRoom)
    
    { this.aRoomList = new Room [pHashMapRoom.size() ];
      int vI =0;
      for ( String vS : pHashMapRoom.keySet() )
      { this.aRoomList[vI] = pHashMapRoom.get(vS);
          vI=vI+1 ;
        
    }

   }  
   /**
    * Create the nextRoom from Random princip
    */
   public static Room nextRoom()
   {
       Random random = new Random();
       return aRoomList[random.nextInt(aRoomList.length)];
       
    }  

}
