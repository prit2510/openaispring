package com.openaispring.openaispring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openaispring.openaispring.Repositorys.QuestionRepository;
import com.openaispring.openaispring.entitys.que;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public que saveQuestion(que question) {
        return questionRepository.save(question);
    }
}

