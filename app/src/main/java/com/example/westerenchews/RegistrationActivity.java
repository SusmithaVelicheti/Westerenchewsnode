package com.example.westerenchews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    EditText fname,lname,email,password,address;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        fname = findViewById(R.id.edtfirstname);
        lname = findViewById(R.id.edtlastname);
        email = findViewById(R.id.edtLoginEmail);
        password = findViewById(R.id.edtloginpassword);
        address = findViewById(R.id.address);
        db = FirebaseFirestore.getInstance();
        findViewById(R.id.btnlogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup(fname.getText().toString(),lname.getText().toString(),email.getText().toString(),password.getText().toString(),address.getText().toString());
            }
        });
    }

    private void signup(String firstname, String lastname, String email, String password, String address) {
        Call<String> call = Retrofit.getClient().register(
                firstname,
                lastname,
                email,
                password,
                address
        );

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().toString().equals("\"Registered!\"")){
                    Toast.makeText(RegistrationActivity.this, "Registration Completed", Toast.LENGTH_SHORT).show();

                    finish();
                }else{
                    Toast.makeText(RegistrationActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


        Map<String, Object> user = new HashMap<>();
        user.put("firstname", firstname);
        user.put("lastname", lastname);
        user.put("email", email);
        user.put("password", password);
        user.put("address", address);



    }
}