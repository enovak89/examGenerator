package com.example.examgenerator.repository;

import com.example.examgenerator.question.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {MathQuestionRepository.class})
@ExtendWith(SpringExtension.class)
class MathQuestionRepositoryTest {

    private MathQuestionRepository mathQuestionRepository;
    @Autowired
    public MathQuestionRepositoryTest(MathQuestionRepository mathQuestionRepository) {
        this.mathQuestionRepository = mathQuestionRepository;
    }

    @BeforeEach
    private void clearQuestionList() {
        mathQuestionRepository.getAllQuestion().clear();
    }

    public static final String QUESTION_STRING_CORRECT = "123";
    public static final String ANSWER_STRING_CORRECT = "321";
    public static final Question QUESTION_CORRECT = new Question("123", "321");

    public static final List<Question> QUESTION_LIST = new ArrayList<>();

    static {
        QUESTION_LIST.add(new Question("123", "321"));
        QUESTION_LIST.add(new Question("456", "654"));
        QUESTION_LIST.add(new Question("789", "987"));
    }
    @Test
    void addQuestion() {
        assertEquals(QUESTION_CORRECT, mathQuestionRepository.addQuestion(QUESTION_CORRECT));
    }

    @Test
    void removeQuestion() {
        assertEquals(QUESTION_CORRECT, mathQuestionRepository.removeQuestion(QUESTION_CORRECT));
    }

    @Test
    void getAllQuestion() {
        mathQuestionRepository.addQuestion(new Question("123", "321"));
        mathQuestionRepository.addQuestion(new Question("456", "654"));
        mathQuestionRepository.addQuestion(new Question("789", "987"));
        assertEquals(QUESTION_LIST, mathQuestionRepository.getAllQuestion());
    }
}