package com.ganesh.quiz.service;

import com.ganesh.quiz.model.Question;
import com.ganesh.quiz.model.Quiz;
import com.ganesh.quiz.repository.QuestionRepository;
import com.ganesh.quiz.repository.QuizRepository;
import com.ganesh.quiz.request.QuizResponse;
import com.ganesh.quiz.response.QuizAnswerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class QuizService {
    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionService questionService;


    public ResponseEntity<String> createQuiz(String category, int numOfQuestions, String title) {

        List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numOfQuestions);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuizResponse>> getQuizQuestionsById(Integer id) {
        Optional<Quiz> quiz = quizRepository.findById(id);

        List<Question> questions =quiz.get().getQuestions();
        List<QuizResponse> quizResponses = new ArrayList<>();

        for(Question question : questions ){
            QuizResponse quizResponse = new QuizResponse(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
            quizResponses.add(quizResponse);
        }

        return new ResponseEntity<>(quizResponses,HttpStatus.OK);



    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<QuizAnswerResponse> responses) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestions();

        int rightAnswer = 0;
        for(QuizAnswerResponse quizAnswerResponse : responses){

            if(quizAnswerResponse.getResponse().equals(questionService.findAnswerById(quizAnswerResponse.getId()))){

                rightAnswer++;
            }

        }

        return new ResponseEntity<>(rightAnswer, HttpStatus.OK);
    }
}
