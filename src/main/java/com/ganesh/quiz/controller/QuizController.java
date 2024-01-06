package com.ganesh.quiz.controller;

import com.ganesh.quiz.request.QuizResponse;
import com.ganesh.quiz.response.QuizAnswerResponse;
import com.ganesh.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestParam String category, @RequestParam int numOfQuestions, @RequestParam String title){
//        return new ResponseEntity<>("Hii", HttpStatus.CREATED);
            return quizService.createQuiz(category, numOfQuestions, title);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuizResponse>> getQuizQuestionsById(@PathVariable Integer id){
        return quizService.getQuizQuestionsById(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<QuizAnswerResponse> responses){
        return quizService.calculateResult(id, responses);
    }

}
