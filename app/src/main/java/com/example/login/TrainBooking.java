package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TrainBooking extends AppCompatActivity {

    private EditText trainName,trainEmail,trainAge,trainContact;
    private Button btnPayment;
    String name, email,age, contact;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private String tname,tdest,ttime,trainNumber,tprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_booking);

        firebaseAuth=FirebaseAuth.getInstance();
        setup();

        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

//        Intent intent = getIntent();
        trainNumber=getIntent().getStringExtra("T");
        tname=getIntent().getStringExtra("TNAME");
        tdest=getIntent().getStringExtra("TDEST");
        ttime=getIntent().getStringExtra("TTIME");
        tprice=getIntent().getStringExtra("TPRICE");

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Validate()){

                    sendUserData();
                }
                else
                {
                    Toast.makeText(TrainBooking.this,"Updation Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void setup(){
        trainAge = (EditText)findViewById(R.id.etTrainage);
        trainContact=(EditText)findViewById(R.id.etTrainmobile);
        trainName=(EditText)findViewById(R.id.etTrainname);
        trainEmail = (EditText)findViewById(R.id.etTrainemail);
        btnPayment=(Button)findViewById(R.id.btnPaymentTrain);
    }

    private Boolean Validate(){
        Boolean result = false;

        name=trainName.getText().toString();
        email=trainEmail.getText().toString();
        age=trainAge.getText().toString();
        contact=trainContact.getText().toString();


        if(name.isEmpty() || age.isEmpty() || email.isEmpty() || contact.isEmpty()){
            Toast.makeText(this,"Please Fill All Fields",Toast.LENGTH_SHORT).show();
        }else{
            result=true;
        }

        return result;
    }

    private void sendUserData(){



        Intent intent = new Intent(TrainBooking.this,PaymentTrain.class);
        intent.putExtra("NAME",name);
        intent.putExtra("EMAIL",email);
        intent.putExtra("AGE",age);
        intent.putExtra("CONTACT",contact);
        intent.putExtra("T",trainNumber);
        intent.putExtra("TNAME",tname);
        intent.putExtra("TDEST",tdest);
        intent.putExtra("TTIME",ttime);
        intent.putExtra("TPRICE",tprice);
        startActivity(intent);

    }

}