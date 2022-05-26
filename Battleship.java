import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Battleship extends JFrame implements ActionListener
{
   private GridLayout grid = new GridLayout(10,10,10,10);
   private String[][] letters = new String[10][10];
   private Boolean[][] ships = new Boolean[10][10];
   private String[] shipLocation = new String[5];
   private JButton startButton = new JButton("Start"), button;
   private ArrayList hitShips = new ArrayList <JButton>();
   private ArrayList hasBeenHit = new ArrayList<JButton>();
   private JFrame startFrame = new JFrame("Battleship");
   private JFrame frame = new JFrame("Battleship");
   private int totalShipHits = 0, totalSpotsHit = 0;
   
   public Battleship()
   {
      titleScreen();
   }
   
   /*Creates the title screen with instructions*/
   public void titleScreen()
   {
      JLabel titleLabel = new JLabel("BATTLESHIP");
      JLabel instructions = new JLabel("There are 5 ships, you have 20 turns to find them all");
      JPanel startPanel = new JPanel(new BorderLayout());
      
      startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      startFrame.setLocation(600,300);
      
      startButton.setBounds(45,100,200,100);
      startButton.addActionListener(this);
      
      startFrame.add(startButton);
      startFrame.setSize(300,300);
      
      titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
      titleLabel.setFont(new Font("Helvectica", Font.BOLD, 40));
      titleLabel.setForeground(Color.red);
      
      instructions.setHorizontalAlignment(SwingConstants.CENTER);
      instructions.setVerticalAlignment(SwingConstants.BOTTOM);
      instructions.setFont(new Font("Helvectica", Font.PLAIN, 12));
      
      startPanel.add(titleLabel, BorderLayout.NORTH);
      startPanel.add(instructions);
      startFrame.add(startPanel);
      startFrame.show();
      
      startFrame.setLayout(new BorderLayout());
      startFrame.setVisible(true);
      
   }
   
   /*Initalizes and adds the spot label to be added to the buttons for the board*/
   public void initializeLetters()
   {
      String[] l = new String[10];
      l[0] = "A";
      l[1] = "B";
      l[2] = "C";
      l[3] = "D";
      l[4] = "E";
      l[5] = "F";
      l[6] = "G";
      l[7] = "H";
      l[8] = "I";
      l[9] = "J";
      
      for(int x = 0; x < letters.length; x++)
      {
         for(int y = 0; y < letters[0].length; y++)
         {
            for(int z = 0; z <= y; z++)
            {
               letters[x][y] = l[x] + "" + (z+1);
            }
         }
      }
   }
   
   /*Sets where the ships will be*/
   public void placeShips()
   {
      int n, totShips = 0;
      
      for(int x = 0; x < ships.length; x++)
      {
         for(int y = 0; y < ships[0].length; y++)
         {
            n = (int)(Math.random() * 100 + 1);
            if(n <= 10 && totShips < 5)
            {
               ships[x][y] = true;
               totShips +=1;    
            }
            else
            {
               ships[x][y] = false;
            }     
         }
         
      }
      
      if(totShips < 5) //Checks if there are 5 ships total, if not the function is recalled
      {
         placeShips();
      }
   }
   
   /*Attaches ship location to the location on the board*/
   public void getPlaceShips()
   {
      int counter = 0;
      
      for(int x = 0; x < ships.length; x++)
      {
         for(int y = 0; y < ships[0].length; y++)
         {
            if(ships[x][y] == true)
            {
               //System.out.println(letters[x][y].toString());
               shipLocation[counter] = letters[x][y].toString();
               counter++;
            }
         }  
      }
   }
   
   /*Checks if a spot on the board has already been hit*/
   public boolean hasBeenHit(JButton b)
   {
      for(int x = 0; x < hasBeenHit.size(); x++)
      {
         if(hasBeenHit.get(x) == button)
         {
            return true;
         }
      }
      
      hasBeenHit.add(button);
      totalSpotsHit++;
      maxTurns();
      return false;
   }
   
   /*Checks if the max turns have been reached or if all ships have been hit*/
   public void maxTurns()
   {
      if(totalShipHits == 5)
      {
         frame.dispose();
         winScreen();
      }
      
      else if(totalSpotsHit == 20)
      {
         frame.dispose();
         loseScreen();
      }
   }
   
   /*Displays the winner screen*/
   public void winScreen()
   {
      JFrame winnerFrame = new JFrame("Win!");
      JPanel winnerPanel = new JPanel(new BorderLayout());
      JLabel winnerLabel = new JLabel("You Won!");
      
      winnerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      winnerFrame.setLocation(600,300);
      winnerFrame.setSize(300,300);
      
      winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
      winnerLabel.setFont(new Font("Serif", Font.BOLD, 40));
      winnerLabel.setForeground(Color.red);
      
      winnerPanel.add(winnerLabel);
      winnerFrame.add(winnerPanel);
      winnerFrame.show();
      
      winnerFrame.setLayout(new BorderLayout());
      winnerFrame.setVisible(true);
      
   }
   
   /*Displays the loser screen*/
   public void loseScreen()
   {
      JFrame loserFrame = new JFrame("Lose!");
      JPanel loserPanel = new JPanel(new BorderLayout());
      JLabel loserLabel = new JLabel("You Lost!");
      
      loserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      loserFrame.setLocation(600,300);
      loserFrame.setSize(300,300);
      
      loserLabel.setHorizontalAlignment(SwingConstants.CENTER);
      loserLabel.setFont(new Font("Serif", Font.BOLD, 40));
      loserLabel.setForeground(Color.red);
      
      loserPanel.add(loserLabel);
      loserFrame.add(loserPanel);
      loserFrame.show();
      
      loserFrame.setLayout(new BorderLayout());
      loserFrame.setVisible(true);
   }
   
   /*Actions for when the buttons are pressed*/
   public void actionPerformed(ActionEvent e)
   {
      button = (JButton)(e.getSource());
      if(e.getSource() == startButton)
      {
         startFrame.dispose();
         createAndShowGUI();
      }
      else if(e.getSource() == hitShips.get(0) || e.getSource() == hitShips.get(1) || e.getSource() == hitShips.get(2) || e.getSource() == hitShips.get(3) || e.getSource() == hitShips.get(4))
      {
        totalShipHits++;
        button.setText("Hit!");
        button.setBackground(Color.black);
        button.setForeground(Color.red);
        hasBeenHit(button);
      }
      else
      {
         button.setText("Miss!");
         button.setBackground(Color.gray);
         hasBeenHit(button);
      }
        
   }
   
   /*Creates the GUI for the battleship board*/
   public void createAndShowGUI()
   {
      JPanel board = new JPanel(new GridLayout(10,10,10,10));
      
      //Parameters for the window
      frame.setSize(800,700);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      initializeLetters();
      placeShips();
      getPlaceShips();
      
      //Buttons
      for(int x = 0; x < letters.length; x++)
      {
         for(int y = 0; y < letters[0].length; y++)
         {
            button = new JButton(letters[x][y].toString());
            button.addActionListener(this);
            for(int z = 0; z < 5; z++)
            {
               if((letters[x][y].toString()).equals(shipLocation[z]))
               {
                  hitShips.add(button);
                  board.add(button);
               }
            }
            board.add(button);
         }
      }
      
      frame.add(board);
      frame.setVisible(true);
   
   }
   
   public static void main(String [] args)
   {
      Battleship b = new Battleship();
      
   }
}


