package com.example.e_shop.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "PRODUCT_TABLE")
public class ProductModel {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id=0;

    public String title="";
    public String detail="";
    public int price=0;
    public String image="";

    public ProductModel() {

    }
}
