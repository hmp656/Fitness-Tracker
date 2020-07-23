package com.prodpingu.fitnesstracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void BMICal(View view) {
        Intent intent = new Intent(this, CaloricReqActivity.class);
        startActivity(intent);
    }

    public void Exercises(View view) {
        Intent intent = new Intent(this, ExerciseActivity.class);
        startActivity(intent);
    }

    public void LaunchTracker(View view) {
        Intent intent = new Intent(this, TrackerLogActivity.class);
        startActivity(intent);
    }
}
