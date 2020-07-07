package com.userfeedback.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userfeedback.dao.FeedbackDAO;
import com.userfeedback.exception.BusinessException;
import com.userfeedback.model.Feedback;
import com.userfeedback.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	private FeedbackDAO dao;

	@Override
	public Feedback submitFeedback(Feedback feedback) throws BusinessException{
		Feedback existingFeedback = dao.findByUsername(feedback.getUsername());
		if(existingFeedback != null) {
			throw new BusinessException(feedback.getUsername()+" has already submitted feedback!");
		}
		return dao.save(feedback);
	}

	@Override
	public Feedback getFeedbackById(int id) throws BusinessException{
		if(id <= 0) {
			throw new BusinessException("Invalid Id");
		}
		Feedback feedback = null;
		try {
		feedback = dao.findById(id).get();
		}catch(NoSuchElementException e) {
			throw new BusinessException("No feedback found for id "+id);
		}
		return feedback;
	}

	@Override
	public Feedback getFeedbackByUser(String username) throws BusinessException{
		Feedback feedback = dao.findByUsername(username);
		if(feedback == null) {
			throw new BusinessException(username+" has not submitted feedback yet!");
		}
		return feedback;
		
	}

	@Override
	public List<Feedback> getFeedbacks() {
		return dao.findAll();
	}

	@Override
	public List<Feedback> getFeedbacksByType(String type) {
		return dao.findByfeedbackType(type);
	}

	@Override
	public List<Feedback> getFeedbackByRating(int rating) {
		return dao.findByRating(rating);
	}

	@Override
	public Feedback deleteFeedback(int id) throws BusinessException {
		if(id <= 0) {
			throw new BusinessException("Invalid Id");
		}
		Feedback feedback = null;
		try {
		feedback = dao.findById(id).get();
		}catch(NoSuchElementException e) {
			throw new BusinessException("No feedback found for id "+id);
		}
		dao.deleteById(id);
		return feedback;
		
	}
	

}
