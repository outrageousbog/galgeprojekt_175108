package com.outrageousbog.android.galgeprojekt;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeScreen extends AppCompatActivity implements View.OnClickListener{
Button mCat1, mCat2, mCat3;
MediaPlayer mp, backgroundMusic;

private final String TAG = "WelcomeScreen";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Log.d(TAG, "onCreate called");

        mp = MediaPlayer.create(this, R.raw.click1);
        backgroundMusic = MediaPlayer.create(this, R.raw.music);

        mCat1 = findViewById(R.id.category_friends);
        mCat2 = findViewById(R.id.category_jakob);
        mCat3 = findViewById(R.id.category_computergames);

        mCat1.setOnClickListener(this);
        mCat2.setOnClickListener(this);
        mCat3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){

        try {
            mp.start();
        }
        catch (NullPointerException e){
            Log.d(TAG,"click-mp " + e);
            mp = MediaPlayer.create(this, R.raw.click1);
            mp.start();
        }

        if (view == mCat1){
            Log.d(TAG, "category 1 clicked");

            Intent intent = new Intent(WelcomeScreen.this,MainActivity.class);
            intent.putExtra("key",1);
            startActivity(intent);

        } if (view == mCat2){
            Log.d(TAG, "category 2 clicked");
            Intent intent = new Intent(WelcomeScreen.this,MainActivity.class);
            intent.putExtra("key",2);
            startActivity(intent);
        } if (view == mCat3) {
            Log.d(TAG, "category 3 clicked");
            Intent intent = new Intent(WelcomeScreen.this, MainActivity.class);
            intent.putExtra("key", 3);
            startActivity(intent);
        }
    }

    /**
     * onResume() is responsible for starting an activity that has already been created.
     *
     */

    @Override
    protected void onResume(){
        Log.d(TAG,"onResume called");
        try {
            backgroundMusic.start();
        }
        catch (NullPointerException e){
            Log.d(TAG,"backgroundmusic: " + e);
            backgroundMusic = MediaPlayer.create(this, R.raw.music);
            backgroundMusic.start();
        }
        super.onResume();
    }

    /**
     * The MediaPLayers are getting released onStop(), as I don't want them to use the resources,
     * when the other activity is in use.
     */
    @Override
    protected void onStop() {
        Log.d(TAG,"onStop called");
        if (backgroundMusic != null) {
            Log.d(TAG,"Background-MP released");
            backgroundMusic.stop();
            backgroundMusic.reset();
            backgroundMusic.release();
            backgroundMusic=null;
        }
        if (mp != null) {
            Log.d(TAG,"click-MP released");
            mp.stop();
            mp.reset();
            mp.release();
            mp=null;
        }
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        Log.d(TAG,"onDestroy called");
        if (backgroundMusic != null) {
            Log.d(TAG,"Background-MP released");
            backgroundMusic.stop();
            backgroundMusic.reset();
            backgroundMusic.release();
            backgroundMusic=null;
        }
        if (mp != null) {
            Log.d(TAG,"click-MP released");
            mp.stop();
            mp.reset();
            mp.release();
            mp=null;
        }
        super.onDestroy();
    }

}