/*
 * Jessica Pu
 * July 17th, 2024
 * Tic Tac Toe GUI
 */

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.util.Random;
 
 public class TicTacToeGUI extends JFrame implements ActionListener {
     private static final int GRID_SIZE_MIN = 3;
     private static int gridSize;
     private JButton[][] buttons;
     private int[][] grid;
     private int movesMade;
     private int player1Score;
     private int player2Score;
     private String player1Name;
     private String player2Name;
     private int nextTurn;
 
     public TicTacToeGUI() {
         setupGame();
     }
 
     private void setupGame() {
         player1Score = 0;
         player2Score = 0;
 
         player1Name = JOptionPane.showInputDialog("Enter a one-word username for player 1:").toUpperCase();
         player2Name = JOptionPane.showInputDialog("Enter a one-word username for player 2:").toUpperCase();
         
         Random random = new Random();
         nextTurn = random.nextInt(2) + 1;
         
         do {
             gridSize = Integer.parseInt(JOptionPane.showInputDialog("What grid size do you want to use (greater or equal to 3)?"));
         } while (gridSize < GRID_SIZE_MIN);
 
         grid = new int[gridSize][gridSize];
         buttons = new JButton[gridSize][gridSize];
         movesMade = 0;
 
         setTitle("Tic Tac Toe");
         setLayout(new GridLayout(gridSize, gridSize));
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(600, 600);
 
         for (int i = 0; i < gridSize; i++) {
             for (int j = 0; j < gridSize; j++) {
                 buttons[i][j] = new JButton();
                 buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                 buttons[i][j].addActionListener(this);
                 add(buttons[i][j]);
             }
         }
 
         displayGrid();
         setVisible(true);
     }
 
     private void displayGrid() {
         for (int i = 0; i < gridSize; i++) {
             for (int j = 0; j < gridSize; j++) {
                 if (grid[i][j] == 1) {
                     buttons[i][j].setText("O");
                 } else if (grid[i][j] == 2) {
                     buttons[i][j].setText("X");
                 } else {
                     buttons[i][j].setText("");
                 }
             }
         }
     }
 
     private boolean checkWinner(int x, int y) {
         int player = grid[x][y];
 
         boolean win = true;
         for (int i = 0; i < gridSize; i++) {
             if (grid[x][i] != player) {
                 win = false;
                 break;
             }
         }
         if (win) return true;
 
         win = true;
         for (int i = 0; i < gridSize; i++) {
             if (grid[i][y] != player) {
                 win = false;
                 break;
             }
         }
         if (win) return true;
 
         if (x == y) {
             win = true;
             for (int i = 0; i < gridSize; i++) {
                 if (grid[i][i] != player) {
                     win = false;
                     break;
                 }
             }
             if (win) return true;
         }
 
         if (x + y == gridSize - 1) {
             win = true;
             for (int i = 0; i < gridSize; i++) {
                 if (grid[i][gridSize - 1 - i] != player) {
                     win = false;
                     break;
                 }
             }
             if (win) return true;
         }
 
         return false;
     }
 
     @Override
     public void actionPerformed(ActionEvent e) {
         JButton buttonClicked = (JButton) e.getSource();
         int x = -1, y = -1;
 
         for (int i = 0; i < gridSize; i++) {
             for (int j = 0; j < gridSize; j++) {
                 if (buttons[i][j] == buttonClicked) {
                     x = i;
                     y = j;
                 }
             }
         }
 
         if (grid[x][y] != 0) {
             JOptionPane.showMessageDialog(this, "This cell is already occupied. Choose another.");
             return;
         }
 
         grid[x][y] = nextTurn;
         movesMade++;
 
         displayGrid();
 
         if (checkWinner(x, y)) {
             String winner = (nextTurn == 1) ? player1Name : player2Name;
             JOptionPane.showMessageDialog(this, "========== " + winner + " wins! ==========");
             if (nextTurn == 1) {
                 player1Score++;
             } else {
                 player2Score++;
             }
             restartGame();
         } else if (movesMade == gridSize * gridSize) {
             JOptionPane.showMessageDialog(this, "========== There is a tie! ==========\nNo changes have been made to the score.");
             restartGame();
         } else {
             nextTurn = (nextTurn == 1) ? 2 : 1;
         }
     }
 
     private void restartGame() {
         int response = JOptionPane.showConfirmDialog(this, "Do you want to play another round?", "Play Again?", JOptionPane.YES_NO_OPTION);
         if (response == JOptionPane.YES_OPTION) {
             setupGame();
         } else {
             System.exit(0);
         }
     }
 
     public static void main(String[] args) {
         new TicTacToeGUI();
     }
 }
 