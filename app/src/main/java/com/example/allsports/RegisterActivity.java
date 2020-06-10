package com.example.allsports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class RegisterActivity extends AppCompatActivity {
    CircularProgressButton cirRegisterButton;
EditText editTextName, editTextEmail,editTextMobile, editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        editTextName  = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextMobile = findViewById(R.id.editTextMobile);
        editTextPassword = findViewById(R.id.editTextPassword);
        cirRegisterButton = findViewById(R.id.cirRegisterButton);
        cirRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }

    private void register() {
        if (TextUtils.isEmpty(editTextName.getText())) {
            editTextName.setError("Please enter Full name");
            editTextName.requestFocus();
            return;
        } else if (TextUtils.isEmpty(editTextEmail.getText())) {
            editTextEmail.setError("Please enter valid Email");
            editTextEmail.requestFocus();
            return;
        }
        else if (TextUtils.isEmpty(editTextMobile.getText())) {
            editTextMobile.setError("Please enter Contact Number");
            editTextMobile.requestFocus();
            return;
        }
        else if (TextUtils.isEmpty(editTextPassword.getText())) {
            editTextPassword.setError("Please enter valid Password");
            editTextPassword.requestFocus();
            return;
        }
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Full Name", editTextName.getText().toString());
        editor.putString("Email", editTextEmail.getText().toString());
        editor.putString("Mobile", editTextMobile.getText().toString());
        editor.putString("Password", editTextPassword.getText().toString());
        editor.commit();

        Toast.makeText(this, "Successfully Register", Toast.LENGTH_SHORT).show();

    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }
}
