package com.service.business.dao;

import com.service.domain.entity.Authenticate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("AuthenticateDao")
public interface AuthenticateDao extends CrudRepository<Authenticate, Integer>{
    
    @Query("select model from Authenticate model where model.userName=:userName")
    public Authenticate getAuthByUserName(@Param("userName") String userName);
}
