package com.twitter.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitter.model.Tweet;
import com.twitter.repository.TweetRepository;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class consumerService {
	
	@Autowired
    ObjectMapper objectMapper;
	
	@Autowired
    private TweetRepository tweetRepo;
	
	public void process(Tweet record) throws JsonProcessingException {
        log.info("emp event" + record);
        tweetRepo.save(record);
    }

}

