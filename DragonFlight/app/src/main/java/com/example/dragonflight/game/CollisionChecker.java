package com.example.dragonflight.game;

import android.graphics.Canvas;
import android.util.Log;

import com.example.dragonflight.framework.BaseScene;
import com.example.dragonflight.framework.CollisionHelper;
import com.example.dragonflight.framework.IGameObject;

import java.util.ArrayList;

public class CollisionChecker implements IGameObject {
    private static final String TAG = CollisionChecker.class.getSimpleName();

    @Override
    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();
        ArrayList<IGameObject> enemies = scene.getObjectsAt(MainScene.Layer.enemy);
        ArrayList<IGameObject> bullets = scene.getObjectsAt(MainScene.Layer.bullet);
        for (IGameObject o1 : enemies) {
            Enemy enemy = (Enemy) o1;
//            boolean removed = false;
            for (IGameObject o2 : bullets) {
                Bullet bullet = (Bullet) o2;
                if (CollisionHelper.collides(enemy, bullet)) {
                    Log.d(TAG, "Collision !!");
                    scene.remove(bullet); // is this recyclable?
                    scene.remove(enemy); // is this recyclable?
                    scene.addScore(enemy.getScore());
//                    removed = true;
                    break;
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
    }
}