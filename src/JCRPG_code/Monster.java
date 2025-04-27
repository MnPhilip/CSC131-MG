package JCRPG_code;

class Monster extends Entity
{
    public Monster(int Mtype)
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
                mp = 0;
                dNum = 3;
                dType = 8;
            case 1:
                //Air Elemental
                name = "Air Elemental";
                hp = 60;
                mp = 0;
                dNum = 4;
                dType = 4;
            case 2:
                //Fire Elemental
                name = "Fire Elemental";
                hp = 60;
                mp = 0;
                dNum = 4;
                dType = 4;
            case 3:
                //Earth Elemental
                name = "Earth Elemental";
                hp = 60;
                mp = 0;
                dNum = 4;
                dType = 4;
            case 4:
                //Water Elemental
                name = "Water Elemental";
                hp = 60;
                mp = 0;
                dNum = 4;
                dType = 4;
            case 5:
                //Alchemist
                name = "Royal Alchemist";
                hp = 80;
                mp = 0;
                dNum = 2;
                dType = 20;
        }
    }
}
