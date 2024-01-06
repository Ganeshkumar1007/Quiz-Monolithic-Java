package com.ganesh.quiz.controller;

import com.ganesh.quiz.model.Question;
import com.ganesh.quiz.service.QuestionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
//        System.out.println("Hii");
        return questionService.getAllQuestions();
    }

    @GetMapping("/allQuestions/category/{category_name}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable String category_name){
        return questionService.getAllQuestionsByCategory(category_name);

    }
@PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

}
