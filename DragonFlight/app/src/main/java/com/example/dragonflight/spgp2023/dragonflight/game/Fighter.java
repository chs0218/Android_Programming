package com.example.dragonflight.spgp2023.dragonflight.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.dragonflight.R;
import com.example.dragonflight.spgp2023.framework.scene.BaseScene;
import com.example.dragonflight.spgp2023.framework.res.BitmapPool;
import com.example.dragonflight.spgp2023.framework.objects.Sprite;
import com.example.dragonflight.spgp2023.framework.view.Metrics;

public class Fighter extends Sprite {
    //private static final float FIGHTER_X = 4.5f;
    private static final float FIGHTER_Y_OFFSET = 1.2f;
    private static final float FIGHTER_WIDTH = 72 * 0.0243f; //1.75f;
    private static final float FIGHTER_HEIGHT = 80 * 0.0243f; //1.75f;
    private static final float TARGET_RADIUS = 0.5f;
    private static final float SPEED = 10.0f;
    private static final float FIGHTER_LEFT = FIGHTER_WIDTH / 2;
    private static final float FIGHTER_RIGHT = 9.0f - FIGHTER_WIDTH / 2;

    private Bitmap sparkBitmap;
    private RectF sparkRect = new RectF();
    private static final float SPARK_WIDTH = 50 * 0.0243f;
    private static final float SPARK_HEIGHT = 30 * 0.0243f;
    private static final float SPARK_OFFSET = 0.7f;
    private float tx;
    private Bitmap targetBitmap;
    private RectF targetRect = new RectF();
    private static final float FIRE_INTERVAL = 0.25f;
    private static final float SPARK_DURATION = 0.1f;
    private float accumulatedTime;

    private static final Rect[] rects = new Rect[] {
            new Rect(  8, 0,   8 + 42, 80),
            new Rect( 76, 0,  76 + 42, 80),
            new Rect(140, 0, 140 + 50, 80),
            new Rect(205, 0, 205 + 56, 80),
            new Rect(270, 0, 270 + 62, 80),
            new Rect(334, 0, 334 + 70, 80),
            new Rect(406, 0, 406 + 62, 80),
            new Rect(477, 0, 477 + 56, 80),
            new Rect(549, 0, 549 + 48, 80),
            new Rect(621, 0, 621 + 42, 80),
            new Rect(689, 0, 689 + 42, 80),
    };
    public Fighter() {
        super(R.mipmap.fighters, Metrics.game_width / 2, Metrics.game_height - FIGHTER_Y_OFFSET, FIGHTER_WIDTH, FIGHTER_HEIGHT); targetBitmap = BitmapPool.get(R.mipmap.target);
        sparkBitmap = BitmapPool.get(R.mipmap.laser_0);
        tx = x;
    }
    public void setTargetPosition(float tx, float ty) {
        this.tx = tx;
        targetRect.set(
                tx - TARGET_RADIUS, y - TARGET_RADIUS,
                tx + TARGET_RADIUS, y + TARGET_RADIUS);
    }

    private void checkFire() {
        accumulatedTime += BaseScene.frameTime;
        if (accumulatedTime < FIRE_INTERVAL) {
            return;
        }

        accumulatedTime -= FIRE_INTERVAL;
        //accumulatedTime = 0; // ??
        fire();
    }

    public void fire() {
        MainScene scene = (MainScene) BaseScene.getTopScene();
        int score = scene.getScore();
        int power = 10 + score / 1000;
        Bullet bullet = Bullet.get(x, y, power);
        BaseScene.getTopScene().add(MainScene.Layer.bullet, bullet);
    }
    @Override
    public void update() {
        super.update();

        float time = BaseScene.frameTime;

        if (tx >= x) {
            x += SPEED * time;
            if (x > tx) {
                x = tx;
            }
        } else {
            x -= SPEED * time;
            if (x < tx) {
                x = tx;
            }
        }

        if (x < FIGHTER_LEFT) x = tx = FIGHTER_LEFT;
        if (x > FIGHTER_RIGHT) x = tx = FIGHTER_RIGHT;

        fixDstRect();
        checkFire();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, rects[5], dstRect, null);
        if (tx != x) {
            canvas.drawBitmap(targetBitmap, null, targetRect, null);
        }

        if (accumulatedTime < SPARK_DURATION) {
            sparkRect.set(x - SPARK_WIDTH / 2, y - SPARK_HEIGHT / 2 - SPARK_OFFSET,
                    x + SPARK_WIDTH / 2, y + SPARK_HEIGHT / 2 - SPARK_OFFSET);
            canvas.drawBitmap(sparkBitmap, null, sparkRect, null);
        }
    }
}
