package com.summer.and.diana.adventuretime.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.summer.and.diana.adventuretime.R;
import com.summer.and.diana.adventuretime.models.User;

public class SignUpActivity extends AppCompatActivity {

    protected EditText mUsername;
    protected EditText mEmail;
    protected EditText mPassword;
    protected Button mSignupButton;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUsername = (EditText) findViewById(R.id.usernameEditText);
        mEmail = (EditText) findViewById(R.id.emailEditText);
        mPassword = (EditText) findViewById(R.id.passwordEditText);
        mSignupButton = (Button) findViewById(R.id.signupButton);
        mPreferences = getApplicationContext().getSharedPreferences("adventuretime", Context.MODE_PRIVATE);

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putString("username", username);
                editor.commit();
                User newUser = new User(username, password, email);
                newUser.save();

                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
