package com.dee.QuizApp.Services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dee.QuizApp.Entity.Question;
import com.dee.QuizApp.Entity.Quiz;
import com.dee.QuizApp.Entity.utils.QuestionWrapper;
import com.dee.QuizApp.Entity.utils.Response;
import com.dee.QuizApp.Exceptions.ResourceNotFoundException;
import com.dee.QuizApp.Repository.QuestionRepo;
import com.dee.QuizApp.Repository.QuizRepo;
import com.dee.QuizApp.Services.QuizService;

@Service
public class QuizServiceImpl implements QuizService{
	
	@Autowired
	private QuestionRepo questionRepo;
	
	@Autowired
	private QuizRepo quizRepo;
	

	@Override
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Integer> questions = questionRepo.findRandomQuestionsByCategory(category, numQ);
		
		if (questions.size() < numQ) {
	        return new ResponseEntity<>("Not enough questions available.", HttpStatus.BAD_REQUEST);
	    }
		
		List<Question> QuestionFromIds = questionRepo.findAllById(questions);
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(QuestionFromIds);
		quizRepo.save(quiz);
		
		return new ResponseEntity<String> ("Quiz created successfully.", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
		Quiz quiz = quizRepo.findById(id)
							.orElseThrow(() -> new ResourceNotFoundException("Quiz", "Id", id));
		
		List<QuestionWrapper> questionnsForUser = quiz.getQuestions()
														.stream()
														.map(q -> new QuestionWrapper(
																q.getId(),
												                q.getQuestionTitle(),
												                q.getOption1(),
												                q.getOption2(),
												                q.getOption3(),
												                q.getOption4()
																))
														.collect(Collectors.toList());
		
		return new ResponseEntity<List<QuestionWrapper>> (questionnsForUser, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Integer> submitQuiz(Integer id, List<Response> responses) {
		int score=0;
		
		for (Response response: responses) {
			 Question question = questionRepo.findById(response.getId())
                     .orElseThrow(() -> new ResourceNotFoundException("Question", "id", response.getId()));
			 
			 if (response.getResponse().equalsIgnoreCase(question.getRightAnswer())) {
				 score++;
			 }

		}
		return new ResponseEntity<Integer> (score, HttpStatus.OK);
	}


	

}
