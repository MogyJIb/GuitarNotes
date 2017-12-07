package by.gstu.zhecka.guitarnotes.model;

import org.apache.commons.lang.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhecka on 9/5/2017.
 */

public class SongDetail implements Serializable {
    private String mChord;
    private Strum mStrum;

    public SongDetail() {
        mStrum = new Strum();
    }

    public SongDetail(Strum strum, String chord) {
        this();
        mStrum.addAll(strum.getAsList());
        mChord = chord;
    }


    public static final class Strum implements Serializable {
        public final static int REST = 0;
        public final static int MUTE = 1;
        public final static int UPSTROCKE = 2;
        public final static int DOWNSTROCKE = 3;

        private List<Integer> mStrum;

        public Strum() {
            mStrum = new ArrayList<>();
        }

        public Strum(List<Integer> strum) {
            this();
            mStrum.addAll(strum);
        }

        public void add(int strokeId) {
            if (checkStroke(strokeId))
                mStrum.add(strokeId);
            else throw new IllegalArgumentException("Invalid.");
        }

        public void deleteLast() {
            if (mStrum.size() > 0)
                mStrum.remove(mStrum.get(mStrum.size() - 1));
            else throw new IndexOutOfBoundsException("List is empty.");
        }

        public int size() {
            return mStrum.size();
        }

        public boolean isEmpty() {
            return mStrum.isEmpty();
        }

        public void addAll(List<Integer> strum) {
            mStrum.addAll(strum);
        }

        public List<Integer> getAsList() {
            return mStrum;
        }

        private boolean checkStroke(int strokeId) {
            switch (strokeId) {
                case REST:
                    return true;
                case MUTE:
                    return true;
                case UPSTROCKE:
                    return true;
                case DOWNSTROCKE:
                    return true;
                default:
                    return false;
            }
        }
    }

    public String getChord() {
        return mChord;
    }

    public void setChord(String chord) {
        mChord = chord;
    }

    public Strum getStrum() {
        return mStrum;
    }

    public void setStrum(Strum strum) {
        mStrum = strum;
    }

    public static SongDetail deserialize(byte[] inform) {
        SongDetail songDetail = (SongDetail) SerializationUtils.deserialize(inform);
        return songDetail;
    }

    public static byte[] serialize(SongDetail songDetail) {
        byte[] data = SerializationUtils.serialize(songDetail);
        return data;
    }
}
