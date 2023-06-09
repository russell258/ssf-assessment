package vttp2023.batch3.ssf.frontcontroller.model;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Login implements Serializable{
    
    @NotNull(message = "Username cannot be empty")
    @Size(min=2, message = "Username must be at least 2 characters")
    private String username;

    @NotNull(message = "Password cannot be empty")
    @Size(min=2, message = "Password must be at least 2 characters")
    private String password;

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

    //additional method here below

}
