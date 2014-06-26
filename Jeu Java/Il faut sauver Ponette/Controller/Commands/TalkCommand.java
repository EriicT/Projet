package Controller.Commands;

import Model.Player;


/**
 * Write a description of class TalkCommand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TalkCommand extends Command
{
    private int vI =0;

    /**
     * Constructor for objects of class TalkCommand
     */
    public TalkCommand()
    {
        
    }
    
    public void execute( Player pPlayer )
    {
         
         if(!hasSecondWord()) 
         { aUserInterface.println( "Ewwwh ! Stop talking alone, you're driving crazy ! "); }
             
          String character = getSecondWord();
          
         if ( character.equals("Biactol"))
         { aUserInterface.println (" Blablabla" ) ;}
         
         if ( character.equals("Unicorne"))
         
           { if ( vI == 1 && aGameModel.getPlayer().getCurrentRoom() == aGameModel.getHashMapRoom().get("coast"))
             {aUserInterface.println( "\n" + " Unicorne : Wait.. You may need this. " );
             pPlayer.addItemInventory(aGameModel.giveBeamer());
             aGameModel.getUnicorne().removeItem(aGameModel.giveBeamer().getItemName());
             aUserInterface.println( "The Magic Unicorne gave you a beamer o/ ");
             vI++; }
             
          if ( vI ==0 )
         {   
             vI++;
             aUserInterface.println( "\n" + " Unicorne :  ~ I know your looking for Ponette and if you want to save her" +"\n" + " You will have to do sacrifice ");
             aUserInterface.showImage("misty2.jpg");
          }
             
             
            else if( vI == 1 && aGameModel.getPlayer().getCurrentRoom() == aGameModel.getHashMapRoom().get("misty") )
           
               aUserInterface.println( "\n" + " The Uniconrne is not here anymore.. " );
               
            
           
           if ( character.equals("Slenderman") )
          {aUserInterface.println ("\n"+"Slenderman : " +"~"+"You're going to die.." );
           aUserInterface.println("Slenderman catchs you and butches you." +"\n"+"There's blood everywhere" );
           aGameEngine.endGame();
           aUserInterface.println(" Next time, don't try to deal with him.. " );
           aUserInterface.showImage("dead.jpg"); } 
         
    
    }
}}
