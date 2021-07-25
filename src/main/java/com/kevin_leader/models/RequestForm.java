package com.kevin_leader.models;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.annotations.Expose;

/**
 * Class for reimbursement request info to be mapped from JSON
 * @author Kevin Leader
 */
public class RequestForm {
	
	private static final Logger log = Logger.getLogger(RequestForm.class);
	@Expose private String email;
	@Expose private String password;
	@Expose private Integer eventId;
	@Expose private String eventName;
	@Expose private String startDate;
	@Expose private String startTime;
	@Expose private String location;
	@Expose private Double tuition;
	@Expose private Integer typeId;
	@Expose private Integer formatId;
	@Expose private String formatName;
	@Expose private String formatDescription;
	@Expose private String passingGradeCutoff;
	@Expose private String endDate;
	@Expose private String endTime;
	@Expose private String description;
	@Expose private Double hoursMissed;
	@Expose private List<Attachment> attachments;
	
	// No-args
	public RequestForm() {
		super();
	}
	
	// Event chosen
	public RequestForm(String email, String password, Integer eventId,
			String description, Double hoursMissed) {
		log.info("Create RequestForm with chosen event");
		this.email = email;
		this.password = password;
		this.eventId = eventId;
		this.description = description;
		this.hoursMissed = hoursMissed;
	}
	
	// Full constructor
	public RequestForm(String email, String password, Integer eventId,
			String eventName, String startDate, String startTime,
			String location, Double tuition, Integer typeId, Integer formatId,
			String formatName, String formatDescription, String 
			passingGradeCutoff, String endDate, String endTime,
			String description, Double hoursMissed, List<Attachment> attachments) {
		log.info("Create RequestForm full object");
		this.email = email;
		this.password = password;
		this.eventId = eventId;
		this.eventName = eventName;
		this.startDate = startDate;
		this.startTime = startTime;
		this.location = location;
		this.tuition = tuition;
		this.typeId = typeId;
		this.formatId = formatId;
		this.formatName = formatName;
		this.formatDescription = formatDescription;
		this.passingGradeCutoff = passingGradeCutoff;
		this.endDate = endDate;
		this.endTime = endTime;
		this.description = description;
		this.hoursMissed = hoursMissed;
		this.attachments = attachments;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Integer getEventId() {
		return eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getLocation() {
		return location;
	}

	public Double getTuition() {
		return tuition;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public Integer getFormatId() {
		return formatId;
	}

	public String getFormatName() {
		return formatName;
	}

	public String getFormatDescription() {
		return formatDescription;
	}

	public String getPassingGradeCutoff() {
		return passingGradeCutoff;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getEndTime() {
		return endTime;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Double getHoursMissed() {
		return hoursMissed;
	}
	
	public List<Attachment> getAttachments() {
		return attachments;
	}
	
}