package Controller;

import View.*;
import Model.*;
import Controller.Commands.Parser;
import Controller.Commands.Command;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * Il faut retrouver Ponette ! 
 * @author : Thomas Esence / Eric Tran
 * @version 08/05/13
 */

public class GameEngine
{
      private Parser aParser;
      private TextView aTextView;
      private UserInterface aUserInterface;
      private GameModel aGameModel;
      private boolean hasBeenTrapped;
      private Player aPlayer;
      private Countdown aCountdown,wCountdown;
      private Command aCommand;
      private boolean usedGrapnel;
      private boolean inDen;
     
     


      /**
      * Constructor for objects of class GameEngine
      * Initialize Countdown,GameModel,Parser,UserInterface,TextView,set the UserInterface.
      */
      public GameEngine()
      {
          this.aCountdown =  new Countdown(450);
          this.wCountdown = new Countdown(3);
          this.aGameModel = new GameModel();
          this.aParser = new Parser();
          this.aUserInterface = new UserInterface(this);
          this.aTextView = new TextView(aGameModel,aUserInterface,aParser);
          this.setSHOW(aUserInterface);
          this.aGameModel.addObserver(aTextView);
          aCommand.setGameEngine(this);
          aCommand.setGameModel(aGameModel);
          aCommand.setUserInterface(aUserInterface);
          this.usedGrapnel = false;
          this.inDen=false;
          
      }
      
      
      /**
       * Initializes the UserInterface and displays the welcome command
       */
      
      public void setSHOW(UserInterface pUserInterface)
      {
          this.aUserInterface = pUserInterface;
          this.aTextView.printWelcome();
          inventory();
           
      }
      
      /**
       * Given a command, process (that is: execute) the command.
       * If this command ends the game, true is returned, otherwise false is 
       * returned.
       */
       
      public void interpretCommand(String pCommandLine) 
      {
          // Update the inventory
          inventory();
         
          // Get the first word of the sentence entered by the player
          Command command = aParser.getCommand(pCommandLine);
          
          // Test if the command is recognized by the game
          if ( command == null )
             aTextView.show("ERROR404, Command not found ! Try Something else !" );
          
          // Else interpret the command in calling the method execute() on the command
          else 
             command.execute( aGameModel.getPlayer()); 
             
          
        }
        
      /** 
       * Returns the TextView
       */
      public TextView getTextView()
      {
          return aTextView;
        }
       
 
        
        
      /** 
       * Returns the Countdown 
       */
      public Countdown getTime()
      {
          return aCountdown;
       }
      
 
        
      /** 
       * Returns the Countdown 
       */
      public Countdown getTimeW()
      {
          return wCountdown;
       }
      
      /** 

      
      /** 
        * Returns the GameMode
        */
      public GameModel getGameModel()
      {
          return aGameModel;
       }
      
      /**
       * Returns the Parser
       */
      public Parser getParser()
      { 
         return aParser;
       }
      
      /**
       * Sets hasBeenTrapped
       */
      public void setHasBeenTrapped(final boolean pValue)
      { 
         this.hasBeenTrapped =pValue;
        }
      /**
       * A getter for the attribute hasBeenTrapped
       */
      public boolean gethasBeenTrapped()
      { 
          return hasBeenTrapped;
       }
      
      
      /**
       * Method to display your inventory in a special Textfield
       */
      public void inventory()
      {
          if(aGameModel.getPlayer().getInventory().InventoryIsEmpty() == true)
             aTextView.iShow( "    Your pockets are empty" );
          // Displays a string if the player's inventory is empty   
          
          else 
             aTextView.iShow (aGameModel.getPlayer().getInventory().sGetStringInventory());
          // Else, displays all the items that the player have
        
       }
        
      /** 
       * Displays the a short description of the current room 
       */
      public void getPlace()
      { 
        aTextView.pShow(aGameModel.getPlayer().getCurrentRoom().getShortDescription());
       }
      
        
      /**
       * Method which ends the game 
       */
      public void endGame()
      {
          aTextView.show(" GAME OVER ");
          aUserInterface.getRealTime().stop();
          aUserInterface.disableButton();
          
       }
       
       /**
        * Returns UsedGrapnel
        */
        public boolean getUsedGrapnel()
        { return usedGrapnel ;}
        
        /**
         * Set UsedGrapnel
         */
       public void setUseGrapnel(final boolean pValue)
       { this.usedGrapnel = pValue; }
       
       /**
        * Set inDen
        */
       public void setInDen(final boolean pValue)
       { this.inDen = pValue;}
       /**
        * Returns inDen
        */
        public boolean getInDen()
        { return inDen ;}
        
        /**
         * Method Win : The player has won
         */
        public void winGame()
        {
            aUserInterface.println("CONGRATULATIONS ! You've found and saved Ponette ! ");
            aUserInterface.getRealTime().stop();
            aUserInterface.showImage("win.jpg");
        }
            
    }
