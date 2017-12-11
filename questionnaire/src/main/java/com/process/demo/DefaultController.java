package com.process.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

	@GetMapping("/")
	public String index(){
		return "Questionnaire Start";
	}
}