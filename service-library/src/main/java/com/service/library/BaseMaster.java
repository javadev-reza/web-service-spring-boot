package com.service.library;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseMaster implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "statusEnabled", nullable=true)
    private Boolean statusEnabled;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the statusEnabled
     */
    public Boolean getStatusEnabled() {
        return statusEnabled;
    }

    /**
     * @param statusEnabled the statusEnabled to set
     */
    public void setStatusEnabled(Boolean statusEnabled) {
        this.statusEnabled = statusEnabled;
    }
    
}
