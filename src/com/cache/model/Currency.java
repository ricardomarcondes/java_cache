package com.cache.model;

import java.util.Date;
/*
 * @author Ricardo Marcondes
 */
public class Currency extends BaseModel{

    private String symbol;

    public Currency() {}

    public Currency(int id, String code, String name, short status, Date lastModifiedDate, int modifyPersonNum, String symbol) {
        super(id, code, name, status, lastModifiedDate, modifyPersonNum);
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
