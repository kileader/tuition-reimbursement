package com.kevin_leader.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.kevin_leader.models.employees.Employee;

@Entity
@Table(name = "departments")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dep_head_emp_id")
	private Employee departmentHead;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	private Set<Employee> employeesInDepartment;

	// No-args constructor
	public Department() {
		super();
	}
	
	// Constructor for a new department with department head filled
	public Department(String name, String description, Employee departmentHead)
	{
		super();
		this.name = name;
		this.description = description;
		this.departmentHead = departmentHead;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee getDepartmentHead() {
		return departmentHead;
	}

	public void setDepartmentHead(Employee departmentHead) {
		this.departmentHead = departmentHead;
	}

	public Set<Employee> getEmployeesInDepartment() {
		return employeesInDepartment;
	}

	public void setEmployeesInDepartment(Set<Employee> employeesInDepartment) {
		this.employeesInDepartment = employeesInDepartment;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", description=" + description + ", departmentHead="
				+ departmentHead + ", employeesInDepartment=" + employeesInDepartment + "]";
	}
}