package com.service.business.service;

import com.service.domain.entity.Employee;
import java.util.List;
import java.util.Map;

public interface EmployeeService {
    public Map<String, Object> saveEmployee(Employee model);
    public Employee getOneEmployee(Integer id);
    public List<Employee> getAllEmployee();
}
