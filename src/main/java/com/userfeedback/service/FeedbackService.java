package com.userfeedback.service;

import java.util.List;

import com.userfeedback.exception.BusinessException;
import com.userfeedback.model.Feedback;

public interface FeedbackService {
	public Feedback submitFeedback(Feedback feedback) throws BusinessException;
	public Feedback getFeedbackById(int id) throws BusinessException;
	public Feedback getFeedbackByUser(String username) throws BusinessException;
	public List<Feedback> getFeedbacks();
	public List<Feedback> getFeedbacksByType(String type);
	public List<Feedback> getFeedbackByRating(int rating);
	public Feedback deleteFeedback(int id) throws BusinessException;

}
