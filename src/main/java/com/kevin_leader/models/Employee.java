package com.kevin_leader.models;

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
@Table(name = "employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	
	@Column(name = "first_name")
	protected String firstName;
	
	@Column(name = "last_name")
	protected String lastName;
	
	protected String email;
	
	protected String password;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "supervisor_emp_id")
	protected Employee supervisor;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "supervisor")
	private Set<Employee> subordinates;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id")
	protected Department department;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ben_co_emp_id")
	protected Employee benefitsCoordinator;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "benefitsCoordinator")
	private Set<Employee> benefitors;
	
	@Column(name = "termination_time")
	protected long terminationTime;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "reimbursee")
	protected Set<Reimbursement> reimbursements;

	// No-args constructor
	public Employee() {
		super();
	}
	
	// Constructor for a new employee not assigned with anyone
	public Employee(String firstName, String lastName, String email,
			String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Employee supervisor) {
		this.supervisor = supervisor;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee getBenefitsCoordinator() {
		return benefitsCoordinator;
	}

	public void setBenefitsCoordinator(
			Employee benefitsCoordinator) {
		this.benefitsCoordinator = benefitsCoordinator;
	}

	public long getTerminationTime() {
		return terminationTime;
	}

	public void setTerminationTime(long terminationTime) {
		this.terminationTime = terminationTime;
	}

	public Set<Reimbursement> getReimbursements() {
		return reimbursements;
	}

	public void setReimbursements(Set<Reimbursement> reimbursements) {
		this.reimbursements = reimbursements;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", supervisor=" + supervisor + ", department=" + department
				+ ", benefitsCoordinator=" + benefitsCoordinator + ", terminationTime=" + terminationTime
				+ ", reimbursements=" + reimbursements + "]";
	}
	
}
