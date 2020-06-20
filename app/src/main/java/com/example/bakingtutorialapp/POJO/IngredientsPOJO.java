package com.example.bakingtutorialapp.POJO;

import android.os.Parcel;
import android.os.Parcelable;

public class IngredientsPOJO implements Parcelable
{
    private String mQuantity;
    private String mMeasure;
    private String mIngredient;

    public IngredientsPOJO(String mQuantity, String mMeasure, String mIngredient) {
        this.mQuantity = mQuantity;
        this.mMeasure = mMeasure;
        this.mIngredient = mIngredient;
    }

    protected IngredientsPOJO(Parcel in) {
        mQuantity = in.readString();
        mMeasure = in.readString();
        mIngredient = in.readString();
    }

    public static final Creator<IngredientsPOJO> CREATOR = new Creator<IngredientsPOJO>()
    {
        @Override
        public IngredientsPOJO createFromParcel(Parcel in) { return new IngredientsPOJO(in); }
        @Override
        public IngredientsPOJO[] newArray(int size) { return new IngredientsPOJO[size]; }
    };

    public String getmQuantity() { return mQuantity; }
    public String getmMeasure() { return mMeasure; }
    public String getmIngredient() { return mIngredient; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mQuantity);
        dest.writeString(mMeasure);
        dest.writeString(mIngredient);
    }
}
