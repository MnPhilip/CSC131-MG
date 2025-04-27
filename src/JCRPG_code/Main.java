package JCRPG_code;

import java.io.IOException;

public class Main{
    public static void main (String[] args){
        RNG generate = new RNG();
        GM gameMaster = new GM();

        int chap = 0;

        System.out.println("PROGRAM STARTED SUCCESSFULLY");

        int tester = generate.diceRoll(4, 4);
        System.out.println("RNG TEST : " + tester);

        try {
            gameMaster.Initialize();
        } catch (IOException ex) {
        }

        Player player = new Player(1);
        Monster monster = new Monster(3);
        System.out.println((gameMaster.combat(player, monster)));
    }
} 