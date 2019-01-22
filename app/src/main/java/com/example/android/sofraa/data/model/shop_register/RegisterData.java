
package com.example.android.sofraa.data.model.shop_register;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterData implements Parcelable {

    public RegisterData() {
    }

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;

    protected RegisterData(Parcel in) {
        name = in.readString();
        email = in.readString();
        password = in.readString();
        confirm_password = in.readString();
        phone = in.readString();
        whatsapp = in.readString();
        regionId = in.readString();
        deliveryCost = in.readString();
        minimumCharger = in.readString();
        availability = in.readString();
        photo = in.readString();
        updatedAt = in.readString();
        createdAt = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            rate = null;
        } else {
            rate = in.readInt();
        }
        photoUrl = in.readString();
    }

    public static final Creator<RegisterData> CREATOR = new Creator<RegisterData>() {
        @Override
        public RegisterData createFromParcel(Parcel in) {
            return new RegisterData(in);
        }

        @Override
        public RegisterData[] newArray(int size) {
            return new RegisterData[size];
        }
    };

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    @SerializedName("confirm_password")

    @Expose
    private String confirm_password;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("whatsapp")
    @Expose
    private String whatsapp;
    @SerializedName("region_id")
    @Expose
    private String regionId;
    @SerializedName("delivery_cost")
    @Expose
    private String deliveryCost;
    @SerializedName("minimum_charger")
    @Expose
    private String minimumCharger;
    @SerializedName("availability")
    @Expose
    private String availability;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("photo_url")
    @Expose
    private String photoUrl;
    @SerializedName("region")
    @Expose
    private Region region;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(String deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public String getMinimumCharger() {
        return minimumCharger;
    }

    public void setMinimumCharger(String minimumCharger) {
        this.minimumCharger = minimumCharger;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(confirm_password);
        parcel.writeString(phone);
        parcel.writeString(whatsapp);
        parcel.writeString(regionId);
        parcel.writeString(deliveryCost);
        parcel.writeString(minimumCharger);
        parcel.writeString(availability);
        parcel.writeString(photo);
        parcel.writeString(updatedAt);
        parcel.writeString(createdAt);
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        if (rate == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(rate);
        }
        parcel.writeString(photoUrl);
    }
}
