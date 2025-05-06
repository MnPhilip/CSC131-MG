// package JCRPG_code;

// public class GPTRUN extends GM{
//     public void GPTCall(Player PObj[], Monster MObj[]) 
//     {
//         String apiKey = "APIKEY"; // Replace this
//         GPT storyAI = new GPT(apiKey);
        
//         System.out.println(storyAI.APIRunner(PObj, "Library", MObj, 1, 0, "they engaged in combat"));
//     }
// }

package JCRPG_code;

import java.util.HashMap;
import java.util.Map;

public class GPTRUN {
    int currentNode;

    private static final Map<Integer, String> storyPrompts = new HashMap<>();
    static {
        storyPrompts.put(1, "You find yourself on the threshold of the Royal Alchemists' home, tread lightly.");
        storyPrompts.put(2, "You are inside. You have the chance to try the vault door or explore the library.");
        storyPrompts.put(3, "You find a strange library whose aisles seem to stretch beyond what is possible. The books all seem misplaced and misshapen.");
        storyPrompts.put(4, "An alarm blares! A Guardian Elemental approaches from a hidden alcove.");
        storyPrompts.put(5, "You reach the vault door. Try to crack the code or break in.");
        storyPrompts.put(6, "Cracking your knuckles, you prepare to try cracking the vault.");
        storyPrompts.put(7, "Making use of your skills and talents, the vault door falls away from it's hinges.");
        storyPrompts.put(8, "Success! The coveted contents of the vault are yours for the taking.");
        storyPrompts.put(9, "Making a swift escape, you find yourself in a grand garden.The centerpiece, glistening fountian of yellow liquid, beckons you.");
        storyPrompts.put(10, "A patch of disturbed dirt catches your eye.");
        storyPrompts.put(11, "You escape, but Ekmeros, Lizard-Man Champion, appears.");
        storyPrompts.put(12, "The Royal Alchemist arrives! Gathering what you can, you prepare to face the Nobles most daunting emissary.");
        storyPrompts.put(13, "The otherwordly prowess of the Alchemist proves far beyond your capabilities too combat...");
        storyPrompts.put(14, "Your keen eye discerns the outline of a door hidden beyond the shrubbery in the garden.");
        storyPrompts.put(15, "Jackpot! The vault contains immense treasures beyond anything you envisioned! Magicks, riches, weapons, tomes, and relics of old!");
        storyPrompts.put(16, "You unearth a trove of hidden relics and magics! Having completed your quest, you seamlessly escape out the back entrance of the garden!.");
        storyPrompts.put(17, "Ekmeros, Lizard-man Champion, obstructs your path. Escape or talk your way out?");
        storyPrompts.put(18, "Ekmeros yawns as you lack anyhting of interest to him, he turns away and saunters off...");
        storyPrompts.put(19, "His slit-eyes thinning, spines rustling, swords gleaming - Ekmeros springs into battle!");
    }

    public void GPTCall() {
        String apiKey = "APIKEY";
        if (apiKey == null || !apiKey.startsWith("sk-")) {
            System.out.println("Missing or invalid API key.");
        }
    
    }

    public int GUIChapUpdate()
    {
        return currentNode;
    }

    public String mapDescUpdate(int currIndex)
    {
        String apiKey = "";
        
        GPT GPT = new GPT(apiKey);
        GM gameMaster = new GM();
        RNG generate = new RNG();

        currentNode = currIndex;

        int roll = generate.diceRoll(20, 1);
        //System.out.println("Dice Roll: " + roll);

        // Auto-progress based on dice rolls
        if (currentNode == 1) 
        {
            currentNode = (roll >= 12) ? 4 : 2;
        } 
        else if (currentNode == 2) 
        {
            currentNode = (roll >= 7) ? 5 : 3;
        } 
        else if (currentNode == 3)
        {
            currentNode = 3;
        }
        else if (currentNode == 4)
        {
            if (roll >= 14)
            {
                currentNode = 9;
            }
            else
            {
                currentNode = 5;
            }
        }
        else if (currentNode == 5) 
        {
            currentNode = (roll >= 10) ? 6 : 7;
        } 
        else if (currentNode == 6 || currentNode == 7)
        {
            if (roll >= 12)
            {
                currentNode = 8;
            }
            else if (currentNode == 6)
            {
                currentNode = 17;
            }
            else if (currentNode == 7)
            {
                currentNode = 4; //MIGHT BE AN ENDING IF THEY DIE FIGHTING THE ELEMENTAL
            }
        } 
        else if (currentNode == 8)
        {
            currentNode = 15;
        } 
        else if (currentNode == 9) 
        {
            if (roll >= 15)
            {
                currentNode = 10;
            }
            else if (roll >= 11)
            {
                currentNode = 14;
            }
        } 
        else if (currentNode == 10) 
        {
            currentNode = 16;
        }
        else if (currentNode == 12)
        {
            currentNode = 12; //FLAG VALUE: COMBAT BASED ENDING - ALCHEMIST
        }
        else if (currentNode == 13)
        {
            System.out.println("PLAYER DEATH");
        }
        else if (currentNode == 14)
        {
            if (roll < 7)
            {
                currentNode = 3;
            }
            else 
            {
                currentNode = 15;
            }
        }
        else if (currentNode == 15) 
        {
            currentNode = 12;
        }
        else if (currentNode == 17) 
        {
            currentNode = (roll >= 15) ? 11 : 18;
        }
        else if (currentNode == 18)
        {
            currentNode = (roll >= 11) ? 9 : 5;
        }
        else if (currentNode == 19)
        {
            currentNode = 19; //FLAG VALUE COMBAT-BASED ENDING
        }
        else
        {
            currentNode++;
        }

        gameMaster.GUIChapUpdate(currentNode, 0);
        return storyPrompts.getOrDefault(currentNode, "ERROR: No prompt for currentNode: " + currentNode);
        //return "ERROR: FELL OUT OF IF STATEMENT";
    }
}

