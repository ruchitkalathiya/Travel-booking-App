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

public class PaymentPlan extends AppCompatActivity {

    private String name,email,age,contact,pname,pdest,ptime,pprice,planNumber;
    private TextView tvname,tvemail,tvage,tvcontact;
    private Button btnPay,btnEditdetails;
    private EditText etCreditcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_plan);

        tvname=(TextView)findViewById(R.id.tv_mainName);
        tvage=(TextView)findViewById(R.id.tv_mainAge);
        tvcontact=(TextView)findViewById(R.id.tv_mainContact);
        tvemail=(TextView)findViewById(R.id.tv_mainEmail);
        btnPay=(Button)findViewById(R.id.btnPay);
        btnEditdetails=(Button)findViewById(R.id.btnEditdetail);
        etCreditcard=(EditText)findViewById(R.id.etCreditcard);

        btnEditdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentPlan.this,PlanBooking.class));
            }
        });

        name= getIntent().getStringExtra("NAME");
        email= getIntent().getStringExtra("EMAIL");
        age= getIntent().getStringExtra("AGE");
        contact= getIntent().getStringExtra("CONTACT");
        planNumber=getIntent().getStringExtra("P");
        pname=getIntent().getStringExtra("PNAME");
        pdest=getIntent().getStringExtra("PDEST");
        ptime=getIntent().getStringExtra("PTIME");
        pprice=getIntent().getStringExtra("PPRICE");

        tvname.setText(name);
        tvemail.setText(email);
        tvage.setText(age);
        tvcontact.setText(contact);


        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etCreditcard.getText().toString().isEmpty())
                    Toast.makeText(PaymentPlan.this,"Please Enter Credit Card Detail.",Toast.LENGTH_SHORT).show();
                else {
                    if (ValidateCreditCardNumber(etCreditcard.getText().toString())){
                       FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                       FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        String UserUID = firebaseAuth.getUid().toString();

                        final DatabaseReference myref = firebaseDatabase.getReference();
                        myref.child("PLANESEATS").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Planseats ps = snapshot.getValue(Planseats.class);
                                switch (Integer.parseInt(planNumber)){
                                    case 1 : {
                                        myref.child("PLANESEATS").child("p1").setValue(ps.getP1()-1);
                                        break;
                                    }
                                    case 2 : {
                                        myref.child("PLANESEATS").child("p2").setValue(ps.getP2()-1);
                                        break;
                                    }
                                    case 3 : {
                                        myref.child("PLANESEATS").child("p3").setValue(ps.getP3()-1);
                                        break;
                                    }
                                    case 4 : {
                                        myref.child("PLANESEATS").child("p4").setValue(ps.getP4()-1);
                                        break;
                                    }
                                    case 5 : {
                                        myref.child("PLANESEATS").child("p5").setValue(ps.getP5()-1);
                                        break;
                                    }
                                    case 6 : {
                                        myref.child("PLANESEATS").child("p6").setValue(ps.getP6()-1);
                                        break;
                                    }
                                    case 7 : {
                                        myref.child("PLANESEATS").child("p7").setValue(ps.getP7()-1);
                                        break;
                                    }
                                    case 8 : {
                                        myref.child("PLANESEATS").child("p8").setValue(ps.getP8()-1);
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
                    else {
                        Toast.makeText(PaymentPlan.this, "Invalid Credit Card.", Toast.LENGTH_SHORT).show();
                    }
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
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid()).child("PLANE").child("PLAN"+planNumber);
        BookUser bookUser = new BookUser(name,email,age,contact,pname,pdest,ptime,pprice);
        myRef.setValue(bookUser);
        Toast.makeText(PaymentPlan.this,"Payment Successful!",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(PaymentPlan.this,SecondActivity.class));
    }

}