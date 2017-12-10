package by.gstu.zhecka.guitarnotes.model;

import java.util.UUID;

/**
 * Created by Zhecka on 08.12.2017.
 */

public class Account {

     private UUID mId;
    private String mLogin;
    private String mPassword;

    public Account(){
        mId = UUID.randomUUID();
        mLogin = "";
        mPassword = "";
    }

    public Account(UUID id, String login, String password) {
        mId = id;
        mLogin = login;
        this.mPassword = password;
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
}
