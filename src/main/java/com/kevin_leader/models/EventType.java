package com.kevin_leader.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "event_types")
public class EventType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "type_name")
	private String typeName;
	
	@Column(name = "percent_coverage")
	private double percentCoverage;
	
	@OneToMany(mappedBy = "eventType")
	private Set<Event> eventsWithType;

	public EventType() {
		super();
	}

	public EventType(String typeName, double percentCoverage) {
		super();
		this.typeName = typeName;
		this.percentCoverage = percentCoverage;
	}

	public EventType(int id, String typeName, double percentCoverage) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.percentCoverage = percentCoverage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public double getPercentCoverage() {
		return percentCoverage;
	}

	public void setPercentCoverage(double percentCoverage) {
		this.percentCoverage = percentCoverage;
	}

	public Set<Event> getEventsWithType() {
		return eventsWithType;
	}

	public void setEventsWithType(Set<Event> eventsWithType) {
		this.eventsWithType = eventsWithType;
	}

	@Override
	public String toString() {
		return "EventType [id=" + id + ", typeName=" + typeName +
				", percentCoverage=" + percentCoverage + "]";
	}
	
}
