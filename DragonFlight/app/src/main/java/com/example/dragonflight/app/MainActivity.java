package com.example.dragonflight.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dragonflight.R;
import com.example.dragonflight.framework.GameView;
import com.example.dragonflight.game.MainScene;

public class MainActivity extends AppCompatActivity {
    private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);

        new MainScene().pushScene();
    }
}