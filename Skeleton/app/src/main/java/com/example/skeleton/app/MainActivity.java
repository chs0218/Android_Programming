package com.example.skeleton.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skeleton.game.MainScene;
import com.example.skeleton.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MainScene().pushScene();
    }
}