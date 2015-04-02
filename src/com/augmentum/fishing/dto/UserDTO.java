package com.augmentum.fishing.dto;

import java.util.Date;

public class UserDTO {
    private Integer id;
    private String userName;
    private String password;
    private String icon;
    private Date createdTime;
    private Date updatedTime;
    private Boolean deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [id=");
        builder.append(id);
        builder.append(", userName=");
        builder.append(userName);
        builder.append(", password=");
        builder.append(password);
        builder.append(", icon=");
        builder.append(icon);
        builder.append(", createdTime=");
        builder.append(createdTime);
        builder.append(", updatedTime=");
        builder.append(updatedTime);
        builder.append(", deleted=");
        builder.append(deleted);
        builder.append("]");
        return builder.toString();
    }

}
