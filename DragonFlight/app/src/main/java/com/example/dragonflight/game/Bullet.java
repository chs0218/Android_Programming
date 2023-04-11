package com.example.dragonflight.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.dragonflight.R;
import com.example.dragonflight.framework.BaseScene;
import com.example.dragonflight.framework.IBoxCollidable;
import com.example.dragonflight.framework.IGameObject;
import com.example.dragonflight.framework.Sprite;

public class Bullet extends Sprite implements IBoxCollidable {
    private static final float BULLET_WIDTH = 28 * 0.0243f;
    private static final float BULLET_HEIGHT = 40 * 0.0243f;
    protected static float SPEED = 20.0f;
    protected static Paint paint;

    public Bullet(float x, float y) {
        super(R.mipmap.laser_1, x, y, BULLET_WIDTH, BULLET_HEIGHT);
        this.x = x;
        this.y = y;
    }
    @Override
    public void update() {
        float frameTime = BaseScene.frameTime;
        y += -SPEED * frameTime;
        fixDstRect();

        if (dstRect.bottom < 0) {
            BaseScene.getTopScene().remove(this);
        }
    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }
}