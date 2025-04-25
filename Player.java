package JCRPG_code;

public class Player extends Entity {
    //Player names will be sent from GUI when created
    RNG generate = new RNG();
    Player(int pRole)
    {
        switch (pRole)
        {
            //HP: Determines if they are alive
            //MP: Determines if/which movements are possible (in the context of the map tree)
            //dNum: Quantity of dice rolled for attacks
            //dType: max number of sides on one instance of the dice being rolled
            case 0:     //Wizard, low hp, low amount of dice but high dice type, high mp
                hp = generate.diceRoll(6, 4);
                //hp = generate.diceRoll(12, 2);
                mp = 20;
                dNum = 2;
                dType = 20;
            case 1:     //Barbarians high hp, low mp, high amount of dice
                hp = generate.diceRoll(12, 4);
                mp = 10;
                dNum = 3;
                dType = 8;
            case 2:     //Bard mid hp, mid mp, mid amount of dice, mid mp
                hp = generate.diceRoll(10, 5);
                mp = 15;
                dNum = 4;
                dType = 6;
        }
    }
}