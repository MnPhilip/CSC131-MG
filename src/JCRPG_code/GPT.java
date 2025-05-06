package JCRPG_code;

import java.net.http.*;
import java.net.URI;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.io.IOException;

public class GPT {
    private String apiKey;
    private GM gameMaster;
    private RNG generator;

    public GPT (String apiKey) {
        if (!isValidApiKey(apiKey)) {
            throw new IllegalArgumentException("Invalid API Key.");
        }
        this.apiKey = apiKey;
        // this.gameMaster = new GM();
        // this.generator = new RNG();
    }

    private boolean isValidApiKey(String APIKey) {
        return APIKey != null && APIKey.startsWith("sk-");
    }

    public String generateRandomEvent(String playerName, String location, String monsterName, String playout) {
        
        String prompt = String.format(
            "A combat encounter between %s and %s in the location %s took place, the order of events being %s, create a dramatic retelling of the battle in 3-4 sentences.",
            playerName, monsterName, location, playout
        );
        return callOpenAIApi(prompt);
    }

    private String callOpenAIApi(String prompt) {
        
        String endpoint = "https://api.openai.com/v1/chat/completions";
        String requestBody = """
        {
          "model": "gpt-3.5-turbo",
          "messages": [{"role": "user", "content": "%s"}],
          "max_tokens": 300,
          "temperature": 0.9
        }
        """.formatted(prompt);

        // System.out.println("REQUEST: " + requestBody);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(endpoint))
            .header("Authorization", "Bearer " + apiKey)
            .header("Content-Type", "application/json")
            .POST(BodyPublishers.ofString(requestBody))
            .build();

        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            String body = response.body();

            int contentStart = body.indexOf("\"content\": \"") + 12;// + 11
            //System.out.println(" CONTENT START: " + contentStart);
            int contentEnd = body.indexOf("\"", contentStart + 200);
            //System.out.println("CONTENT END: " + contentEnd);
            if (contentStart > 10 && contentEnd > contentStart) {
                String content = body.substring(contentStart, contentEnd);
                return content.replace("\\n", "\n");
            } else {
                return "API response parsing failed:\n" + body;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error generating story.";
        }


    }

    public String APIRunner(String player, String location, String monster, String playout) 
    {
        //playout = player + " uses dual swords against " + monster;
        String Story = "ERROR - STORY LOST";
        Story = generateRandomEvent(player, location, monster, playout);
        return Story;
        // for (int i = 1; i <= 5; i++) {
        //     String story = generateRandomEvent(
        //         "Aria", "The Crystal Forest", "Orb of Whispers", "shadow wolves"
        //     );
        //     System.out.println("Story " + i + ":\n" + story + "\n------------------------\n");
        // }
    }
}



// package JCRPG_code;

// import java.net.http.*;
// import java.net.URI;
// import java.net.http.HttpRequest.BodyPublishers;
// import java.net.http.HttpResponse.BodyHandlers;
// import java.io.IOException;

// public class GPT {
//     private String apiKey = "";
//     public GPT() {
//         if (!isValidApiKey(apiKey)) {
//             throw new IllegalArgumentException("Invalid API Key.");
//         }
//     }

//     private boolean isValidApiKey(String key) {
//         return key != null && key.startsWith("sk-");
//     }

//     public String generateFromPrompt(String prompt) {
//         return callOpenAIApi("Narrate this RPG scene: " + prompt);
//     }

//     private String callOpenAIApi(String prompt) {
//         String endpoint = "https://api.openai.com/v1/chat/completions";
//         String requestBody = """
//         {
//           "model": "gpt-3.5-turbo",
//           "messages": [{"role": "user", "content": "%s"}],
//           "max_tokens": 300,
//           "temperature": 0.9
//         }
//         """.formatted(prompt);

//         HttpRequest request = HttpRequest.newBuilder()
//             .uri(URI.create(endpoint))
//             .header("Authorization", "Bearer " + apiKey)
//             .header("Content-Type", "application/json")
//             .POST(BodyPublishers.ofString(requestBody))
//             .build();

//         HttpClient client = HttpClient.newHttpClient();
//         try {
//             HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
//             String body = response.body();

//             int contentStart = body.indexOf("\"content\":\"") + 11;
//             int contentEnd = body.indexOf("\"", contentStart);
//             if (contentStart > 10 && contentEnd > contentStart) {
//                 String content = body.substring(contentStart, contentEnd);
//                 return content.replace("\\n", "\n");
//             } else {
//                 return "API response parsing failed:\n" + body;
//             }
//         } catch (IOException | InterruptedException e) {
//             e.printStackTrace();
//             return "Error generating story.";
//         }
//     }
// }
