package com.example.examgenerator.service;

import com.example.examgenerator.exception.IncorrectArgumentException;
import com.example.examgenerator.exception.QuestionIsAlreadyAddedException;
import com.example.examgenerator.exception.QuestionNotFoundedException;
import com.example.examgenerator.question.Question;
import com.example.examgenerator.repository.QuestionRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Random;

@Service
@Component("MathQuestionService")
public class MathQuestionService implements QuestionService {

    @Autowired
    @Qualifier("MathQuestionRepository")
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(String question, String answer) {
        if (checkQuestionText(question, answer)) {
            Question q = new Question(question, answer);
            if (!questionRepository.getAllQuestion().contains(q)) {
                return questionRepository.addQuestion(q);
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
            if (questionRepository.getAllQuestion().contains(q)) {
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
            if (questionRepository.getAllQuestion().contains(q)) {
                return questionRepository.removeQuestion(q);
            } else {
                throw new QuestionNotFoundedException("Вопрос не найден");
            }
        } else {
            throw new IncorrectArgumentException("Укажите корректный вопрос и/или ответ");
        }
    }

    @Override
    public Collection<Question> getAllQuestion() {
        return questionRepository.getAllQuestion();
    }

    @Override
    public Question getRandomQuestion() {
        int i = new Random().nextInt(questionRepository.getAllQuestion().size());
        return questionRepository.getAllQuestion().get(i);
    }

    public boolean checkQuestionText(String question, String answer) {
        if (question != null && answer != null && !answer.equals(question) && StringUtils.isNumeric(question) &&
                StringUtils.isNumeric(answer))
            return true;
        else return false;
    }
}
