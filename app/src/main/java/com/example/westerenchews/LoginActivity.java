package com.example.westerenchews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    FirebaseFirestore db;
    EditText edt_email,edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_email = findViewById(R.id.edtLoginEmail);
        edt_password = findViewById(R.id.edtloginpassword);
        db = FirebaseFirestore.getInstance();
        getSupportActionBar().hide();
        findViewById(R.id.btnlogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin(edt_email.getText().toString(),edt_password.getText().toString());
            }
        });
        findViewById(R.id.txtnoregister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
            }
        });
    }

    private void checkLogin(final String email, final String password) {

        Call<String> call = Retrofit.getClient().login(
                email,
                password
        );

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().toString().equals("\"User Logged in successfuly.\"")){
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class)
                            .putExtra("email",email));
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
}