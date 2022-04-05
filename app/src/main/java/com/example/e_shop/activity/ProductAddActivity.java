package com.example.e_shop.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;


import com.example.e_shop.R;
import com.example.e_shop.db.DatabaseRepository;
import com.example.e_shop.model.ProductModel;

import java.util.ArrayList;
import java.util.Collections;

public class ProductAddActivity extends AppCompatActivity {

    DatabaseRepository databaseRepository;

    private void save_product() {
        Toast.makeText(this, "Saving product...", Toast.LENGTH_SHORT).show();
        databaseRepository.save_product(productModel);
    }

    ArrayList<String> images = new ArrayList<>();
    EditText title, price, details;
    Button submit;
    ProductModel productModel;
    Context context;

    boolean is_edit = false;
    String edit_product_id = "";
    ProductModel edit_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
        context = this;
        databaseRepository = new DatabaseRepository(this);
        bind_views();

        Intent intent = getIntent();
        if (intent.hasExtra("edit_product")) {
            edit_product_id = intent.getStringExtra("edit_product");
            if (edit_product_id != null) {
                if (!edit_product_id.isEmpty()) {
                    get_product();
                }
            }
        }

    }

    private void get_product() {
        databaseRepository.get_product(Integer.valueOf(edit_product_id + "")).observe(this, new Observer<ProductModel>() {
            @Override
            public void onChanged(ProductModel productModel) {
                if (productModel == null) {
                    Toast.makeText(context, "Product not found od db.", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }
                edit_product = productModel;
                is_edit = true;
                feed_data();
            }
        });
    }

    private void feed_data() {
        title.setText(edit_product.title + "");
        price.setText(edit_product.price + "");
        details.setText(edit_product.detail + "");
        submit.setText("UPDATE PRODUCT");
    }


    private void bind_views() {
        title = findViewById(R.id.title);
        submit = findViewById(R.id.submit);
        price = findViewById(R.id.price);
        details = findViewById(R.id.details);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_edit) {
                    up_date_product();
                } else {
                    validate_fields();
                }
            }
        });
    }

    private void up_date_product() {
        edit_product.title = title.getText().toString();
        edit_product.price = Integer.valueOf(price.getText().toString());
        edit_product.detail = details.getText().toString();
        create_images();
        edit_product.image = images.get(3);
        databaseRepository.update_product(edit_product);
        Toast.makeText(context, "Updated!...", Toast.LENGTH_SHORT).show();
        finish();
        return;
    }

    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> prices = new ArrayList<>();

    private void validate_fields() {
        titles.clear();
        prices.clear();
        titles.add("Hollow Painted Flower Ring");
        titles.add("Stainless Steel Alloy Bracelet");
        titles.add("Ethnic Print High Low Hem Hooded Ja");
        titles.add("Butterfly Pearl Women Ring");
        titles.add("Fish Print Ear High Low Hoodie");
        titles.add("Men's Stainless Steel Tiger Necklace");
        titles.add("Vintage Printed Long Sleeve Blouse");
        titles.add("Denim Cloth Plush Lined Zipper Boots");
        titles.add("Fashion Stainless Steel Men's Ring");

        prices.add("220");
        prices.add("340");
        prices.add("420");
        prices.add("440");
        prices.add("165");


        Collections.shuffle(titles);
        Collections.shuffle(titles);
        Collections.shuffle(prices);
        Collections.shuffle(titles);
        Collections.shuffle(titles);
        Collections.shuffle(titles);
        Collections.shuffle(prices);
        Collections.shuffle(titles);
        Collections.shuffle(titles);
        Collections.shuffle(titles);
        Collections.shuffle(prices);
        Collections.shuffle(titles);
        title.setText(titles.get(3).toString());
        price.setText(prices.get(0).toString());
        details.setText("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud");


        productModel = new ProductModel();
        productModel.title = title.getText().toString();
        if (productModel.title.isEmpty()) {
            title.requestFocus();
            Toast.makeText(this, "Title too short.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (price.getText().toString().isEmpty()) {
            Toast.makeText(this, "Price not set.", Toast.LENGTH_SHORT).show();
            price.requestFocus();
            return;
        }

        productModel.price = Integer.valueOf(price.getText().toString());

        productModel.detail = details.getText().toString();

        create_images();

        productModel.image = images.get(2);

        save_product();

    }

    private void create_images() {

        images.clear();
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/46/14/c9241ea3-b5fa-4bc4-8387-1cfcd8d5572e.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/33/7C/84ec2e2a-ac83-4d5b-87ab-5b5114e6725b.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/59/65/baccf83e-b72a-497c-96db-d69fb2a51f8b.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/8D/83/7730e751-f1db-44b9-8b71-9154a74c4704.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/59/74/c213d6f2-e9d4-4c18-8983-6e94cccbf52c.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/F4/3E/5dd6fb3b-5742-4c40-87b9-8b7e6ccbd0e8.jpeg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/79/DE/f6d072bf-aaed-4495-9f57-80fd9740c30f.jpeg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/FF/04/10b6bb5d-f1bf-4587-9664-5b4a973a7f82.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/6A/6B/820d360d-0f91-4071-b8bc-df967be4c206.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/75/C7/618bf16a-8656-46ae-8f41-6c8dcee5facf.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/14/CE/8ec2106b-baaa-412b-a2b4-a39521d34e86.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/01/EA/cf451db4-8a06-4f99-8541-7b9f39ec2648.jpg");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/A5/AC/7151fae4-7c9f-429d-b460-214023bcdb04.jpg");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/39/47/5bc7266e-e63b-4c22-a75e-045568bf3d74.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/ser1/newchic/images/55/A8/4c13d08c-7cee-4bb1-9224-91db6e11517d.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/F4/3E/5dd6fb3b-5742-4c40-87b9-8b7e6ccbd0e8.jpeg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/79/DE/f6d072bf-aaed-4495-9f57-80fd9740c30f.jpeg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/FF/04/10b6bb5d-f1bf-4587-9664-5b4a973a7f82.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/6A/6B/820d360d-0f91-4071-b8bc-df967be4c206.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/75/C7/618bf16a-8656-46ae-8f41-6c8dcee5facf.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/14/CE/8ec2106b-baaa-412b-a2b4-a39521d34e86.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/01/EA/cf451db4-8a06-4f99-8541-7b9f39ec2648.jpg");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/A5/AC/7151fae4-7c9f-429d-b460-214023bcdb04.jpg");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/39/47/5bc7266e-e63b-4c22-a75e-045568bf3d74.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/ser1/newchic/images/55/A8/4c13d08c-7cee-4bb1-9224-91db6e11517d.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/ser1/newchic/images/A9/EE/c8b6c295-b1da-462f-9a7b-adf8c237642f.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/ser1/newchic/images/34/AA/de157e36-1155-4d42-9901-83d46c48e564.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/ser1/newchic/images/1B/25/9f54f72a-0775-4efb-ab85-6cb53527b4f2.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/09/11/a2056424-6638-4284-999e-2df4f58425cb.jpg?s=702x936");
        images.add("https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/31/0E/acb4c456-4bdd-4ec0-b56f-f99a1bda6807.jpg?s=702x936");
        images.add("https://cdn.shopify.com/s/files/1/0684/2619/products/blacktribalmask1_1000x1400.jpg?v=1601394382");
        images.add("https://cdn.shopify.com/s/files/1/0684/2619/products/Umi-kids-tee-black-green-kente_6_1000x1400.jpg?v=1611851814");
        images.add("https://cdn.shopify.com/s/files/1/0684/2619/products/Womens-nea-tshirt-black-black-green-kente_1_1000x1399.jpg?v=1611851107");
        images.add("https://cdn.shopify.com/s/files/1/0684/2619/products/Abio-mens-tshirt-black-green-kente_3_1000x1401.jpg?v=1611835856");

        Collections.shuffle(images);
        Collections.shuffle(images);
        Collections.shuffle(images);

        Collections.shuffle(images);
        Collections.shuffle(images);
        Collections.shuffle(images);
        Collections.shuffle(images);
        Collections.shuffle(images);
        Collections.shuffle(images);
        Collections.shuffle(images);
        Collections.shuffle(images);
        Collections.shuffle(images);
    }


}