package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PaymentTrain extends AppCompatActivity {

    private String name,email,age,contact,tname,tdest,ttime,tprice,trainNumber;
    private TextView tvtname,tvtemail,tvtage,tvtcontact;
    private Button btntrainPay,btntrainEditdetails;
    private EditText ettrainCreditcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_train);

        tvtname=(TextView)findViewById(R.id.tvt_mainName);
        tvtage=(TextView)findViewById(R.id.tvt_mainAge);
        tvtcontact=(TextView)findViewById(R.id.tvt_mainContact);
        tvtemail=(TextView)findViewById(R.id.tvt_mainEmail);
        btntrainPay=(Button)findViewById(R.id.btntrainPay);
        btntrainEditdetails=(Button)findViewById(R.id.btntrainEditdetail);
        ettrainCreditcard=(EditText)findViewById(R.id.ettrainCreditcard);

        btntrainEditdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentTrain.this,TrainBooking.class));
            }
        });

        name= getIntent().getStringExtra("NAME");
        email= getIntent().getStringExtra("EMAIL");
        age= getIntent().getStringExtra("AGE");
        contact= getIntent().getStringExtra("CONTACT");
        trainNumber=getIntent().getStringExtra("T");
        tname=getIntent().getStringExtra("TNAME");
        tdest=getIntent().getStringExtra("TDEST");
        ttime=getIntent().getStringExtra("TTIME");
        tprice=getIntent().getStringExtra("TPRICE");

        tvtname.setText(name);
        tvtemail.setText(email);
        tvtage.setText(age);
        tvtcontact.setText(contact);


        btntrainPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ettrainCreditcard.getText().toString().isEmpty())
                    Toast.makeText(PaymentTrain.this,"Please Enter Credit Card Detail.",Toast.LENGTH_SHORT).show();
                else {
                    if (ValidateCreditCardNumber(ettrainCreditcard.getText().toString())){
                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        String UserUID = firebaseAuth.getUid().toString();

                        final DatabaseReference myref = firebaseDatabase.getReference();
                        myref.child("TRAINSEATS").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Planseats ps = snapshot.getValue(Planseats.class);
                                switch (Integer.parseInt(trainNumber)){
                                    case 1 : {
                                        myref.child("TRAINSEATS").child("p1").setValue(ps.getP1()-1);
                                        break;
                                    }
                                    case 2 : {
                                        myref.child("TRAINSEATS").child("p2").setValue(ps.getP2()-1);
                                        break;
                                    }
                                    case 3 : {
                                        myref.child("TRAINSEATS").child("p3").setValue(ps.getP3()-1);
                                        break;
                                    }
                                    case 4 : {
                                        myref.child("TRAINSEATS").child("p4").setValue(ps.getP4()-1);
                                        break;
                                    }
                                    case 5 : {
                                        myref.child("TRAINSEATS").child("p5").setValue(ps.getP5()-1);
                                        break;
                                    }
                                    case 6 : {
                                        myref.child("TRAINSEATS").child("p6").setValue(ps.getP6()-1);
                                        break;
                                    }
                                    case 7 : {
                                        myref.child("TRAINSEATS").child("p7").setValue(ps.getP7()-1);
                                        break;
                                    }
                                    case 8 : {
                                        myref.child("TRAINSEATS").child("p8").setValue(ps.getP8()-1);
                                        break;
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        Upload();
                    }
                    else
                        Toast.makeText(PaymentTrain.this, "Invalid Credit Card.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
    }

    private Boolean ValidateCreditCardNumber(String creditcard) {
        Boolean result = false;

        int[] number = new int[creditcard.length()];
        for (int i = 0; i < creditcard.length(); i++) {
            number[i] = Integer.parseInt(creditcard.substring(i, i + 1));
        }
        for (int i = number.length - 2; i >= 0; i = i - 2) {
            int j = number[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            number[i] = j;
        }
        int sum = 0;
        for (int i = 0; i < number.length; i++) {
            sum += number[i];
        }
        result = (sum % 10 == 0);

        return result;
    }

    private void Upload(){
        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid()).child("TRAIN").child("TRAIN"+trainNumber);
        BookUser bookUser = new BookUser(name,email,age,contact,tname,tdest,ttime,tprice);
        myRef.setValue(bookUser);
//        Intent intent =new Intent(PaymentTrain.this,TrainActivity.class);
        Toast.makeText(PaymentTrain.this,"Payment Successful!",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(PaymentTrain.this,SecondActivity.class));
    }

}