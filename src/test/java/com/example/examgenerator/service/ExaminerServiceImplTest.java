package com.example.examgenerator.service;

import com.example.examgenerator.question.Question;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
@ContextConfiguration(classes = {ExaminerServiceImpl.class})
@ExtendWith(MockitoExtension.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExaminerServiceImplTest {

    @Mock
    private static JavaQuestionService javaQuestionService;

    @Mock
    private MathQuestionService mathQuestionService;
//
//    @MockBean
//    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerServiceImpl;

    Integer amount = 2;
    Integer javaQuestionServiceSize = 3;
    Integer mathQuestionServiceSize = 3;
    Integer javaQuestionAmount = 1;
    Integer mathQuestionAmount = 1;
    Question javaQuestionCorrect = new Question("ABC", "CBA");
    Question mathQuestionCorrect = new Question("123", "321");
    List<Question> javaQuestionList = new ArrayList<>(List.of(javaQuestionCorrect));
    List<Question> mathQuestionList = new ArrayList<>(List.of(mathQuestionCorrect));
    Set<Question> expectedQuestionSet = new HashSet<>(Set.of(javaQuestionCorrect, mathQuestionCorrect));

    @BeforeEach
    void setMockBehavior() {
        javaQuestionService = mock(JavaQuestionService.class);

        when(javaQuestionService.getAllQuestion()).thenReturn(javaQuestionList);
    }

//    @BeforeEach
//    public void initMocks(){
//        MockitoAnnotations.initMocks(this.javaQuestionService);
//    }


    @Test
    void getQuestionCorrect() {
        // Подготовка данных
//        Integer amount = 2;
//        Integer javaQuestionServiceSize = 3;
//        Integer mathQuestionServiceSize = 3;
//        Integer javaQuestionAmount = 1;
//        Integer mathQuestionAmount = 1;
//        Question javaQuestionCorrect = new Question("ABC", "CBA");
//        Question mathQuestionCorrect = new Question("123", "321");
//        List<Question> javaQuestionList = new ArrayList<>(List.of(javaQuestionCorrect));
//        List<Question> mathQuestionList = new ArrayList<>(List.of(mathQuestionCorrect));
//        Set<Question> expectedQuestionSet = new HashSet<>(Set.of(javaQuestionCorrect, mathQuestionCorrect));

        // Подготовка ожидаемого результата
//        doReturn(javaQuestionList).when(javaQuestionService).getAllQuestion();
//        when(javaQuestionService.getAllQuestion()).thenReturn(javaQuestionList);
        when(mathQuestionService.getAllQuestion()).thenReturn(mathQuestionList);
        when(javaQuestionService.getRandomQuestion()).thenReturn(javaQuestionCorrect);
        when(mathQuestionService.getRandomQuestion()).thenReturn(mathQuestionCorrect);

        // Начало теста
        assertEquals(expectedQuestionSet, examinerServiceImpl.getQuestion(amount));

    }




}