package com.twitter.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.twitter.model.Tweet;
import com.twitter.services.KafkaSender;
import com.twitter.services.TweetServices;


@Controller
public class AppController {
	
	@Autowired
	TweetServices service;
	@Autowired
	KafkaSender kafkaSender;
	
	
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Tweet> listTweets = service.listAll();
		model.addAttribute("listTweet", listTweets);
		return "index";
	}
	
	//LinkedHashMap<String, List>
	@GetMapping("postTweets/{hashtag}")
	public String hello3(@PathVariable("hashtag") String hashtag, @RequestHeader(value="Authorization") String authorization) {
		
		System.out.println(authorization);
		String url = "https://api.twitter.com/2/tweets/search/recent?query=";
		url = url+hashtag;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorization);
		HttpEntity request = new HttpEntity(headers);
		ResponseEntity<LinkedHashMap> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, LinkedHashMap.class);
		LinkedHashMap<String, List> tweets = responseEntity.getBody();
		
		//System.out.println(tweets);
		//Set<Entry<String, List>> = tweets.entrySet();
		
		
		Object values =tweets.get("data");
		
		System.out.println(tweets.size());
//		JSONObject jsonObject = new JSONObject(tweets.get("data"));
//		System.out.println(jsonObject.length());
		//System.out.println(jsonObject.get("data"));
		
		//for(int i = 0; jsonObject.get("data"))
		
		//8-27
		//37
		
		//String id = jsonObject.getString("id");
		
		//System.out.println(id);
		//System.out.println(values.get(0).toString().charAt(0));
		System.out.println(tweets.get("data").size());
		//System.out.println(values.toString());
		
		for(int i = 0; i<tweets.get("data").size(); i++) {
			String id = tweets.get("data").get(i).toString().substring(4, 23);
			int length = tweets.get("data").get(i).toString().length();
			String text = tweets.get("data").get(i).toString().substring(30,length-1);
			System.out.println(id+" "+text);
			
			Tweet t = new Tweet();
			t.setId(id);
			t.setText(text);
			t.setHashTag(hashtag);
			kafkaSender.send(t);
		}
		
		return "AfterPostingTweet";
		
	}
	
	@GetMapping("viewTweet/{hashTag}")
	public String getTweets(@PathVariable("hashTag") String hashTag, Model model) {
		List<Tweet> listTweets = service.listByHashTag(hashTag);
		model.addAttribute("listTweet", listTweets);
		return "index";
	}
	

}
