package com.example.dell_pc.health_first;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.Map;

public class DietTable extends AppCompatActivity {

    private DatabaseReference databaseReference;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_table);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

       //get the current user UID
        if(user!=null)
        {
            uid = user.getUid();
        }

        //findViewById(R.id.edit).setOnClickListener(this);

        Button button = (Button) findViewById(R.id.edit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                Toast.makeText(getApplicationContext(), "Diet updated successfully...!!!",
                        Toast.LENGTH_SHORT).show();
            }
        });


        Button backButton = (Button) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent in=new Intent(DietTable.this,Diet.class);
                startActivity(in);

                /*mDatabase.child("users").child(mUserId).child("items").push().child("title").setValue(text.getText().toString());
                text.setText("");*/
            }
        });

        //get refrence to database
        databaseReference = FirebaseDatabase.getInstance().getReference().child(uid).child("diet");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                   /* GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};
                    List messages = dataSnapshot.getValue(t);*/

                GenericTypeIndicator<Map<String,Object>> t = new GenericTypeIndicator<Map<String,Object>>() {};
                // Map messages = dataSnapshot.getChildren();

                Iterator<DataSnapshot> itr=dataSnapshot.getChildren().iterator();
                TableLayout stk = (TableLayout) findViewById(R.id.activity_diet_table);
                TableRow tbrow0 = new TableRow(getApplicationContext());
                TextView tv0 = new TextView(getApplicationContext());
                tv0.setText(" Sl.No ");
                tv0.setTextColor(Color.GRAY);
                tbrow0.addView(tv0);
                TextView tv1 = new TextView(getApplicationContext());
                tv1.setText(" Food Name ");
                tv1.setTextColor(Color.GRAY);
                tbrow0.addView(tv1);
                TextView tv2 = new TextView(getApplicationContext());
                tv2.setText(" Serving Size ");
                tv2.setTextColor(Color.GRAY);
                tbrow0.addView(tv2);
                TextView tv3 = new TextView(getApplicationContext());
                tv3.setText(" Calories ");
                tv3.setTextColor(Color.GRAY);
                tbrow0.addView(tv3);
                stk.addView(tbrow0);
                int i=0;
                int total=0;

                while(itr.hasNext())
                {
                    String key=itr.next().getKey();

                    String tempfoodname = (String)dataSnapshot.child(key).child("foodname").getValue();
                    String tempcal = dataSnapshot.child(key).child("calories").getValue()+"";
                    String tempblood_ = dataSnapshot.child(key).child("servingSize").getValue()+"";

                    // table formation

                    TableRow tbrow = new TableRow(getApplicationContext());


                    TextView t4v = new TextView(getApplicationContext());
                    t4v.setText("" + i++);
                    t4v.setTextColor(Color.BLACK);
                    t4v.setGravity(Gravity.CENTER);

                    tbrow.addView(t4v);

                    EditText t1v = new EditText(getApplicationContext());
                    t1v.setText((String)dataSnapshot.child(key).child("foodname").getValue());
                    t1v.setTextColor(Color.BLACK);
                    t1v.setGravity(Gravity.CENTER);
                    t1v.setId(i);
                    tbrow.addView(t1v);



                    EditText t2v = new EditText(getApplicationContext());
                    t2v.setText(dataSnapshot.child(key).child("calories").getValue()+"");
                    t2v.setTextColor(Color.BLACK);
                    t2v.setGravity(Gravity.CENTER);
                    t1v.setId(i+1);
                    tbrow.addView(t2v);
                    total+=Integer.parseInt(dataSnapshot.child(key).child("calories").getValue()+"");

                    EditText t3v = new EditText(getApplicationContext());
                    t3v.setText(dataSnapshot.child(key).child("servingSize").getValue()+"");
                    t3v.setTextColor(Color.BLACK);
                    t3v.setGravity(Gravity.CENTER);
                    t1v.setId(i+2);
                    tbrow.addView(t3v);



                    stk.addView(tbrow);

                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }



        });

       // init();
    }




    public void saveInfo()
    {



    }




}
