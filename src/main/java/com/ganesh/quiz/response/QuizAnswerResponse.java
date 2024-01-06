package com.ganesh.quiz.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuizAnswerResponse {
    private  Integer id;
    private String response;
}
