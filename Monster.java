class Monster extends Entity
{
    public Monster(int Mtype)
    {
        switch (Mtype)
        {
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
                //fill with stats
                name = "Air Elemental";
                hp = 60;
                mp = 0;
                dNum = 4;
                dType = 4;
            case 2:
                //Fire Elemental
                //fill with stats
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
                name = "Alchemist";
                hp = 80;
                mp = 0;
                dNum = 2;
                dType = 20;
        }
    }
}
