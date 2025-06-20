package com.dee.QuizApp.Entity.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

	private String message;
	private boolean success;
	
}
