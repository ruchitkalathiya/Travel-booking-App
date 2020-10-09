package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class yourBooking extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private ListView listViewp,listViewt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourbooking);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        listViewp=(ListView)findViewById(R.id.lview);
        listViewt=(ListView)findViewById(R.id.lviewt);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        String UserUID = firebaseAuth.getUid().toString();


        final ArrayList<String> listp = new ArrayList<>(),listt=new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list_item,listp),adapter1=new ArrayAdapter<String>(this,R.layout.list_item,listt);
        listViewp.setAdapter(adapter);
        listViewt.setAdapter(adapter1);

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference(UserUID+"/PLANE");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    planeDetails planedetails = snapshot1.getValue(planeDetails.class);
                    listp.add(planedetails.toString());
                }
                adapter.notifyDataSetChanged();}
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        final DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference(UserUID+"/TRAIN");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    trainDetails traindetails = snapshot1.getValue(trainDetails.class);
                    listt.add(traindetails.toString());
                }
                adapter1.notifyDataSetChanged();}
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        progressDialog.dismiss();

    }
}