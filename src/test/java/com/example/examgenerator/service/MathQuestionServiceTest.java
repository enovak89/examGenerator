package com.example.examgenerator.service;

import com.example.examgenerator.exception.IncorrectArgumentException;
import com.example.examgenerator.exception.QuestionIsAlreadyAddedException;
import com.example.examgenerator.exception.QuestionNotFoundedException;
import com.example.examgenerator.repository.MathQuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collections;
import java.util.List;

import static com.example.examgenerator.service.MathQuestionData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {MathQuestionService.class})
@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {

    @Mock
    private MathQuestionRepository mathQuestionRepository;
    @InjectMocks
    private MathQuestionService mathQuestionService;

    @Test
    void addQuestionCorrect() {
        // Подготовка ожидаемого результата
        when(mathQuestionRepository.getAllQuestion()).thenReturn(Collections.emptyList());
        when(mathQuestionRepository.addQuestion(QUESTION_CORRECT)).thenReturn(QUESTION_CORRECT);

        // Начало теста
        assertEquals(QUESTION_CORRECT, mathQuestionService.addQuestion(QUESTION_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void addQuestionWithQuestionIsAlreadyAddedException() {
        // Подготовка ожидаемого результата
        when(mathQuestionRepository.getAllQuestion()).thenReturn(QUESTION_LIST);

        // Начало теста
        assertThrows(QuestionIsAlreadyAddedException.class,
                () -> mathQuestionService.addQuestion(QUESTION_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void addQuestionWithIncorrectArgumentExceptionFirst() {
        // Начало теста
        assertThrows(IncorrectArgumentException.class,
                () -> mathQuestionService.addQuestion(ANSWER_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void addQuestionWithIncorrectArgumentExceptionSecond() {
        // Начало теста
        assertThrows(IncorrectArgumentException.class,
                () -> mathQuestionService.addQuestion(null, ANSWER_STRING_CORRECT));
    }
    @Test
    void addQuestionWithIncorrectArgumentExceptionThird() {
        // Начало теста
        assertThrows(IncorrectArgumentException.class,
                () -> mathQuestionService.addQuestion(QUESTION_STRING_CORRECT, null));
    }

    @Test
    void findQuestionCorrect() {
        // Подготовка ожидаемого результата
        when(mathQuestionRepository.getAllQuestion()).thenReturn(QUESTION_LIST);

        // Начало теста
        assertEquals(QUESTION_CORRECT, mathQuestionService.findQuestion(QUESTION_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void findQuestionWithQuestionNotFoundedException() {
        // Подготовка ожидаемого результата
        when(mathQuestionRepository.getAllQuestion()).thenReturn(Collections.emptyList());

        // Начало теста
        assertThrows(QuestionNotFoundedException.class,
                () -> mathQuestionService.findQuestion(QUESTION_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void findQuestionWithIncorrectArgumentExceptionFirst() {
        // Начало теста
        assertThrows(IncorrectArgumentException.class,
                () -> mathQuestionService.findQuestion(ANSWER_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void findQuestionWithIncorrectArgumentExceptionSecond() {
        // Начало теста
        assertThrows(IncorrectArgumentException.class,
                () -> mathQuestionService.findQuestion(null, ANSWER_STRING_CORRECT));
    }
    @Test
    void findQuestionWithIncorrectArgumentExceptionThird() {
        // Начало теста
        assertThrows(IncorrectArgumentException.class,
                () -> mathQuestionService.findQuestion(QUESTION_STRING_CORRECT, null));
    }

    @Test
    void removeQuestionCorrect() {
        // Подготовка ожидаемого результата
        when(mathQuestionRepository.getAllQuestion()).thenReturn(QUESTION_LIST);
        when(mathQuestionRepository.removeQuestion(QUESTION_CORRECT)).thenReturn(QUESTION_CORRECT);

        // Начало теста
        assertEquals(QUESTION_CORRECT, mathQuestionService.removeQuestion(QUESTION_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void removeQuestionWithQuestionNotFoundedException() {
        // Подготовка ожидаемого результата
        when(mathQuestionRepository.getAllQuestion()).thenReturn(Collections.emptyList());

        // Начало теста
        assertThrows(QuestionNotFoundedException.class,
                () -> mathQuestionService.removeQuestion(QUESTION_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void removeQuestionWithIncorrectArgumentExceptionFirst() {
        // Начало теста
        assertThrows(IncorrectArgumentException.class,
                () -> mathQuestionService.removeQuestion(ANSWER_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void removeQuestionWithIncorrectArgumentExceptionSecond() {
        // Начало теста
        assertThrows(IncorrectArgumentException.class,
                () -> mathQuestionService.removeQuestion(null, ANSWER_STRING_CORRECT));
    }
    @Test
    void removeQuestionWithIncorrectArgumentExceptionThird() {
        // Начало теста
        assertThrows(IncorrectArgumentException.class,
                () -> mathQuestionService.removeQuestion(QUESTION_STRING_CORRECT, null));
    }

    @Test
    void getAllQuestionCorrect() {
        // Подготовка ожидаемого результата
        when(mathQuestionRepository.getAllQuestion()).thenReturn(QUESTION_LIST);

        // Начало теста
        assertEquals(QUESTION_LIST, mathQuestionService.getAllQuestion());
    }

    @Test
    void getAllQuestionEmpty() {
        // Подготовка ожидаемого результата
        when(mathQuestionRepository.getAllQuestion()).thenReturn(Collections.emptyList());

        // Начало теста
        assertEquals(Collections.emptyList(), mathQuestionService.getAllQuestion());
    }

    @Test
    void getRandomQuestionCorrect() {
        // Подготовка ожидаемого результата
        when(mathQuestionRepository.getAllQuestion()).thenReturn(List.of(QUESTION_CORRECT));

        // Начало теста
        assertEquals(QUESTION_CORRECT, mathQuestionService.getRandomQuestion());
    }

    @Test
    void checkQuestionTextCorrect() {
        assertTrue(mathQuestionService.checkQuestionText(QUESTION_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void checkQuestionTextIncorrect() {
        assertFalse(mathQuestionService.checkQuestionText(ANSWER_STRING_CORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void checkQuestionTextIncorrectQuestionString() {
        assertFalse(mathQuestionService.checkQuestionText(QUESTION_STRING_INCORRECT, ANSWER_STRING_CORRECT));
    }

    @Test
    void checkQuestionTextIncorrectAnswerString() {
        assertFalse(mathQuestionService.checkQuestionText(QUESTION_STRING_CORRECT, ANSWER_STRING_INCORRECT));
    }

    @Test
    void checkQuestionTextNullQuestionString() {
        assertFalse(mathQuestionService.checkQuestionText(null, ANSWER_STRING_CORRECT));
    }

    @Test
    void checkQuestionTextNullAnswerString() {
        assertFalse(mathQuestionService.checkQuestionText(QUESTION_STRING_CORRECT, null));
    }

}