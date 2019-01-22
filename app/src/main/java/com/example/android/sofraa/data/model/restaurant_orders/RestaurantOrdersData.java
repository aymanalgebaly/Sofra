
package com.example.android.sofraa.data.model.restaurant_orders;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantOrdersData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("payment_method_id")
    @Expose
    private String paymentMethodId;
    @SerializedName("cost")
    @Expose
    private String cost;
    @SerializedName("delivery_cost")
    @Expose
    private String deliveryCost;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("commission")
    @Expose
    private String commission;
    @SerializedName("net")
    @Expose
    private String net;
    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("delivered_at")
    @Expose
    private Object deliveredAt;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("client")
    @Expose
    private Client client;
    @SerializedName("pivot")
    @Expose
    private Pivot pivot;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("restaurant")
    @Expose
    private Restaurant restaurant;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(String deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Object getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Object deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Client getClient() {
        return client;
    }

    public void setPivot(Pivot pivot) {
        this.pivot = pivot;
    }
    public Pivot getPivott() {
        return pivot;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
