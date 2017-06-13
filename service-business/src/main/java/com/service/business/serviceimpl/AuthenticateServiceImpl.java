package com.service.business.serviceimpl;

import com.service.business.dao.AuthenticateDao;
import com.service.business.service.AuthenticateService;
import com.service.domain.entity.Authenticate;
import com.service.library.CommonUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AuthenticateService")
public class AuthenticateServiceImpl implements AuthenticateService{
    
    @Autowired
    private AuthenticateDao authenticateDao;
    
    @Override
    public Map<String, Object> login(Authenticate model){
        Map<String, Object> result = new HashMap<>();
        try {
            if(CommonUtil.isNotNullOrEmpty(model)){
                Authenticate authenticate = authenticateDao.getAuthByUserName(model.getUserName());
                if(CommonUtil.isNotNullOrEmpty(authenticate)){
                    Boolean isValidPassword = false;//new PasswordUtil().isPasswordEqual(model.getPassword(), authenticate.getPassword());
                    if(model.getPassword().equalsIgnoreCase(authenticate.getPassword())){
                        isValidPassword = true;
                    }
                    if(isValidPassword){
                        result.put("userName", authenticate.getUserName());
                        result.put("password", authenticate.getPassword());
                    } else{
                        result = null;
                    }
                } else{
                    result = null;
                }
            }
        } catch (Exception e) {
            
        }
        return result;
    }
    
    @Override
    public Map<String, Object> saveLoginUser(Authenticate model){
        Map<String, Object> result = new HashMap<>();
        
        if(CommonUtil.isNotNullOrEmpty(model)){
            Authenticate authenticate = new Authenticate();
            
            if(CommonUtil.isNotNullOrEmpty(model.getUserName())){
                authenticate.setUserName(model.getUserName());
            }
            if(CommonUtil.isNotNullOrEmpty(model.getPassword())){
                authenticate.setPassword(model.getPassword());
            }
            if(CommonUtil.isNotNullOrEmpty(model.getEmployee())){
                authenticate.setEmployee(model.getEmployee());
            }
            if(CommonUtil.isNotNullOrEmpty(authenticate)){
                Authenticate authenticateResult = authenticateDao.save(authenticate);
                result.put("id", authenticateResult.getId());
            }
        }
        return result;
    }
    
    @Override
    public List<Map<String, Object>> getAll(){
        List<Map<String, Object>> listData = authenticateDao.getAllAuth();
        return listData;
    }
}
