package com.kevin_leader.models.employees;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kevin_leader.models.Department;

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
	
	@Column(name = "available_reimbursement")
	protected double availableReimbursement;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "supervisor_id")
	protected Supervisor supervisor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id", foreignKey =
			@ForeignKey(name = "employees_department_id_fkey"))
	protected Department department;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ben_co_emp_id")
	protected BenefitsCoordinator benefitsCoordinator;
	
	@Column(name = "termination_time")
	protected long terminationTime;

	// No-args constructor
	public Employee() {
		super();
	}
	
	// Constructor for a new employee all set up to receive a reimbursement
	public Employee(String firstName, String lastName, String email,
			String password, Supervisor supervisor, Department department,
			BenefitsCoordinator benefitsCoordinator) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.availableReimbursement = 1000;
		this.supervisor = supervisor;
		this.department = department;
		this.benefitsCoordinator = benefitsCoordinator;
	}
	
	// Constructor for a new employee not assigned with anyone
	public Employee(String firstName, String lastName, String email,
			String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.availableReimbursement = 1000;
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

	public double getAvailableReimbursement() {
		return availableReimbursement;
	}

	public void setAvailableReimbursement(double availableReimbursement) {
		this.availableReimbursement = availableReimbursement;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public BenefitsCoordinator getBenefitsCoordinator() {
		return benefitsCoordinator;
	}

	public void setBenefitsCoordinator(
			BenefitsCoordinator benefitsCoordinator) {
		this.benefitsCoordinator = benefitsCoordinator;
	}

	public long getTerminationTime() {
		return terminationTime;
	}

	public void setTerminationTime(long terminationTime) {
		this.terminationTime = terminationTime;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", availableReimbursement=" + availableReimbursement + ", supervisor="
				+ supervisor + ", department=" + department + ", benefitsCoordinator=" + benefitsCoordinator
				+ ", terminationTime=" + terminationTime + "]";
	}
	
}
