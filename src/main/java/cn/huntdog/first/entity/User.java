package cn.huntdog.first.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by JonDai on 2016/7/6.
 */
@Table
@Entity
public class User extends IdEntity{

    private String userName;
    private String password;

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
}
