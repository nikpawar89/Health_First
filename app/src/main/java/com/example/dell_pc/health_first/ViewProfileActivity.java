package com.example.dell_pc.health_first;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewProfileActivity extends Activity {
    private EditText user,age,weight,hight,blood,doctor,EmerName,EmerContact;
    private Button submit,back;
    ProgressDialog progressDialog;
    int count,myNum ;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileview);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            String uid = user.getUid();
            //UserInformation userInformation = new UserInformation(firstName,lastName,age,phoneNumber,emergencyContact,username,password);


            Toast.makeText(this," your uid is "+uid,Toast.LENGTH_SHORT).show();
            databaseReference = FirebaseDatabase.getInstance().getReference().child(uid);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String uname = (String)dataSnapshot.child("userName").getValue();
                    String emecon = (String)dataSnapshot.child("emerName").getValue();
                    String blood_ = (String)dataSnapshot.child("blood").getValue();
                    doctor.setText(uname.toString());
                    blood.setText(blood_.toString());
                    EmerContact.setText(emecon.toString());

                    Log.v("name", (String)  uname);
                    Log.v("emename", (String)  emecon);
                    Log.v("blood", (String)  blood_);
                    //Toast.makeText(getApplicationContext(), acctname , Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }


        submit =(Button)findViewById(R.id.Submit);
        back =(Button)findViewById(R.id.Back);

        progressDialog = new ProgressDialog(this);

        Spinner dynamicSpinner = (Spinner) findViewById(R.id.spinner);
        String[] items = new String[] {"Male", "Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        dynamicSpinner.setAdapter(adapter);
        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                submitUser();

            }
        });
    }

    private void submitUser(){

        //user = (EditText)findViewById(R.id.UserID);
        age = (EditText)findViewById(R.id.AgeID);
        weight= (EditText)findViewById(R.id.WeightID);
        hight= (EditText)findViewById(R.id.HightID);
        blood = (EditText)findViewById(R.id.BloodID);
        doctor= (EditText)findViewById(R.id.doctorNameID);
        EmerName= (EditText)findViewById(R.id.emergencyNameID);
        EmerContact= (EditText)findViewById(R.id.emergencyNumberID);


       // String User = user.getText().toString().trim();
        String Age = age.getText().toString().trim();
        String Weight = weight.getText().toString().trim();
        String Hight = hight.getText().toString().trim();
        String Blood = blood.getText().toString().trim();
        String Doctor= doctor.getText().toString().trim();
        String emerName = EmerName.getText().toString().trim();
        String emerContact = EmerContact.getText().toString().trim();
        databaseReference = FirebaseDatabase.getInstance().getReference();



        if(TextUtils.isEmpty(Age) && TextUtils.isEmpty(Weight) && TextUtils.isEmpty(Hight) &&
                TextUtils.isEmpty(Doctor)&& TextUtils.isEmpty(emerName)&& TextUtils.isEmpty(emerContact)
                && TextUtils.isEmpty(Blood)){
            Toast.makeText(this," Please Fill The Fields Completely ",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Age.length() != 0 ){
            myNum = Integer.parseInt(Age);
            if (myNum > 150 || myNum < 18 ){
                Toast.makeText(this," Please Enter a Valid Age",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (emerContact.length() != 0 ){
            count = emerContact.length();
            if (count<10 || count>13){
                Toast.makeText(this," Please Enter a Valid Phone Number",Toast.LENGTH_SHORT).show();
                EmerContact.setText("");
                return;
            }
        }



        saveProfileInformation("Nikhil",Age,Weight,Hight,Blood,Doctor,emerName,emerContact);




    }

    private void saveProfileInformation(String userName, String weight,String height,String age,String blood,String doctor,
                                        String emerName ,String emerContact) {

        ProfileInformation submit = new ProfileInformation(userName,weight,height,age,blood,doctor,emerName,emerContact);

        databaseReference.child("Nikhil").setValue(submit);

        //displaying a success toast
        Toast.makeText(this," Profile Updated Successfully",Toast.LENGTH_SHORT).show();
    }

    public void homepage(View view) {
        //System should redirect to the homepage
        //Intent i=new Intent(ViewProfileActivity.this, HomeActivity.class);
        //startActivity(i);
    }
}
