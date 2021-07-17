package com.kevin_leader.models.event;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.kevin_leader.models.reimbursement.Reimbursement;

@Entity
@Table(name = "events")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "event_name")
	private String eventName;
	
	@Column(name = "start_time")
	private long startTime;
	
	private String location;
	
	private double tuition;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "event_type_id")
	private EventType eventType;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "grading_format_id")
	private GradingFormat gradingFormat;
	
	@Column(name = "end_time")
	private long endTime;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Reimbursement> reimbursements;
	
	// No-arg
	public Event() {
		super();
	}
	
	// Id-less with unknown end time
	public Event(String eventName, long startTime, String location,
			double tuition, EventType eventType, GradingFormat gradingFormat) {
		super();
		this.eventName = eventName;
		this.startTime = startTime;
		this.location = location;
		this.tuition = tuition;
		this.eventType = eventType;
		this.gradingFormat = gradingFormat;
	}
	
	// Id-less with end time
	public Event(String eventName, long startTime, String location,
			double tuition, EventType eventType, GradingFormat gradingFormat,
			long endTime) {
		super();
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

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
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
		return "Event [id=" + id + ", eventName=" + eventName + ", startTime=" + startTime + ", location=" + location
				+ ", tuition=" + tuition + ", eventType=" + eventType + ", gradingFormat=" + gradingFormat
				+ ", endTime=" + endTime + ", reimbursements=" + reimbursements + "]";
	}
	
}
