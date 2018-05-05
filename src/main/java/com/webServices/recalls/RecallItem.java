package com.webServices.recalls;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class RecallItem {

	@JsonProperty("ReportReceivedDate")
	private String reportReceivedDate;

	@JsonProperty("Manufacturer")
	private String manufacturer;

	@JsonProperty("Model")
	private String model;

	@JsonProperty("Summary")
	private String summary;

	@JsonProperty("NHTSACampaignNumber")
	private String nHTSACampaignNumber;

	@JsonProperty("ModelYear")
	private String modelYear;

	@JsonProperty("Make")
	private String make;

	@JsonProperty("Component")
	private String component;

	@JsonProperty("Conequence")
	private String conequence;

	@JsonProperty("Notes")
	private String notes;

	@JsonProperty("Remedy")
	private String remedy;

	public void setReportReceivedDate(String reportReceivedDate){
    	this.reportReceivedDate = reportReceivedDate;
	}

	public String getReportReceivedDate(){
		return reportReceivedDate;
	}

	public void setManufacturer(String manufacturer){
		this.manufacturer = manufacturer;
	}

	public String getManufacturer(){
		return manufacturer;
	}

	public void setModel(String model){
		this.model = model;
	}

	public String getModel(){
		return model;
	}

	public void setSummary(String summary){
		this.summary = summary;
	}

	public String getSummary(){
		return summary;
	}

	public void setNHTSACampaignNumber(String nHTSACampaignNumber){
		this.nHTSACampaignNumber = nHTSACampaignNumber;
	}

	public String getNHTSACampaignNumber(){
		return nHTSACampaignNumber;
	}

	public void setModelYear(String modelYear){
		this.modelYear = modelYear;
	}

	public String getModelYear(){
		return modelYear;
	}

	public void setMake(String make){
		this.make = make;
	}

	public String getMake(){
		return make;
	}

	public void setComponent(String component){
		this.component = component;
	}

	public String getComponent(){
		return component;
	}

	public void setConequence(String conequence){
		this.conequence = conequence;
	}

	public String getConequence(){
		return conequence;
	}

	public void setNotes(String notes){
		this.notes = notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setRemedy(String remedy){
		this.remedy = remedy;
	}

	public String getRemedy(){
		return remedy;
	}

	@Override
 	public String toString(){
		return 
			"RecallItem{" +
			"reportReceivedDate = '" + reportReceivedDate + '\'' + 
			",manufacturer = '" + manufacturer + '\'' + 
			",model = '" + model + '\'' + 
			",summary = '" + summary + '\'' + 
			",nHTSACampaignNumber = '" + nHTSACampaignNumber + '\'' + 
			",modelYear = '" + modelYear + '\'' + 
			",make = '" + make + '\'' + 
			",component = '" + component + '\'' + 
			",conequence = '" + conequence + '\'' + 
			",notes = '" + notes + '\'' + 
			",remedy = '" + remedy + '\'' + 
			"}";
		}
}