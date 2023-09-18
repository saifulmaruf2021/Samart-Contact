package com.example.smartcontact;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

public class splash_screen extends AppCompatActivity {

    ShimmerTextView tvContact;
    Animation left_to_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for_full_screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_splash_screen);

        tvContact = findViewById(R.id.tvContact);
        Shimmer shimmer = new Shimmer();
        shimmer.start(tvContact);

        left_to_right = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
        tvContact.setAnimation(left_to_right);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent myInt = new Intent(splash_screen.this, MainActivity.class);
                startActivity(myInt);
                finish();
            }
        },3000);


    }
}