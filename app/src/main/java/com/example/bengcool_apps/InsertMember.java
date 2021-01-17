package com.example.bengcool_apps;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class InsertMember {
    private String Name;
    private String Email;
    private String Password1;
    private String Password2;

    public InsertMember() {
    }

    public InsertMember(String name, String email, String password1, String password2) {
        this.Name = name;
        this.Email = email;
        this.Password1 = password1;
        this.Password2 = password2;

    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword1() {
        return Password1;
    }

    public void setPassword1(String password1) {
        Password1 = password1;
    }

    public String getPassword2() {
        return Password2;
    }

    public void setPassword2(String password2) {
        Password2 = password2;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", Name);
        result.put("email", Email);
        result.put("password1", Password1);
        result.put("password2", Password2);

        return result;
    }
}
