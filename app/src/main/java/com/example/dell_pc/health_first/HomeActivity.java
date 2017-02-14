package com.example.dell_pc.health_first;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {
    Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        b1 = (Button)findViewById(R.id.button);

       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               PopupMenu popupMenu=new PopupMenu(getApplicationContext(),v);

              // MenuInflater menuInflater=popupMenu.getMenuInflater();
               popupMenu.inflate(R.menu.activity_popupmenu);
               popupMenu.show();

               popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                   @Override
                   public boolean onMenuItemClick(MenuItem item) {
                       Toast.makeText(getApplicationContext(),item.toString(),Toast.LENGTH_SHORT).show();
                       if(item.toString().equals("Profile")){
                           Intent in=new Intent(HomeActivity.this,ViewProfileActivity.class);
                           startActivity(in);

                       }
                       return true;
                   }
               });
           }
       });



    }
}
