package com.example.examgenerator.service;

import com.example.examgenerator.exception.IncorrectAmountQuestionException;
import com.example.examgenerator.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    Collection<Question> randomQuestionSet = new HashSet<>();

    @Autowired
    @Qualifier("JavaQuestionService")
    private QuestionService javaquestionService;

    @Autowired
    @Qualifier("MathQuestionService")
    private QuestionService mathquestionService;


    @Override
    public Collection<Question> getQuestion(Integer amount) {
        Integer questionBaseAmount = javaquestionService.getAllQuestion().size() + mathquestionService.getAllQuestion().size();
        if (amount > questionBaseAmount) {
            throw new IncorrectAmountQuestionException("Количество вопросов в базе: " + questionBaseAmount);
        }

        Integer javaQuestionAmount = new Random().nextInt(amount + 1);
        if (javaQuestionAmount > javaquestionService.getAllQuestion().size() || amount > questionBaseAmount / 2) {
            javaQuestionAmount = javaquestionService.getAllQuestion().size();
        }
        Integer mathQuestionAmount = amount - javaQuestionAmount;
        if (mathQuestionAmount > mathquestionService.getAllQuestion().size()) {
            mathQuestionAmount = mathquestionService.getAllQuestion().size();
        }

        randomQuestionSet = Stream.
                generate(() -> javaquestionService.getRandomQuestion())
                .distinct()
                .limit(javaQuestionAmount)
                .collect(Collectors.toSet());

        randomQuestionSet.addAll(Stream.
                generate(() -> mathquestionService.getRandomQuestion())
                .distinct()
                .limit(mathQuestionAmount)
                .collect(Collectors.toSet()));

        return randomQuestionSet;


    }

}
