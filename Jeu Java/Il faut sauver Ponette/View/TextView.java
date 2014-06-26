package View;
import Model.GameModel;
import Controller.Commands.Parser;
import java.util.Observable;
import java.util.Observer;
/**
 * MVC : View !
 */

public class TextView implements Observer
{
  
    private GameModel aGameModel;
    private UserInterface aShow;
    private Parser aParser;
    

    /**
     * Constructor for objects of class TextView
     */
    public TextView( final GameModel pGameModel, final UserInterface pShow, final Parser pParser)
    {
        // initialise instance variables
        this.aGameModel = pGameModel;
        this.aShow = pShow;
        this.aParser = pParser;
    }
    
    
      public void show(String pString) 
    {
        aShow.println(pString);
    }

     
    public void iShow( String pString)
    {  aShow.iPrint(pString); }
    
    public void pShow( String pepitoString)
    { aShow.pPrint(pepitoString);  }
    
    public void printWelcome()
    {
        show(  aGameModel.getWelcomeString()) ;
        printLongLocationInfo();
        printLocationInfo();
       

        if(aGameModel.getCurrentRoom().getImageName() != null)
            {  aShow.showImage(aGameModel.getCurrentRoom().getImageName()); }
            
           
    }
      public void printLocationInfo()
    {
        pShow(aGameModel.getLocationInfo() );
    }
      public void printLongLocationInfo()
    {
        show(aGameModel.getLongLocationInfo() );
    }
    public void printQuit()
    {
        show("\n" +aGameModel.getEndString() + "\n");
        aShow.enable(false);
        
    }
    
    public void printHelp()
    {
        show("\n" + aGameModel.getHelpString()); 
        show(aParser.showCommands());
    }
         
   
  
    
    public void update(Observable o, Object arg)
    {
        printLocationInfo();
        if(aGameModel.getCurrentRoom().getImageName() != null)
        {  aShow.showImage(aGameModel.getCurrentRoom().getImageName()); }
    }
    
    
    
}