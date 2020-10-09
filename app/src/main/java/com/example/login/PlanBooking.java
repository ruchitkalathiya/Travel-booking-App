package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PlanBooking extends AppCompatActivity {

    private EditText planName,planEmail,planAge,planContact;
    private Button btnPayment;
    String name, email,age, contact;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private String pname,pdest,ptime,planNumber,pprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_booking);

        firebaseAuth=FirebaseAuth.getInstance();
        setup();

        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        planNumber=getIntent().getStringExtra("P");
        pname=getIntent().getStringExtra("PNAME");
        pdest=getIntent().getStringExtra("PDEST");
        ptime=getIntent().getStringExtra("PTIME");
        pprice=getIntent().getStringExtra("PPRICE");

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Validate()){

                    sendUserData();
                }
                else
                {
                    Toast.makeText(PlanBooking.this,"Updation Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void setup(){
        planAge = (EditText)findViewById(R.id.etPlanage);
        planContact=(EditText)findViewById(R.id.etPlanmobile);
        planName=(EditText)findViewById(R.id.etPlanname);
        planEmail = (EditText)findViewById(R.id.etPlanemail);
        btnPayment=(Button)findViewById(R.id.btnPaymentPlan);
    }

    private Boolean Validate(){
        Boolean result = false;

        name=planName.getText().toString();
        email=planEmail.getText().toString();
        age=planAge.getText().toString();
        contact=planContact.getText().toString();


        if(name.isEmpty() || age.isEmpty() || email.isEmpty() || contact.isEmpty()){
            Toast.makeText(this,"Please Fill All Fields",Toast.LENGTH_SHORT).show();
        }else{
            result=true;
        }

        return result;
    }

    private void sendUserData(){
        Intent intent = new Intent(PlanBooking.this,PaymentPlan.class);
        intent.putExtra("NAME",name);
        intent.putExtra("EMAIL",email);
        intent.putExtra("AGE",age);
        intent.putExtra("CONTACT",contact);
        intent.putExtra("P",planNumber);
        intent.putExtra("PNAME",pname);
        intent.putExtra("PDEST",pdest);
        intent.putExtra("PTIME",ptime);
        intent.putExtra("PPRICE",pprice);
        startActivity(intent);
    }

}