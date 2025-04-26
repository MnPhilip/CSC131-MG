package JCRPG_code;
import java.util.Random;

public class RNG //extends GM
{
    //dx is number of sides on dice
    //amt is number of dice
    public int diceRoll(int dx, int amt) {
        Random numGen = new Random(); //Allows us to use Random Number generator, 
        int currRand; //Will hold the individual rolls before adding them to the total
            //THESE WILL BOTH RESET TO 0 EACH RUN WE WANT THIS
        int total = 0; //Holds total before sending back to wherever it was called

        for (int i = 0; i < amt; i++)
        {
            currRand = numGen.nextInt(dx) + 1; //SYNTAX: nextInt(max value) + (lowest value)
            total += currRand;
            //System.out.println("TEST: " + currRand + " " + total); //For testing purposes, can be removed towards end of production - MP
        }
        return total;
    }
}