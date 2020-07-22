package com.apg.payment;

/**
 * Object to Store details about credentials and user Token
 */
public class OauthUser {

    String name;
    String email;
    String phoneNumber;
    String EPUID;
    String accountNumber;
    String subscriberSubType;
    String subscriberType;
    String PUID;
    String access_token;
    String token_type;
    String refresh_token;
    int expires_in;
    String id_tokenl;
    String oneTimeToken;

    public OauthUser() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEPUID() {
        return EPUID;
    }

    public void setEPUID(String EPUID) {
        this.EPUID = EPUID;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSubscriberSubType() {
        return subscriberSubType;
    }

    public void setSubscriberSubType(String subscriberSubType) {
        this.subscriberSubType = subscriberSubType;
    }

    public String getSubscriberType() {
        return subscriberType;
    }

    public void setSubscriberType(String subscriberType) {
        this.subscriberType = subscriberType;
    }

    public String getPUID() {
        return PUID;
    }

    public void setPUID(String PUID) {
        this.PUID = PUID;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getId_tokenl() {
        return id_tokenl;
    }

    public void setId_tokenl(String id_tokenl) {
        this.id_tokenl = id_tokenl;
    }

    public String getOneTimeToken() {
        return oneTimeToken;
    }

    public void setOneTimeToken(String oneTimeToken) {
        this.oneTimeToken = oneTimeToken;
    }
}
