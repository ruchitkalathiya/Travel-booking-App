package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Splashscreen extends AppCompatActivity {

   private int SLEEP = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        getSupportActionBar().hide();
        Launcher launcher = new Launcher();
        launcher.start();
    }

    private class Launcher extends Thread{
        public void run(){
            try
            {
                sleep(1000 * SLEEP);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

            startActivity(new Intent(Splashscreen.this,MainActivity.class));
            Splashscreen.this.finish();
        }
    }
}