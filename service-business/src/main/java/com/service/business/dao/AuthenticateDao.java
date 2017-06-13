package com.service.business.dao;

import com.service.domain.entity.Authenticate;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("AuthenticateDao")
public interface AuthenticateDao extends CrudRepository<Authenticate, Integer>{
    
    @Query("select model from Authenticate model where model.userName=:userName")
    public Authenticate getAuthByUserName(@Param("userName") String userName);
    
    @Query("select NEW Map("
            + "a.id as id,"
            + "a.userName as userName,"
            + "a.password as password,"
            + "b.id as employeeId,"
            + "b.name as name,"
            + "b.address as address,"
            + "b.phone as phone) "
            + "from Authenticate a "
            + "left join a.employee b ")
    public List<Map<String, Object>> getAllAuth();
}
