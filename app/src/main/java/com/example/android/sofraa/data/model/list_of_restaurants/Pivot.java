
package com.example.android.sofraa.data.model.list_of_restaurants;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pivot implements Parcelable {

    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;

    protected Pivot(Parcel in) {
        restaurantId = in.readString();
        categoryId = in.readString();
    }

    public static final Creator<Pivot> CREATOR = new Creator<Pivot>() {
        @Override
        public Pivot createFromParcel(Parcel in) {
            return new Pivot(in);
        }

        @Override
        public Pivot[] newArray(int size) {
            return new Pivot[size];
        }
    };

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(restaurantId);
        parcel.writeString(categoryId);
    }
}
