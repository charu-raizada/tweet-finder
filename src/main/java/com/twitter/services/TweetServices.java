package com.twitter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.model.Tweet;
import com.twitter.repository.TweetRepository;

@Service
public class TweetServices {

	@Autowired
	private TweetRepository repo;
	
	public List<Tweet> listAll(){
		return repo.findAll();
	}
	
	
}
