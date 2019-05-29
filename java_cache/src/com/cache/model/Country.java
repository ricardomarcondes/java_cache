package com.cache.model;

import java.util.Date;
/*
 * @author Ricardo Marcondes
 */
public class Country extends BaseModel {
    public Country() {
        super();
    }

    public Country(int id, String code, String name, short status, Date lastModifiedDate, int modifyPersonNum) {
        super(id, code, name, status, lastModifiedDate, modifyPersonNum);
    }
}
