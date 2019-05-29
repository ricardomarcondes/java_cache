package com.cache.model;

import java.util.Date;
/*
 * @author Ricardo Marcondes
 */
public abstract class BaseModel implements Cacheable{

    private int Id;
    private String code;
    private String name;
    private short status;
    private Date lastModifiedDate;
    private int modifyPersonNum;

    public BaseModel() {}

    public BaseModel(int id, String code, String name, short status, Date lastModifiedDate, int modifyPersonNum) {
        Id = id;
        this.code = code;
        this.name = name;
        this.status = status;
        this.lastModifiedDate = lastModifiedDate;
        this.modifyPersonNum = modifyPersonNum;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getModifyPersonNum() {
        return modifyPersonNum;
    }

    public void setModifyPersonNum(int modifyPersonNum) {
        this.modifyPersonNum = modifyPersonNum;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "Id=" + Id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", lastModifiedDate=" + lastModifiedDate +
                ", modifyPersonNum=" + modifyPersonNum +
                '}';
    }
}
