package by.gstu.zhecka.guitarnotes.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Zhecka on 20.12.2017.
 */

public class Author implements Serializable {
    private UUID mId;
    private String mName;
    private String mDebutdate;
    private String mCountry;
    private String mGroupMembers;

    public Author(){
        mId = UUID.randomUUID();
        mName = "";
        mDebutdate = "";
        mCountry = "";
        mGroupMembers = "";
    }

    public Author(String name, String debutdate, String country, String groupMembers) {
        this();
        mName = name;
        mDebutdate = debutdate;
        mCountry = country;
        mGroupMembers = groupMembers;
    }

    public Author(UUID id, String name, String debutdate, String country, String groupMembers) {
       this(name,debutdate,country,groupMembers);
        mId = id;

    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDebutdate() {
        return mDebutdate;
    }

    public void setDebutdate(String debutdate) {
        mDebutdate = debutdate;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getGroupMembers() {
        return mGroupMembers;
    }

    public void setGroupMembers(String groupMembers) {
        mGroupMembers = groupMembers;
    }
}
