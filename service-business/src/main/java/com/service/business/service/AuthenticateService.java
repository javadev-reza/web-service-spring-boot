package com.service.business.service;

import com.service.domain.entity.Authenticate;
import java.util.Map;

public interface AuthenticateService {
    public Map<String, Object> login(Authenticate model);
    public Map<String, Object> saveLoginUser(Authenticate model);
}
