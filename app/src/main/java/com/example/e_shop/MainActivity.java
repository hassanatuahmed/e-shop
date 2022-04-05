package com.example.e_shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.e_shop.activity.ProductAddActivity;
import com.example.e_shop.activity.ProductDetailsActivity;
import com.example.e_shop.adapter.ProductAdapter;
import com.example.e_shop.db.DatabaseRepository;
import com.example.e_shop.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button add_product;

    public static final String DATABASE_NAME = "E_SHOP_MUSIC_DB";
    public static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    RecyclerView recyclerView;
    DatabaseRepository databaseRepository;

    private void get_local_data() {
        databaseRepository = new DatabaseRepository(this);
        databaseRepository.get_all_products().observe(this, new Observer<List<ProductModel>>() {
            @Override
            public void onChanged(List<ProductModel> _productModels) {
                if (_productModels == null) {
                    Toast.makeText(MainActivity.this, "No Product Found.", Toast.LENGTH_SHORT).show();
                    return;
                }
                productModels.clear();
                productModels = _productModels;
                init_rec_view();
            }
        });
    }

    private void init_rec_view() {

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setNestedScrollingEnabled(false);
        productAdapter = new ProductAdapter(productModels, this);
        recyclerView.setAdapter(productAdapter);

        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ProductModel obj, int pos) {
                Intent intent = new Intent(MainActivity.this, ProductDetailsActivity.class);
                intent.putExtra("product_id", obj.id + "");
                MainActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind_views();
    }

    List<ProductModel> productModels = new ArrayList<>();
    ProductAdapter productAdapter;

    private void bind_views() {
        add_product = findViewById(R.id.add_product);

        recyclerView = findViewById(R.id.recyclerView);


        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ProductAddActivity.class);
                MainActivity.this.startActivity(i);
            }
        });

        get_local_data();
    }


}