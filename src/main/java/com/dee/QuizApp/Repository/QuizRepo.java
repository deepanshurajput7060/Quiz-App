package com.dee.QuizApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dee.QuizApp.Entity.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Integer>{

}
