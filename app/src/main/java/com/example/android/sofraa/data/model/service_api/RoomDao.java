package com.example.android.sofraa.data.model.service_api;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.sofraa.data.model.list_of_restaurant_items.ItemFoodData;

import java.util.List;


@Dao
public interface RoomDao {

    @Insert
    void insertItemToCar(ItemFoodData ... foodItem);

    @Update
    void updateItemToCar(ItemFoodData ... foodItem);

    @Delete
    void deleteItemToCar(ItemFoodData ... foodItem);

    @Query("Delete from car_food")
    void deleteAllItemToCar();

    @Query("Select * from car_food")
    List<ItemFoodData> getAllItem();
}

