public class GM {
    RNG randNum = new RNG();


    //HAHAHHHAHHAHA
        //HAHAHHHAHHAHA
            //HAHAHHHAHHAHA
                //HAHAHHHAHHAHA
                    //HAHAHHHAHHAHA
                    
    String GMOutput = "";
    Player PObj[3];  // Player type
    Monster Mobj[6]; // Monster type

    boolean combat (Entity defender, Entity attacker, int damage ){
        while(defender.hp > 0 && attacker.hp > 0){
             int Adiceroll = randNum.diceRoll(20, 1);
             int Ddiceroll = randNum.diceRoll(20, 1);
                if(Adiceroll > Ddiceroll){
                    return false;
                }else{
                    Update(PObj[1], damage);
                }
        }
        
        return true;
    }
    int Update(Player Pobj, int value){
        int HPUpdate = Pobj.hp - value;
        return HPUpdate;
    }
    void Prompt(int chap){

    }
    void Response(int chap, Entity PObj, int result){
        

    }
    void Initialize(){

    }
    String resolve(String gmp, String up, int res){
        return "";
    }

}