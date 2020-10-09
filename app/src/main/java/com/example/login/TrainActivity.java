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

public class TrainActivity extends AppCompatActivity {

    private CardView train1,train2,train3,train4,train5,train6,train7,train8;
    static public int seat[] = new int[8];
    private TextView tvt1,tvt2,tvt3,tvt4,tvt5,tvt6,tvt7,tvt8;
    private ProgressDialog progressDialog;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        setUp();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        String UserUID = firebaseAuth.getUid().toString();
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        DatabaseReference myref = FirebaseDatabase.getInstance().getReference("TRAINSEATS");
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Planseats ps = snapshot.getValue(Planseats.class);
                tvt1.setText("Remaining seats : "+ps.getP1());
                seat[0]=ps.getP1();
                tvt2.setText("Remaining seats : "+ps.getP2());
                seat[1]=ps.getP2();
                tvt3.setText("Remaining seats : "+ps.getP3());
                seat[2]=ps.getP3();
                tvt4.setText("Remaining seats : "+ps.getP4());
                seat[3]=ps.getP4();
                tvt5.setText("Remaining seats : "+ps.getP5());
                seat[4]=ps.getP5();
                tvt6.setText("Remaining seats : "+ps.getP6());
                seat[5]=ps.getP6();
                tvt7.setText("Remaining seats : "+ps.getP7());
                seat[6]=ps.getP7();
                tvt8.setText("Remaining seats : "+ps.getP8());
                seat[7]=ps.getP8();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        
        train1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seat[0]!=0) {
                    Intent intent =new Intent(TrainActivity.this,TrainBooking.class);
                    intent.putExtra("T","1");
                    intent.putExtra("TNAME","Jodhpur Express");
                    intent.putExtra("TDEST","Surat -> Jodhpur");
                    intent.putExtra("TTIME","1:00 AM - 7:00 AM");
                    intent.putExtra("TPRICE","500");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(TrainActivity.this,"No seats Remaining!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        train2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seat[1]!=0) {
                    Intent intent =new Intent(TrainActivity.this,TrainBooking.class);
                    intent.putExtra("T","2");
                    intent.putExtra("TNAME","Garibrath Express");
                    intent.putExtra("TDEST","Surat -> Hridwar");
                    intent.putExtra("TTIME","2:00 AM - 3:00 PM");
                    intent.putExtra("TPRICE","1500");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(TrainActivity.this,"No seats Remaining!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        train3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seat[2]!=0) {
                    Intent intent =new Intent(TrainActivity.this,TrainBooking.class);
                    intent.putExtra("T","3");
                    intent.putExtra("TNAME","Hawrah Superfast");
                    intent.putExtra("TDEST","Kolkata -> Patna");
                    intent.putExtra("TTIME","5:30 AM - 7:00 AM");
                    intent.putExtra("TPRICE","1000");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(TrainActivity.this,"No seats Remaining!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        train4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seat[3]!=0) {
                    Intent intent =new Intent(TrainActivity.this,TrainBooking.class);
                    intent.putExtra("T","4");
                    intent.putExtra("TNAME","Chennai Express");
                    intent.putExtra("TDEST","Chennai -> Hyderabad");
                    intent.putExtra("TTIME","3:00 PM - 6:00 PM");
                    intent.putExtra("TPRICE","750");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(TrainActivity.this,"No seats Remaining!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        train5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seat[4]!=0) {
                    Intent intent =new Intent(TrainActivity.this,TrainBooking.class);
                    intent.putExtra("T","5");
                    intent.putExtra("TNAME","Ahmedabad Superfast");
                    intent.putExtra("TDEST","Mumbai -> Ahmedabad");
                    intent.putExtra("TTIME","6:00 AM - 11:30 AM");
                    intent.putExtra("TPRICE","2000");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(TrainActivity.this,"No seats Remaining!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        train6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seat[5]!=0) {
                    Intent intent =new Intent(TrainActivity.this,TrainBooking.class);
                    intent.putExtra("T","6");
                    intent.putExtra("TNAME","Surat Intercity");
                    intent.putExtra("TDEST","Surat -> Ahmedabad");
                    intent.putExtra("TTIME","10:00 AM - 4:45 PM");
                    intent.putExtra("TPRICE","450");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(TrainActivity.this,"No seats Remaining!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        train7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seat[6]!=0) {
                    Intent intent =new Intent(TrainActivity.this,TrainBooking.class);
                    intent.putExtra("T","7");
                    intent.putExtra("TNAME","Vadodara Intercity");
                    intent.putExtra("TDEST","Ahmedabad -> Vadodara");
                    intent.putExtra("TTIME","1:00 PM - 7:0 PM");
                    intent.putExtra("TPRICE","400");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(TrainActivity.this,"No seats Remaining!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        train8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seat[7]!=0) {
                    Intent intent =new Intent(TrainActivity.this,TrainBooking.class);
                    intent.putExtra("T","8");
                    intent.putExtra("TNAME","Gujarat Queen");
                    intent.putExtra("TDEST","Mumbai -> Ahmedabad");
                    intent.putExtra("TTIME","6:00 AM - 2:15 PM");
                    intent.putExtra("TPRICE","300");;
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(TrainActivity.this,"No seats Remaining!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setUp(){
        train1 = findViewById(R.id.train1);
        train2 = findViewById(R.id.train2);
        train3 = findViewById(R.id.train3);
        train4 = findViewById(R.id.train4);
        train5 = findViewById(R.id.train5);
        train6 = findViewById(R.id.train6);
        train7 = findViewById(R.id.train7);
        train8 = findViewById(R.id.train8);
        tvt1 = (TextView)findViewById(R.id.tvt1);
        tvt2 = (TextView)findViewById(R.id.tvt2);
        tvt3 = (TextView)findViewById(R.id.tvt3);
        tvt4 = (TextView)findViewById(R.id.tvt4);
        tvt5 = (TextView)findViewById(R.id.tvt5);
        tvt6 = (TextView)findViewById(R.id.tvt6);
        tvt7 = (TextView)findViewById(R.id.tvt7);
        tvt8 = (TextView)findViewById(R.id.tvt8);
    }
}