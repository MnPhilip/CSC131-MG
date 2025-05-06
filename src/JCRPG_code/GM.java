package JCRPG_code;
import JCRPG_gui.LauncherGUI;
import JCRPG_gui.*;
import java.io.File;
import java.io.IOException;
import java.lang.classfile.instruction.ThrowInstruction;
import java.util.*;

//PERSONAL BUILD
@SuppressWarnings("unused")
public class GM {
    //HAHAHHHAHHAHA
        //HAHAHHHAHHAHA
            //HAHAHHHAHHAHA
                //HAHAHHHAHHAHA
                    //HAHAHHHAHHAHA
                    //for ttesting purposes


    
    static int currTextIndex;
    //Text lines in the file it's reading from - See the note above there PObj are initialized - MP
    static String combatOutput;

    String[] mapLoc = {"Intro", "Foyer", "Infinite Library", "Elemental's Alcove", "Vault Door", "Vault beyond the painting of the Alchemist", "Inside the vault after cracking it open", "Succesfully entering the vault",
                        "Luscious Garden featuring an elaborate fountain whos 'liquid' tastes of asparagus", "In a corner of the garden, a patch of disturbed dirt", "of the basement after escaping the Guardian Elemental, but running into Ekmeros - Lizard-man champion", 
                        "of the vault interior, after taking what you can, you watch as the Royal Alchemist arrives", "death at the hands of Royal Alchemist", "the garden, finding a hidden door", "the vault interior, discovering riches, magics, and relics of obscene wealth",
                        "in the garden, digging up a patch of loose dirt", " failing to break into the vault, Ekmeros, Lizard-man Champion appears", " in the Foyer, Ekmeros, Lizard-man Champion, grows bored and saunters away", "Ekmeros, Lizard-man Champion Springs into battle"};
    LauncherGUI mainGUI;
    static boolean GUIflag = true;
    Player[] PObj = new Player[3]; //Holds Player instances
    Monster[] MObj = new Monster[6]; // Holds Monster instances

    //POBJ: Array of all available player, 
    //VALUE: how much health is being removed (positive number) or added (negative number),
    //PLAYERINDEX: Number in array for which player is being affected
    public Player[] playerUpdate()
    {
        return PObj;
    }

    //     return -1; //Flag value: Code somehow fell out of if statement
    // }
    public void Initialize() throws IOException //IOException allows the file garbage to function without complaining
    {
        // playerInit(1,1, "Waldo");
        // monsterInit();
        // GPT gpt = new GPT("APIKEY");
        // System.out.println(gpt.APIRunner(PObj[1].name, "Library", MObj[2].name, "Waldo summons the power of the sun to defeat " + MObj[2].name));



        //System.out.println("SUCCESFULLY CALLED INIT IN GM CLASS:"); //Only purpose is to prove it was called - MP
        //this.mainGUI = new LauncherGUI();
        //FOLLOWING CODE WILL CREATE READ-FILE OBJECTS FOR FUTURE TEXT FILES
        //Replace with folder-to-file path when they are created


        // Scanner scanFile = new Scanner(new File("src\\JCRPG_code\\test.txt")); //TO MAKE THIS FUNCTION:
        //                                                                 //Right click "test.txt" and click "copy path"
        //                                                                 //Then paste it between the quotation marks, KEEP THE QUOTATION MARKS 

        // while(scanFile.hasNextLine())
        // {
        //     textFileLines[lineCounter] = scanFile.nextLine() + "\n";
        //     //System.out.print(textFileLines[lineCounter]); //- USed for testing -MP

        //     lineCounter++;
        // }

        // scanFile.close();

        // for (int x = 0; textFileLines[x] != null; x++)
        // {
        //     System.out.println(textFileLines[x]);
        // }
        
        if (GUIflag)
        {
            this.mainGUI = new LauncherGUI();
            GUIflag = false;
        }
        else
        {
            System.out.print(GUIflag);
        }
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

    void Prompt(int chap)
    {
        //TODO: Should Grab text from the GMTextArray and send it to the GUI for output
        //Code will use the chap integer to know which line to grab from the array

        //TODO: GM needs to check the map index and see if a monster should be present, and 
        //present it to the player if there is 

        //If a monster is present, start combat. IF NOT, accept a response from the player

    }
    void Response(int chap, Entity PObj, int result)
    {
        //TODO: Will update the proper PlayerObject response box with an applicable response from the Player[index] text array (or eventually GPT)
    }
    
    //Function handles interactions between two entities, the damage dealt to each, calling respective functions to updates stats
    //and what happens if/when one dies/leaves
    //NOTE: (MP) - Changed from defender/attacked to player/monster to make looping and determining when/how to update
    //stats significantly easier, PvP will not be implemented anyway so this works out
    int combat (Player player, Monster monster) //NOTE: (MP) - Changed return value to int, this way the combat can resolve with more outcomes than just a death
    {
        //System.out.println("SUCCESFULLY CALLED COMBAT\n");
        RNG generate = new RNG(); //DO NOT MOVE TO TOP OF CLASS - WILL BREAK THE PROGRAM - MP
                                //It's a little bit annoying but putting at the top causes a recursive overflow for some reason
        Entity creature = new Entity();
        
        GPTRUN gptRUN = new GPTRUN();
        //GPT gpt = new GPT("APIKEY");
        int dmg, outcome;
        int plrCheck, monCheck; //Used to determine if the attack lands or is dodged
        boolean turn = true; //Monster always attacks first, meaning TRUE or turn = monster attacking, FALSE or !turn = Player attacking
        combatOutput = "";

        //RETURN CODES:
        int playerDeath = 0; //Event that player dies in combat
        int monsterDeath = 1; //Event that a monster is defeated in combat
        int lizardEscape = 2; //in the event the Lizard Man rolls a check to escape successfully
        int playerEscape = 3; //Event that Player escapes combat to a new location
        int playerVictory = 4; //Event the player kills the Royal Alchemist and wins the game

        while (player.hp > 0 && monster.hp > 0)
        {
            //System.out.println("\n");
            //Add some way for player or monster to flee and return true if successful flee
            //DMG = determines the damage dealt from one entity to another
            dmg = (turn)? generate.diceRoll(monster.dType, monster.dNum) : generate.diceRoll(player.dType, player.dNum);

            plrCheck = generate.diceRoll(20, 1) + player.def; //Holds outome of player attack/defensive roll
            monCheck = generate.diceRoll(20, 1); //Holds outome of monster attack/defensive roll

            //System.out.println("Player: " + plrCheck + "  Monster: " + monCheck);
            
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
                    if (monster.name == "Lizard Man")
                    {
                        GUIChapUpdate(18, 0);
                    }
                    else
                    {
                        GUIChapUpdate(13, 0);
                    }
                    combatOutput += ("The " + monster.name + " dealt " + dmg + " to " + player.name + ", ending their adventure, ");
                    return playerDeath; //Player Died
                }
                else 
                {
                    combatOutput += (player.name + " took " + dmg + " damage, from the " + monster.name + ", ");
                    //TODO: Add call to GUI to update the players health being displayed, as well as display
                    //text to GUI window and not terminal
                }
            }
            else if (turn && monCheck < plrCheck) //Monster attack but they miss, player can escape
                {
                    escapeCheck = generate.diceRoll(20, 1);
                    if (escapeCheck == 20 || escapeCheck >= 17) //These numbers are arbitrary we can make them more sophisticated later
                    {
                        //TODO: Add Code to check where the player is and what map indexes they can flee too
                        combatOutput += (player.name + " deftly escapes the " + monster.name + " and makes it into the " + gptRUN.mapDescUpdate(GUIChapUpdate(0, 1)));/*mapLocation.name*/
                        return playerEscape; // Player escaped to new location
                    }
                    else
                    {
                        combatOutput += (player.name + " dodged the attack from the " + monster.name + ", ");
                    }
                }
            else if(!turn && plrCheck >= monCheck) //If the player is attacking and rolls a higher check
            {
                outcome = creature.HPset(monster, dmg);
                if (outcome == 0)
                {
                    combatOutput += (player.name + " dealt " + dmg + " damage to kill the " + monster.name + ", ");
                    GUIChapUpdate(19, 0);
                    return monsterDeath; //Monster DIED
                }
                else
                {
                    combatOutput += ("The " + monster.name + " suffered " + dmg + " damage from " + player.name + ", ");
                    //TODO: Update to print to the GUI and not the terminal
                }
            }
            else if (!turn && plrCheck < monCheck)
            { //THIS ESCAPE POSSIBILITITY IS ONLY AVAILABLE FOR LIZARD MAN, THE OTHER CREATURE CAN'T MOVE
                escapeCheck = generate.diceRoll(20, 1);
                if (monster.name.equals("Lizard Man") && escapeCheck >= 10)
                {
                    // GPT.mapDescUpdate(monster.mapLoc);
                    // monster.mapLoc = currTextIndex;
                    combatOutput += ("With his adept footwork and crafty movements the lizard man gets away..., ");
                    return lizardEscape;
                }
                else
                {
                    combatOutput += (monster.name + " avoided the attack from " + player.name + ", ");
                }
            }
            
            // if(defense < dmg)
            // {
            //     //(turn)? Update(defender, dmg) : Update(attacker, dmg);
            //     //This bit was Added by JD I am unsure what it does but eventually this statement needs to be an assignment statement - MP
            // }
            turn = !turn;
        }// while (player.hp > 0 && monster.hp > 0);

        //System.out.println(combatOutput);
        if (player.hp <= 0)
        {
            //combatOutput = player.name + " has already fallen...\n";
            return playerDeath; //PLAYER DIED
        }
        else if (monster.hp <= 0)
        {
            //combatOutput = monster.name + " lay defeated on the ground, there is little else to be found here.\n";
            return monsterDeath; //monster DIED
        }
        return -1; //FLAG VALUE: Somehow fell out of loop without resolution
    }

    ////////////////GUI BASED FUNCTIONS BELOW//////////////////////////////////////

    // boolean launchGUI(boolean GUIflag)
    // {
    //     //System.out.println(GUIflag);
    //     if (GUIflag)
    //     {
    //         GM.this.mainGUI = new LauncherGUI();
    //         GUIflag = false;
    //     }
    //     return GUIflag;
    // }

    public Player[] playerInit(int pRole, int pIndex, String pName)
    {
        PObj[pIndex] = new Player(pRole);
        PObj[pIndex].name = pName;

        return PObj;
    }

    public Monster[] monsterInit()
    {
        for (int x = 0; x < 6; x++)
        {
            MObj[x] = new Monster(x); //Populates all monster array values with the applicable stats, these are always the same regardless of user input
        }
        return MObj;
    }

    public String combatInit(int player, int monster) //Allows GUI to call combat, specifically returns to 
    {
        //System.out.println(PObj[player] + " versus " +  MObj[monster].name);
        int outCome = (combat(PObj[player], MObj[monster]));
        String finalOutput;

        System.out.println("THIS IS COMBATOUTPUT: " + combatOutput);

        GPT gpt = new GPT("APIKEY");
        switch(outCome)
        {
            case 0:
                    combatOutput = combatOutput + "\n" + PObj[player].name + " was defeated by " + MObj[monster].name;
                    finalOutput = gpt.APIRunner(PObj[player].name, mapLoc[4 - 1], MObj[monster].name, combatOutput);
                    System.out.println(finalOutput);
                    return finalOutput;
            case 1:
                    combatOutput = combatOutput + "\n" + MObj[monster].name + " was defeated by " + PObj[player].name;
                    finalOutput = gpt.APIRunner(PObj[player].name, mapLoc[4 - 1], MObj[monster].name, combatOutput);
                    System.out.println(finalOutput);
                    return finalOutput;
            case 2:
                    combatOutput = combatOutput + "\n" + MObj[monster].name + " escapes to fight another day...";
                    finalOutput = gpt.APIRunner(PObj[player].name, mapLoc[4 - 1], MObj[monster].name, combatOutput);
                    System.out.println(finalOutput);
                    return finalOutput;
            case 3:
                    combatOutput = combatOutput + "\n" + PObj[player].name + " escapes combat succesfully";
                    finalOutput = gpt.APIRunner(PObj[player].name, mapLoc[4 - 1], MObj[monster].name, combatOutput);
                    System.out.println(finalOutput);
                    return finalOutput;
            case 4:
                    combatOutput = combatOutput + "\n" + PObj[player].name + " strikes down the Royal Alchemist!";
                    finalOutput = gpt.APIRunner(PObj[player].name, mapLoc[4 - 1], MObj[monster].name, combatOutput);
                    System.out.println(finalOutput);
                    return finalOutput;
        }
        return combatOutput;
    }
         

    public String GUIGMUpdate(int currChap)
    {   
        GPTRUN GPTrun = new GPTRUN();
        return GPTrun.mapDescUpdate(currChap);

        //return "ERROR: CODE NOT COMPLETED ['RESOLVE' FUNC IN GM CLASS]"; //Will eventually be equipped to send/recieve data from GPT
    }

    public int GUIChapUpdate(int currIndex, int Status)
    {
        if (Status == 0)
        {
            currTextIndex = currIndex;
            //System.out.println(currTextIndex);
            if (currTextIndex == 9)
            {
                
            }
            return 0;
        }
        else if (Status == 1)
        {
            //System.out.println(currTextIndex);
            return currTextIndex;
        }
        return -1;
    }

}