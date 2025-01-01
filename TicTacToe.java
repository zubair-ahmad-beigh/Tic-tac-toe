import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        // Initialize the game
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
        char currentPlayer = 'X'; // User starts
        char winner = ' ';
        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");
        printBoard(board);

        // Game loop
        while (!gameOver) {
            if (currentPlayer == 'X') {
                // User's turn
                System.out.print("Your turn (Enter position 1-9): ");
                int position;
                try {
                    position = scanner.nextInt() - 1;
                    if (position < 0 || position >= 9 || board[position] != ' ') {
                        System.out.println("Invalid move! Try again.");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input! Enter a number between 1 and 9.");
                    scanner.next(); // Clear invalid input
                    continue;
                }
                board[position] = currentPlayer;
            } else {
                // System's turn
                System.out.println("System's turn...");
                int position = systemMove(board);
                board[position] = currentPlayer;
            }

            // Print the updated board
            printBoard(board);

            // Check for a winner or draw
            winner = checkWinner(board);
            if (winner != ' ') {
                System.out.println("Winner: " + (winner == 'X' ? "User" : "System"));
                gameOver = true;
            } else if (isDraw(board)) {
                System.out.println("It's a draw!");
                gameOver = true;
            } else {
                // Switch player
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }

        scanner.close();
    }

    // Prints the current state of the board
    public static void printBoard(char[] board) {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            System.out.print(" " + board[i] + " ");
            if (i % 3 != 2) System.out.print("|");
            if (i % 3 == 2 && i != 8) System.out.println("\n---|---|---");
        }
        System.out.println("\n");
    }

    // Checks if there is a winner
    public static char checkWinner(char[] board) {
        int[][] winningPositions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
                {0, 4, 8}, {2, 4, 6}             // Diagonals
        };

        for (int[] positions : winningPositions) {
            if (board[positions[0]] == board[positions[1]] &&
                    board[positions[1]] == board[positions[2]] &&
                    board[positions[0]] != ' ') {
                return board[positions[0]];
            }
        }
        return ' '; // No winner yet
    }

    // Checks if the game is a draw
    public static boolean isDraw(char[] board) {
        for (char c : board) {
            if (c == ' ') return false;
        }
        return true;
    }

    // Generates a move for the system
    public static int systemMove(char[] board) {
        Random random = new Random();
        int position;
        do {
            position = random.nextInt(9);
        } while (board[position] != ' ');
        return position;
    }
}
