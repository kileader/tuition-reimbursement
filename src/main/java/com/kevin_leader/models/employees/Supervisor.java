package com.kevin_leader.models.employees;

import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.kevin_leader.models.Department;

public class Supervisor extends Employee {
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "supervisor")
	private Set<Employee> subordinates;

	// No-arg
	public Supervisor() {
		super();
	}
	
	// New employee
	public Supervisor(String firstName, String lastName, String email,
			String password, Supervisor supervisor, Department department,
			BenefitsCoordinator benefitsCoordinator) {
		super(firstName, lastName, email, password, supervisor, department,
				benefitsCoordinator);
	}
	
	// New employee without assignments
	public Supervisor(String firstName, String lastName, String email,
			String password) {
		super(firstName, lastName, email, password);
	}

	public Set<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	@Override
	public String toString() {
		return "Supervisor [subordinates=" + subordinates + ", id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", password=" + password + ", availableReimbursement="
				+ availableReimbursement + ", supervisor=" + supervisor + ", department=" + department
				+ ", benefitsCoordinator=" + benefitsCoordinator + ", terminationTime=" + terminationTime + "]";
	}
}
