package com.example.examgenerator.repository;

import com.example.examgenerator.question.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question addQuestion(String question, String answer);
    Question removeQuestion(Question question);
    Collection<Question> getAllQuestion();
}
