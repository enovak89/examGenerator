package com.example.examgenerator.exception;

public class QuestionIsAlreadyAddedException extends RuntimeException{
    public QuestionIsAlreadyAddedException(String message) {
        super(message);
    }
}
