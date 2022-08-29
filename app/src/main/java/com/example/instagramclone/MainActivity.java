package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSave,btnGetAllData,btnTransition;
    private EditText edtName,edtPunchPower,edtPunchSpeed,edtKickPower,edtKickSpeed;
    private TextView txtGetData;
    private String allKickBoxers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = findViewById(R.id.btnSave);
        btnGetAllData = findViewById(R.id.btnGetAllData);
        edtName= findViewById(R.id.edtName);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        txtGetData = findViewById(R.id.txtGetData);
        btnSave.setOnClickListener(this);
        btnGetAllData.setOnClickListener(this);
        btnTransition.setOnClickListener(this);
        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject>  parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("MwsQohHceO", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object!=null && e==null){
                            txtGetData.setText(object.get("name")+"'s"+" PunchPower is :  "+object.get("punchPower"));
                        }
                    }
                });

            }
        });



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGetAllData:
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                queryAll.whereGreaterThan("punchPower",400);
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        allKickBoxers="";
                        if (e==null){
                            if (objects.size()>0){
                                for (ParseObject parseObject : objects){
                                    allKickBoxers = allKickBoxers + parseObject.get("name")+"\n";
                                }
                                FancyToast.makeText(MainActivity.this,  allKickBoxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();


                            }
                            else{
                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            }
                        }
                        else{
                            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                        }
                    }
                });
                break;
            case R.id.btnTransition:
                break;
            case R.id.btnSave:


                try {
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
                } catch (Exception e) {
                    FancyToast.makeText(this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                }
            break;
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
