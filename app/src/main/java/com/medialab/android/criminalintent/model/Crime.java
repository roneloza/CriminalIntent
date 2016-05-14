package com.medialab.android.criminalintent.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by roneloza on 5/9/16.
 */
public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime() {

        this.mId = UUID.randomUUID();
        this.mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String pTitle) {
        mTitle = pTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date pDate) {
        mDate = pDate;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean pSolved) {
        mSolved = pSolved;
    }

    public String formatDate() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMM dd, yyyy.");
        String dateFormatString = dateFormat.format(this.getDate());

        return dateFormatString;
    }
}
