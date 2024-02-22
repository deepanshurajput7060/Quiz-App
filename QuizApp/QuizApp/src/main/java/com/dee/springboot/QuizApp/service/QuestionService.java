package com.dee.springboot.QuizApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dee.springboot.QuizApp.dao.QuestionDao;
import com.dee.springboot.QuizApp.model.Question;

@Service
public class QuestionService {

	@Autowired
	private QuestionDao questionDao;
	
//	public List<Question> getAllQuestions() {		
//		return questionDao.findAll();
//	}
	public ResponseEntity<List<Question>> getAllQuestions() {		
		try {
			return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(questionDao.findAll(), HttpStatus.BAD_REQUEST) ;
	}

	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		try {
			return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.BAD_REQUEST) ;
	}

	public ResponseEntity<String> addQuestion(Question question) {
		questionDao.save(question);
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

}
