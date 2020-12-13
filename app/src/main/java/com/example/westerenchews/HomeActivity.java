package com.example.westerenchews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.westerenchews.test.ProductsResponse;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    String userEmail;
    private FirebaseFirestore db;

    RecyclerView list;
    ArrayList<Product> arrayList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        userEmail = getIntent().getExtras().getString("email");
        list = findViewById(R.id.list);

        db = FirebaseFirestore.getInstance();
        list.setLayoutManager(new GridLayoutManager(getApplicationContext(),2,RecyclerView.VERTICAL,false));
        list.setAdapter(new ProductAdapter(this,arrayList,userEmail,db));

        getProducts();

    }

    private void getProducts() {

        Call<ProductsResponse> call = Retrofit.getClient().getProducts("");
        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                arrayList.addAll(response.body().getData());
                list.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {

            }
        });

    }


}