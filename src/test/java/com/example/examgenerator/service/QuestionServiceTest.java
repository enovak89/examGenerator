package com.example.examgenerator.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {JavaQuestionService.class})
@ExtendWith(SpringExtension.class)
class QuestionServiceTest {
    @Autowired
    QuestionService questionService;

    @Test
    void addQuestion() {
    }

    @Test
    void findQuestion() {
    }

    @Test
    void removeQuestion() {
    }

    @Test
    void getAllQuestion() {
    }

    @Test
    void getRandomQuestion() {
    }
}