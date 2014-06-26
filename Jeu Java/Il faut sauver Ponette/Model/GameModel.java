package Model;

import Model.Room.*;
import Model.Items.*;
import Controller.*;

import java.util.HashMap;
import java.util.Observable;
import java.util.Stack;

/**
 * MVC : Model
 */


public class GameModel extends Observable
{ 


  private static HashMap<String, Room> aHashMapRoom;
  private Room  tunnel, basement, edge, misty, dark, fcave, cave, endcave, hole, muddy, coast, prairie, house, endmisty, scarecrow, den, fall,freaky;
  private Player aPlayer;
  private Character  Slenderman, BlackSpot;
  private MovingCharacter Unicorne;
  private Item banana, coconut, cookie, beamer, trash, poneybrush, rope, pebble, hook, grapnel,Biactol, squirrel, knife, poop ,bone, bat;



    /**
     * Initializes the HashMap Rooms and launches create Room
     */
    public GameModel()
    {
        
        aHashMapRoom = new HashMap<String,Room>();
        createRooms();
        

    }
    
    /**
     *  Returns the String Welcome
     */
    public String getWelcomeString()
    {  return ( 
        "Ponette is lost. You are with Biactol. The Forest is soooo dark... "+ "\n"
        +"You have to find Ponette as fast as possible or she will be EATEN"+ "\n"
        +"Press help if you are weak :)"
        +"\n");
  
    }
    
    
    /**
     * Creates Rooms, Items and Player, sets their position
     */
    private void createRooms()
    {
       
      
      
        // create the rooms
        edge = new Room( "               the place where  "+"\n" +"         Ponette has disappeared","edge.jpg","Out of breath, Biactol asks you to slow down :You've just arrived to the place where you've seen Ponette for the last" +"\n"+ "time.You're looking for some clue that would lead you to her."+ "\n"+ "There's nothing more than trashes from your picnic of this afternon" +"\n"+ " - Something's wrong here said Biactol.. Heey ! Look there's two paths !" +"\n" + " Let's move ! We have to find her !!!!  ",false);
        fall = new Room( "             front of a waterfall","waterfall.jpg"," You've come in front of a Waterfall and everything seems alright." +"\n"+ "Suddently you hear a whinny from above the waterfall.. It sounds like the Magic Unicorn !  ",false);
        fcave = new Room( "           front of a dark cave","fcave.jpg"," You've just found where the blow came from : It's the entrance of a cave. "+"\n"+"There's almost no light inside, do you want to go on?",false);
        cave = new Room ( "               in the dark cave ","cave.jpg","After walking and hitting billion of stalagmites, you've arrived to a place where a powerfull light falls on the floor."+"\n"+"It seems that you can climb to escape this painful place.But just before leaving,"+"\n"+"Biactol sights at the cave and discovers a low beam :~Interesting ! What is it ? " ,false);
        prairie = new Room("            a prairie, where reigns "+"\n" +"            a disturbing silence.","prairie.jpg","You're now in a prairie. There's nothing else but a house.. "+"\n"+"Your blood is rushing in your head. You know Ponette is inside.",false);
        misty = new Room( "               the misty path","misty.jpg","Every step you make seems to add more mist.. The path goes on in front of you" +"\n"+"Something makes noise over there.. Weird !   " + "Go on ! We have to find Ponette fast ! ",false);
        endmisty = new Room( "          the end of this misty path","endmisty.jpg","Fortunately, the mist is going down and the atmospher is warmer." +"\n"+ "The path goes on and you can hear water falling on your right. " +"\n"+ "Where do you choose to go ? ",false);
        dark = new Room ( "             the dark path","dark.jpg","There are a lot of trees so it's getting dark.." +"\n"+ " The path feels colder right ahead. You're also hearing a blow near you,"+"\n"+" it's sounds like it comes from below. What do you want to do ?",false );
        endcave = new Room ( "             a deadend path", "endcave.jpg" ,"You're approaching the source of the beam, it's colorul over there ! Do you want to get to the source ?",false);
        hole = new Room ( "             almost above the cave ","hole.jpg","You're almost up there ! One more effort ! ",false);
        muddy = new Room ( "             the forest, once again, but  "+"\n" +" this time the floor is slidding. Be carefull ! ","muddy.jpg","It's a pleasure to feels the sunbeam on your skin !  You're looking around " +"\n"+ " and see that you're in the forest once again, the floor is super sliding !"+"\n"+" Where do you want to go now ? ",true);
        coast = new Room ( "       on the top of the waterfall ","coast.jpg","Finally ! You succeeded to climb above the waterfall"+"\n"+"The forest is less thicker.. and you can almost see the boundary of the forest !"+"\n"+" Keep going ! Ponette shouldn't be so faar ! ",false);
        scarecrow = new Room ( "         front of a scarecrow","scarecrow.jpg","After walking a while, you're facing an.. old Scwarecrow.. In the middle of the forest.."+"\n"+"This place is really freaky. Why is that old Scarecrow standing here ? "+"\n"+" Biactol wants to leave this place the faster we can : ~ Let's go ! ",false);
        den = new Room ( "             front of a Wolf'den  "+"\n" +"      and it smells baaaaaad ","den.jpg","~ Oh ! Wolves ! There are starrying at us ! Don't move.. "+"\n"+" The Wolves start to run toward you ! May be should run away?" ,true);
        house = new Room ("             a terrific house ","house.jpg","You rushed to the House and discover a terrific mess in the house"+"\n"+"You feel your hear beat and start to break down.. ~Not now says Biactol"+"\n"+"You hear screams from the basement of the house ! Quick ! ",false);
        tunnel = new TransporterRoom( "              a strange tunnel","tunnel.jpg","The source is a little orb, and it's shining intensly ! " +"\n"+" ~No Biactol don't touch that ! "+"\n"+ "Sh***t ! You shouldn't have touched that ! Try to escape !",false);
        basement = new Room ( "              finally arrived where Ponette is held..","final.jpg","Round, black, bad : here is the monster who kidnapped Ponette ! Beat BlackSpot before he kill all of us !",true);
        freaky = new Room ( "        at the end of the Dark Path " +"\n", "freaky.jpg", "There's a little mist here, and no more noise anymore.. " + "\n"+ "Something crackles right ahead.. Oh ! Someone stands in front of you: He's watching you..!"+ "\n"+ "It's Slenderman : What do you want to do.. ?", false );
        
        
        // initialise room exits
        edge.setExit( "left",dark );
        edge.setExit( "right",misty );
        // dark Path
        dark.setExit( "down", fcave);
        dark.setExit( "right", edge);
        dark.setExit("straight", freaky);
        fcave.setExit( "straight",cave );
        fcave.setExit( "back",dark);
        cave.setExit( "back",fcave);
        cave.setExit( "up",hole);
        cave.setExit ( "straight", endcave);
        endcave.setExit ("back", cave);
        endcave.setExit ("straight",tunnel);
        hole.setExit ( "You can't go back, or you will fall and die. But you can go", hole);
        hole.setExit( "up", muddy);
        den.setExit( "run",cave);
        //***Forced path : what ever you do lead you to this room
        muddy.setExit( "straight",misty );
        muddy.setExit( "down",misty  );
        muddy.setExit( "left",misty  );
        muddy.setExit( "right",misty  );
        muddy.setExit( "up",misty  );
        muddy.setExit( "back",misty);
        
        //***Misty Path
        misty.setExit( "straight",endmisty);
        misty.setExit( "back",edge);
        endmisty.setExit ( "back",misty);
        endmisty.setExit( "left",fall );
        endmisty.setExit( "straight",scarecrow );
        scarecrow.setExit( "straight",den );
        scarecrow.setExit( "back", endmisty);
        fall.setExit( "back", endmisty);
        coast.setExit("back",fall);
        coast.setExit( "straight",prairie);
        prairie.setExit("back",coast);
        prairie.setExit( "straight",house );
        house.setExit( "down", basement);
        basement.setExit("up",house);
       

    
    // initialise hashmapRoom.
    aHashMapRoom.put( "edge",edge);
    aHashMapRoom.put( "misty",misty);
    aHashMapRoom.put( "dark",dark);
    aHashMapRoom.put( "fcave",fcave);
    aHashMapRoom.put( "cave",cave);
    aHashMapRoom.put( "endcave",endcave);
    aHashMapRoom.put( "hole",hole); 
    aHashMapRoom.put( "muddy",muddy);
    aHashMapRoom.put( "coast",coast);
    aHashMapRoom.put( "prairie",prairie);
    aHashMapRoom.put( "house",house);
    aHashMapRoom.put( "endmisty",endmisty);
    aHashMapRoom.put( "scarecrow",scarecrow);
    aHashMapRoom.put( "den",den);
    aHashMapRoom.put( "fall",fall);
    aHashMapRoom.put( "tunnel",tunnel);
    aHashMapRoom.put( "basement",basement);
    
   //create the items
    banana = new Item ( "banana", " a beautiful banana, yellow and without any damages. It smells so sweet ! " , 5);
    coconut = new Item ( "coconut", " a coconut, the classic model, brown and round."+"\n"+" An old african proverbs says :~ The guy who eats coconuts, trusts his anus ~ " ,10);
    cookie = new Item ( "cookie", " a strange cookie, there's not chocolate but raisin or something that looks like raisin. Nobody likes raisin.. " ,1);
    beamer = new Beamer ("beamer"," a funny stone with the form of a gun! Ooow ! There's also a trigger.. Let's try it ? ",30); 
    trash = new Item ( "trash", " trash from your picnic. There are only chicken bones from your  ", 25);
    poneybrush = new Item ( "poneybrush", " a Poney brush to brush your beautiful Poney hair ", 7 );
    rope = new Item ( "rope"," a long rope, a bit damaged but still strong. It should be usefull !",25);
    pebble = new Item( "pebble", " a litte rock", 1 );
    hook = new Item ("hook", " a hook, Captain Hook has almost the same hook. You could hook this hook with somethintem "  +"\n" +"that can be hooked to that hook ", 50);
    grapnel = new Item ( "grapnel", " a super powerfull tool that can be thrown to climb ", 75);
    Biactol = new Item("Biactol", " your friend Biactol !",5); 
    squirrel = new Item ("squirrel"," a fat squirrel, he's ginger and soft RrrRrRr ",10);
    bone = new Item ("bone", " a piece of a corpse.. Maybe it's PONETTE ! ",6 );
    knife = new Item ( "knife", " an old and rusty knife",2);
    poop = new Item ("poop", " a squirrel's poop.. Why did you take that ?!? ", 2);
    bat = new Item ( "bat"," something bold and smiling ",10);
    
    
    
   
   // puts the Item   
      fall.addItem(banana);
      
      fall.addItem(coconut);
      
      edge.addItem(cookie);
      
      edge.addItem(trash);
      
      edge.addItem(pebble);
      fall.addItem(pebble);
      scarecrow.addItem(pebble);
      fcave.addItem(pebble);
      cave.addItem(pebble);
      endcave.addItem(pebble);
      tunnel.addItem(pebble);
      basement.addItem(pebble);
      misty.addItem(pebble);
      dark.addItem(pebble);
      hole.addItem(pebble);
      muddy.addItem(pebble);
      coast.addItem(pebble);
      prairie.addItem(pebble); 
      house.addItem(pebble); 
      endmisty.addItem(pebble);
      den.addItem(pebble);
      freaky.addItem(pebble);
      
      
      scarecrow.addItem(rope);
      
      fall.addItem(hook);
      
      endmisty.addItem(poop);
      
      cave.addItem(knife);
      
      cave.addItem(bat);
      
      cave.addItem(bone);
      den.addItem(bone);
     
      dark.addItem(poop);
      
      dark.addItem(squirrel);
      coast.addItem(squirrel);
      edge.addItem(squirrel);
      
      

  
    
 
    
      // creates characters 
    aPlayer = new Player ( "Poney", 700, edge);
    aPlayer.addItemInventory(poneybrush);
    aPlayer.addItemInventory(Biactol);
    Unicorne = new MovingCharacter ( "Magic Unicorne", 500, misty );
    Unicorne.addItemInventory(beamer);
    Slenderman = new Character ( "Slenderman", 80, freaky);
    BlackSpot= new Character ( "BlackSport",1, basement);
    
     
    
   
}

 
    /**
     *  Returns Help String
     */
    public String getHelpString() 
    {
        return("Poney needs your help ! " +
            "Let's try to find her : look everywhere, she may be anywhere !" + "\n" +
            "But don't let you catch.. There are mean people in there.." + "\n"       
            );
    }
    
    /**
     * Returns Look String
     */
    public String getLookString()
    {
        return(aPlayer.getCurrentRoom().getLongDescription()+ "\n" +aPlayer.getCurrentRoom().getItemList()) ;
    }
    
    /**
     * Returns the Long description of the Room
     */
    public String getLongLocationInfo()
    {
        return( aPlayer.getCurrentRoom().getLongDescription() );
           
    }
    /**
     * Returns the Short description of the Room
     */
    public String getLocationInfo()
    {   
          if (  aPlayer.getWalkthroughRoom().empty() == true )
         {return  ( "\n" + "                  You are in : " + "\n" + aPlayer.getCurrentRoom().getShortDescription() ); }
        else
        return ( "\n" + "                  You are in : " + "\n" +( aPlayer.getCurrentRoom().getShortDescription())  +"\n" +  "\n" + "                 (   You come from " +" \n" +  aPlayer.lookPreviousRoom().getShortDescription() + "  )"); 
    }
    
   
    
     /**
     * Returns the Room of the String Parameter
     * Parameter : String
     */
    public Room getHashMapRoom( final String pRoom )
    {
        return aHashMapRoom.get(pRoom);
    }
    /**
     * Method goRoom, sets the Nex tRoom as Current Room and notify Observers
     */
    public void goRoom(Room pCurrentRoom)
    {
        aPlayer.setCurrentRoom(pCurrentRoom);
        setChanged();
        notifyObservers();
    }
    /**
     * Returns the Current Room
     */
    public Room getCurrentRoom()
    {
        return aPlayer.getCurrentRoom(); }
    /**
     * Returns the state of the WalkthroughRoom Stack
     */
    public boolean lookWalkthroughRoom()
    { 
       return aPlayer.getWalkthroughRoom().empty();
    }
    
     /**
     * Returns String Quit
     */
    public String getEndString()
    {
        return ("Oooh.. See you !");
    }
   
    /**
     * Calls the Method actualiseWalkthroughRoom() in the object Player
     */
    public void actualiseWalkthroughRoom()
    { aPlayer.actualiseWalkthroughRoom();}
    
 
    
    /**
     * Calls the Method getPreviousRoom() in the object Player
     */
    public Room getPreviousRoom()
    { return aPlayer.getPreviousRoom();}
    
   /**
    * Returns the Player
    */
    public Player getPlayer()
       { return aPlayer; }
       
    
   
   /**
    * Returns the HashmapRoom
    */
   public  static HashMap getHashMapRoom()
   {
      return aHashMapRoom;
    }
    
   /**
    * Returns the Room from the String Parameter
    * Parameter : String
    */
   public Room getRoom(String pStringRoom)
   { return aHashMapRoom.get(pStringRoom);}
   
   /**
    * Returns the Unicorne
    */
   public MovingCharacter getUnicorne()
   { return Unicorne; }
   
   /**
    * Returns the beamer
    */
   public Item giveBeamer()
   { return beamer ;}
   
   /**
    * Returns Room where grapnel leads
    */
   public Room getGrapnelRoom()
    { return coast ; }
    
    /**
     * Returns Grapnel 
     */
    public Item getGrapnel()
    { return grapnel; }
}

  












