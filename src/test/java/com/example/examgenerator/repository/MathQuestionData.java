package com.example.examgenerator.repository;

import com.example.examgenerator.question.Question;

import java.util.ArrayList;
import java.util.List;

public class MathQuestionData {
    public static final String QUESTION_STRING_CORRECT = "123";
    public static final String ANSWER_STRING_CORRECT = "321";
    public static final Question QUESTION_CORRECT = new Question("123", "321");

    public static final List<Question> QUESTION_LIST = new ArrayList<>();
    static {
        QUESTION_LIST.add(new Question("123", "321"));
        QUESTION_LIST.add(new Question("456", "654"));
        QUESTION_LIST.add(new Question("789", "987"));
    }
}
