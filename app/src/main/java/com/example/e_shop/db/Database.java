package com.example.e_shop.db;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import com.example.e_shop.model.ProductModel;


@androidx.room.Database(entities = {ProductModel.class}, version = 2, exportSchema = false)


public abstract class Database extends RoomDatabase {

    private static Database instance;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract DbInterface dbInterface();

    public static synchronized Database getGetInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            Database.class,"DATABASE_NAME")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }



}
