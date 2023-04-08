package com.example.examgenerator.controller;

import com.example.examgenerator.exception.IncorrectArgumentException;
import com.example.examgenerator.exception.QuestionIsAlreadyAddedException;
import com.example.examgenerator.exception.QuestionNotFoundedException;
import com.example.examgenerator.question.Question;
import com.example.examgenerator.service.MathQuestionService;
import com.example.examgenerator.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController("MathQuestionController")
@RequestMapping("/exam/math")
public class MathQuestionController {
//    @Qualifier("MathQuestionService")
    private final MathQuestionService questionService;

    public MathQuestionController(MathQuestionService questionService) {
        this.questionService = questionService;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IncorrectArgumentException.class, QuestionNotFoundedException.class, QuestionIsAlreadyAddedException.class})
    public String handleException(RuntimeException e) {
        return HttpStatus.BAD_REQUEST.toString() + " " + e.getMessage();
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam(required = false) String question, @RequestParam(required = false) String answer) {
        return questionService.addQuestion(question, answer);
    }

    @GetMapping("/find")
    public Question findQuestion(@RequestParam(required = false) String question, @RequestParam(required = false) String answer) {
        return questionService.findQuestion(question, answer);
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam(required = false) String question, @RequestParam(required = false) String answer) {
        return questionService.removeQuestion(question, answer);
    }

    @GetMapping()
    public Collection<Question> getAllQuestion() {
        return questionService.getAllQuestion();
    }

}
