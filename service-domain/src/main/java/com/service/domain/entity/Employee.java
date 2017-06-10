package com.service.domain.entity;

import com.service.library.BaseMaster;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="M_Employee")
public class Employee extends BaseMaster {

    @Column(name="name")
    private String name;
    
    @Column(name="address")
    private String address;
    
    @Column(name="phone")
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
