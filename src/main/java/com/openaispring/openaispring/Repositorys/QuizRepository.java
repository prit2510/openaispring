package com.openaispring.openaispring.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openaispring.openaispring.entitys.Quiz;

import java.util.UUID;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}

