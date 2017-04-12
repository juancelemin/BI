package com.inteligenciac.bi.bi;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jguil on 2/04/2017.
 */

public class Answer implements Parcelable {
    private String description = "BI";
    private String category = "none";
    private String type;

    public Answer(String description, String category) {
        this.description = description;
        this.category = category;
        type = "";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    protected Answer(Parcel in) {
        description = in.readString();
        category = in.readString();
        type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(category);
        dest.writeString(type);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Answer> CREATOR = new Parcelable.Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };
}