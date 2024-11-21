package com.openaispring.openaispring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openaispring.openaispring.Repositorys.QuizRepository;
import com.openaispring.openaispring.entitys.Quiz;
import com.openaispring.openaispring.entitys.que;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public Quiz saveQuiz(Quiz quiz, List<que> questions) {
        quiz.setQuestions(questions); // Associate questions with the quiz
        quiz.setCreatedAt(LocalDateTime.now()); // Set the current timestamp
        return quizRepository.save(quiz);
    }
}

