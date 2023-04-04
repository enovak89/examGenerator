package com.example.examgenerator.service;

import com.example.examgenerator.question.Question;

import java.util.Collection;
import java.util.Collections;

public interface QuestionService {
    Question addQuestion(String question, String answer);
    Question findQuestion(String question, String answer);
    Question removeQuestion(String question, String answer);
    Collection<Question> getAllQuestion();

    Question getRandomQuestion();

}
