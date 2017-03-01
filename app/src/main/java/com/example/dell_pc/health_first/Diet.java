package com.example.dell_pc.health_first;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Diet extends AppCompatActivity implements View.OnClickListener{

    private EditText medittextfoodname;
    private EditText meditTextcalorie;
    private EditText meditTextservingsize;
     private DatabaseReference databaseReference;
    Map<String,Object> taskMap = new HashMap<String,Object>();
    List<DietFields> foodList;
    Map<String,Object> foodMap = new HashMap<String,Object>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();//get the current user UID
            Toast.makeText(getApplicationContext(), "Please Enter Valid Food name",
                    Toast.LENGTH_SHORT).show();
            //get refrence to database
            databaseReference = FirebaseDatabase.getInstance().getReference().child(uid).child("diet");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    GenericTypeIndicator<Map<String,Object>> genericTypeIndicator =new GenericTypeIndicator<Map<String,Object>>(){};

                    foodMap=dataSnapshot.getValue(genericTypeIndicator);

                   // foodList=dataSnapshot.child("foodname").getValue();

                   /* String firstname_ = (String)dataSnapshot.child("firstName").getValue();
                    String lastname_ = (String)dataSnapshot.child("lastName").getValue();
                    Long phoneno_ = (Long)dataSnapshot.child("phoneNumber").getValue();
                    String age_ = (String) dataSnapshot.child("age").getValue();
                    String weight_ = (String)dataSnapshot.child("weight").getValue();
                    String height_ = (String)dataSnapshot.child("height").getValue();
                    String blood_ = (String)dataSnapshot.child("blood").getValue();
                    String docname_ = (String)dataSnapshot.child("doctor").getValue();
                    String emeconname = (String)dataSnapshot.child("emerName").getValue();
                    String emeconno = (String)dataSnapshot.child("emerContact").getValue();*/


                  /*  if (firstname_ != null)
                    {
                        taskMap.put("firstName", firstname_);
                    }
                    if (lastname_ != null)
                    {
                        taskMap.put("lastName",lastname_);
                    }
                    if (phoneno_.toString().length()!=0)
                    {
                        taskMap.put("phoneNumber",phoneno_.toString());
                    }
                   if (age_ != null)
                    {
                        taskMap.put("age",age_);
                    }
                    if (weight_ != null)
                    {
                        taskMap.put("weight",weight_);
                    }
                    if (height_ != null)
                    {
                        taskMap.put("height",height_);
                    }
                    if(blood_ != null)
                    {
                        taskMap.put("height",blood_);
                    }
                    if (docname_ != null)
                    {
                        taskMap.put("doctor",docname_);
                    }
                    if (emeconname != null)
                    {
                        taskMap.put("emerName",emeconname);


                    }
                    if(emeconno != null)
                    {
                        taskMap.put("emerContact",emeconno);
                    }
                    if()
                    {
                        taskMap.put("genedr",geneder);
                    }*/

                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }

            });
        }
        //end pulling data from database


        medittextfoodname = (EditText) findViewById(R.id.editTextfoodname);
        meditTextservingsize = (EditText) findViewById(R.id.editTextservingsize);
        meditTextcalorie = (EditText) findViewById(R.id.editTextcalorie);


        findViewById(R.id.button4add).setOnClickListener(this);
        findViewById(R.id.button5edit).setOnClickListener(this);
        findViewById(R.id.button6back).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String food= medittextfoodname.getText().toString();
        String calorie= meditTextcalorie.getText().toString();
        String servingsize= meditTextservingsize.getText().toString();

        boolean valid =true;
        int id=v.getId();
        if(id==R.id.button4add)
        {
            if(medittextfoodname.getText().toString().length()==0 || !isvalidString(medittextfoodname.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please Enter Valid Food name",
                        Toast.LENGTH_SHORT).show();
                valid=false;
            }

            if(meditTextservingsize.getText().toString().length()==0  || (!(TextUtils.isDigitsOnly(meditTextservingsize.getText().toString())))
                    ){



                Toast.makeText(getApplicationContext(), "Please Enter valid Serving size",
                        Toast.LENGTH_SHORT).show();

                valid=false;

            }

            if(meditTextservingsize.getText().toString().length()!=0 && (TextUtils.isDigitsOnly(meditTextservingsize.getText().toString())))
            {
                if(!(Integer.parseInt(meditTextservingsize.getText().toString())>=1 &&

                        Integer.parseInt(meditTextservingsize.getText().toString())<=100))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter serving size in the range of 1-100",
                            Toast.LENGTH_SHORT).show();
                    valid=false;
                }
            }

            if(meditTextcalorie.getText().toString().length()==0 || !(TextUtils.isDigitsOnly(meditTextcalorie.getText().toString()) ))
            {

                Toast.makeText(getApplicationContext(), "Please Enter valid value for Calorie ",
                        Toast.LENGTH_SHORT).show();
                valid=false;
            }

            if((meditTextcalorie.getText().toString().length()!=0) && (TextUtils.isDigitsOnly(meditTextcalorie.getText().toString()) ))
            {
                if(!(Integer.parseInt(meditTextcalorie.getText().toString())>=1 &&

                        Integer.parseInt(meditTextcalorie.getText().toString())<=100 ))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter serving  ",
                            Toast.LENGTH_SHORT).show();
                    valid=false;
                }
            }



            if(valid)
            {

                saveDietInfo(food,Integer.parseInt(servingsize),Integer.parseInt(calorie));



            }

        }

        if(id==R.id.button6back){



            Intent in=new Intent(Diet.this,HomeActivity.class);
            startActivity(in);



        }
    }



    public boolean isvalidString(String food){
        Pattern pattern;
        Matcher matcher;

        String food_pattern= "[a-zA-Z]+";
        pattern = Pattern.compile(food_pattern);
        matcher=pattern.matcher(food);

        return matcher.matches();
    }

    public void saveDietInfo(String foodname,int servingSize,int calories)
    {


        Toast.makeText(this,"Save info called",Toast.LENGTH_SHORT).show();
        DietFields field=new DietFields(foodname,servingSize,calories);
        //foodList.add(field);

       // foodMap.put("","")


        foodMap.put("foodname",foodname);
        foodMap.put("servingSize",servingSize);
        foodMap.put("calories",calories);



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            String uid = user.getUid();//get the current user UID
            Toast.makeText(this,"uid while saving data is "+uid,Toast.LENGTH_SHORT).show();
           //databaseReference.updateChildren(dietvals);



            DatabaseReference ref=databaseReference.push();
            ref.setValue(foodMap);

        Toast.makeText(this, " Profile Updated Successfully", Toast.LENGTH_SHORT).show();
            Intent in=new Intent(Diet.this,HomeActivity.class);
            startActivity(in);

        }

    }
}