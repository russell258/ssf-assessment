package vttp2023.batch3.ssf.frontcontroller.model;

import java.io.Serializable;
import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Login implements Serializable{
    
    @NotNull(message = "Username cannot be empty")
    @Size(min=2, message = "Username must be at least 2 characters")
    private String username;

    @NotNull(message = "Password cannot be empty")
    @Size(min=2, message = "Password must be at least 2 characters")
    private String password;

    private int fAttempt = 0;

    private boolean isAuth=false;

    

    public int getfAttempt() {
        return fAttempt;
    }

    public void setfAttempt(int fAttempt) {
        this.fAttempt = fAttempt;
    }

    //increase attempt when fail
    public void increaseAttempt(){
        this.fAttempt++;
    }

    public Login(
            @NotNull(message = "Username cannot be empty") @Size(min = 2, message = "Username must be at least 2 characters") String username,
            @NotNull(message = "Password cannot be empty") @Size(min = 2, message = "Password must be at least 2 characters") String password) {
        this.username = username;
        this.password = password;
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

    //convert string to json object
    public static JsonObject toJSON(String json){
        JsonReader r = (JsonReader) Json.createReader(new StringReader(json));
        return r.readObject();
    }

    public JsonObject toJSON(){
        return Json.createObjectBuilder()
            .add("username",username)
            .add("password",password)
            .build();
    }

    public boolean isAuth() {
        return isAuth;
    }

    public void setAuth(boolean isAuth) {
        this.isAuth = isAuth;
    }

}
