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
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;

    @Column(name = "supervisor_emp_id")
    private Integer supervisorEmpId;

    @Column(name = "dep_head_emp_id")
    private Integer depHeadEmpId;
    
    @Column(name = "ben_co_emp_id")
    private Integer benCoEmpId;

    @Column(name = "termination_time")
    private Long terminationTime;

    @OneToMany(mappedBy = "reimbursee")
    private transient Set<Reimbursement> reimbursements;

    // No-args constructor
    public Employee() {
        super();
    }
    
    // Log In Constructor
    public Employee(String email, String password) {
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

    public Integer getSupervisorEmpId() {
        return supervisorEmpId;
    }

    public void setSupervisorEmpId(Integer supervisorEmpId) {
        this.supervisorEmpId = supervisorEmpId;
    }

    public Integer getDepHeadEmpId() {
        return depHeadEmpId;
    }

    public void setDepHeadEmpId(Integer depHeadEmpId) {
        this.depHeadEmpId = depHeadEmpId;
    }

    public Integer getBenCoEmpId() {
        return benCoEmpId;
    }

    public void setBenCoEmpId(Integer benCoEmpId) {
        this.benCoEmpId = benCoEmpId;
    }

    public Long getTerminationTime() {
        return terminationTime;
    }

    public void setTerminationTime(Long terminationTime) {
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
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName="
                + lastName + ", email=" + email + ", password=" + password
                + ", supervisorEmpId=" + supervisorEmpId + ", depHeadEmpId="
                + depHeadEmpId + ", benCoEmpId=" + benCoEmpId
                + ", terminationTime=" + terminationTime + "]";
    }

}
