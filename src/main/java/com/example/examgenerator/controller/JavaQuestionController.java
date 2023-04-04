package com.example.examgenerator.controller;

import com.example.examgenerator.exception.IncorrectArgumentException;
import com.example.examgenerator.exception.QuestionIsAlreadyAddedException;
import com.example.examgenerator.exception.QuestionNotFoundedException;
import com.example.examgenerator.question.Question;
import com.example.examgenerator.service.JavaQuestionService;
import com.example.examgenerator.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/exam")
public class JavaQuestionController {
    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
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

    @GetMapping("/java")
    public Collection<Question> getAllQuestion() {
        return questionService.getAllQuestion();
    }
}
