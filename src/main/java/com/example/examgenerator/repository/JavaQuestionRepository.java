package com.example.examgenerator.repository;

import com.example.examgenerator.question.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
@Component("JavaQuestionRepository")
public class JavaQuestionRepository implements QuestionRepository {
    private List<Question> questionList = new ArrayList<>();

    @PostConstruct
    public void init() {
        questionList.add(new Question("abc", "cba"));
        questionList.add(new Question("def", "fed"));
        questionList.add(new Question("xyz", "zyx"));
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
