package com.service.business.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.business.dao.EmployeeDao;
import com.service.business.service.EmployeeService;
import com.service.domain.entity.Employee;
import com.service.library.CommonUtil;
import java.util.HashMap;
import java.util.Map;

@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService{
    
    @Autowired
    private EmployeeDao employeeDao;
    
    @Override
    public Map<String, Object> saveEmployee(Employee model){
        Map<String, Object> result = new HashMap<>();
        
        if(CommonUtil.isNotNullOrEmpty(model)){
            Employee employee = new Employee();
            
            if(CommonUtil.isNotNullOrEmpty(model.getName())){
                employee.setName(model.getName());
            }
            if(CommonUtil.isNotNullOrEmpty(model.getAddress())){
                employee.setAddress(model.getAddress());
            }
            if(CommonUtil.isNotNullOrEmpty(model.getPhone())){
                employee.setPhone(model.getPhone());
            }
            if(CommonUtil.isNotNullOrEmpty(employee)){
                Employee employeeResult = employeeDao.save(employee);
                result.put("id", employeeResult.getId());
            }
        }
        return result;
    }
    
    @Override
    public Employee getOneEmployee(Integer id){
        return employeeDao.findOne(id);
    }
    
    @Override
    public List<Employee> getAllEmployee(){
        return (List<Employee>) employeeDao.findAll();
    }
}
