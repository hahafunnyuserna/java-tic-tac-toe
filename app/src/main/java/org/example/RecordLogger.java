package org.example;

import java.io.*;
import java.util.*;

public class RecordLogger
{
    int games = 0;
    int[] wins = {0, 0};
    int ties = 0;

    public RecordLogger()
    {
        this.wins = wins;
        this.ties = ties;
        this.games = games;
    }

    public void addGame()
    {
        games++;
    }

    public void addWin(int place)
    {
        wins[place]++;
    }

    public void addTie()
    {
        ties++;
    }

    public void printRecord()
    {
        System.out.println("Games: " + games);
        System.out.println("Player 1 Wins: " + wins[0]);
        System.out.println("Player 2 Wins: " + wins[1]);
        System.out.println("Ties: " + ties);
    }

    public void writeFile()
    {
        try (FileWriter writer = new FileWriter("log.txt"))
        {
            writer.write("Game Statistics:");
            writer.write("\nGames: " + games);
            writer.write("\nPlayer 1 Wins: " + wins[0]);
            writer.write("\nPlayer 2 Wins: " + wins[1]);
            writer.write("\nTies: " + ties);

        } catch (IOException exception) {
            System.out.println("ERROR: " + exception.getMessage());
        }
    }    
}


