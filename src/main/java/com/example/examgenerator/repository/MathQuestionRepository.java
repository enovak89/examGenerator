package com.example.examgenerator.repository;

import com.example.examgenerator.exception.IncorrectArgumentException;
import com.example.examgenerator.exception.QuestionIsAlreadyAddedException;
import com.example.examgenerator.exception.QuestionNotFoundedException;
import com.example.examgenerator.question.Question;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository("MathQuestionRepository")
public class MathQuestionRepository implements QuestionRepository {

    private List<Question> questionList = new ArrayList<>();

    @PostConstruct
    public void init() {
        questionList.add(new Question("123", "321"));
        questionList.add(new Question("456", "654"));
        questionList.add(new Question("789", "987"));
    }

    @Override
    public Question addQuestion(Question question) {
        questionList.add(question);
        return question;
    }

    @Override
    public Question removeQuestion(Question question) {
        questionList.remove(question);
        return question;
    }

    @Override
    public List<Question> getAllQuestion() {
        return questionList;
    }
}
