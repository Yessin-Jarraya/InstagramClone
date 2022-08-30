package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtWelcome;
    Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        txtWelcome=findViewById(R.id.txtWelcome);
        btnLogOut=findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnLogOut:
                Log.i("baba","ma7abech?");
                ParseUser.logOut();
                Log.i("baba","mama");
                Intent intent = new Intent(WelcomeActivity.this, SignUpLoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }

    }
}