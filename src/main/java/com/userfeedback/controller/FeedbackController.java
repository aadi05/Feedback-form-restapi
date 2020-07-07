package com.userfeedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.userfeedback.exception.BusinessException;
import com.userfeedback.model.Feedback;
import com.userfeedback.service.FeedbackService;

@RestController
public class FeedbackController {
	
	@Autowired
	private FeedbackService service;
	
	private MultiValueMap<String, String> map;
	
	@PostMapping("feedback")
	public ResponseEntity<Feedback> submitFeedback(@RequestBody Feedback feedback) {
		try {
			return new ResponseEntity<Feedback>(service.submitFeedback(feedback),HttpStatus.OK);
		} catch (BusinessException e) {
			map=new LinkedMultiValueMap<>();
			map.add("message", e.getMessage());
			return new ResponseEntity<Feedback>(null,map, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("feedback/{id}")
	public ResponseEntity<Feedback> getFeedbackById(@PathVariable("id") int id) throws BusinessException {
		try {
			return new ResponseEntity<Feedback>(service.getFeedbackById(id),HttpStatus.OK);
		} catch (BusinessException e) {
			map=new LinkedMultiValueMap<>();
			map.add("message", e.getMessage());
			return new ResponseEntity<Feedback>(null,map, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("feedback/user/{username}")
	public ResponseEntity<Feedback> getFeedbackByUser(@PathVariable("username") String username) throws BusinessException {
		try {
			return new ResponseEntity<Feedback>(service.getFeedbackByUser(username),HttpStatus.OK);
		} catch (BusinessException e) {
			map=new LinkedMultiValueMap<>();
			map.add("message", e.getMessage());
			return new ResponseEntity<Feedback>(null,map, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("feedbacks")
	public List<Feedback> getFeedbacks() {
		return service.getFeedbacks();
	}
	
	@GetMapping("feedbacks/type/{type}")
	public List<Feedback> getFeedbacksByType(@PathVariable("type") String type) {
		return service.getFeedbacksByType(type);
	}
	
	@GetMapping("feedbacks/rating/{rating}")
	public List<Feedback> getFeedbackByRating(@PathVariable("rating") int rating) {
		return service.getFeedbackByRating(rating);
	}
	
	@DeleteMapping("feedback/{id}")
	public ResponseEntity<Feedback> deleteFeedback(@PathVariable("id") int id) throws BusinessException {
		try {
			return new ResponseEntity<Feedback>(service.deleteFeedback(id),HttpStatus.OK);
		} catch (BusinessException e) {
			map=new LinkedMultiValueMap<>();
			map.add("message", e.getMessage());
			return new ResponseEntity<>(null,map, HttpStatus.NOT_FOUND);
		}
	}
}
