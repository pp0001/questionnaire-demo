package com.process.demo.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.process.demo.model.ConsumerService;

@RestController
public class DefaultController {

	@GetMapping("/")
	public String index(){
		return "Questionnaire Process Start";
	}
	
	@Inject
	private MessageRepo mRepo;
	
	@RequestMapping("/getMessage")
	public String getMsg() {
		return "Recieved Message: " + mRepo.findAll();
	}
}
