package com.example.examgenerator.repository;

import com.example.examgenerator.question.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {JavaQuestionRepository.class})
@ExtendWith(SpringExtension.class)
class JavaQuestionRepositoryTest {

    private final JavaQuestionRepository javaQuestionRepository;

    @Autowired
    public JavaQuestionRepositoryTest(JavaQuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
    }

    @BeforeEach
    private void clearQuestionList() {
        javaQuestionRepository.getAllQuestion().clear();
    }

    public static final String QUESTION_STRING_CORRECT = "ABC";
    public static final String ANSWER_STRING_CORRECT = "CBA";
    public static final Question QUESTION_CORRECT = new Question("ABC", "BCA");

    public static final List<Question> QUESTION_LIST = new ArrayList<>();

    static {
        QUESTION_LIST.add(new Question("ABC", "CBA"));
        QUESTION_LIST.add(new Question("DEF", "FED"));
        QUESTION_LIST.add(new Question("XYZ", "ZYX"));
    }

    @Test
    void addQuestion() {
        assertEquals(QUESTION_CORRECT, javaQuestionRepository.addQuestion(QUESTION_CORRECT));
    }

    @Test
    void removeQuestion() {
        assertEquals(QUESTION_CORRECT, javaQuestionRepository.removeQuestion(QUESTION_CORRECT));
    }

    @Test
    void getAllQuestion() {
        javaQuestionRepository.addQuestion(new Question("ABC", "CBA"));
        javaQuestionRepository.addQuestion(new Question("DEF", "FED"));
        javaQuestionRepository.addQuestion(new Question("XYZ", "ZYX"));
        assertEquals(QUESTION_LIST, javaQuestionRepository.getAllQuestion());
    }
}