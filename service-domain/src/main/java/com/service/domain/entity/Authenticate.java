package com.service.domain.entity;

import com.service.library.BaseMaster;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="M_Authenticate")
public class Authenticate extends BaseMaster{
    
    @Column(name="userName")
    private String userName;
    
    @Column(name="password")
    private String password;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="employeeFk")
    private Employee employee;
    
    @Column(name="employeeFk", insertable=false, updatable=false)
    private Integer employeeId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

   
}
