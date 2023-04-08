package com.example.examgenerator.service;

import com.example.examgenerator.exception.IncorrectAmountQuestionException;
import com.example.examgenerator.question.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ExaminerServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ExaminerServiceImplTest {

    @MockBean
    private JavaQuestionService javaQuestionService;

    @MockBean
    private MathQuestionService mathQuestionService;


    private final ExaminerServiceImpl examinerServiceImpl;
    @Autowired
    public ExaminerServiceImplTest(ExaminerServiceImpl examinerServiceImpl) {
        this.examinerServiceImpl = examinerServiceImpl;
    }


    @Test
    void getQuestionCorrect() {
        // Подготовка данных
        Integer amount = 2;
        Question javaQuestionCorrect = new Question("ABC", "CBA");
        Question mathQuestionCorrect = new Question("123", "321");
        List<Question> javaQuestionList = new ArrayList<>(List.of(javaQuestionCorrect));
        List<Question> mathQuestionList = new ArrayList<>(List.of(mathQuestionCorrect));
        Set<Question> expectedQuestionSet = new HashSet<>(Set.of(javaQuestionCorrect, mathQuestionCorrect));

        // Подготовка ожидаемого результата
        when(javaQuestionService.getAllQuestion()).thenReturn(javaQuestionList);
        when(mathQuestionService.getAllQuestion()).thenReturn(mathQuestionList);
        when(javaQuestionService.getRandomQuestion()).thenReturn(javaQuestionCorrect);
        when(mathQuestionService.getRandomQuestion()).thenReturn(mathQuestionCorrect);

        // Начало теста
        assertEquals(expectedQuestionSet, examinerServiceImpl.getQuestion(amount));
    }

    @Test
    void getQuestionWithException() {
        // Подготовка данных
        Integer amount = 3;
        Question javaQuestionCorrect = new Question("ABC", "CBA");
        Question mathQuestionCorrect = new Question("123", "321");
        List<Question> javaQuestionList = new ArrayList<>(List.of(javaQuestionCorrect));
        List<Question> mathQuestionList = new ArrayList<>(List.of(mathQuestionCorrect));

        // Подготовка ожидаемого результата
        when(javaQuestionService.getAllQuestion()).thenReturn(javaQuestionList);
        when(mathQuestionService.getAllQuestion()).thenReturn(mathQuestionList);

        // Начало теста
        assertThrows(IncorrectAmountQuestionException.class, () -> examinerServiceImpl.getQuestion(amount));
    }
}