package com.example.examgenerator.repository;

import com.example.examgenerator.question.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.examgenerator.repository.MathQuestionData.*;
import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {MathQuestionRepository.class})
@ExtendWith(SpringExtension.class)
class MathQuestionRepositoryTest {
    @Autowired
    private MathQuestionRepository mathQuestionRepository;

    @BeforeEach
    private void clearQuestionList() {
        mathQuestionRepository.getAllQuestion().clear();
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