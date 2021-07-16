package com.kevin_leader.models.employees;

import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.kevin_leader.models.Department;

public class BenefitsCoordinator extends Employee {
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "benefitsCoordinator")
	private Set<Employee> benefitsRecipients;
	
	// No-arg
	public BenefitsCoordinator() {
		super();
	}
	
	// New employee
	public BenefitsCoordinator(String firstName, String lastName, String email,
			String password, Supervisor supervisor, Department department,
			BenefitsCoordinator benefitsCoordinator) {
		super(firstName, lastName, email, password, supervisor, department,
				benefitsCoordinator);
	}
	
	// New employee without assignments
	public BenefitsCoordinator(String firstName, String lastName, String email,
			String password) {
		super(firstName, lastName, email, password);
	}

	public Set<Employee> getBenefitsRecipients() {
		return benefitsRecipients;
	}

	public void setBenefitsRecipients(Set<Employee> benefitsRecipients) {
		this.benefitsRecipients = benefitsRecipients;
	}
	
	
	
}
