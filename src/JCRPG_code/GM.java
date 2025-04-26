package JCRPG_code;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GM {
    //HAHAHHHAHHAHA
        //HAHAHHHAHHAHA
            //HAHAHHHAHHAHA
                //HAHAHHHAHHAHA
                    //HAHAHHHAHHAHA
                    //for ttesting purposes
    int txtLineCount = 999; //Flag value, replace with test file line count
    int lineCounter = 0; //Counter for addressing specific indexes in the array, utilized in the while loop to read text lines from the file
    String[] textFileLines = new String[txtLineCount]; //This array size will need to be re-sized to the total number of individual
    //Text lines in the file it's reading from - See the note above there PObj are initialized - MP
                
    String GMOutput = ""; //Will hold text when text files are implemented
    Player[] PObj = new Player[3]; //Holds Player instances
    Monster[] MObj = new Monster[8]; // Holds Monster instances


    //Function handles interactions between two entities, the damage dealt to each, calling respective functions to updates stats
    //and what happens if/when one dies/leaves
    boolean combat (Entity defender, Entity attacker) 
    {
        RNG generate = new RNG(); //DO NOT MOVE TO TOP OF CLASS - WILL BREAK THE PROGRAM - MP
                                    //It's a little bit annoying but putting at the top causes a recursive overflow for some reason
        int dmg, defense;
        boolean turn = true;
        do 
        {
            //Add some way for player or monster to flee and return true if successful flee
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
        GMOutput = textFileLines[chap];
        System.out.println(GMOutput);
    }
    void Response(int chap, Entity PObj, int result)
    {
        //TODO: Will update the proper PlayerObject response box with an applicable response from the Player[index] text array (or eventually GPT)
    }

    
    void Initialize() throws IOException //IOException allows the file garbage to function without complaining
    {
        System.out.println("SUCCESFULLY CALLED INIT IN GM CLASS"); //Only purpose is to prove it was called - MP
        
        //FOR LOOPS ARE REQUIRED TO POPULATE MONSTER AND PLAYER
        //Arrays require fixed sizes, adding beyond the pre-established size will required a whole slew of new functions just to resize them - MP
        for (int x = 0; x < 3; x++)
        {
            PObj[x] = new Player(x);
        }

        for (int x = 0; x < 8; x++)
        {
            MObj[x] = new Monster(x); //Populates all monster array values with the applicable stats, these are always the same regardless of user input
        }
        
        //FOLLOWING CODE WILL CREATE READ-FILE OBJECTS FOR FUTURE TEXT FILES
        //Replace with folder-to-file path when they are created
        Scanner scanFile = new Scanner(new File("E:\\CONTAINER\\CSUS\\CSC131\\CSC131-MG-MP-FormatFix\\CSC131-MG-MP-FormatFix\\src\\JCRPG_code\\test.txt")); //TO MAKE THIS FUNCTION:
                                                                        //Right click "test.txt" and click "copy path"
                                                                        //Then paste it between the quotation marks, KEEP THE QUOTATION MARKS 

        while(scanFile.hasNextLine())
        {
            textFileLines[lineCounter] = scanFile.nextLine() + "\n";
            //System.out.print(textFileLines[lineCounter]); //- USed for testing -MP

            lineCounter++;
        }

        scanFile.close();

    ///////////////////////////////////////////////////////////////////////////////////////
        // Entity entity = new Entity(); USED FOR TESTING
        // entity.testFunc(PObj[0]);
        // entity.testFunc(PObj[1]);
        // entity.testFunc(PObj[2]);

        // textFileLines[0] = "TEST";
        // textFileLines[1] = "TEST AGAIN";

        // System.out.println(textFileLines[0]);
        // System.out.println(textFileLines[1]); USED FOR TESTING
    }
    String resolve(String gmPrompt, String userPrompt, int response){
        return "ERROR: CODE NOT COMPLETED ['RESOLVE' FUNC IN GM CLASS]"; //Will eventually be equipped to send/recieve data from GPT
        //Input to GPT needs to be framed to construct a string using the GM prompt, user prompt, and the response typed in from the user in a way that 
        //phrases the whole thing as a question essentially asking "How would you tie this prompt to the response to lead to this conclusion", while also
        //requesting that GPT only send a short, useable response instead of an explanation so the value it returns can immediately be printed in the GUI
    }

}