package org.example;

import java.io.*;
import java.util.*;

public class RecordLogger
{
    int[] wins = {0, 0};
    public int ties = 0;

    void addWin(int place)
    {
        wins[place]++;
    }

    void addTie()
    {
        ties++;
    }

    void printRecord()
    {
        System.out.println("Player 1 Wins - " + wins[0]);
        System.out.println("Player 2 Wins - " + wins[1]);
        System.out.println("Ties - " + ties);
    }

    
}


