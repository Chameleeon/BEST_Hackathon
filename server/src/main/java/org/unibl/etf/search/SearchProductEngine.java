package org.unibl.etf.search;

import dev.ai4j.openai4j.OpenAiClient;
import dev.ai4j.openai4j.chat.ChatCompletionModel;
import dev.ai4j.openai4j.chat.ChatCompletionRequest;
import dev.ai4j.openai4j.chat.ChatCompletionResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchProductEngine {
    private OpenAiClient openAiClient;

    private final String systemPrompt =
            "You are a product engine assistant. You will be recieving prompts in Bosnian, Serbian"
                + " and Croatian\n"
                + "Based on the user's query, provide ONLY THE LOGIC VALUE if the product is found "
                + " Do not provide anything else except the TRUE or FALSE.  For example: {'image':"
                + " 'base64encodedimage', 'product': 'banane'}\n"
                + " this means that the user is requesting a check for whether bananas are listed"
                + " as an item on the receipt base64 image. If there is a match,  respond with"
                + " TRUE, otherwise respond with FALSE. ";

    @Autowired
    public SearchProductEngine() {
        this.openAiClient =
                OpenAiClient.builder().openAiApiKey(System.getenv("OPENAI_API_KEY")).build();
    }

    public String search(String query) {
        String prompt = buildPrompt(query);
        String responseText = callOpenAiApi(systemPrompt, prompt);

        return responseText.equals("TRUE") ? "Product found" : "Product not found";
    }

    private String buildPrompt(String query) {
        StringBuilder prompt = new StringBuilder("Here is what I want: " + query);
        prompt.append("Here is a receipt of products\n");

        return prompt.toString();
    }

    private String callOpenAiApi(String systemMessage, String userMessage) {

        ChatCompletionRequest request =
                ChatCompletionRequest.builder()
                        .model(ChatCompletionModel.GPT_4O)
                        .addSystemMessage(systemMessage)
                        .addUserMessage(userMessage)
                        .temperature(0.4)
                        .build();

        ChatCompletionResponse response = openAiClient.chatCompletion(request).execute();
        return response.choices().get(0).message().content().trim();
    }
}
