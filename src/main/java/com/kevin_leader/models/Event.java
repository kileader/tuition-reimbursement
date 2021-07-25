package com.kevin_leader.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "events")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose private int id;
	
	@Column(name = "event_name")
	@Expose private String eventName;
	
	@Column(name = "start_time")
	@Expose private long startTime;
	
	@Expose private String location;
	
	@Expose private double tuition;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "event_type_id")
	@Expose private EventType eventType;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "grading_format_id")
	@Expose private GradingFormat gradingFormat;
	
	@Column(name = "end_time")
	@Expose private Long endTime;
	
	@OneToMany
	private transient Set<Reimbursement> reimbursements;
	
	// No-arg
	public Event() {
		super();
	}
	
	// Id-less with end time
	public Event(String eventName, long startTime, String location,
			double tuition, EventType eventType, GradingFormat gradingFormat,
			Long endTime) {
		super();
		this.eventName = eventName;
		this.startTime = startTime;
		this.location = location;
		this.tuition = tuition;
		this.eventType = eventType;
		this.gradingFormat = gradingFormat;
		this.endTime = endTime;
	}

	// Full constructor
	public Event(int id, String eventName, long startTime, String location,
			double tuition, EventType eventType, GradingFormat gradingFormat,
			Long endTime) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.startTime = startTime;
		this.location = location;
		this.tuition = tuition;
		this.eventType = eventType;
		this.gradingFormat = gradingFormat;
		this.endTime = endTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getTuition() {
		return tuition;
	}

	public void setTuition(double tuition) {
		this.tuition = tuition;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public GradingFormat getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(GradingFormat gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Set<Reimbursement> getReimbursements() {
		return reimbursements;
	}

	public void setReimbursements(Set<Reimbursement> reimbursements) {
		this.reimbursements = reimbursements;
	}

	@Override
	public String toString() {
		String gradingFormatId = (gradingFormat != null) ?
				String.valueOf(gradingFormat.getId()) : "";
		String eventTypeId = (eventType != null) ?
				String.valueOf(eventType.getId()) : "";
		return "Event [id=" + id + ", eventName=" + eventName + ", startTime=" + startTime + ", location=" + location
				+ ", tuition=" + tuition + ", eventTypeId=" + eventTypeId + ", gradingFormatId=" + gradingFormatId
				+ ", endTime=" + endTime + "]";
	}
	
}
