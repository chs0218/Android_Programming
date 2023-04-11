package com.example.dragonflight.game;

import android.util.Log;
import android.view.MotionEvent;

import com.example.dragonflight.R;
import com.example.dragonflight.framework.AnimSprite;
import com.example.dragonflight.framework.BaseScene;
import com.example.dragonflight.framework.IGameObject;
import com.example.dragonflight.framework.Metrics;

public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final Fighter fighter;

    public MainScene() {
        fighter = new Fighter();
        add(fighter);
        add(new EnemyGenerator());
//        AnimSprite animSprite = new AnimSprite(R.mipmap.enemy_01, 4.5f, 5.0f, 1.8f, 1.8f, 10.0f, 0);
//        add(animSprite);
    }

    @Override
    public void update(long elapsedNanos) {
        super.update(elapsedNanos);
        checkCollsion();
    }

    private void checkCollsion() {
        for (IGameObject o1 : objects) {
            if (!(o1 instanceof Enemy)) {
                continue;
            }
            Enemy enemy = (Enemy) o1;
            for (IGameObject o2 : objects) {
                if (!(o2 instanceof Bullet)) {
                    continue;
                }
                Bullet bullet = (Bullet) o2;
                if (CollisionHelper.collides(enemy, bullet)) {
                    Log.d(TAG, "Collision !!");
                    remove(bullet);
                    remove(enemy);
                    break;
                }
            }
        }
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
