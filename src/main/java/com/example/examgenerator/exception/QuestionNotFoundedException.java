package com.example.examgenerator.exception;

public class QuestionNotFoundedException extends RuntimeException{
    public QuestionNotFoundedException(String message) {
        super(message);
    }
}
