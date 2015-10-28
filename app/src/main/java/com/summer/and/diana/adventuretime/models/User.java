package com.summer.and.diana.adventuretime.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

/**
 * Created by Guest on 10/28/15.
 */

@Table(name = "Users", id = "_id" )
public class User extends Model {

    @Column(name = "Username")
    private String mUsername;

    @Column(name = "Password")
    private String mPassword;

    @Column(name = "Email")
    private String mEmail;

    public User() {
        super();
    }

    public User(String username, String password, String email) {
        super();
        mUsername = username;
        mPassword = password;
        mEmail = email;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public static User find(String username) {
        return new Select()
                .from(User.class)
                .where("Username = ?", username)
                .executeSingle();
    }

}
