package com.example.examgenerator.service;

import com.example.examgenerator.exception.IncorrectArgumentException;
import com.example.examgenerator.exception.QuestionIsAlreadyAddedException;
import com.example.examgenerator.exception.QuestionNotFoundedException;
import com.example.examgenerator.question.Question;
import org.springframework.stereotype.Service;

import java.util.Random;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private List<Question> questionList = new ArrayList<>();

    @Override
    public Question addQuestion(String question, String answer) {
        if (checkQuestionText(question, answer)) {
            Question q = new Question(question, answer);
            if (!questionList.contains(q)) {
                questionList.add(q);
                return q;
            } else {
                throw new QuestionIsAlreadyAddedException("Вопрос был добавлен ранее");
            }
        } else {
            throw new IncorrectArgumentException("Укажите корректный вопрос и/или ответ");
        }
    }

    @Override
    public Question findQuestion(String question, String answer) {
        if (checkQuestionText(question, answer)) {
            Question q = new Question(question, answer);
            if (questionList.contains(q)) {
                return q;
            } else {
                throw new QuestionNotFoundedException("Вопрос не найден");
            }
        } else {
            throw new IncorrectArgumentException("Укажите корректный вопрос и/или ответ");
        }
    }

    @Override
    public Question removeQuestion(String question, String answer) {
        if (checkQuestionText(question, answer)) {
            Question q = new Question(question, answer);
            if (questionList.contains(q)) {
                questionList.remove(q);
                return q;
            } else {
                throw new QuestionNotFoundedException("Вопрос не найден");
            }
        } else {
            throw new IncorrectArgumentException("Укажите корректный вопрос и/или ответ");
        }
    }

    @Override
    public Collection<Question> getAllQuestion() {
        return questionList;
    }

    @Override
    public Question getRandomQuestion() {
        int i = new Random().nextInt(questionList.size());
        return questionList.get(i);
    }

    public boolean checkQuestionText(String question, String answer) {
        if (question != null && answer != null && !answer.equals(question))
            return true;
        else return false;
    }
}
