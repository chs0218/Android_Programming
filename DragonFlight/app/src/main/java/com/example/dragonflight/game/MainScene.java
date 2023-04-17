package com.example.dragonflight.game;

import android.util.Log;
import android.view.MotionEvent;

import com.example.dragonflight.R;
import com.example.dragonflight.framework.AnimSprite;
import com.example.dragonflight.framework.BaseScene;
import com.example.dragonflight.framework.IGameObject;
import com.example.dragonflight.framework.Metrics;

import java.util.ArrayList;

public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final Fighter fighter;
    public enum Layer {
        bg1, enemy, bullet, player, bg2, ui, controller, COUNT
    }
    private Score score;
    public MainScene() {
        initLayers(Layer.COUNT);
        fighter = new Fighter();
        add(Layer.player, fighter);
        add(Layer.bg1, new Background(R.mipmap.bg_city, 0.2f));
        add(Layer.bg2, new Background(R.mipmap.clouds, 0.4f));
//        add(Layer.bg1, new VertScrollBackground(R.mipmap.bg_city, 0.2f));
//        add(Layer.bg2, new VertScrollBackground(R.mipmap.clouds, 0.4f));
        score = new Score();
        add(Layer.ui, score);
        add(Layer.controller, new EnemyGenerator());
        add(Layer.controller, new CollisionChecker());
    }
    public void addScore(int amount) {
        score.add(amount);
    }
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float x = Metrics.toGameX(event.getX());
                float y = Metrics.toGameY(event.getY());
                fighter.setTargetPosition(x, y);
                return true;
        }
        return super.onTouchEvent(event);
    }
}
