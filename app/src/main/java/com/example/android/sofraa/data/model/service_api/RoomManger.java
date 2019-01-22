package com.example.android.sofraa.data.model.service_api;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.android.sofraa.data.model.list_of_restaurant_items.ItemFoodData;

@Database(entities = {ItemFoodData.class}, version = 1, exportSchema = false)
@TypeConverters({DataTypeConverter.class})
public abstract class RoomManger extends RoomDatabase {

    private static RoomManger roomManger;

    public abstract RoomDao roomDao();

    public static synchronized RoomManger getInstance(Context context) {
        if (roomManger == null) {
            roomManger = Room.databaseBuilder(context.getApplicationContext(), RoomManger.class, "sofra_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return roomManger;
    }

}
