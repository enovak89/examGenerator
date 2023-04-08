package com.example.examgenerator.controller;

import com.example.examgenerator.exception.IncorrectAmountQuestionException;
import com.example.examgenerator.question.Question;
import com.example.examgenerator.service.ExaminerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;

@RestController
public class ExamController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IncorrectAmountQuestionException.class)
    public String handleException(RuntimeException e) {
        return HttpStatus.BAD_REQUEST.toString() + " " + e.getMessage();
    }

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }


    @GetMapping(path = "/exam/get/{amount}")
    public Collection<Question> getRandomQuestion(@PathVariable Integer amount) {
        return examinerService.getQuestion(amount);
    }


}
