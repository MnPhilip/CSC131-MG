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

    boolean combat (Entity defender, Entity attacker, int damage){
        while(defender.hp > 0 && attacker.hp > 0){
             int Adiceroll = generate.diceRoll(20, 1);
             int Ddiceroll = generate.diceRoll(20, 1);
                if(Adiceroll > Ddiceroll){
                    return false;
                }else{
                    Update(PObj, damage);
                }
        }
        
        return true;
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