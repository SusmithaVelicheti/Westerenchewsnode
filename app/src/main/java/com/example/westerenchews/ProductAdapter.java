package com.example.westerenchews;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapter extends RecyclerView.Adapter <ProductAdapter.MyViewHolder>{
    Activity activity;
    String userEmail;
    FirebaseFirestore db;
    ArrayList<Product> arrayList;
    public ProductAdapter(Activity activity, ArrayList<Product> arrayList, String userEmail, FirebaseFirestore db) {
        this.activity = activity;
        this.arrayList = arrayList;
        this.userEmail = userEmail;
        this.db = db;
    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(activity.getLayoutInflater().inflate(R.layout.row_person,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductAdapter.MyViewHolder holder, int position) {

        holder.amount.setText(arrayList.get(position).getAmount());
        holder.name.setText(arrayList.get(position).getName());
        Glide.with(activity).load(arrayList.get(position).getImageurl()).into(holder.image);
        holder.buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlaceOrder(arrayList.get(holder.getAdapterPosition()).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,amount,buynow;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            amount = itemView.findViewById(R.id.amount);
            buynow = itemView.findViewById(R.id.buynow);
        }
    }

    private void PlaceOrder(String productid) {

        Call<String> call = Retrofit.getClient().orders(
                userEmail,
                productid
        );

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(activity, "Order Placed", Toast.LENGTH_SHORT).show();
                activity.finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
