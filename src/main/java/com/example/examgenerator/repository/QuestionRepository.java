package com.example.examgenerator.repository;

import com.example.examgenerator.question.Question;

import java.util.Collection;
import java.util.List;

public interface QuestionRepository {
    Question addQuestion(Question question);

    Question removeQuestion(Question question);

    List<Question> getAllQuestion();
}
