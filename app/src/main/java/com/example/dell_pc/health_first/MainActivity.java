package com.example.dell_pc.health_first;


import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.content.Intent;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends Activity {
    Button b1,b2,b3;
    EditText ed1,ed2;

    TextView tx1;
    int counter = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.button1);
        b3 =(Button)findViewById(R.id.button3);
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed1.getText().toString().equals("admin") &&
                        ed2.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),"Redirecting to Home",Toast.LENGTH_SHORT).show();
                    Intent in=new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(in);


                }else{
                    Toast.makeText(MainActivity.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                }

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this,ForgotPasswordActivity.class);
                startActivity(in);



            }
        });

       b3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in=new Intent(MainActivity.this,Registration.class);
                startActivity(in);
            }

        });



    }
}
