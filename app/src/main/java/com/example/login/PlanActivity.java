package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PlanActivity extends AppCompatActivity {

    private CardView plan1,plan2,plan3,plan4,plan5,plan6,plan7,plan8;
    private int[] seat = new int[8];
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8;
    private String planNumber;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        setUp();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        String UserUID = firebaseAuth.getUid().toString();
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        DatabaseReference myref = FirebaseDatabase.getInstance().getReference("PLANESEATS");
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              Planseats ps = snapshot.getValue(Planseats.class);
                tv1.setText("Remaining seats : "+ps.getP1());
                seat[0]=ps.getP1();
                tv2.setText("Remaining seats : "+ps.getP2());
                seat[1]=ps.getP2();
                tv3.setText("Remaining seats : "+ps.getP3());
                seat[2]=ps.getP3();
                tv4.setText("Remaining seats : "+ps.getP4());
                seat[3]=ps.getP4();
                tv5.setText("Remaining seats : "+ps.getP5());
                seat[4]=ps.getP5();
                tv6.setText("Remaining seats : "+ps.getP6());
                seat[5]=ps.getP6();
                tv7.setText("Remaining seats : "+ps.getP7());
                seat[6]=ps.getP7();
                tv8.setText("Remaining seats : "+ps.getP8());
                seat[7]=ps.getP8();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        plan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seat[0]!=0) {
                    Intent intent =new Intent(PlanActivity.this,PlanBooking.class);
                    intent.putExtra("P","1");
                    intent.putExtra("PNAME","Air India");
                    intent.putExtra("PDEST","Surat -> Ahmedabad");
                    intent.putExtra("PTIME","1:00 AM - 1:30 AM");
                    intent.putExtra("PPRICE","5000");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(PlanActivity.this,"No seats Remaining!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        plan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seat[1]!=0) {
                    Intent intent =new Intent(PlanActivity.this,PlanBooking.class);
                    intent.putExtra("P","2");
                    intent.putExtra("PNAME","Air India");
                    intent.putExtra("PDEST","Surat -> Hyderabad");
                    intent.putExtra("PTIME","3:00 AM - 4:00 AM");
                    intent.putExtra("PPRICE","7000");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(PlanActivity.this,"No seats Remaining!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        plan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seat[2]!=0) {
                    Intent intent =new Intent(PlanActivity.this,PlanBooking.class);
                    intent.putExtra("P","3");
                    intent.putExtra("PNAME","Air India");
                    intent.putExtra("PDEST","Ahemdabad -> Delhi");
                    intent.putExtra("PTIME","3:30 AM - 5:00 AM");
                    intent.putExtra("PPRICE","10000");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(PlanActivity.this,"No seats Remaining!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        plan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seat[3]!=0) {
                    Intent intent =new Intent(PlanActivity.this,PlanBooking.class);
                    intent.putExtra("P","4");
                    intent.putExtra("PNAME","Air India");
                    intent.putExtra("PDEST","Mumbai -> Goa");
                    intent.putExtra("PTIME","4:15 AM - 6:00 AM");
                    intent.putExtra("PPRICE","5000");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(PlanActivity.this,"No seats Remaining!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        plan5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seat[4]!=0) {
                    Intent intent =new Intent(PlanActivity.this,PlanBooking.class);
                    intent.putExtra("P","5");
                    intent.putExtra("PNAME","Spicejet");
                    intent.putExtra("PDEST","MUmbai -> Dhaka");
                    intent.putExtra("PTIME","6:00 AM - 8:30 AM");
                    intent.putExtra("PPRICE","15000");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(PlanActivity.this,"No seats Remaining!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        plan6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seat[5]!=0) {
                    Intent intent =new Intent(PlanActivity.this,PlanBooking.class);
                    intent.putExtra("P","6");
                    intent.putExtra("PNAME","Emirates");
                    intent.putExtra("PDEST","Surat -> Abu Dhabi");
                    intent.putExtra("PTIME","10:00 AM - 3:00 PM");
                    intent.putExtra("PPRICE","20000");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(PlanActivity.this,"No seats Remaining!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        plan7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seat[6]!=0) {
                    Intent intent =new Intent(PlanActivity.this,PlanBooking.class);
                    intent.putExtra("P","7");
                    intent.putExtra("PNAME","Air India");
                    intent.putExtra("PDEST","Kolkata -> Chennai");
                    intent.putExtra("PTIME","1:00 PM - 2:30 PM");
                    intent.putExtra("PPRICE","9000");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(PlanActivity.this,"No seats Remaining!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        plan8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seat[7]!=0) {
                    Intent intent =new Intent(PlanActivity.this,PlanBooking.class);
                    intent.putExtra("P","8");
                    intent.putExtra("PNAME","Air India");
                    intent.putExtra("PDEST","Kashmir -> Hyderabad");
                    intent.putExtra("PTIME","6:00 PM - 9:15 PM");
                    intent.putExtra("PPRICE","5000");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(PlanActivity.this,"No seats Remaining!",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void setUp(){
        plan1 = findViewById(R.id.plan1);
        plan2 = findViewById(R.id.plan2);
        plan3 = findViewById(R.id.plan3);
        plan4 = findViewById(R.id.plan4);
        plan5 = findViewById(R.id.plan5);
        plan6 = findViewById(R.id.plan6);
        plan7 = findViewById(R.id.plan7);
        plan8 = findViewById(R.id.plan8);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv4);
        tv5 = (TextView)findViewById(R.id.tv5);
        tv6 = (TextView)findViewById(R.id.tv6);
        tv7 = (TextView)findViewById(R.id.tv7);
        tv8 = (TextView)findViewById(R.id.tv8);
    }
}