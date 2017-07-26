package com.jmgits.sample.github.mvp.user.model.view;

import com.google.gson.annotations.SerializedName;

/**
 * Created by javi-more-garc on 26/07/17.
 */

public class UserDetails {

    @SerializedName("login")
    private String username;

    private String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
