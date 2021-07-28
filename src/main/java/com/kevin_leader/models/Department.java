package com.kevin_leader.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int id;

    @Expose
    private String name;

    @Expose
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dep_head_emp_id")
    @Expose
    private Employee departmentHead;

    @OneToMany(mappedBy = "department")
    private transient Set<Employee> employeesInDepartment;

    // No-args constructor
    public Department() {
        super();
    }

    // Id-less constructor
    public Department(String name, String description,
            Employee departmentHead) {
        super();
        this.name = name;
        this.description = description;
        this.departmentHead = departmentHead;
    }

    // Full constructor
    public Department(int id, String name, String description,
            Employee departmentHead) {
        super();
        this.id = id;
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
        String depHeadEmpId = (departmentHead != null)
                ? String.valueOf(departmentHead.getId())
                : "";
        return "Department [id=" + id + ", name=" + name + ", description="
                + description + ", depHeadEmpId=" + depHeadEmpId + "]";
    }
}