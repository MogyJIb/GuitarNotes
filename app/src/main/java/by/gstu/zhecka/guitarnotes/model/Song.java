package by.gstu.zhecka.guitarnotes.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Zhecka on 8/23/2017.
 */

public final class Song implements Serializable {

    private UUID mId;
    private UUID mAuthorId;
    private String mName;
    private Author mAuthor;
    private StringBuilder mText;

    private SongDetail mSongDetail;


    public Song() {
        mId = UUID.randomUUID();
        mText = new StringBuilder();
        mSongDetail = new SongDetail();
    }

    public Song(String name, UUID authorId, String text) {
        this();

        mName = name;
        mAuthorId = authorId;
        mText.append(text);
    }

    public Song(UUID id, String name, UUID authorId, String text,SongDetail songDetail) {
        mId = id;
        mName = name;
        mAuthorId = authorId;
        mText = new StringBuilder(text);
        mSongDetail = songDetail;
    }

    public static Song newInstance(Song song){
        return new Song(song.getId(),
                song.getName(),
                song.getAuthorId(),
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

    public Author getAuthor() {
        return mAuthor;
    }

    public void setAuthor(Author author) {
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

    public UUID getAuthorId() {
        return mAuthorId;
    }

    public void setAuthorId(UUID authorId) {
        mAuthorId = authorId;
    }
}
