package com.example.cookierun.spgp2023.cookierun.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cookierun.R;
import com.example.cookierun.spgp2023.cookierun.game.MainScene;
import com.example.cookierun.spgp2023.framework.scene.BaseScene;
import com.example.cookierun.spgp2023.framework.view.GameView;

public class MainActivity extends AppCompatActivity {
    private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        gameView.setFullScreen();
        setContentView(gameView);

        new MainScene().pushScene();
    }

    protected void onPause() {
        gameView.pauseGame();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resumeGame();
    }

    @Override
    protected void onDestroy() {
        BaseScene.popAll();
        super.onDestroy();
    }
}