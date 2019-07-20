package org.fbc.ai_and_blockchain.utils;

import java.io.Serializable;
import java.math.BigInteger;

public class User implements Serializable {
    private String account;
    private String pubKey;
    private String verifyKey;
    private BigInteger userType;

    public User() {
    }

    public User(String account, String pubKey, String verifyKey, BigInteger userType) {
        this.account = account;
        this.pubKey = pubKey;
        this.verifyKey = verifyKey;
        this.userType = userType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public String getVerifyKey() {
        return verifyKey;
    }

    public void setVerifyKey(String verifyKey) {
        this.verifyKey = verifyKey;
    }

    public BigInteger getUserType() {
        return userType;
    }

    public void setUserType(BigInteger userType) {
        this.userType = userType;
    }
}
