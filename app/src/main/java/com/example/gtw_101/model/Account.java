package com.example.gtw_101.model;

import java.io.Serializable;

public class Account implements Serializable {

    // Store account ID
    private int accountId;

    // Store account full name
    private String fullName;

    // Store account username
    private String username;

    // Store account password
    private String password;

    public Account() {
    }

    /**
     * Create constructor method Account()
     *
     * @param accountId store account id
     * @param fullName  store account full name
     * @param username  store account username
     * @param password  store account password
     */
    public Account(int accountId, String fullName, String username, String password) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
