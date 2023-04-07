package com.example.examgenerator.service;

import com.example.examgenerator.exception.IncorrectArgumentException;
import com.example.examgenerator.exception.QuestionIsAlreadyAddedException;
import com.example.examgenerator.exception.QuestionNotFoundedException;
import com.example.examgenerator.question.Question;
import com.example.examgenerator.repository.JavaQuestionRepository;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static com.example.examgenerator.service.JavaQuestionData.*;

@ContextConfiguration(classes = {JavaQuestionService.class})
@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {

    @Mock
    private JavaQuestionRepository javaQuestionRepository;
    @InjectMocks
    private JavaQuestionService javaQuestionService;

    @Test
    void addQuestionCorrect() {
        // Подготовка ожидаемого результата
        when(javaQuestionRepository.getAllQuestion()).thenReturn(Collections.emptyList());
        when(javaQuestionRepository.addQuestion(QUESTION_CORRECT)).thenReturn(QUESTION_CORRECT);

        // Начало теста
        assertEquals(QUESTION_CORRECT, javaQuestionService.addQuestion(QUESTION_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void addQuestionWithQuestionIsAlreadyAddedException() {
        // Подготовка ожидаемого результата
        when(javaQuestionRepository.getAllQuestion()).thenReturn(QUESTION_LIST);

        // Начало теста
        assertThrows(QuestionIsAlreadyAddedException.class,
                () -> javaQuestionService.addQuestion(QUESTION_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void addQuestionWithIncorrectArgumentExceptionFirst() {
        // Начало теста
        assertThrows(IncorrectArgumentException.class,
                () -> javaQuestionService.addQuestion(ANSWER_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void addQuestionWithIncorrectArgumentExceptionSecond() {
        // Начало теста
        assertThrows(IncorrectArgumentException.class,
                () -> javaQuestionService.addQuestion(null, ANSWER_STRING_CORRECT));
    }
    @Test
    void addQuestionWithIncorrectArgumentExceptionThird() {
        // Начало теста
        assertThrows(IncorrectArgumentException.class,
                () -> javaQuestionService.addQuestion(QUESTION_STRING_CORRECT, null));
    }

    @Test
    void findQuestionCorrect() {
        // Подготовка ожидаемого результата
        when(javaQuestionRepository.getAllQuestion()).thenReturn(QUESTION_LIST);

        // Начало теста
        assertEquals(QUESTION_CORRECT, javaQuestionService.findQuestion(QUESTION_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void findQuestionWithQuestionNotFoundedException() {
        // Подготовка ожидаемого результата
        when(javaQuestionRepository.getAllQuestion()).thenReturn(Collections.emptyList());

        // Начало теста
        assertThrows(QuestionNotFoundedException.class,
                () -> javaQuestionService.findQuestion(QUESTION_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void findQuestionWithIncorrectArgumentExceptionFirst() {
        // Начало теста
        assertThrows(IncorrectArgumentException.class,
                () -> javaQuestionService.findQuestion(ANSWER_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void findQuestionWithIncorrectArgumentExceptionSecond() {
        // Начало теста
        assertThrows(IncorrectArgumentException.class,
                () -> javaQuestionService.findQuestion(null, ANSWER_STRING_CORRECT));
    }
    @Test
    void findQuestionWithIncorrectArgumentExceptionThird() {
        // Начало теста
        assertThrows(IncorrectArgumentException.class,
                () -> javaQuestionService.findQuestion(QUESTION_STRING_CORRECT, null));
    }

    @Test
    void removeQuestionCorrect() {
        // Подготовка ожидаемого результата
        when(javaQuestionRepository.getAllQuestion()).thenReturn(QUESTION_LIST);
        when(javaQuestionRepository.removeQuestion(QUESTION_CORRECT)).thenReturn(QUESTION_CORRECT);

        // Начало теста
        assertEquals(QUESTION_CORRECT, javaQuestionService.removeQuestion(QUESTION_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void removeQuestionWithQuestionNotFoundedException() {
        // Подготовка ожидаемого результата
        when(javaQuestionRepository.getAllQuestion()).thenReturn(Collections.emptyList());

        // Начало теста
        assertThrows(QuestionNotFoundedException.class,
                () -> javaQuestionService.removeQuestion(QUESTION_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void removeQuestionWithIncorrectArgumentExceptionFirst() {
        // Начало теста
        assertThrows(IncorrectArgumentException.class,
                () -> javaQuestionService.removeQuestion(ANSWER_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void removeQuestionWithIncorrectArgumentExceptionSecond() {
        // Начало теста
        assertThrows(IncorrectArgumentException.class,
                () -> javaQuestionService.removeQuestion(null, ANSWER_STRING_CORRECT));
    }
    @Test
    void removeQuestionWithIncorrectArgumentExceptionThird() {
        // Начало теста
        assertThrows(IncorrectArgumentException.class,
                () -> javaQuestionService.removeQuestion(QUESTION_STRING_CORRECT, null));
    }

    @Test
    void getAllQuestionCorrect() {
        // Подготовка ожидаемого результата
        when(javaQuestionRepository.getAllQuestion()).thenReturn(QUESTION_LIST);

        // Начало теста
        assertEquals(QUESTION_LIST, javaQuestionService.getAllQuestion());
    }

    @Test
    void getAllQuestionEmpty() {
        // Подготовка ожидаемого результата
        when(javaQuestionRepository.getAllQuestion()).thenReturn(Collections.emptyList());

        // Начало теста
        assertEquals(Collections.emptyList(), javaQuestionService.getAllQuestion());
    }

    @Test
    void getRandomQuestionCorrect() {
        // Подготовка ожидаемого результата
        when(javaQuestionRepository.getAllQuestion()).thenReturn(List.of(QUESTION_CORRECT));

        // Начало теста
        assertEquals(QUESTION_CORRECT, javaQuestionService.getRandomQuestion());
    }

    @Test
    void checkQuestionTextCorrect() {
        assertTrue(javaQuestionService.checkQuestionText(QUESTION_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void checkQuestionTextIncorrect() {
        assertFalse(javaQuestionService.checkQuestionText(ANSWER_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void checkQuestionTextIncorrectQuestionString() {
        assertFalse(javaQuestionService.checkQuestionText(null, ANSWER_STRING_CORRECT));
    }

    @Test
    void checkQuestionTextIncorrectAnswerString() {
        assertFalse(javaQuestionService.checkQuestionText(QUESTION_STRING_CORRECT, null));
    }

}