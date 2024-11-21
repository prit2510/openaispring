package com.openaispring.openaispring.Controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

// import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.openaispring.openaispring.Models.ChatCompletionRequest;
import com.openaispring.openaispring.Models.ChatCompletionResponse;
import com.openaispring.openaispring.Services.QuestionService;
import com.openaispring.openaispring.Services.QuizService;
import com.openaispring.openaispring.entitys.Quiz;
import com.openaispring.openaispring.entitys.que;

import org.springframework.beans.factory.annotation.Value;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.openaispring.openaispring.forms.Question;
import com.openaispring.openaispring.forms.QuizRequestform;
import com.openaispring.openaispring.forms.QuizResponse;

@RestController
public class MainController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${groq.api.url}")
    private String groqApiUrl;

    @PostMapping("/hitGroqApi")
    public String getGroqResponseAndSaveQuiz(@RequestBody QuizRequestform quizRequest) {
        String topic = quizRequest.getTopic();
        int noQue = quizRequest.getNoQue();
        String prompt = "generate " + noQue + " mcqs quiz on " + topic + " give response in json format";

        try {
            // Call the external AI API
            ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest("llama3-groq-70b-8192-tool-use-preview", prompt);
            ChatCompletionResponse response = restTemplate.postForObject(groqApiUrl, chatCompletionRequest, ChatCompletionResponse.class);

            if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
                throw new RuntimeException("Invalid response from AI API");
            }

            String responseContent = response.getChoices().get(0).getMessage().getContent();

            // Check if the response is JSON
            if (responseContent.startsWith("{") || responseContent.startsWith("[")) {
                Gson gson = new Gson();
                QuizResponse quizResponse = gson.fromJson(responseContent, QuizResponse.class);

                // Create and save Quiz entity
                Quiz quizEntity = new Quiz();
                quizEntity.setSubject(topic);
                quizEntity.setGrade(12); // Customize as needed
                quizEntity.setDifficulty("Medium"); // Customize as needed
                quizEntity.setTotalQuestions(noQue);
                quizEntity.setMaxScore(100); // Customize as needed

                List<que> questionEntities = quizResponse.getQuestions().stream().map(question -> {
                    que queEntity = new que();
                    queEntity.setQuestionText(question.getQuestion());
                    queEntity.setOptions(gson.toJson(question.getOptions())); // Convert options to JSON format
                    queEntity.setCorrectAnswer(question.getCorrectAnswer());
                    return queEntity;
                }).collect(Collectors.toList());

                quizService.saveQuiz(quizEntity, questionEntities);

                return "Quiz and questions saved successfully!";
            } else {
                // If response is not JSON
                return "Response is not in JSON format: " + responseContent;
            }

        } catch (JsonSyntaxException e) {
            // Handle JSON parsing errors
            return "Failed to parse the response as JSON: " + e.getMessage();
        } catch (HttpClientErrorException e) {
            // Handle HTTP errors
            return "Error during API call: " + e.getStatusCode() + " - " + e.getResponseBodyAsString();
        } catch (Exception e) {
            // Handle general errors
            return "An error occurred: " + e.getMessage();
        }
    }
}

