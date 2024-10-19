package com.openaispring.openaispring.Controllers;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.openaispring.openaispring.Models.ChatCompletionRequest;
import com.openaispring.openaispring.Models.ChatCompletionResponse;

import org.springframework.beans.factory.annotation.Value;

// @RestController
// public class MainController {
//     @Autowired
//     RestTemplate restTemplate;

//     @Value("${groq.api.url}")
//     private String groqApiUrl;

//     @PostMapping("/hitGroqApi")
//     public String getGroqResponse(@RequestBody String topic) {
        
//         String prompt="genrate 5 mcqs quiz on"+topic+"give responc in json format";
//         ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest("llama3-groq-70b-8192-tool-use-preview", prompt);
//         ChatCompletionResponse response = restTemplate.postForObject(groqApiUrl, chatCompletionRequest, ChatCompletionResponse.class);
//         String responseContent = response.getChoices().get(0).getMessage().getContent();
        
//         return responseContent;
//     }
// }
import com.google.gson.Gson;

import com.openaispring.openaispring.forms.QuizResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    @Autowired
    RestTemplate restTemplate;

    @Value("${groq.api.url}")
    private String groqApiUrl;

    // @PostMapping("/hitGroqApi")
    // public QuizResponse getGroqResponse(@RequestBody String topic) {
        
    //     String prompt = "generate 5 mcqs quiz on " + topic + " give response in json format";
    //     ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest("llama3-groq-70b-8192-tool-use-preview", prompt);
    //     ChatCompletionResponse response = restTemplate.postForObject(groqApiUrl, chatCompletionRequest, ChatCompletionResponse.class);
    //     String responseContent = response.getChoices().get(0).getMessage().getContent();
        
    //     // Deserialize JSON response into Java object using Gson
    //     Gson gson = new Gson();
    //     QuizResponse quizResponse = gson.fromJson(responseContent, QuizResponse.class);
        
    //     return quizResponse;  // Return deserialized object
    // }
    @PostMapping("/hitGroqApi")
public String getGroqResponse(@RequestBody String topic) {
    
    String prompt = "generate 5 mcqs quiz on " + topic + " give response in json format";
    ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest("llama3-groq-70b-8192-tool-use-preview", prompt);
    ChatCompletionResponse response = restTemplate.postForObject(groqApiUrl, chatCompletionRequest, ChatCompletionResponse.class);
    
    String responseContent = response.getChoices().get(0).getMessage().getContent();
    
    // Check if response starts with '{' or '[' to identify if it's a JSON object/array
    if (responseContent.startsWith("{") || responseContent.startsWith("[")) {
        // Deserialize if it's a valid JSON
        Gson gson = new Gson();
        QuizResponse quizResponse = gson.fromJson(responseContent, QuizResponse.class);
        return quizResponse.toString();
    } else {
        // Return raw response if it's not JSON
        return "Response is not in JSON format: " + responseContent;
    }
}

}

