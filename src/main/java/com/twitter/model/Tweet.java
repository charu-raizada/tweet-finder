package com.twitter.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tweet {
	
	@Id
	private String id;
	private String text;
	private String hashTag;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getHashTag() {
		return hashTag;
	}
	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}
	@Override
	public String toString() {
		return "Tweet [id=" + id + ", text=" + text + ", hashTag=" + hashTag + "]";
	}
	
	
	
	

}
