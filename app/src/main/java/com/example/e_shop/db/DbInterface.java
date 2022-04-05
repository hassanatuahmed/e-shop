package com.example.e_shop.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.e_shop.model.ProductModel;

import java.util.List;

@Dao
public interface DbInterface {

    @Insert
    void save_product(ProductModel productModel);

    @Query("SELECT * FROM PRODUCT_TABLE ORDER BY id DESC")
    LiveData<List<ProductModel>> get_all_product();

    @Query("SELECT * FROM PRODUCT_TABLE WHERE id = :product_id")
    LiveData<ProductModel> get_product(int product_id);

    @Query("DELETE FROM PRODUCT_TABLE WHERE id = :product_id")
    void delete_product(Integer product_id);

    @Update
    void update_product(ProductModel productModel);
}
