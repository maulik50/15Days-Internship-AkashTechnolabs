package com.example.sampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    ImageView imageview;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        imageview = findViewById(R.id.splash_icon);

        AlphaAnimation animation = new AlphaAnimation(0,1);
        animation.setDuration(2500);
        imageview.startAnimation(animation);


        mediaPlayer = MediaPlayer.create(SplashActivity.this,R.raw.splash_sound);
        mediaPlayer.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                mediaPlayer.stop();
            }
        }, 3000);

                }
    }

