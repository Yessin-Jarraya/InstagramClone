package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSave;
    private EditText edtName,edtPunchPower,edtPunchSpeed,edtKickPower,edtKickSpeed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = findViewById(R.id.btnSave);
        edtName= findViewById(R.id.edtName);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        btnSave.setOnClickListener(this);


    }

    @Override
    public void onClick(View view){
        try{
            ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("name", edtName.getText().toString());
            kickBoxer.put("punchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
            kickBoxer.put("punchPower", Integer.parseInt(edtPunchPower.getText().toString()));
            kickBoxer.put("kickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
            kickBoxer.put("kickPower", Integer.parseInt(edtKickPower.getText().toString()));
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(MainActivity.this, kickBoxer.get("name") + " the Kickboxer object is successfully saved to server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    }
                }
            });
        } catch(Exception e){
            FancyToast.makeText(this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
        }

    }
    //  public void helloWorldTapped(View view){
//        ParseObject boxer = new ParseObject("Boxer");
//        boxer.put("punchSpeed",200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e==null){
//                    Toast.makeText(MainActivity.this,"the boxer object is saved successfully",Toast.LENGTH_SHORT).show();
//                }else{Toast.makeText(MainActivity.this,"the boxer object is not saved successfully",Toast.LENGTH_SHORT).show();}
//
//            }
//        });
//        ParseObject kickBoxer = new ParseObject("KickBoxer");
//        kickBoxer.put("name","Yessin");
//        kickBoxer.put("punchSpeed",100);
//        kickBoxer.put("punchPower",100);
//        kickBoxer.put("kickSpeed",300);
//        kickBoxer.put("kickPower",300);
//        kickBoxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e==null){
//                    Toast.makeText(MainActivity.this,kickBoxer.get("name")+" the Kickboxer object is succesfully saved to server",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


    }
