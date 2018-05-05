package com.webServices.vin;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class VinResults{

	@JsonProperty("Message")
	private String message;

	@JsonProperty("Results")
	private List<VinItem> results;

	@JsonProperty("Count")
	private int count;

	@JsonProperty("SearchCriteria")
	private String searchCriteria;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setResults(List<VinItem> results){
		this.results = results;
	}

	public List<VinItem> getResults(){
		return results;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setSearchCriteria(String searchCriteria){
		this.searchCriteria = searchCriteria;
	}

	public String getSearchCriteria(){
		return searchCriteria;
	}

	@Override
 	public String toString(){
		return 
			"VinResults{" + 
			"message = '" + message + '\'' + 
			",results = '" + results + '\'' + 
			",count = '" + count + '\'' + 
			",searchCriteria = '" + searchCriteria + '\'' + 
			"}";
		}
}