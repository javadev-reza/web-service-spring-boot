package com.parking.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.service.business.service.EmployeeService;
import com.service.domain.entity.Employee;
import com.service.library.CommonUtil;
import com.service.library.RestUtil;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @RequestMapping(value = "/save-employee", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveEmployee(@Valid @RequestBody Employee model, HttpServletRequest request) {
        try {
            Map<String, String> message = new HashMap<>();
            Map<String, Object> result = employeeService.saveEmployee(model);
            if(CommonUtil.isNullOrEmpty(result)){
                message.put("ERROR_MESSAGE", "gagal input");
                return RestUtil.getJsonResponse(result, HttpStatus.BAD_REQUEST);
            } else{
                message.put("SUCCESS_MESSAGE", "input berhasil");
                return RestUtil.getJsonResponse(result, HttpStatus.CREATED, message);
            }
	}catch (Exception e){
            LOGGER.error("Got exception {} when saveEmployee", e.getMessage());
            return RestUtil.getJsonHttptatus(HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
    
    @RequestMapping(value = "/get-one-employee", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getOneEmployee(@RequestParam(value = "id", required = true) Integer id, HttpServletRequest request) {
        try {
            Employee result = employeeService.getOneEmployee(id);
            if(CommonUtil.isNotNullOrEmpty(result)){
                return RestUtil.getJsonResponse(result, HttpStatus.OK);
            } else{
                return RestUtil.getJsonResponse(result, HttpStatus.NOT_FOUND);
            }
	}catch (RuntimeException e){
            LOGGER.error("Got exception {} when getOneEmployee", e.getMessage());
            return RestUtil.getJsonHttptatus(HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }             
    
    @RequestMapping(value = "/get-all-employee", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllEmployee(HttpServletRequest request) {
        try {
            List<Employee> result = employeeService.getAllEmployee();
            if(CommonUtil.isNotNullOrEmpty(result)){
                return RestUtil.getJsonResponse(result, HttpStatus.OK);
            } else{
                return RestUtil.getJsonResponse(result, HttpStatus.NOT_FOUND);
            }
	}catch (RuntimeException e){
            LOGGER.error("Got exception {} when getAllEmployee", e.getMessage());
            return RestUtil.getJsonHttptatus(HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
}
