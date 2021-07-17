package com.kevin_leader.models.reimbursement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kevin_leader.models.Employee;
import com.kevin_leader.models.event.Event;

@Entity
@Table(name = "reimbursements")
public class Reimbursement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id")
	private Employee reimbursee;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "event_id")
	private Event event;
	
	private String description;
	
	@Column(name = "submission_time")
	private long submissionTime;
	
	@Column(name = "hours_missed")
	private double hoursMissed;
	
	@Column(name = "final_grade")
	private String finalGrade;
	
	@Column(name = "actual_claim")
	private double actualClaim;

	public Reimbursement() {
		super();
	}

	public Reimbursement(Employee reimbursee, Event event, String description,
			long submissionTime) {
		super();
		this.reimbursee = reimbursee;
		this.event = event;
		this.description = description;
		this.submissionTime = submissionTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getReimbursee() {
		return reimbursee;
	}

	public void setReimbursee(Employee reimbursee) {
		this.reimbursee = reimbursee;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(long submissionTime) {
		this.submissionTime = submissionTime;
	}

	public double getHoursMissed() {
		return hoursMissed;
	}

	public void setHoursMissed(double hoursMissed) {
		this.hoursMissed = hoursMissed;
	}

	public String getFinalGrade() {
		return finalGrade;
	}

	public void setFinalGrade(String finalGrade) {
		this.finalGrade = finalGrade;
	}

	public double getActualClaim() {
		return actualClaim;
	}

	public void setActualClaim(double actualClaim) {
		this.actualClaim = actualClaim;
	}

	@Override
	public String toString() {
		return "Reimbursements [id=" + id + ", reimbursee=" + reimbursee + ", event=" + event + ", description="
				+ description + ", submissionTime=" + submissionTime + ", hoursMissed=" + hoursMissed + ", finalGrade="
				+ finalGrade + ", actualClaim=" + actualClaim + "]";
	}
	
}
