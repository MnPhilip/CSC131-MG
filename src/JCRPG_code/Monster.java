package JCRPG_code;

public class Monster extends Entity
{
    Monster(int Mtype)
    {
        switch (Mtype)
        {
            //NAME: To be printed in the GUI
            //HP: Determines when the player wins once HP is 0
            //MP: Increments each turn, meaning when it hits a certain value the monster unleashes a stronger attack
            //dNum: max number on each of their dice
            //dType: quantity of dice rolled for an attack
            case 0:
                // lizard man
                //fill with stats
                name = "Lizard Man";
                hp = 40;
                maxHP = hp;
                mp = 0;
                dNum = 4;
                dType = 5;
                RNG generate = new RNG();
                //mapLoc = generate.diceRoll(4, 1);
                break;
            case 1:
                //Air Elemental
                name = "Air Elemental";
                hp = 60;
                maxHP = hp;
                mp = 0;
                dNum = 4;
                dType = 8;
                break;
            case 2:
                //Fire Elemental
                name = "Fire Elemental";
                hp = 60;
                maxHP = hp;
                mp = 0;
                dNum = 4;
                dType = 8;
                break;
            case 3:
                //Earth Elemental
                name = "Earth Elemental";
                hp = 60;
                maxHP = hp;
                mp = 0;
                dNum = 4;
                dType = 8;
                break;
            case 4:
                //Water Elemental
                name = "Water Elemental";
                hp = 60;
                maxHP = hp;
                mp = 0;
                dNum = 4;
                dType = 8;
                break;
            case 5:
                //Alchemist
                name = "Royal Alchemist";
                hp = 80;
                maxHP = hp;
                mp = 0;
                dNum = 3;
                dType = 20;
                break;
        }
    }
}