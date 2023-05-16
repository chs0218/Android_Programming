package com.example.cookierun.spgp2023.cookierun.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.cookierun.R;
import com.example.cookierun.spgp2023.cookierun.game.MainScene;
import com.example.cookierun.spgp2023.framework.scene.BaseScene;
import com.example.cookierun.spgp2023.framework.view.GameView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String PARAM_STAGE_INDEX = "stageIndex";
    private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        int stage = extras.getInt(PARAM_STAGE_INDEX);
        Log.d(TAG, "MainActivity.onCreate: stage=" + stage);
        gameView = new GameView(this);
        gameView.setFullScreen();
        setContentView(gameView);

        new MainScene(this, stage).pushScene();
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
        GameView.clear();
        super.onDestroy();
    }
}