package com.example.bakingtutorialapp.POJO;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ReciepePOJO implements Parcelable {
    private int mid;
    private String mName;
    private ArrayList<StepsPOJO> mSteps;
    private ArrayList<IngredientsPOJO> mIngredients;
    private String mServing;

    public ReciepePOJO(int mid, String mName, ArrayList<StepsPOJO> mSteps, ArrayList<IngredientsPOJO> mIngredients, String mServing)
    {
        this.mid = mid;
        this.mName = mName;
        this.mSteps = mSteps;
        this.mIngredients = mIngredients;
        this.mServing = mServing;
    }

    protected ReciepePOJO(Parcel in) {
        mid = in.readInt();
        mName = in.readString();
        mSteps = in.createTypedArrayList(StepsPOJO.CREATOR);
        mIngredients = in.createTypedArrayList(IngredientsPOJO.CREATOR);
        mServing = in.readString();
    }

    public static final Creator<ReciepePOJO> CREATOR = new Creator<ReciepePOJO>()
    {
        @Override
        public ReciepePOJO createFromParcel(Parcel in) { return new ReciepePOJO(in); }
        @Override
        public ReciepePOJO[] newArray(int size) { return new ReciepePOJO[size]; }
    };

    public int getMid() { return mid; }

    public String getmName() { return mName; }

    public ArrayList<StepsPOJO> getmSteps() { return mSteps; }

    public ArrayList<IngredientsPOJO> getmIngredients() { return mIngredients; }

    public String getmServing() { return mServing; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mid);
        dest.writeString(mName);
        dest.writeTypedList(mSteps);
        dest.writeTypedList(mIngredients);
        dest.writeString(mServing);
    }
}
