package com.kevin_leader.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "grading_formats")
public class GradingFormat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int id;

    @Column(name = "format_name")
    @Expose
    private String formatName;

    @Expose
    private String description;

    @Column(name = "passing_grade_cutoff")
    @Expose
    private String passingGradeCutoff;

    @OneToMany(mappedBy = "gradingFormat")
    private transient Set<Event> eventsWithFormat;

    public GradingFormat() {
        super();
    }

    public GradingFormat(String formatName, String description,
            String passingGradeCutoff) {
        super();
        this.formatName = formatName;
        this.description = description;
        this.passingGradeCutoff = passingGradeCutoff;
    }

    public GradingFormat(int id, String formatName, String description,
            String passingGradeCutoff) {
        super();
        this.id = id;
        this.formatName = formatName;
        this.description = description;
        this.passingGradeCutoff = passingGradeCutoff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassingGradeCutoff() {
        return passingGradeCutoff;
    }

    public void setPassingGradeCutoff(String passingGradeCutoff) {
        this.passingGradeCutoff = passingGradeCutoff;
    }

    public Set<Event> getEventsWithFormat() {
        return eventsWithFormat;
    }

    public void setEventsWithFormat(Set<Event> eventsWithFormat) {
        this.eventsWithFormat = eventsWithFormat;
    }

    @Override
    public String toString() {
        return "GradingFormat [id=" + id + ", formatName=" + formatName
                + ", description=" + description + ", passingGradeCutoff="
                + passingGradeCutoff + "]";
    }
}
