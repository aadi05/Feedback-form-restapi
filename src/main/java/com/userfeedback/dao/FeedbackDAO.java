package com.userfeedback.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userfeedback.model.Feedback;

@Repository
public interface FeedbackDAO extends JpaRepository<Feedback, Integer>{
	
	public Feedback findByUsername(String username);
	public List<Feedback> findByfeedbackType(String type);
	public List<Feedback> findByRating(int rating);
	
}
