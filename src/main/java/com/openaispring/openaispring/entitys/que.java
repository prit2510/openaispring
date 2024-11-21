package com.openaispring.openaispring.entitys;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "questions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class que{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;
    // @Id
    // @GeneratedValue
    // @Column(name = "question_id", columnDefinition = "VARCHAR(36)")
    // private UUID questionId;

    @ManyToOne
    @JoinColumn(name = "quizId")
    private Quiz quiz;

    @Column(columnDefinition = "TEXT")
    private String questionText;

    @Column(columnDefinition = "JSON")
    private String options;

    private String correctAnswer;

   
}


