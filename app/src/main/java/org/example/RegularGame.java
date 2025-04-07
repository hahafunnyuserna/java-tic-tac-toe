package org.example;

import java.util.Scanner;

public class RegularGame
{    
    public RecordLogger log = new RecordLogger();

    public static Scanner scan;

    static void createBoard(char[][] board)
    {
        System.out.println("—————————————");
        
        for (int i = 0; i < 3; i++)
        {
            System.out.print("❚ ");
            for (int j = 0; j < 3; j++)
            {
                System.out.print(board[i][j] + " ❚ ");
            }

            System.out.println("\n—————————————");
        }
    }

    static int moveOneCheck(char player)
    {
        scan = new Scanner(System.in);
        int move = 0;
        System.out.println("Player " + player + ", enter a row (0-2) to play:");
        while (true)
        {
            if (scan.hasNextInt())
            {
                move = scan.nextInt();
                if ((move < 3) && (move > -1))
                {
                    break;
                } else {
                    System.out.println("ERROR: Invalid input. Try again.\nPlayer " + player + ", enter a row:");
                    scan.next();
                }
            } else {
                System.out.println("ERROR: Invalid input. Try again.\nPlayer " + player + ", enter a row:");
                scan.next();
            }
        }

        return move;
    }

    static int moveTwoCheck(char player)
    {
        scan = new Scanner(System.in);
        int move = 0;
        System.out.println("Player " + player + ", enter a column (0-2) to play:");
        while (true)
        {
            if (scan.hasNextInt())
            {
                move = scan.nextInt();
                if ((move < 3) && (move > -1))
                {
                    break;
                } else {
                    System.out.println("ERROR: Invalid input. Try again.\nPlayer " + player + ", enter a column:");
                    scan.next();
                }
            } else {
                System.out.println("ERROR: Invalid input. Try again.\nPlayer " + player + ", enter a column:");
                scan.next();
            }
        }

        return move;
    }


    static boolean checkWin(char[][] board, char player)
    {
        for (int i = 0; i < 3; i++)
        { 
            if ((board[i][0] == player) && (board[i][1] == player) 
                && (board[i][2] == player))
            {
                return true;
            }
    
            if ((board[0][i] == player) && (board[1][i] == player)
                && (board[2][i] == player))
            {
                return true;
            }
        } 
    
        if ((board[0][0] == player) && (board[1][1] == player) 
            && (board[2][2] == player))
            {
                return true;
            }
    
        if ((board[0][2] == player) && (board[1][1] == player) 
            && (board[2][0] == player))
        {
            return true;
        }
    
        return false; 
    }

    public void standardGame()
    {
        

        scan = new Scanner(System.in);

        System.out.println("\n\n\n\n\n");
        System.out.println("Please input the character for player 1:");
        char playerOne = scan.nextLine().charAt(0);
        
        System.out.println("Please input the character for player 2:");
        char playerTwo = scan.nextLine().charAt(0);
        if ((playerTwo == playerOne))
        {
            while (true)
            {
                if (playerTwo == playerOne)
                {
                    System.out.println("ERROR: Marker is identical to player 1.\nPlease input the character for player 2:");
                }
                
                playerTwo = scan.nextLine().charAt(0);
                if (playerTwo != playerOne)
                {
                    break;
                }
            }
        }

        char[][] board =
        {
            { ' ', ' ', ' ' }, 
            { ' ', ' ', ' ' }, 
            { ' ', ' ', ' ' }
        }; 
                                
        char players[] = {playerOne, playerTwo}; 
        int row = 0;
        int col = 0;

        int turn;
        
        for (turn = 0; turn < 9; turn++)
        { 
            for (int i = 0; i < 2; i++)
            {
                createBoard(board); 

                while (true)
                { 
                    row = moveOneCheck(players[i]);
                    col = moveTwoCheck(players[i]);

                    if (board[row][col] != ' ')
                    { 
                        System.out.println("This space is already filled. Try again.");
                    } else { 
                        break;
                    } 
                } 

                board[row][col] = players[i]; 

                if (checkWin(board, players[i]))
                { 
                    createBoard(board); 
                    System.out.println("\n\nPlayer " + players[i] + " wins!"); 
                    log.addWin(i);
                    return;
                } 
            }
                
        } 

        createBoard(board); 

        if ((turn == 9) && !(checkWin(board, players[0])) && !(checkWin(board, players[1])))
        { 
            System.out.println("It's a draw!");
            log.addTie();
            return;
        } 



    }
    
}
