package com.dee.springboot.QuizApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dee.springboot.QuizApp.model.Question;
import com.dee.springboot.QuizApp.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
