package JCRPG_code;

public class Player extends Entity {
    //Player names will be sent from GUI when created
    RNG generate = new RNG();
    Player(int pRole)
    {
        switch (pRole)
        {
            hp = 40;
            //HP: Determines if they are alive
            //MP: Determines if/which movements are possible (in the context of the map tree)
            //dNum: Quantity of dice rolled for attacks
            //dType: max number of sides on one instance of the dice being rolled
            //CONCEPT: Add a "def" stat, which adds a flat number to the players defensive roll to increase their chances of dodging or reduce damage
            case 0:     //Wizard, low hp, low amount of dice but high dice type, high mp
                hp = generate.diceRoll(10, 5);
                def = 3;
                mp = 20;
                dNum = 10;
                dType = 8;
            case 1:     //Barbarians high hp, low mp, high amount of dice
                hp = generate.diceRoll(12, 9);
                def = 7;
                mp = 10;
                dNum = 5;
                dType = 8;
            case 2:     //Bard mid hp, mid mp, mid amount of dice, mid mp
                hp = generate.diceRoll(20, 5);
                def = 5;
                mp = 15;
                dNum = 8;
                dType = 6;
        }
    }
}