package com.example.dell_pc.health_first;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ForgotPasswordActivity extends Activity  {
    Button b1,b2,b3;
    EditText ed1;

    TextView tx1;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        ed1 = (EditText)findViewById(R.id.editText);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ForgotPasswordActivity.this,MainActivity.class);
                startActivity(in);



            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed1.getText().toString().equals("tuktuk")){
                    Toast.makeText(getApplicationContext(),"Redirecting to Reset Password page",Toast.LENGTH_SHORT).show();
                    Intent in=new Intent(ForgotPasswordActivity.this,ResetPasswordActivity.class);
                    startActivity(in);

                }
            }
        });

    }
}