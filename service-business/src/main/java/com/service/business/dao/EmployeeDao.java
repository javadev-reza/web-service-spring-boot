package com.service.business.dao;

import com.service.domain.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("EmployeeDao")
public interface EmployeeDao extends CrudRepository<Employee, Integer>{
   
}
