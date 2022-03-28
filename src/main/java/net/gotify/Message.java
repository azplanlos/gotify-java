package net.gotify;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message{

	@JsonProperty("date")
	private String date;

	@JsonProperty("appid")
	private int appid;

	@JsonProperty("id")
	private int id;

	@JsonProperty("message")
	private String message;

	@JsonProperty("title")
	private String title;

	@JsonProperty("priority")
	private int priority;

	public String getDate(){
		return date;
	}

	public int getAppid(){
		return appid;
	}

	public int getId(){
		return id;
	}

	public String getMessage(){
		return message;
	}

	public String getTitle(){
		return title;
	}

	public int getPriority(){
		return priority;
	}
}