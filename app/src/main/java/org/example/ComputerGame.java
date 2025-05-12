package org.example;

import java.util.*;


public class ComputerGame
{
    public char charOne;
    public char charTwo;
    
    public char[][] board =
        {
            { ' ', ' ', ' ' }, 
            { ' ', ' ', ' ' }, 
            { ' ', ' ', ' ' }
        }; 

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

    public int randomGen(int numOne, int numTwo)
    {
        if (Math.random() > 0.5)
        {
            return numOne;
        } else {
            return numTwo;
        }
    }

    public int computerGame(RecordLogger log, int mod)
    {
        int turnCount = 0;

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
                                
        char player = 'v';
        char oppo = 'v';
        int row = 0;
        int col = 0;

        int turn;
        
        for (turn = 0; turn <= 4; turn++)
        { 
            createBoard(board); 

            if (mod == 1)
            {
                turnCount++;
                computerMove(player, oppo, turnCount);
            }

            turnCount++;

            while (true)
            { 
                row = moveOneCheck(player);
                col = moveTwoCheck(player);

                if (board[row][col] != ' ')
                { 
                    System.out.println("This space is already filled. Try again.");
                } else { 
                    break;
                } 
            } 

            board[row][col] = player; 

            if (checkWin(board, player))
            { 
                createBoard(board); 
                System.out.println("\n\nPlayer " + player + " wins!"); 
                    
                if (player == playerOne)
                {
                    log.addWin(0);
                } else {
                    log.addWin(1);
                }
                log.addGame();
                log.printRecord();

                return ((turn + 1) % 2);
            } 

            player = (player == playerOne) ? playerTwo : playerOne;
            oppo = (player == playerTwo) ? playerOne : playerTwo;

            if (mod == 0)
            {
                turnCount++;
                computerMove(player, oppo, turnCount);
            }

        } 

        createBoard(board); 

        if ((turn == 9) && !(checkWin(board, playerOne)) && !(checkWin(board, playerTwo)))
        { 
            System.out.println("It's a draw!");
            log.addTie();
            log.addGame();
            log.printRecord();
            return 0;
        } 
        return 0;

    }

    public int computerMove(char player, char oppo, int turnCount)
    {
        if (turnCount == 1)
        {
            board[randomGen(0, 2)][randomGen(0, 2)] = player;
        } else if ((turnCount == 2) && (board[1][1] == ' ')) {
                board[1][1] = player;
        } else {
            for (int i = 0; i < 3; i++)
            {
                if (board[i][1] == ' ')
                {
                    board[i][1] = player;
                    if (checkWin(board, player))
                    {
                        return 0;
                    } else {
                        board[i][1] = oppo;
                        if (checkWin(board, oppo))
                        {
                            board[i][1] = player;
                            return 0;  
                        } else {
                            board[i][1] = ' ';
                        }
                    }
                }
                
                if (board[1][i] == ' ')
                {
                    board[1][i] = player;
                    if (checkWin(board, player))
                    {
                        return 0;
                    } else {
                        board[1][i] = oppo;
                        if (checkWin(board, oppo))
                        {
                            board[1][i] = player;
                            return 0;  
                        } else {
                            board[1][i] = ' ';
                        }
                    }
                }

                for (int j = 0; j < 3; j++)
                {
                    if (board[i][j] == ' ')
                    {
                        board[i][j] = player;
                        return 0;
                    }
                }
            }

        }

        return 0;
    }
}
