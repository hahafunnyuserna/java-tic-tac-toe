package org.example;

import java.util.Scanner;

class Main
{
    RecordLogger log = new RecordLogger();

    public static Scanner scan;

    static boolean repeatGame()
    {
        scan = new Scanner(System.in);

        System.out.println("Do you want to play again? [Y/N]");

        scan = new Scanner(System.in);

        String repeatInput = scan.nextLine();
        while (!(repeatInput.equalsIgnoreCase("yes")) && !(repeatInput.equalsIgnoreCase("no")) && !(repeatInput.equalsIgnoreCase("y")) && !(repeatInput.equalsIgnoreCase("n")))
        {
            System.out.println("ERROR: Please select a valid option.");
            repeatInput = scan.nextLine();
        }

        return ((repeatInput.equalsIgnoreCase("yes")) || (repeatInput.equalsIgnoreCase("y")));
    }

    public static void main(String[] args)
    {
        RecordLogger log = new RecordLogger();

        RegularGame standard = new RegularGame();
        ComputerGame computer = new ComputerGame();
        
        boolean repeat = true;

        int move = 0;
    
        System.out.println("Welcome!");
        
        while (repeat)
        {
            scan = new Scanner(System.in);
            System.out.println("Please select a game:\n2-Player Game - G\nComputer Game - C");
            String game = scan.nextLine();
            while (!(game.equalsIgnoreCase("g")) && !(game.equalsIgnoreCase("c")))
            {
                System.out.println("ERROR: Please select a valid option.");
                game = scan.nextLine();
            }

            if (game.equalsIgnoreCase("c"))
            {
                System.out.println("Please select which player goes first:\nHuman - 1\nComputer - 2");
                int compSelect = scan.nextInt();
                while (!(compSelect == 1) && !(compSelect == 2))
                {
                    System.out.println("ERROR: Please select a valid option.");
                    compSelect = scan.nextInt();
                }
                compSelect--;
                System.out.println(compSelect);

                move = computer.computerGame(log, compSelect);
               
            } else {
                move = standard.standardGame(log, move);
            }
            repeat = repeatGame();             
        }
        scan.close();

        log.writeFile();
        System.out.println("Records printed at /app/log.txt.\nGoodbye.");

    }    
}