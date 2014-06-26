package View;

import Controller.GameEngine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;


/**
 * 
 * @author Eric Tran
 * @version 11/05/2013
 */
public class UserInterface implements ActionListener
{
    private GameEngine aGameEngine;
    private JFrame myFrame;
    private JTextField entryField;
    private JTextArea log,sLogI,sLogP;
    
 
    private JPanel mainPanel,actionPanel,movePanel,spePanel,midPanel,fontPanel,sidePanel;
    private JButton buttonRagequit,buttonLook,buttonHelp;
 
    private JButton buttonLeft,buttonRight,buttonStraight,buttonBack;
  
    private JButton buttonUp,buttonDown,buttonRun,buttonInventory,buttonPrevious;
 
    private JLabel image,mainCountdown,wCountdown;
    private Timer realTime, realTimeW ;
    

    /**
     * Inializes the GameEngine and calls the CreateGui Method
     */
    
  
    public UserInterface(GameEngine pGameEngine)
    {
        aGameEngine = pGameEngine;
        createGUI();
    
    }


     /**
     * Print out some text into the text area, followed by a line break.
     * Parameter Text
     */
    public void println(String text)
    {
        log.append(text + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }
    
     /**
     * Print out some text into the text area Item.
     * Parameter : Text
     */
    public void iPrint(String text)
    {
        sLogI.setText(text);
      
    }
    
    /**
     *  Print out some text into the text area Place.
     *  Parameter : Text 
     */
    public void pPrint(String text)
    { sLogP.setText(text);}
  
    
    /**
     * Show an image file in the interface.
     * Parameter : Image Name
     */
    public void showImage(String imageName)
    {
        URL imageURL = this.getClass().getClassLoader().getResource("Pictures/"+imageName);
        if(imageURL == null)
            System.out.println( "No image here");
        else {
            ImageIcon icon = new ImageIcon(imageURL);
            image.setIcon(icon);
            myFrame.pack();
        }
    }

    /**
     * Enable or disable input in the input field.
     */
    public void enable(boolean on)
    {
        entryField.setEditable(on);
        if(!on)
            entryField.getCaret().setBlinkRate(0);
    }

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
      // Initializes the attributs
      
        myFrame = new JFrame( "Il faut retrouver Ponette");
        myFrame.setPreferredSize(new Dimension(886,700));
        sidePanel = new JPanel();
        fontPanel = new JPanel();
        mainPanel = new JPanel();
        movePanel = new JPanel();
        actionPanel = new JPanel();
        spePanel= new JPanel();
        midPanel= new JPanel();
        entryField = new JTextField(34);
        image = new JLabel();
        sLogP=new JTextArea();
        sLogP.setEditable(false);
        log = new JTextArea();
        sLogI=new JTextArea();
        log.setEditable(false);
        sLogI.setEditable(false);
        JScrollPane listScroller = new JScrollPane(log);
        mainCountdown=new JLabel("                         "+aGameEngine.getTime().counterString());
        wCountdown = new JLabel("              "+aGameEngine.getTimeW().counterString());
       
        // Set Size to these elments
        sLogP.setMaximumSize(new Dimension(201,130));
        sLogP.setPreferredSize(new Dimension(200,130));
        sLogI.setMaximumSize(new Dimension(201,421));
        sLogI.setPreferredSize(new Dimension(200,420));
        listScroller.setPreferredSize(new Dimension(672,120));
        entryField.setPreferredSize(new Dimension(672,30));
        
        // Set Size of the two main Panels and their contains
        fontPanel.setPreferredSize(new Dimension(672,700));
        sidePanel.setPreferredSize(new Dimension(200,700));
        sidePanel.setLayout(new BorderLayout());
        sidePanel.add(sLogI, BorderLayout.NORTH);
        sidePanel.add(mainCountdown, BorderLayout.CENTER);
        sidePanel.add(sLogP, BorderLayout.SOUTH);   
        fontPanel.setLayout(new BorderLayout());
        fontPanel.add(image,BorderLayout.NORTH);
        fontPanel.add(midPanel,BorderLayout.CENTER);
        fontPanel.add(mainPanel,BorderLayout.SOUTH);
        
       
        
        
        // Set Size of the n-1 Panels and their contains
        mainPanel.setPreferredSize(new Dimension(672,130));
        midPanel.setPreferredSize(new Dimension(672,130));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(spePanel,BorderLayout.EAST);
        mainPanel.add(actionPanel,BorderLayout.WEST);
        mainPanel.add(movePanel,BorderLayout.CENTER);
        mainPanel.add(entryField,BorderLayout.SOUTH);
        midPanel.setLayout(new BorderLayout());
        midPanel.add(listScroller,BorderLayout.NORTH);
        
        
        
      
        
        
        

   
      // Initializes theses elements 
      buttonLeft = new JButton("Left");
      buttonBack = new JButton("Back");
      buttonStraight = new JButton("Straight");
      buttonRight= new JButton("Right");
     
      realTime=new Timer(1000,this);
      realTimeW=new Timer(1000,this);
      buttonUp= new JButton("Climb");
      buttonDown=new JButton("Dig");
      buttonRun=new JButton("Run");
      buttonInventory = new JButton ("Inventory");
      buttonPrevious = new JButton("Previous");    
      buttonRagequit = new JButton( "Wan2Quit ? ");
      buttonLook = new JButton( " Look " );
      buttonHelp= new JButton("Mummy heeeelp me");
      
      // Set Size of the n- 2 Panel and their contains
      spePanel.setPreferredSize(new Dimension(224,130));
      actionPanel.setPreferredSize(new Dimension(224,130));
      movePanel.setPreferredSize(new Dimension(224,130));
      entryField.setPreferredSize(new Dimension(672,30));
      actionPanel.setLayout(new BorderLayout()); 
      actionPanel.add(buttonLook,BorderLayout.NORTH);
      actionPanel.add(buttonRagequit, BorderLayout.SOUTH);
      actionPanel.add(buttonHelp,BorderLayout.CENTER);
      
      
      movePanel.setLayout(new BorderLayout());
      movePanel.add(buttonStraight,BorderLayout.NORTH);
      movePanel.add(buttonBack,BorderLayout.SOUTH);
      movePanel.add(buttonLeft,BorderLayout.WEST);
      movePanel.add(buttonRight,BorderLayout.EAST);
      movePanel.add(wCountdown,BorderLayout.CENTER);
      
      spePanel.setLayout(new BorderLayout()); 
      spePanel.add(buttonUp,BorderLayout.EAST);
      spePanel.add(buttonDown,BorderLayout.WEST);
      spePanel.add(buttonRun,BorderLayout.CENTER);
      spePanel.add(buttonInventory,BorderLayout.NORTH);
      spePanel.add(buttonPrevious,BorderLayout.SOUTH);
        

    
    
      // Set the Frame
      myFrame.getContentPane().add(fontPanel, BorderLayout.WEST);
      myFrame.getContentPane().add(sidePanel, BorderLayout.EAST);
      
      myFrame.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

      // Add the buttons to the listener : if pressed, we'll know  
      buttonLeft.addActionListener(this);
      buttonRight.addActionListener(this);
      buttonBack.addActionListener(this);
      buttonStraight.addActionListener(this);
      buttonRagequit.addActionListener(this);
      buttonLook.addActionListener(this);
      buttonPrevious.addActionListener(this);
      buttonInventory.addActionListener(this);
      buttonHelp.addActionListener(this);
      buttonUp.addActionListener(this);
      buttonDown.addActionListener(this);
      buttonRun.addActionListener(this);
        
        
      entryField.addActionListener(this);
      myFrame.pack();
      myFrame.setVisible(true);
      entryField.requestFocus();
      realTime.start();
    }

    /**
     * Actionlistener interface for the entries : Button/TextField/Countdown
     */
    public void actionPerformed(ActionEvent e) 
    {
       
        
        Object objet = e.getSource();
        if (objet == entryField )
           processCommand();
        else if(objet == buttonRagequit )
        processCommand( "quit");
        else if(objet == buttonLook)
        processCommand( "look") ;
        else if(objet == buttonPrevious)
        processCommand("back");
        else if(objet == buttonInventory)
        processCommand("items");
        else if(objet == buttonHelp)
        processCommand("help");
        
        else if(objet == buttonRight)
        processCommand("go right");
        else if(objet == buttonStraight)
        processCommand("go straight");
        else if(objet == buttonLeft)
        processCommand("go left");
        else if(objet == buttonBack)
        processCommand("go back");
        
         else if (objet==realTimeW && aGameEngine.getInDen()==true )
        {  aGameEngine.getTimeW().decrement();
           wCountdown.setText("              "+aGameEngine.getTimeW().counterString()); 
         if ( aGameEngine.getTimeW().countDownState() ==false )
         { realTimeW.stop();
           aGameEngine.endGame() ;
           println("Too sloooooow ! You've been eaten !");
           showImage("deadw.jpg");}
           else  {}}
          
        else if(objet == buttonUp)
        processCommand("go up");
        else if(objet == buttonRun)
        processCommand("go run");
        else if(objet == buttonDown)
        processCommand("go down");
        
        else if (objet==realTime)
          {aGameEngine.getTime().decrement();
          mainCountdown.setText("                         "+aGameEngine.getTime().counterString());
          if ( aGameEngine.getTime().countDownState() ==false ) 
          {realTime.stop();
          aGameEngine.endGame();
          println(" Nooooo ! You've come too late ! Ponette is dead ! ");
           showImage("eaten.jpg"); }
          else
             {} }
          
       
        
        
    }
    
    /**
     * Process Command for the entryfield :
     *  - "translate" the input in 2 words
     *  - process the 2 words
     */
   private void processCommand()
   {
      String input = entryField.getText();
      entryField.setText( "");
      aGameEngine.interpretCommand(input);
    }
   /**
    * Process Command for the button : We already know the first and second word
    */
   private void processCommand(String button)
   { 
      aGameEngine.interpretCommand(button);
     }
     /**
      * Returns the realTime
      */
   public Timer getRealTime()
   { return realTime;}
   
   public Timer getRealTimeW()
   { return realTimeW ; }
   
   public void disableButton()
   { buttonUp.setEnabled(false);
     buttonDown.setEnabled(false);
     buttonRun.setEnabled(false);
     buttonInventory.setEnabled(false);
     buttonPrevious.setEnabled(false);
     buttonLeft.setEnabled(false);
     buttonRight.setEnabled(false);
     buttonStraight.setEnabled(false);
     buttonBack.setEnabled(false);
     buttonRagequit.setEnabled(false);
     buttonLook.setEnabled(false);
     buttonHelp.setEnabled(false);
}
}
