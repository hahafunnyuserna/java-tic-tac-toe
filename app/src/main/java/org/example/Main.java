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
        
        boolean repeat = true;

        int move = 0;
    
        System.out.println("Welcome!");
        while (repeat)
        {
            move = standard.standardGame(log, move);
            repeat = repeatGame();
    
        }

        scan.close();

        log.writeFile();
        System.out.println("Records printed at /app/log.txt.\nGoodbye.");

        
        
    }    
}