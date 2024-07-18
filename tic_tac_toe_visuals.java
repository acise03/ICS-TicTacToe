/*
 * Jessica Pu
 * July 18th, 2024
 * Tic Tac Toe
 */

// NOTE: I have prior experience with 2D arrays, the random library, and swing/awt (HOWEVER, I referenced [https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/package-summary.html] for some of the GUI commands)

import java.util.Random; //import random library
//GUI libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class tic_tac_toe_visuals // name of class
{ // start of class
    static int[][] grid; //grid for players' moves (0 for no move there yet, 1 for player 1 move, 2 for player 2 move)
    static JPanel score; // panel for showing scores
    static JButton[][] buttons; // buttons for board
    static int grid_size = 0; // the chosen size
    static int player1_score = 0; // score for player 1
    static int player2_score = 0; // score for player 2
    static String player1_name = null; // inputted username for player 1
    static String player2_name = null; // inputted username for player 2
    static JFrame frame; // main window for game
    static int nextTurn; // who's turn is next (or has just been made)
    static int moves_made; // total moves made
    static JLabel score1; // label for player 1 score
    static JLabel score2; // label for player 2 score

    public static void main(String[] args) // magic line
    { // start of main method
            player1_name = JOptionPane.showInputDialog("Enter a username for player 1:").toUpperCase(); // prompt and store username
            player2_name = JOptionPane.showInputDialog("Enter a username for player 2:").toUpperCase(); // prompt and store username
            JOptionPane.showMessageDialog(null, player1_name + ", you will be using O's"); // inform user of their piece
            JOptionPane.showMessageDialog(null, player2_name + ", you will be using X's"); // inform user of their piece
            game(); // calls game method
    } // end of main method

    public static void game() // start game
    { // start of method
            frame = new JFrame("TIC TAC TOE"); // set up frame and name it
            moves_made = 0; // reset the total moves made

                do 
                    grid_size = Integer.parseInt(JOptionPane.showInputDialog("Enter a grid size (greater than 2):")); // prompt and store user inputted game size
                while (grid_size < 3); // keep asking if the inputted number is less than 3
            
            Random random = new Random(); // random object
            nextTurn = random.nextInt(2) + 1; // pick a random number 0 to 1 but adding one means random number 1 or 2
            
                if (nextTurn == 1) // if person 1 is going first
                    JOptionPane.showMessageDialog(frame, "This time, " + player1_name + " will go first."); // tell user with person 1's name
                else if (nextTurn == 2) // if person 2 is going first
                    JOptionPane.showMessageDialog(frame, "This time, " + player2_name + " will go first."); // tell user with person 2's name

            grid = new int[grid_size][grid_size]; // set up 2D array for user moves
            buttons = new JButton[grid_size][grid_size]; // set up visual grid with buttons as a box
            frame.setBackground(new Color(247, 160, 114)); // set colour
            score = new JPanel(new FlowLayout(FlowLayout.CENTER)); // for displaying scores
            score1 = new JLabel(player1_name + ": " + player1_score); // label shows player 1's score
            score2 = new JLabel(player2_name + ": " + player2_score); // label shows player 2's score
            score.setBackground(new Color(169, 222, 249)); // background for score region is set
            score.add(score1); // score 1 is put onto the score panel
            score.add(score2); // score 2 is put onto the score panel
            frame.add(score, BorderLayout.NORTH); // the score panel is put onto the frame and set in the north area
            JPanel board = new JPanel(new GridLayout(grid_size, grid_size)); // create board for grid

                for (int first_value = 0; first_value < grid_size; first_value = first_value + 1) // loop through each array of the 2D array
                {
                        for (int second_value = 0; second_value < grid_size; second_value = second_value + 1) // loop through ever element of the 1D array
                        {
                            buttons[first_value][second_value] = new JButton(); // initialize button
                            buttons[first_value][second_value].setFont(new Font("Serif", Font.BOLD, 300 / grid_size)); // set font, size (dependent on the grid size) for X's and Os
                            buttons[first_value][second_value].setBackground(new Color(249, 247, 243)); // Set button background color
                            buttons[first_value][second_value].setForeground(new Color(62, 187, 250)); // Set text color
                    
                                buttons[first_value][second_value].addActionListener(new ActionListener() // adds listener to detect if button is pressed
                                {
                                    public void actionPerformed(ActionEvent click) // whenever a button is clicked
                                    {
                                        action(click); // calls method
                                    }
                                });

                            board.add(buttons[first_value][second_value]); // buttons are added to the board panel
                        }
                }

            showGrid(); // calls method to show grid 
            frame.add(board, BorderLayout.CENTER); // board is added to the frame
            frame.setSize(600, 700); // frame size is set
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set way window is closed
            frame.setVisible(true); // show frame
            
            if (grid_size >= 10) // if the grid size is large
                JOptionPane.showMessageDialog(frame, "Use a greater display monitor or full screen the display if the X's or O's are not visible"); // warm user about X's and O's visibility
    } // end of method

    public static void showGrid() // method to display grid
    { // start of method
            for (int first_value = 0; first_value < grid_size; first_value = first_value + 1) // loop through each 1D array
            {
                    for (int second_value = 0; second_value < grid_size; second_value = second_value + 1) // loop through each element
                    {
                            if (grid[first_value][second_value] == 1) // if it is player 1
                                    buttons[first_value][second_value].setText("O"); // display O for player 1's symbol
                            else if (grid[first_value][second_value] == 2) // if it is player 2
                                buttons[first_value][second_value].setText("X"); // display X for player 2's symbol
                            else // no player has gone there yet
                                buttons[first_value][second_value].setText(""); // display nothing
                    }
            }
    } // end of method

    public static void action(ActionEvent press) // if a button is clicked
    { // start of method
        int horiz = 0; // first value in grid
        int verti = 0; // second value in grid

            JButton click = (JButton) press.getSource(); // get clicked button

                for (int first_value = 0; first_value < grid_size; first_value = first_value + 1) // loop through each 1D array
                {
                        for (int second_value = 0; second_value < grid_size; second_value = second_value + 1) // loop through each element
                        {
                                if (buttons[first_value][second_value] == click) // check if the button was the one that was clicked
                                { 
                                    horiz = first_value; // store the value 
                                    verti = second_value; // store the value
                                }
                        }
                }

                if (grid[horiz][verti] != 0)// if the spot already has a piece on it
                { 
                    JOptionPane.showMessageDialog(frame, "This cell is already occupied. Choose another."); // tell user
                    return; // return and wait for a button to be pressed
                }
         
            grid[horiz][verti] = nextTurn; // set the spot on the grid to have the nextTurn player value
            moves_made = moves_made + 1; // add 1 because a move has been made 
            showGrid(); // show the grid
         
                if (ifWinner(horiz, verti) == true) // the move created a win
                { 
                        if (nextTurn == 1) // if it was player 1's move
                        { 
                            player1_score = player1_score + 1; // add 1 to their score
                            JOptionPane.showMessageDialog(frame, "Congratulations, " + player1_name + " WINS!"); // tell users
                        }
                        else if (nextTurn == 2) // if it was player 2's move
                        { 
                            player2_score = player2_score + 1; // add 1 to their score
                            JOptionPane.showMessageDialog(frame, "Congratulations, " + player2_name + " WINS!"); // tell users
                        }

                    frame.setVisible(false); // close window
                    restart(); // restart the game
                }

                else if (moves_made == grid_size * grid_size) // if the grid is filled (all moves have been made)
                {
                    JOptionPane.showMessageDialog(frame, "There is a tie."); // tell users there is a tie
                    frame.setVisible(false); // close window
                    restart(); // restart the game
                }

                else // if no one wins
                { 
                        if (nextTurn == 1) // if player 1 went
                            nextTurn = 2; // it is player 2's turn next
                       
                        else if (nextTurn == 2) // if player 2 went
                            nextTurn = 1; // it is player 1's turn next
                }
    } // end of method

    public static void restart() // method called when a game ended
    { // start of method
        int response = JOptionPane.showConfirmDialog(frame, "Do you want to play another round?", "Restart?", JOptionPane.YES_NO_OPTION); // prompts user
      
            if (response == JOptionPane.YES_OPTION) // if player wants to play again
                game(); // run game
          
            else // otherwise,
            {
                JOptionPane.showMessageDialog(frame, "FINAL SCORE\n" + player1_name + ": " + player1_score + "\n" + player2_name + ": " + player2_score); // show scores
                System.exit(0); // end game
            }
    } // end of method

    public static boolean ifWinner(int x_pos, int y_pos) // method to check if someone wins
    { // start of method
        int player = 0; // player at position
        boolean same_player = true; // if it is still the same player

            if (x_pos == y_pos) // if move is on diagonal
            { 
                player = grid[0][0]; // player is set (1 or 2)
               
                    if (player != 0) // if someone went at that location
                    {
                        same_player = true; // set same player to true
                      
                            for (int location = 1; location < grid_size; location++) // loop through each diagonal
                            {
                                    if (grid[location][location] != player || grid[location][location] == 0) // if it is not equal to original player, or is empty
                                    {
                                        same_player = false; // not the same player, set it to false
                                        break; // exit loop
                                    }
                            }
                      
                            if (same_player == true) // if it is still the same player
                                return true; // there is a winner!
                    }
            }

            if (x_pos + y_pos == grid_size - 1) // if move is on other diagonal
            {
                player = grid[0][grid_size - 1]; // player is set (1 or 2)
              
                    if (player != 0) // if someone went at that location
                    {
                        same_player = true; // set same player to true

                            for (int location = 1; location < grid_size; location++) // loop through each diagonal
                            {
                                if (grid[location][grid_size - 1 - location] != player || grid[location][grid_size - 1 - location] == 0)// if it is not equal to original player, or is empty
                                { 
                                    same_player = false; // not the same player, set it to false
                                    break; // exit loop
                                }
                            }

                            if (same_player == true) // if it is still the same player
                                return true; // there is a winner!
                    }
            }

            player = grid[x_pos][0]; // player is set (1 or 2)

                if (player != 0) // if someone went at that location
                { 
                    same_player = true; // set same player to true

                        for (int location = 0; location < grid_size; location++) // loop through each row
                        {
                                if (grid[x_pos][location] != player || grid[x_pos][location] == 0) // if it is not equal to original player, or is empty
                                {
                                    same_player = false; // not the same player, set it to false
                                    break; // exit loop
                                }
                        }

                        if (same_player == true) // if it is still the same player
                            return true; // there is a winner!
                }

            player = grid[0][y_pos]; // player is set (1 or 2)

                if (player != 0) // if someone went at that location
                {
                    same_player = true; // set same player to true

                        for (int location = 0; location < grid_size; location++) // loop through each column
                        {
                                if (grid[location][y_pos] != player || grid[location][y_pos] == 0) // if it is not equal to original player, or is empty
                                {
                                    same_player = false; // not the same player, set it to false
                                    break; // exit loop
                                }
                        }

                        if (same_player == true) // if it is still the same player
                            return true; // there is a winner!
                }
                
            return false; // if the method is not returned yet, then there is no winnre, so return false
    } // end of method
} // end of class