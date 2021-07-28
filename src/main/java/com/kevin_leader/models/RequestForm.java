package com.kevin_leader.models;

import org.apache.log4j.Logger;

import com.google.gson.annotations.Expose;

/**
 * Class for reimbursement request info to be mapped from JSON
 * 
 * @author Kevin Leader
 */
public class RequestForm {

    private static final Logger log = Logger.getLogger(RequestForm.class);
    @Expose
    private Integer employeeId;
    @Expose
    private Integer eventId;
    @Expose
    private String eventName;
    @Expose
    private String startDate;
    @Expose
    private String startTime;
    @Expose
    private String location;
    @Expose
    private Double tuition;
    @Expose
    private Integer typeId;
    @Expose
    private Integer formatId;
    @Expose
    private String formatName;
    @Expose
    private String formatDescription;
    @Expose
    private String passingGradeCutoff;
    @Expose
    private String endDate;
    @Expose
    private String endTime;
    @Expose
    private String description;
    @Expose
    private Double hoursMissed;

    // No-args
    public RequestForm() {

    }

    // Event chosen
    public RequestForm(Integer employeeId, Integer eventId,
            String description, Double hoursMissed) {
        log.info("Create RequestForm with chosen event");
        this.employeeId = employeeId;
        this.eventId = eventId;
        this.description = description;
        this.hoursMissed = hoursMissed;
    }

    // Full constructor
    public RequestForm(Integer employeeId, Integer eventId,
            String eventName, String startDate, String startTime,
            String location, Double tuition, Integer typeId, Integer formatId,
            String formatName, String formatDescription,
            String passingGradeCutoff, String endDate, String endTime,
            String description, Double hoursMissed) {
        log.info("Create RequestForm full object");
        this.employeeId = employeeId;
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
    }

    public Integer getEmployeeId() {
        return employeeId;
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

    @Override
    public String toString() {
        return "RequestForm [employeeId=" + employeeId + ", eventId=" + eventId
                + ", eventName=" + eventName + ", startDate=" + startDate
                + ", startTime=" + startTime + ", location=" + location
                + ", tuition=" + tuition + ", typeId=" + typeId + ", formatId="
                + formatId + ", formatName=" + formatName
                + ", formatDescription=" + formatDescription
                + ", passingGradeCutoff=" + passingGradeCutoff + ", endDate="
                + endDate + ", endTime=" + endTime + ", description="
                + description + ", hoursMissed=" + hoursMissed + "]";
    }

}