package com.example.gymBroApp.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gymBroApp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewActivity extends AppCompatActivity {

    private Button btn, cBtn;
    private FirebaseAuth mAuth;
    private String email,password;
    private EditText emailIn, pwIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_view_layout);
        getSupportActionBar().hide();
        emailIn = findViewById(R.id.email);
        pwIn = findViewById(R.id.password);
        btn = findViewById(R.id.log_in_button);
        cBtn = findViewById(R.id.createBtn);
        mAuth = FirebaseAuth.getInstance();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                email = emailIn.getText().toString();
                password = pwIn.getText().toString();

                if (emailIn.getText().toString().equals("") || pwIn.getText().toString().equals("")){
                    Toast.makeText(v.getContext(), "Fill In All The Fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginViewActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                FirebaseUser user = mAuth.getCurrentUser();
                                openHomePage();
                            }
                            else {
                                Toast.makeText(v.getContext(), "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        cBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreatePage();
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    public void openHomePage(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void openCreatePage(){
        Intent intent2 = new Intent(this, CreateUserActivity.class);
        startActivity(intent2);
    }
}
