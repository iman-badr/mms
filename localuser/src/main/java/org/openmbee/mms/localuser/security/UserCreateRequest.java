package org.openmbee.mms.localuser.security;

import java.io.Serializable;

public class UserCreateRequest implements Serializable {

    private static final long serialVersionUID = -849270125785286560L;

    private String username;
    private String password;
    private boolean admin;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}
