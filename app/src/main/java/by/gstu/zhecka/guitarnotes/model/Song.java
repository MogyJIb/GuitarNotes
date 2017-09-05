package by.gstu.zhecka.guitarnotes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Zhecka on 8/23/2017.
 */

public final class Song implements Serializable {

    private UUID mId;

    private String mName;
    private String mAuthor;
    private StringBuilder mText;

    private SongDetail mSongDetail;


    public Song() {
        mId = UUID.randomUUID();
        mText = new StringBuilder();
        mSongDetail = new SongDetail();
    }

    public Song(String name, String author, String text) {
        this();

        mName = name;
        mAuthor = author;
        mText.append(text);
    }

    public Song(UUID id, String name, String author, String text,SongDetail songDetail) {
        mId = id;
        mName = name;
        mAuthor = author;
        mText = new StringBuilder(text);
        mSongDetail = songDetail;
    }

    public static Song newInstance(Song song){
        return new Song(song.getId(),
                song.getName(),
                song.getAuthor(),
                song.getText(),
                song.getSongDetail());
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

    public void setText(String text) {
        clearText();
        appendText(text);
    }

    public String getTittle(){
        return getAuthor()+" - "+getName();
    }

    public SongDetail getSongDetail() {
        return mSongDetail;
    }

    public void setSongDetail(SongDetail songDetail) {
        mSongDetail = songDetail;
    }

    @Override
    public String toString() {
        return getAuthor()+" - "+getName()+"\n"+getText();
    }
}
