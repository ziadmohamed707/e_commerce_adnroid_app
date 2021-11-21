package com.example.myapplication.Sellers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SellerLoginActivity extends AppCompatActivity {

    private Button sellerLoginBtn;
    private EditText emailInput, passwordInput;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);


        mAuth = FirebaseAuth.getInstance();

        loadingBar = new ProgressDialog(this);
        sellerLoginBtn = findViewById(R.id.seller_login_btn);
        emailInput = findViewById(R.id.seller_login_email);
        passwordInput = findViewById(R.id.seller_login_password);


        sellerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginSeller();
            }
        });

    }

    private void loginSeller() {


        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (!email.equals("") && !password.equals("")) {
            loadingBar.setTitle("Login");
            loadingBar.setMessage("Please wait, while we are checking the credentials..");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();


            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        loadingBar.dismiss();
                        Toast.makeText(SellerLoginActivity.this, "Your registered successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SellerLoginActivity.this, SellerHomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();                    }
                }
            });

        }else {
            Toast.makeText(this, "Please complete the registration form...", Toast.LENGTH_SHORT).show();

        }


    }
}