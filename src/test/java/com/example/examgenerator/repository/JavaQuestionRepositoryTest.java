package com.example.examgenerator.repository;

import com.example.examgenerator.question.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.examgenerator.repository.JavaQuestionData.*;
import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {JavaQuestionRepository.class})
@ExtendWith(SpringExtension.class)
class JavaQuestionRepositoryTest {

    @Autowired
    private JavaQuestionRepository javaQuestionRepository;

    @BeforeEach
    private void clearQuestionList() {
        javaQuestionRepository.getAllQuestion().clear();
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