package com.example.dell_pc.health_first;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class ViewProfileActivity extends Activity {
    Button b1,b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileview);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewProfileActivity.this, "Redirecting...", Toast.LENGTH_SHORT).show();
                Intent in=new Intent(ViewProfileActivity.this,UpdateProfileActivity.class);
                startActivity(in);



            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewProfileActivity.this, "Redirecting...", Toast.LENGTH_SHORT).show();
                Intent in=new Intent(ViewProfileActivity.this,HomeActivity.class);
                startActivity(in);


            }
        });




    }
}
