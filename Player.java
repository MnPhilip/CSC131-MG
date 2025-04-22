public class Player extends Entity {
    //Player names will be sent from GUI when created
    Player(int pRole){
        switch (pRole) {
            case 0: 
            //Wizard, low hp, low amount of dice but high dice type, high mp
                hp = generate.diceRoll(12, 2);
                mp = 20;
                dNum = 2;
                dType = 20;
            //Barbarians high hp, low mp, high amount of dice
            case 1:
                mp = 10;
                dNum = 4;
                dType = 6;
            case 2:
                mp = 15;
                dNum = 6;
                dType = 4;
            //Bard mid hp, mid mp, mid amount of dice, mid mp
        }
    }

}