/*
 * Jessica Pu
 * July 16th, 2024
 * Tic Tac Toe CPT
 */

import java.util.Scanner; // import the scan toolbox
import java.util.Random;

public class tic_tac_toe // name of class
{ // start of class
    static int[][] grid;
    static int grid_size = 0;

    public static void main(String[] args) // magic line
    { // start of main method
        Scanner scan = new Scanner(System.in);
        int moves_made = 0;
        int player1_score = 0;
        int player2_score = 0;
        String player1_name = null;
        String player2_name = null;
        String play_again = null;
        boolean game_ended = true;
        int next_turn = 0;
        String next_player = null;
        int round_number = 0;
        int playerXmove = 0;
        int playerYmove = 0;
        boolean win = true;
        System.out.println("WELCOME TO TIC TAC TOE");
        System.out.println("{INSTRUCTIONS}");
        System.out.print("Enter a one word username for player 1: ");
        player1_name = scan.next().toUpperCase();
        System.out.print("Enter a one word username for player 2: ");
        player2_name = scan.next().toUpperCase();
        System.out.format(player1_name + ", your piece will be O\n");
        System.out.format(player2_name + ", your piece will be X\n");

        Random r = new Random();
        next_turn = r.nextInt(2) + 1;

        do {
            round_number = round_number + 1;
            System.out.println("========================================");
            System.out.format("ROUND " + round_number + ":\n");

            game_ended = false;
            System.out.print("What grid size do you want to use (greater or equal to 3)? ");
            grid_size = scan.nextInt();
            while (grid_size < 3) {
                System.out.print("Enter a positive integer greater or equal to 3 as a grid size please. ");
                grid_size = scan.nextInt();
            }
            grid = new int[grid_size][grid_size];

            System.out.print("By randomly picking, ");
            if (next_turn == 1)
                next_player = player1_name;
            else if (next_turn == 2)
                next_player = player2_name;
            System.out.print(next_player);
            System.out.println(" will go first.");

            display_grid();

            while (game_ended == false) {
                if (next_turn == 1)
                    next_player = player1_name;
                else if (next_turn == 2)
                    next_player = player2_name;
                do {
                    System.out.print(next_player + ", enter the horizontal coordinate for your move: ");
                    playerXmove = scan.nextInt();
                    System.out.print(next_player + ", enter the vertical coordinate for your move: ");
                    playerYmove = scan.nextInt();
                    if (grid[playerXmove][playerYmove] != 0)
                        System.out.println("Enter an empty grid coordinate");
                } while (grid[playerXmove][playerYmove] != 0);
                moves_made = moves_made + 1;
                grid[playerXmove][playerYmove] = next_turn;
                display_grid();
                win = ifWinner(playerXmove, playerYmove);
                if (win == true) {
                    game_ended = true;
                    if (next_turn == 1)
                        player1_score = player1_score + 1;
                    else if (next_turn == 2)
                        player2_score = player2_score + 1;

                    System.out.println("========== " + next_player + " wins! ==========");
                } else if (moves_made == grid_size * grid_size) {
                    game_ended = true;
                    System.out
                            .println("========== There is a tie! ==========\nNo changes have been made to the score.");

                } else {

                    if (next_turn == 1)
                        next_turn = 2;
                    else if (next_turn == 2)
                        next_turn = 1;
                }
            }
            System.out.format(player1_name + "'s score is " + player1_score);
            System.out.println();
            System.out.format(player2_name + "'s score is " + player2_score);
            System.out.println();
            System.out.print("Do you want to play another round (yes or no)? ");
            play_again = scan.next();
        } while (play_again.equalsIgnoreCase("yes"));

        scan.close();

    } // end of main method

    public static void display_grid() {
        System.out.println("\nTIC TAC TOE GRID\n");
        System.out.format("%-5s", "");
        for (int first_value = 0; first_value < grid_size; first_value++) {
            System.out.format("%-5s", first_value);
        }
        System.out.println();
        for (int first_coord = 0; first_coord < grid_size; first_coord++) {
            System.out.format("%-5d", first_coord);
            for (int second_coord = 0; second_coord < grid_size; second_coord++) {
                if (grid[first_coord][second_coord] == 0) {
                    System.out.format("%-5s", "");
                } else if (grid[first_coord][second_coord] == 1) {
                    System.out.format("%-5s", "O");
                } else if (grid[first_coord][second_coord] == 2) {
                    System.out.format("%-5s", "X");
                }
            }
            System.out.println();
        }
        System.out.println();

    }

    public static boolean ifWinner(int x_pos, int y_pos) {
        int player = 0;
        boolean same_player = true;
        if (x_pos == y_pos) {
            player = grid[0][0];
            if (player != 0) {
                same_player = true;
                for (int location = 1; location < grid_size; location++) {
                    if (grid[location][location] != player || grid[location][location] == 0) {
                        same_player = false;
                        break;
                    }
                }
                if (same_player == true)
                    return true;
            }

        }
        if (x_pos + y_pos == grid_size - 1) {
            player = grid[0][grid_size - 1];
            if (player != 0) {
                same_player = true;
                for (int location = 1; location < grid_size; location++) {
                    if (grid[location][grid_size - 1 - location] != player
                            || grid[location][grid_size - 1 - location] == 0) {
                        same_player = false;
                        break;
                    }
                }
                if (same_player == true)
                    return true;
            }

        }
        player = grid[x_pos][0];
        if (player != 0) {
            same_player = true;
            for (int location = 0; location < grid_size; location++) {
                if (grid[x_pos][location] != player || grid[x_pos][location] == 0) {
                    same_player = false;
                    break;
                }

            }
            if (same_player == true)
                return true;
        }
        player = grid[0][y_pos];
        same_player = true;
        if (player != 0) {
            same_player = true;
            for (int location = 0; location < grid_size; location++) {
                if (grid[location][y_pos] != player || grid[location][y_pos] == 0) {
                    same_player = false;
                    break;
                }

            }
            if (same_player == true)
                return true;
        }
        return false;
    }
} // end of class
