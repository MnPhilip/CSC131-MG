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
    //NOTE: (MP) - Changed from defender/attacked to player/monster to make looping and determining when/how to update
                    //stats significantly easier, PvP will not be implemented anyway so this works out
    int combat (Player player, Monster monster) //NOTE: (MP) - Changed return value to int, this way the combat can resolve with more outcomes than just a death
    {
        System.out.println("SUCCESFULLY CALLED COMBAT\n");
        RNG generate = new RNG(); //DO NOT MOVE TO TOP OF CLASS - WILL BREAK THE PROGRAM - MP
                                //It's a little bit annoying but putting at the top causes a recursive overflow for some reason
        Entity creature = new Entity();
        int dmg, outcome;
        int plrCheck, monCheck; //Used to determine if the attack lands or is dodged
        boolean turn = true; //Monster always attacks first, meaning TRUE = monster attacking, FALSE = Player attacking

        do 
        {
            creature.testFunc(player);
            creature.testFunc(monster);
            //Add some way for player or monster to flee and return true if successful flee
            //DMG = determines the damage dealt from one entity to another
            dmg = (turn)? generate.diceRoll(monster.dType, monster.dNum) : generate.diceRoll(player.dType, player.dNum);

            plrCheck = generate.diceRoll(20, 1) + player.def; //Holds outome of player attack/defensive roll
            monCheck = generate.diceRoll(20, 1); //Holds outome of monster attack/defensive roll

            System.out.println("Player: " + plrCheck + "  Monster: " + monCheck + "\n");
            
            int escapeCheck = -1; //Allows potential for the rolling entity to run away
            //outcome = used to determine what action the "defending" entity is taking (who ever is not attacking)
            //outcome = (turn)? generate.diceRoll(monster.dType, monster.dNum) : generate.diceRoll(player.dType, player.dNum);
            
            //CONCEPT: if/else statement for if damage is higher, deal damage, if else defense is higher, skip and go to the next turn
            //This would mean we use the 0/1 returned value from update to determine if the loop continues
            if (turn && monCheck >= plrCheck) //If the monster is attacking and rolls a higher check
            {
                outcome = creature.HPset(player, dmg);
                if (outcome == 0)
                {
                    System.out.println("The " + monster.name + " dealt " + dmg + " to " + player.name + ", ending their adventure");
                    return 0; //Player Died
                }
                else 
                {
                    System.out.println("The player took " + dmg + " damage, from the " + monster.name);
                    //TODO: Add call to GUI to update the players health being displayed, as well as display
                    //text to GUI window and not terminal
                }
            }
            else if (turn && monCheck < plrCheck) //Monster attack but they miss player can escape
                {
                    escapeCheck = generate.diceRoll(20, 1);
                    if (escapeCheck == 20 || escapeCheck >= 17) //These numbers are arbitrary we can make them more sophisticated later
                    {
                        //TODO: Add Code to check where the player is and what map indexes they can flee too
                    }
                }
            else if(!turn && plrCheck >= monCheck) //If the player is attacking and rolls a higher check
            {
                outcome = creature.HPset(monster, dmg);
                if (outcome == 0)
                {
                    System.out.println(player.name + " dealt " + dmg + " damage to kill the " + monster.name);
                    return 1; //Monster DIED
                }
                else
                {
                    System.out.println("The " + monster.name + " suffered " + dmg + " damage from " + player.name);
                    //TODO: Update to print to the GUI and not the terminal
                }
            }
            else if (!turn && plrCheck < monCheck && monster.name.equals("Lizard Man"));
            { //THIS ESCAPE POSSIBILITITY IS ONLY AVAILABLE FOR LIZARD MAN, THE OTHER CREATURE CAN'T MOVE
                //TODO: Check where the lizard man is and what map indexes he can flee too
                //return 2; //Lizard man escaped
            }
            
            // if(defense < dmg)
            // {
            //     //(turn)? Update(defender, dmg) : Update(attacker, dmg);
            //     //This bit was Added by JD I am unsure what it does but eventually this statement needs to be an assignment statement - MP
            // }

            turn = !turn;
        } while (player.hp > 0 && monster.hp > 0);
        if (player.hp <= 0)
        {
            return 0; //PLAYER DIED
        }
        else if (monster.hp <= 0)
        {
            return 1; //monster DIED
        }
        return -1; //FLAG VALUE: Somehow fell out of loop without resolution
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
        //TODO: GM needs to check the map index and see if a monster should be present, and 
        //present it to the player if there is 

        //If a monster is present, start combat. IF NOT, accept a response from the player

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
        for (int y = 0; y < 3; y++)
        {
            PObj[y] = new Player(y);
        }

        for (int x = 0; x < 6; x++)
        {
            MObj[x] = new Monster(x); //Populates all monster array values with the applicable stats, these are always the same regardless of user input
            
            System.out.println(x);
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