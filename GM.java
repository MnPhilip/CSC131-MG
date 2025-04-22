public class GM {
    RNG generate = new RNG();

    //HAHAHHHAHHAHA
        //HAHAHHHAHHAHA
            //HAHAHHHAHHAHA
                //HAHAHHHAHHAHA
                    //HAHAHHHAHHAHA
                    //for ttesting purposes
                    
    String GMOutput = "";
    Player[] PObj = {};
    Monster[] Mobj = {}; // Monster type

    boolean combat (Entity defender, Entity attacker)
    {
        int dmg, defense;
        boolean turn = true;
        do 
        {
            // add some way for player or monster to flee and return true if successful flee
            dmg = (turn)? diceroll(attacker.dType, attacker.dNum) : diceRoll(defender.dType, defender.dNum);
            stat = (turn)? diceroll(defender.dType, defender.dNum) : diceroll(attacker.dType, attacker.dNum);
            if(stat < dmg)
            {
                (turn)? Update(defender, dmg) : Update(attacker, dmg);
            }
  
        } while (defender.hp >0 && attacker.hp > 0);
        return false; // someone died

    }
    int Update(Player Pobj[], int value){
        int HPUpdate = Pobj[0].hp - value;
        return HPUpdate;
    }
    void Prompt(int chap){

    }
    void Response(int chap, Entity PObj, int result){
        

    }
    void Initialize()
    {
        System.out.println("SUCCESFULLY CALLED INIT IN GM CLASS");
        
    }
    String resolve(String gmp, String up, int res){
        return "";
    }

}