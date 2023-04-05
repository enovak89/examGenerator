package com.example.examgenerator.service;

import com.example.examgenerator.question.Question;

import javax.swing.*;
import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestion(Integer amount);
}
