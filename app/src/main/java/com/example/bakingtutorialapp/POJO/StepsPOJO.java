package com.example.bakingtutorialapp.POJO;

import android.os.Parcel;
import android.os.Parcelable;

public class StepsPOJO implements Parcelable
{
    private  String mId;
    private  String mShortDescription;
    private  String mLongDescription;
    private  String mVideoUrl;

    public StepsPOJO(String mId, String mShortDescription, String mLongDescription, String mVideoUrl) {
        this.mId = mId;
        this.mShortDescription = mShortDescription;
        this.mLongDescription = mLongDescription;
        this.mVideoUrl = mVideoUrl;
    }

    protected StepsPOJO(Parcel in) {
        mId = in.readString();
        mShortDescription = in.readString();
        mLongDescription = in.readString();
        mVideoUrl = in.readString();
    }

    public static final Creator<StepsPOJO> CREATOR = new Creator<StepsPOJO>()
    {
        @Override
        public StepsPOJO createFromParcel(Parcel in) { return new StepsPOJO(in); }
        @Override
        public StepsPOJO[] newArray(int size) { return new StepsPOJO[size]; }
    };

    public String getmId() { return mId; }
    public String getmShortDescription() {
        return mShortDescription;
    }
    public String getmLongDescription() {
        return mLongDescription;
    }
    public String getmVideoUrl() {
        return mVideoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mShortDescription);
        dest.writeString(mLongDescription);
        dest.writeString(mVideoUrl);
    }
}
