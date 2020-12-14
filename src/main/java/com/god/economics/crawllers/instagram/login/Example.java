package com.god.economics.crawllers.instagram.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("user")
    @Expose
    private Boolean user;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("authenticated")
    @Expose
    private Boolean authenticated;
    @SerializedName("oneTapPrompt")
    @Expose
    private Boolean oneTapPrompt;
    @SerializedName("status")
    @Expose
    private String status;

    public Boolean getUser() {
        return user;
    }

    public void setUser(Boolean user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Boolean getOneTapPrompt() {
        return oneTapPrompt;
    }

    public void setOneTapPrompt(Boolean oneTapPrompt) {
        this.oneTapPrompt = oneTapPrompt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}