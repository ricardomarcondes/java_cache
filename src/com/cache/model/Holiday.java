package com.cache.model;

import java.util.Date;
/*
 * @author Ricardo Marcondes
 */
public class Holiday extends BaseModel{

    private Date date;

    public Holiday(int id, String code, String name, short status, Date lastModifiedDate, int modifyPersonNum, Date date) {
        super(id, code, name, status, lastModifiedDate, modifyPersonNum);
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
