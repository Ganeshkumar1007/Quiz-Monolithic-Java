package com.ganesh.quiz.service;

import com.ganesh.quiz.model.Question;
import com.ganesh.quiz.repository.QuestionRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    public ResponseEntity<List<Question>> getAllQuestions() {
            try{
                return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
            }
            catch (Exception e){
               e.printStackTrace();

            }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    public ResponseEntity<List<Question>> getAllQuestionsByCategory(String category_name) {
        try {
            return new ResponseEntity<>(questionRepository.findAllQuestionsByCategory(category_name), HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionRepository.findAllQuestionsByCategory(category_name), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionRepository.save(question);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }
    public String findAnswerById(Integer id){
        Question question = questionRepository.findById(id).get();
        return question.getRightAnswer();
    }

}
