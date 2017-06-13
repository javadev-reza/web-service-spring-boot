package com.parking.system.controller;

import com.service.business.service.AuthenticateService;
import com.service.domain.entity.Authenticate;
import com.service.domain.entity.Employee;
import com.service.library.CommonUtil;
import com.service.library.PasswordUtil;
import com.service.library.RestUtil;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticateController {

    @Autowired
    private AuthenticateService authenticateService;
    
    private final Logger LOGGER = LoggerFactory.getLogger(AuthenticateController.class);
    
    @RequestMapping(value = "/login-user", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity loginUser(@Valid @RequestBody Authenticate model, HttpServletRequest request) {
        ResponseEntity responseEntity = null;
        try {
            Map<String, String> message = new HashMap<>();
            if(CommonUtil.isNullOrEmpty(model.getUserName()) || CommonUtil.isNullOrEmpty(model.getPassword())) {
                message.put("ERROR_MESSAGE", "Username or Password is empty");
                responseEntity = RestUtil.getJsonResponse(null, HttpStatus.BAD_REQUEST, message);
            } else{
                Map<String, Object> result = authenticateService.login(model);
                if(CommonUtil.isNullOrEmpty(result)){
                    message.put("ERROR_MESSAGE", "Invalid Username or Password");
                    responseEntity = RestUtil.getJsonResponse(result, HttpStatus.NOT_ACCEPTABLE, message);
                }else if(CommonUtil.isNotNullOrEmpty(result)){
                    PasswordUtil token = new PasswordUtil();
                    message.put("X-AUTH-TOKEN", token.encryptPassword((String)result.get("userName")+(String)result.get("password")));
                    responseEntity = RestUtil.getJsonResponse(result, HttpStatus.OK, message);
                }
            }
	}catch (UnsupportedEncodingException | NoSuchAlgorithmException e){
            LOGGER.error("Got exception {} when loginUser", e.getMessage());
            return RestUtil.getJsonHttptatus(HttpStatus.UNAUTHORIZED);
	}
        return responseEntity;
    }
    
    @RequestMapping(value = "/save-login-user", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveLoginUser(@Valid @RequestBody Authenticate model, HttpServletRequest request) {
        try {
            Map<String, String> message = new HashMap<>();
            Map<String, Object> result = authenticateService.saveLoginUser(model);
            if(CommonUtil.isNullOrEmpty(result)){
                message.put("ERROR_MESSAGE", "gagal input");
                return RestUtil.getJsonResponse(result, HttpStatus.BAD_REQUEST, message);
            } else{
                message.put("SUCCESS_MESSAGE", "input berhasil");
                return RestUtil.getJsonResponse(result, HttpStatus.CREATED, message);
            }
	}catch (Exception e){
            LOGGER.error("Got exception {} when saveLoginUser", e.getMessage());
            return RestUtil.getJsonHttptatus(HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
    
    @RequestMapping(value = "/get-all-user", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllLoginUser(HttpServletRequest request) {
        try {
            List<Map<String, Object>> result = authenticateService.getAll();
            if(CommonUtil.isNotNullOrEmpty(result)){
                return RestUtil.getJsonResponse(result, HttpStatus.OK);
            } else{
                return RestUtil.getJsonResponse(result, HttpStatus.NOT_FOUND);
            }
	}catch (RuntimeException e){
            LOGGER.error("Got exception {} when getAllLoginUser", e.getMessage());
            return RestUtil.getJsonHttptatus(HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
}
