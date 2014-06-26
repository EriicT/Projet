package Controller.Commands;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Set;
import Model.Player;

/**
 * Il faut retrouver Ponette ! 
 * @author : Thomas Esence / Eric Tran
 * @version 08/05/13
 */

public class CommandWords
{
    private HashMap<String, Command> validCommands;

    /**
     * Create the hashMap and Store all the commands of the game
     */
    
    public CommandWords()
    {
        validCommands = new HashMap<String, Command>();
        
        validCommands.put("help", new HelpCommand());
        validCommands.put("go", new GoCommand());
        validCommands.put("look", new LookCommand());
        validCommands.put("eat", new EatCommand());
        validCommands.put("back", new BackCommand());
        validCommands.put("take", new TakeCommand());
        validCommands.put("drop", new DropCommand());
        validCommands.put("quit", new QuitCommand());
        validCommands.put("test", new TestCommand());
        validCommands.put("items", new ItemsCommand());
        validCommands.put("use", new UseCommand());
        validCommands.put("examine", new ExamineCommand());
        validCommands.put("alea",new AleaCommand()) ;
        validCommands.put("talk",new TalkCommand());
        validCommands.put("throw",new ThrowCommand());

    }
    
    
    public Command getCommandWord(String commandWord)
    {
        return (Command)validCommands.get(commandWord);
     
     }
    
    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }
    
   
    
       /*
     * returns a String of all valid commands.
     
    public String getCommandList() 
    {
        StringBuilder validCommands = new StringBuilder();
        for(String command : validCommands.keySet()) {
           validCommands.append( command + "  //  " );
        }
        return "You can " + validCommands.toString();
    }
    */
   
    public String showAll() 
    { 
         StringBuilder aCommandStringBuilder = new StringBuilder();
                           Set<String> keys =validCommands.keySet();
                  for (String validCommand : keys)
                        aCommandStringBuilder.append( validCommand +"  //  ");
                      
              
              return aCommandStringBuilder.toString();
        
    }

}
