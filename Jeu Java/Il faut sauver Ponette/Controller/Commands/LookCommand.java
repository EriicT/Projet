package Controller.Commands;
import Model.Player;
/**
 * Write a description of class LookCommand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LookCommand extends Command
{
   public LookCommand ()
   {}
   
   
   public void execute (Player pPlayer)
   
   {  if ( aGameModel.getPlayer().getCurrentRoom().equals("misty"))
       { aUserInterface.println(aGameModel.getLookString());
         aUserInterface.println("OOh ! LOOK THERE IS A UNICORNE ");} 
       else 
       aUserInterface.println(aGameModel.getLookString());

}
}
