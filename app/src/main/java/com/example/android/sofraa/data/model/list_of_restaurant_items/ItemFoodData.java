
package com.example.android.sofraa.data.model.list_of_restaurant_items;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "car_food")
public class ItemFoodData implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int item_Id;

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("preparing_time")
    @Expose
    private String preparingTime;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("photo_url")
    @Expose
    private String photoUrl;

    private int counter;

    public ItemFoodData(Integer id, String createdAt, String updatedAt, String name, String description, String price, String preparingTime, String photo, String restaurantId, String photoUrl, int counter) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.description = description;
        this.price = price;
        this.preparingTime = preparingTime;
        this.photo = photo;
        this.restaurantId = restaurantId;
        this.photoUrl = photoUrl;
        this.counter = counter;
    }

    public ItemFoodData() {

    }

    protected ItemFoodData(Parcel in) {
        item_Id = in.readInt();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        createdAt = in.readString();
        updatedAt = in.readString();
        name = in.readString();
        description = in.readString();
        price = in.readString();
        preparingTime = in.readString();
        photo = in.readString();
        restaurantId = in.readString();
        photoUrl = in.readString();
        counter = in.readInt();
    }

    public static final Creator<ItemFoodData> CREATOR = new Creator<ItemFoodData>() {
        @Override
        public ItemFoodData createFromParcel(Parcel in) {
            return new ItemFoodData(in);
        }

        @Override
        public ItemFoodData[] newArray(int size) {
            return new ItemFoodData[size];
        }
    };

    public int getItem_Id() {
        return item_Id;
    }

    public void setItem_Id(int item_Id) {
        this.item_Id = item_Id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPreparingTime() {
        return preparingTime;
    }

    public void setPreparingTime(String preparingTime) {
        this.preparingTime = preparingTime;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(item_Id);
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(createdAt);
        parcel.writeString(updatedAt);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(price);
        parcel.writeString(preparingTime);
        parcel.writeString(photo);
        parcel.writeString(restaurantId);
        parcel.writeString(photoUrl);
        parcel.writeInt(counter);
    }
}
