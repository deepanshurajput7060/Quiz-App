package com.dee.QuizApp.Services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dee.QuizApp.Dto.QuestionDto;
import com.dee.QuizApp.Entity.utils.QuestionWrapper;
import com.dee.QuizApp.Entity.utils.Response;

public interface QuestionService {

	ResponseEntity<List<QuestionDto>> getAllQuestions();

	ResponseEntity<List<QuestionDto>> getQuestionsByCategory(String category);

	ResponseEntity<String> addQuestion(QuestionDto questionDto);

	ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions);

	ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds);

	ResponseEntity<Integer> getScore(List<Response> responses);

	ResponseEntity<String> deleteQuestion(Integer id);

}
