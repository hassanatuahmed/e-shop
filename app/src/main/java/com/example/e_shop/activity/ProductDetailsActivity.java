package com.example.e_shop.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.example.e_shop.R;
import com.example.e_shop.db.DatabaseRepository;
import com.example.e_shop.model.ProductModel;


public class ProductDetailsActivity extends AppCompatActivity {

    Intent i;
    String product_id = "";
    DatabaseRepository databaseRepository;
    ProductModel product = null;

    private void delete_product() {
        databaseRepository.delete_product(Integer.valueOf(product_id));
        Toast.makeText(context, "Deleted...", Toast.LENGTH_SHORT).show();
        finish();
        return;
    }


    private void get_product() {
        databaseRepository.get_product(Integer.valueOf(product_id + "")).observe(this, new Observer<ProductModel>() {
            @Override
            public void onChanged(ProductModel productModel) {
                if (productModel == null) {
                    Toast.makeText(ProductDetailsActivity.this, "Product not found od db.", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }
                product = productModel;
                feed_data();
            }
        });
    }

    TextView product_title, product_price, product_details;
    ImageView product_image;
    Button btn_delete, btn_edit;
    Context context;

    private void feed_data() {
        product_title = findViewById(R.id.product_title);
        product_image = findViewById(R.id.product_image);
        product_price = findViewById(R.id.product_price);
        product_details = findViewById(R.id.product_details);
        btn_delete = findViewById(R.id.btn_delete);
        btn_edit = findViewById(R.id.btn_edit);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductAddActivity.class);
                intent.putExtra("edit_product", product_id + "");
                ProductDetailsActivity.this.startActivity(intent);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are you sure you want to delete " + product.title + " ?");
                builder.setIcon(R.drawable.ic_delete);
                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delete_product();
                    }
                });
                builder.setNegativeButton("CANCEL", null);
                builder.show();
            }
        });

        product_title.setText(product.title);
        product_price.setText(product.price + "");
        product_details.setText(product.detail);

        Glide.with(this)
                .load(product.image)
                .into(product_image);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        context = this;
        i = getIntent();
        if (!i.hasExtra("product_id")) {
            Toast.makeText(this, "Product ID not found.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        product_id = i.getStringExtra("product_id");
        if (product_id == null) {
            Toast.makeText(this, "Product ID is null.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        databaseRepository = new DatabaseRepository(this);

        get_product();
    }


}