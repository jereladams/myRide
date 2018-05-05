package com.webServices.recalls;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class RecallResults{

	@JsonProperty("Message")
	private String message;

	@JsonProperty("Results")
	private List<RecallItem> results;

	@JsonProperty("Count")
	private int count;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setResults(List<RecallItem> results){
		this.results = results;
	}

	public List<RecallItem> getResults(){
		return results;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	@Override
 	public String toString(){
		return 
			"RecallResults{" + 
			"message = '" + message + '\'' + 
			",results = '" + results + '\'' + 
			",count = '" + count + '\'' + 
			"}";
		}
}