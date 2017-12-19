package by.gstu.zhecka.guitarnotes.model;

import java.util.UUID;

/**
 * Created by Zhecka on 08.12.2017.
 */

public class Account {

     private UUID mId;
     private String mName;
    private String mLogin;
    private String mPassword;

    public Account(){
        mId = UUID.randomUUID();
        mLogin = "";
        mPassword = "";
        mName = "";
    }

    public Account(String name, String login, String password) {
        this();
        mName = name;
        mLogin = login;
        this.mPassword = password;
    }

    public Account(UUID id, String name, String login, String password) {
        this(name,login,password);
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        mLogin = login;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
