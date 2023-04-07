package com.example.examgenerator.service;

import com.example.examgenerator.question.Question;

import java.util.ArrayList;
import java.util.List;

public class JavaQuestionData {

    public static final String QUESTION_STRING_CORRECT = "ABC";
    public static final String ANSWER_STRING_CORRECT = "CBA";
    public static final Question QUESTION_CORRECT = new Question("ABC", "CBA");

    public static final List<Question> QUESTION_LIST = new ArrayList<>();

    static {
        QUESTION_LIST.add(new Question("ABC", "CBA"));
        QUESTION_LIST.add(new Question("DEF", "FED"));
        QUESTION_LIST.add(new Question("XYZ", "ZYX"));
    }


}
