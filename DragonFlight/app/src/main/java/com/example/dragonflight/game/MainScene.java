package com.example.dragonflight.game;

import android.view.MotionEvent;

import com.example.dragonflight.framework.BaseScene;
import com.example.dragonflight.framework.Metrics;

public class MainScene extends BaseScene {
    private final Fighter fighter;

    public MainScene() {
        fighter = new Fighter();
        add(fighter);
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
