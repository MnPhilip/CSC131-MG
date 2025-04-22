public class GM {
    RNG generate = new RNG(); //Up here so this whole class and connected classes can use it

    //HAHAHHHAHHAHA
        //HAHAHHHAHHAHA
            //HAHAHHHAHHAHA
                //HAHAHHHAHHAHA
                    //HAHAHHHAHHAHA
                    //for ttesting purposes
                    
    String GMOutput = ""; //Will hold text when text files are implemented
    Player[] PObj = {}; //Holds Player instances
    Monster[] MObj = {}; // Holds Monster instances


    //Function handles interactions between two entities, the damage dealt to each, calling respective functions to updates stats
    //and what happens if/when one dies/leaves
    boolean combat (Entity defender, Entity attacker) 
    {
        int dmg, defense;
        boolean turn = true;
        do 
        {
            // add some way for player or monster to flee and return true if successful flee
            dmg = (turn)? generate.diceRoll(attacker.dType, attacker.dNum) : generate.diceRoll(defender.dType, defender.dNum);
            defense = (turn)? generate.diceRoll(defender.dType, defender.dNum) : generate.diceRoll(attacker.dType, attacker.dNum);
            //CONCEPT: if/else statement for if damage is higher, deal damage, if else defense is higher, skip and go to the next turn
                //This would mean we use the 0/1 returned value from update to determine if the loop continues
            
            if(defense < dmg)
            {
                //(turn)? Update(defender, dmg) : Update(attacker, dmg);
                //This bit was Added by JD I am unsure what it does but eventually this statement needs to be an assignment statement - MP
            }
  
        } while (defender.hp >0 && attacker.hp > 0);
        return false; // someone died

    }

    //POBJ: Array of all available player, 
    //VALUE: how much health is being removed (positive number) or added (negative number),
    //PLAYERINDEX: Number in array for which player is being affected
    int Update(Player Pobj[], int value, int playerIndex)
    {
        Pobj[playerIndex].hp = Pobj[playerIndex].hp - value;
        if (Pobj[playerIndex].hp <= 0)
        {
            return 1; //Player Died
        }
        else if (Pobj[playerIndex].hp > 0)
        {
            return 0; //Player is still alive
        }

        return -1; //Flag value: Code somehow fell out of if statement
    }
    void Prompt(int chap)
    {
        //TODO: Should Grab text from the GMTextArray and send it to the GUI for output
        //Code will use the chap integer to know which line to grab from the array
    }
    void Response(int chap, Entity PObj, int result)
    {
        //TODO: Will update the proper PlayerObject response box with an applicable response from the Player[index] text array (or eventually GPT)
    }
    void Initialize()
    {
        System.out.println("SUCCESFULLY CALLED INIT IN GM CLASS");
        PObj[0] = new Player(0); //TESTER VALUES, WILL EVENTUALLY GRAB RESPECTIVE
        PObj[1] = new Player(1); //PROLE VALUES FROM GUI AFTER USER INPUT
        PObj[2] = new Player(2);

        for (int x = 0; x < 8; x++)
        {
            MObj[x] = new Monster(x); //Populates all monster array values with the applicable stats, these are always the same regardless of user input
        }
        
    }
    String resolve(String gmPrompt, String userPrompt, int response){
        return "ERROR: CODE NOT COMPLETED [RESOLVE IN GM CLASS]"; //Will eventually be equipped to send/recieve data from GPT
        //Input to GPT needs to be framed to construct a string using the GM prompt, user prompt, and the response typed in from the user in a way that 
        //phrases the whole thing as a question essentially asking "How would you tie this prompt to the response to lead to this conclusion", while also
        //requesting that GPT only send a short, useable response instead of an explanation so the value it returns can immediately be printed in the GUI
    }

}