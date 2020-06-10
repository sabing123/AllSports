package com.example.allsports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etpassword;
    Button loginbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //for changing status bar icon colors
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.etEmail);
        etpassword = findViewById(R.id.etPassword);
        loginbutton = findViewById(R.id.btnuserlogin);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Userlogin();
            }

        });
    }

    private void Userlogin() {
        if (TextUtils.isEmpty(etEmail.getText())) {
            etEmail.setError("Please enter Valid Email");
            etEmail.requestFocus();
            return;
        } else if (TextUtils.isEmpty(etpassword.getText())) {
            etpassword.setError("Please enter valid Password");
            etpassword.requestFocus();
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        String email = sharedPreferences.getString("Email", "");
        String Password = sharedPreferences.getString("Password","" );

        if (email.equals(etEmail.getText().toString()) || Password.equals(etpassword.getText().toString())){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Successfully Login..........", Toast.LENGTH_SHORT).show();
            finish();

        }
        else{
            Toast.makeText(this, "Either Email or Password is incorrect...", Toast.LENGTH_SHORT).show();
            etEmail.setText("");
            etpassword.setText("");
            etEmail.setVisibility(View.VISIBLE);
            etEmail.setBackgroundColor(Color.RED);
            etpassword.setBackgroundColor(Color.RED);
        }


    }

    public void onLoginClick(View View){
        startActivity(new Intent(this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }
}
