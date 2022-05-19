package com.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.twitter.model.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Integer>{

	@Query("SELECT t FROM Tweet t WHERE t.hashTag = ?1")
	List<Tweet> findByHashTag(String HashTag);
}
