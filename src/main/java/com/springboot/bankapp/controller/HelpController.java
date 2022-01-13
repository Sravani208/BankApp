package com.springboot.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bankapp.model.Help;
import com.springboot.bankapp.service.HelpService;

@RestController
public class HelpController {

	@Autowired
	private HelpService helpService;
	
	@PostMapping("/help")
	public Help postQandA(@RequestBody Help help){
		return helpService.postQandA(help);
		
	}
	
	@GetMapping("/help/{id}")
	public Help getQandAById(@PathVariable ("id") Long id) {
		return helpService.getQandAById(id);
	}
}