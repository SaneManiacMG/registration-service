package com.reverside.registerationapp.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;
    @Column
    @NotNull
    private String email;
    @Column
    @NotNull
    private String password;
    @Column(name = "is_hr_verified")
    @NotNull
    private Boolean isHrVerified;
    @Column(name = "is_password_changed")
    @NotNull
    private Boolean isPasswordChanged;
    @Column(name = "account_created")
    @NotNull
    private Date accountCreated;
    @Column(name = "last_login")
    private Date lastLogin;

    public User(String email) {
        this.email = email;
        this.password = "DEFAULT";
        this.isHrVerified = false;
        this.isPasswordChanged = false;
        this.accountCreated = new Date();
        this.lastLogin = null;
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getHrVerified() {
        return isHrVerified;
    }

    public void setHrVerified(Boolean hrVerified) {
        isHrVerified = hrVerified;
    }

    public Boolean getPasswordChanged() {
        return isPasswordChanged;
    }

    public void setPasswordChanged(Boolean passwordChanged) {
        isPasswordChanged = passwordChanged;
    }

    public Date getAccountCreated() {
        return accountCreated;
    }

    public void setAccountCreated(Date accountCreated) {
        this.accountCreated = accountCreated;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isHrVerified=" + isHrVerified +
                ", isPasswordChanged=" + isPasswordChanged +
                ", accountCreated=" + accountCreated +
                ", lastLogin=" + lastLogin +
                '}';
    }
}
