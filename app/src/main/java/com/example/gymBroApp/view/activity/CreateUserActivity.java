package com.example.gymBroApp.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gymBroApp.R;
import com.example.gymBroApp.model.User;
import com.example.gymBroApp.viewModel.UserViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateUserActivity extends AppCompatActivity {

    private EditText tName,tEmail,tAge,tPw,tWo,tWr,tLoc,tPhone;
    private Button signUpBtn;
    private UserViewModel mModel;
    private ProgressBar pbar;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user_view_layout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        tName = findViewById(R.id.createName);
        tAge = findViewById(R.id.createAge);
        tEmail = findViewById(R.id.createEmail);
        tPw = findViewById(R.id.createPw);
        tWo = findViewById(R.id.createWoPref);
        tWr = findViewById(R.id.createWePref);
        tLoc = findViewById(R.id.createLocation);
        tPhone = findViewById(R.id.createNr);
        signUpBtn = findViewById(R.id.signUpBtn);
        pbar = findViewById(R.id.createBar);
        pbar.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        User user = new User();
        int age, phoneNr;

        mModel = new ViewModelProvider(this).get(UserViewModel.class);
        mModel.getAllUsers();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbar.setVisibility(View.VISIBLE);
                user.setName(tName.getText().toString());
                user.setAge(Integer.parseInt(tAge.getText().toString()));
                user.setPhoneNr(Integer.parseInt(tPhone.getText().toString()));
                user.setEmail(tEmail.getText().toString());
                user.setPw(tPw.getText().toString());
                user.setLocation(tLoc.getText().toString());
                user.setWorkPref(tWo.getText().toString());
                user.setWeatherPref(tWr.getText().toString());
                mModel.insert(user);
                pbar.setVisibility(View.INVISIBLE);

                mAuth.createUserWithEmailAndPassword(tEmail.getText().toString(),tPw.getText().toString()).addOnCompleteListener(CreateUserActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                        }
                        else {
                            Toast.makeText(v.getContext(), "Couldn't create user", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                OpenLogInPage();

            }
        });
    }

    public void OpenLogInPage(){
        Intent intent = new Intent(this, LoginViewActivity.class);
        startActivity(intent);

    }

}
