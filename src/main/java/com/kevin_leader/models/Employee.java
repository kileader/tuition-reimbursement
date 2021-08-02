package com.kevin_leader.models;

import java.util.Set;

import javax.persistence.CascadeType;
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

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int id;

    @Column(name = "first_name")
    @Expose
    private String firstName;

    @Column(name = "last_name")
    @Expose
    private String lastName;

    @Expose
    private String email;

    @Expose
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "supervisor_emp_id")
    @Expose
    private Employee supervisor;

    @OneToMany(mappedBy = "supervisor")
    private Set<Employee> subordinates;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dep_head_emp_id")
    @Expose
    private Employee departmentHead;
    
    @OneToMany(mappedBy = "departmentHead")
    private Set<Employee> departmentEmployees;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ben_co_emp_id")
    private Employee benefitsCoordinator;

    @OneToMany(mappedBy = "benefitsCoordinator")
    private Set<Employee> benefitors;

    @Column(name = "termination_time")
    @Expose
    private Long terminationTime;

    @OneToMany(mappedBy = "reimbursee")
    private Set<Reimbursement> reimbursements;

    // No-args constructor
    public Employee() {
        super();
    }
    
    // Log In Constructor
    public Employee(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Id-less Constructor
    public Employee(String firstName, String lastName, String email,
            String password, Employee supervisor, Employee departmentHead,
            Employee benefitsCoordinator, Long terminationTime) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.supervisor = supervisor;
        this.departmentHead = departmentHead;
        this.benefitsCoordinator = benefitsCoordinator;
        this.terminationTime = terminationTime;
    }

    // Full Constructor
    public Employee(Integer id, String firstName, String lastName, String email,
            String password, Employee supervisor, Employee departmentHead,
            Employee benefitsCoordinator, Long terminationTime) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.supervisor = supervisor;
        this.departmentHead = departmentHead;
        this.benefitsCoordinator = benefitsCoordinator;
        this.terminationTime = terminationTime;
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

    public Employee getDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(Employee departmentHead) {
        this.departmentHead = departmentHead;
    }

    public Employee getBenefitsCoordinator() {
        return benefitsCoordinator;
    }

    public void setBenefitsCoordinator(Employee benefitsCoordinator) {
        this.benefitsCoordinator = benefitsCoordinator;
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
        String supervisorEmpId = (supervisor != null)
                ? String.valueOf(supervisor.getId())
                : "";
        String depHeadEmpId = (departmentHead != null)
                ? String.valueOf(departmentHead.getId())
                : "";
        String benCoEmpId = (benefitsCoordinator != null)
                ? String.valueOf(benefitsCoordinator.getId())
                : "";
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName="
                + lastName + ", email=" + email + ", password=" + password
                + ", supervisorEmpId=" + supervisorEmpId + ", depHeadEmpId="
                + depHeadEmpId + ", benCoEmpId=" + benCoEmpId
                + ", terminationTime=" + terminationTime + "]";
    }

}
