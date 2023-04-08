package com.example.examgenerator.service;

import com.example.examgenerator.exception.IncorrectAmountQuestionException;
import com.example.examgenerator.question.Question;
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

//    @Qualifier("JavaQuestionService")
    private final JavaQuestionService javaQuestionService;


//    @Qualifier("MathQuestionService")
    private final MathQuestionService mathQuestionService;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService, MathQuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }


    @Override
    public Collection<Question> getQuestion(Integer amount) {
        Integer questionBaseAmount = javaQuestionService.getAllQuestion().size() + mathQuestionService.getAllQuestion().size();
        if (amount > questionBaseAmount) {
            throw new IncorrectAmountQuestionException("Количество вопросов в базе: " + questionBaseAmount);
        }

        Integer javaQuestionAmount = new Random().nextInt(amount + 1);
        if (javaQuestionAmount > javaQuestionService.getAllQuestion().size() || amount > questionBaseAmount / 2) {
            javaQuestionAmount = javaQuestionService.getAllQuestion().size();
        }
        Integer mathQuestionAmount = amount - javaQuestionAmount;
        if (mathQuestionAmount > mathQuestionService.getAllQuestion().size()) {
            mathQuestionAmount = mathQuestionService.getAllQuestion().size();
        }

        randomQuestionSet = Stream.
                generate(() -> javaQuestionService.getRandomQuestion())
                .distinct()
                .limit(javaQuestionAmount)
                .collect(Collectors.toSet());

        randomQuestionSet.addAll(Stream.
                generate(() -> mathQuestionService.getRandomQuestion())
                .distinct()
                .limit(mathQuestionAmount)
                .collect(Collectors.toSet()));
        return randomQuestionSet;
    }
}
