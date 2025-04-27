package JCRPG_code;
public class Entity extends GM{
    public int hp;
    public int mp;
    public int dNum;
    public int dType;
    public String name;

    public int HPset(Entity currCreature, int HPlost)
    {
        currCreature.hp = currCreature.hp - HPlost;
        if (currCreature.hp <= 0)
        {
            return 0;
        }
        return currCreature.hp;
    }

    public int MPset(Entity currCreature, int MPlost)
    {
        currCreature.mp = currCreature.mp - MPlost;
        if (currCreature.mp <= 0)
        {
            return 0;
        }
        return currCreature.mp;
    }

    public int Status()
    {
        return -1; //I don't remember what this does -MP
    }

    public void testFunc(Entity creature)
    {
        System.out.println("Entity: Creature stats test\n");
        System.out.println(creature.name + " " + creature.hp + " " + creature.mp + " " + creature.dNum + " "  + creature.dType);
    }
}