package JCRPG_code;

import java.io.IOException;

public class Main{
    public static void main (String[] args){
        RNG generate = new RNG();
        GM gameMaster = new GM();

        System.out.println("PROGRAM STARTED SUCCESSFULLY");
        int tester = generate.diceRoll(4, 4);
        System.out.println("RNG VALUE : " + tester);


        try {
            gameMaster.Initialize();
        } catch (IOException ex) {
        }
        
    }
} 