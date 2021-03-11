package com.thomasvitale.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

@Data @AllArgsConstructor @NoArgsConstructor
public class Book {

	@Id
	private Long id;
	private String title;
}
