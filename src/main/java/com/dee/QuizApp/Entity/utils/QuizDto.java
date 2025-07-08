package com.dee.QuizApp.Entity.utils;

import java.util.List;

import com.dee.QuizApp.Entity.Question;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema (
		name = "Quiz",
		description = "Schema to hold Quiz Information"
		)
public class QuizDto {

	@NotEmpty (message = "This field can not be empty")
	@Size (min = 5, max = 500, message = "Title length must be between 5 to 500 character")
    private String title;

	@NotEmpty (message = "This field can not be empty")
    private List<Question> questions;
}
