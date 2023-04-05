package com.example.examgenerator.service;

import com.example.examgenerator.exception.IncorrectAmountQuestionException;
import com.example.examgenerator.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    Collection<Question> randomQuestionSet = new HashSet<>();
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestion(Integer amount) {
//        Collection<Question> list = new ArrayList<>();
//        Stream<Integer>
//                Stream.generate(() -> new )
//        list.stream().
//        randomQuestionSet = Stream.
//                generate(() -> questionService.getRandomQuestion()).limit(amount).
        if (amount > questionService.getAllQuestion().size()) {
            throw new IncorrectAmountQuestionException("Количество вопросов в базе: " + questionService.getAllQuestion().size());
        }
        do {
            randomQuestionSet.add(questionService.getRandomQuestion());
        }
        while (randomQuestionSet.size() < amount);
        return randomQuestionSet;
    }

}
