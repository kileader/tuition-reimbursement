package com.kevin_leader.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "reimbursements")
public class Reimbursement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    @Expose
    private Employee reimbursee;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    @Expose
    private Event event;

    @Expose
    private String description;

    @Column(name = "submission_time")
    @Expose
    private long submissionTime;

    @Column(name = "hours_missed")
    @Expose
    private Double hoursMissed;

    @Column(name = "final_grade")
    @Expose
    private String finalGrade;

    @Column(name = "actual_claim")
    @Expose
    private Double actualClaim;

    public Reimbursement() {
        super();
    }

    public Reimbursement(Employee reimbursee, Event event, String description,
            long submissionTime, Double hoursMissed, String finalGrade,
            Double actualClaim) {
        super();
        this.reimbursee = reimbursee;
        this.event = event;
        this.description = description;
        this.submissionTime = submissionTime;
        this.hoursMissed = hoursMissed;
        this.finalGrade = finalGrade;
        this.actualClaim = actualClaim;
    }

    public Reimbursement(int id, Employee reimbursee, Event event,
            String description, long submissionTime, Double hoursMissed,
            String finalGrade, Double actualClaim) {
        super();
        this.id = id;
        this.reimbursee = reimbursee;
        this.event = event;
        this.description = description;
        this.submissionTime = submissionTime;
        this.hoursMissed = hoursMissed;
        this.finalGrade = finalGrade;
        this.actualClaim = actualClaim;
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

    public Double getHoursMissed() {
        return hoursMissed;
    }

    public void setHoursMissed(Double hoursMissed) {
        this.hoursMissed = hoursMissed;
    }

    public String getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade;
    }

    public Double getActualClaim() {
        return actualClaim;
    }

    public void setActualClaim(Double actualClaim) {
        this.actualClaim = actualClaim;
    }

    @Override
    public String toString() {
        String employeeId = (reimbursee != null)
                ? String.valueOf(reimbursee.getId())
                : "";
        String eventId = (event != null) ? String.valueOf(event.getId()) : "";
        return "Reimbursement [id=" + id + ", employeeId=" + employeeId
                + ", eventId=" + eventId + ", description=" + description
                + ", submissionTime=" + submissionTime + ", hoursMissed="
                + hoursMissed + ", finalGrade=" + finalGrade + ", actualClaim="
                + actualClaim + "]";
    }

}
