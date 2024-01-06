package com.ganesh.quiz.repository;

import com.ganesh.quiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
//    @Query("Select q from Question q where q.category = :category_name")
    public List<Question> findAllQuestionsByCategory(@Param("category_name") String category_name); // table name, type of the primary key

    @Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY rand() LIMIT :numOfQuestions", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(@Param("category") String category, @Param("numOfQuestions") int numOfQuestions);
}
