package com.openaispring.openaispring.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizRequestform {
    private String topic;
    private int noQue;
}
