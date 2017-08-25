package by.gstu.zhecka.guitarnotes.model;

import java.util.UUID;

/**
 * Created by Zhecka on 8/23/2017.
 */

public final class Song {

    private UUID mId;

    private String mName;
    private String mAuthor;
    private StringBuilder mText;

    public Song(String name, String author, String text) {
        this();

        mName = name;
        mAuthor = author;
        mText.append(text);
    }

   public Song() {
        mId = UUID.randomUUID();
        mText = new StringBuilder();
    }

    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getText() {
        return mText.toString();
    }

    public void clearText() {
        mText = new StringBuilder();
    }

    public void appendText(String text){
        mText.append(text);
    }

    public String getTittle(){
        return getAuthor()+" - "+getName();
    }

    @Override
    public String toString() {
        return getAuthor()+" - "+getName()+"\n"+getText();
    }
}
