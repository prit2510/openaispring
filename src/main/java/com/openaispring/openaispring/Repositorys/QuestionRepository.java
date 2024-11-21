package com.openaispring.openaispring.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openaispring.openaispring.entitys.que;

import java.util.UUID;

public interface QuestionRepository extends JpaRepository<que, Integer> {
}

