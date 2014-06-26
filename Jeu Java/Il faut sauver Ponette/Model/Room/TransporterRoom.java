package Model.Room;
import Model.*;
/**
 * 
 * 
 * @author Eric Tran/Esence Thomas WITH THE HELP OF NAJI ASTIER AND YOHAN ROBIN
 * @version 11/05/13
 */
public class TransporterRoom extends Room
{  /**
    * Creates the Transporter Room
    */
    public TransporterRoom( final String pDescription, final String pImage, final String pLongDescription, final boolean pTrapDoor)
    {
      super( pDescription, pImage,pLongDescription ,pTrapDoor);
   }
   
   /**
    * Overwrite the method getExit
    */
   @Override
   public  Room getExit( String direction )
   
   {  RoomRandomizer random = new RoomRandomizer (GameModel.getHashMapRoom());
     return random.nextRoom();
    }
}
