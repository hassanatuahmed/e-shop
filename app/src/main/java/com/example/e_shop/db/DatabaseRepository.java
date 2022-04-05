package com.example.e_shop.db;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;


import com.example.e_shop.model.ProductModel;

import java.util.List;

public class DatabaseRepository {
    public Database database;

    public  DbInterface dbInterface;

    Context context;
    private static final String TAG = "DatabaseRepository";

    public DatabaseRepository(Context context) {
        this.context = context;
        database = Database.getGetInstance(context);
        dbInterface = database.dbInterface();
    }


    public void save_product(ProductModel productModel) {

        database.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    dbInterface.save_product(productModel);
                    Log.d(TAG, "run: SAVED PRODUCT: => " + productModel.title);
                } catch (Exception e) {
                    Log.d(TAG, "run: FAILED TO SAVE PRODUCT: => " + productModel.title + "" +
                            "becasue : " + e.getMessage() +
                            ""

                    );
                }

            }
        });
    }


    public LiveData<List<ProductModel>> get_all_products() {
        return dbInterface.get_all_product();
    }

    public LiveData<ProductModel> get_product(int product_id) {
        return dbInterface.get_product(product_id);
    }


    public void update_product(ProductModel product) {
        database.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    dbInterface.update_product(product);
                    Log.d(TAG, "run: successfull UPDATE ==> ");
                } catch (Exception e) {
                    Log.d(TAG, "run: FAILED TO UPDATE ==> " + e.getMessage());
                }
            }
        });
    }

    public void delete_product(Integer product_id) {
        database.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    dbInterface.delete_product(product_id);
                    Log.d(TAG, "run: successfull DELETE ==> ");
                } catch (Exception e) {
                    Log.d(TAG, "run: FAILED TO DELETE ==> " + e.getMessage());
                }
            }
        });
    }
}
