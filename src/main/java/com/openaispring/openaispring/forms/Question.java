package com.openaispring.openaispring.forms;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Question {
    private String question;
    private List<String> options;
    private String correctAnswer;
}
