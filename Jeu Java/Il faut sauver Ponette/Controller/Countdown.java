package Controller;


/**
 * Il faut retrouver Ponette ! 
 * @author : Thomas Esence / Eric Tran
 * @version 08/05/13
 */


public class Countdown
{
    private int counter;
    
    public Countdown ( final int pCounter)
    { this.counter=pCounter; }
    
    /** The method we use to decrement the countdown */
    public void decrement()
    {this.counter = counter -1; } 
    
    /** A getter for the attribute counter */
    public int getTime()
    {return this.counter; }
    
    public String counterString()
    {
        StringBuilder counterString = new StringBuilder();
        // Creates a new Stringbuilder named "counterString"
        
        return counterString.append(counter).toString();
        // Diplays successively the countdown values
    }
  
    /** The method we use to know if the countdown is finished or not */
    public boolean countDownState()
    { 
        if ( counter == 0 )
           return false;
           
        else
           return true;    
    }
    

}