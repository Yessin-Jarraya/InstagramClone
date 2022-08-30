package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSignUp,btnLogIn;
    private EditText edtSignUpName,edtSignUpPassword,edtLogInName,edtLogInPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);
        btnSignUp=findViewById(R.id.btnSignUp);
        btnLogIn=findViewById(R.id.btnLogIn);
        edtSignUpName=findViewById(R.id.edtSignUpName);
        edtSignUpPassword=findViewById(R.id.edtSignUpPassword);
        edtLogInName=findViewById(R.id.edtLogInName);
        edtLogInPassword=findViewById(R.id.edtLogInPassword);
        btnSignUp.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:
                ParseUser appUser = new ParseUser();
                appUser.setUsername(edtSignUpName.getText().toString());
                appUser.setPassword(edtSignUpPassword.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null){
                            FancyToast.makeText(SignUpLoginActivity.this,  appUser.get("username")+" is signed up succesfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                        }
                        else{
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                        }
                    }
                });
                break;
            case R.id.btnLogIn:
                ParseUser.logInInBackground(edtLogInName.getText().toString(), edtLogInPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user!=null && e==null){
                            FancyToast.makeText(SignUpLoginActivity.this,  " You Logged in successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            Intent intent = new Intent(SignUpLoginActivity.this,WelcomeActivity.class);
                            startActivity(intent);
                            finish();

                        }
                        else {                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();


                        }
                    }
                });
                break;
        }

    }
}
