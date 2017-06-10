package com.service.library;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class BaseTransaction implements Serializable{
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)", unique = true)
    private String noRec;
    
    @Column(name = "statusEnabled", nullable=true)
    private Boolean statusEnabled;;

    /**
     * @return the noRec
     */
    public String getNoRec() {
        return noRec;
    }

    /**
     * @param noRec the noRec to set
     */
    public void setNoRec(String noRec) {
        this.noRec = noRec;
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
