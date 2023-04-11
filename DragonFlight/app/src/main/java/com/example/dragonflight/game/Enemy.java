package com.example.dragonflight.game;

import android.graphics.RectF;

import com.example.dragonflight.R;
import com.example.dragonflight.framework.AnimSprite;
import com.example.dragonflight.framework.BaseScene;
import com.example.dragonflight.framework.IBoxCollidable;
import com.example.dragonflight.framework.Metrics;
import com.example.dragonflight.framework.Sprite;

public class Enemy extends AnimSprite implements IBoxCollidable {

    private static final int[] resIds = {
            R.mipmap.enemy_01, R.mipmap.enemy_02, R.mipmap.enemy_03, R.mipmap.enemy_04, R.mipmap.enemy_05,
            R.mipmap.enemy_06, R.mipmap.enemy_07, R.mipmap.enemy_08, R.mipmap.enemy_09, R.mipmap.enemy_10,
            R.mipmap.enemy_11, R.mipmap.enemy_12, R.mipmap.enemy_13, R.mipmap.enemy_14, R.mipmap.enemy_15,
            R.mipmap.enemy_16, R.mipmap.enemy_17, R.mipmap.enemy_18, R.mipmap.enemy_19, R.mipmap.enemy_20,
    };

    public static final int MAX_LEVEL = resIds.length - 1;
    private static final float SPEED = 2.0f;
    private static final float SIZE = 1.8f;

    protected RectF collisionRect = new RectF();

    public Enemy(int index, int level) {
        super(resIds[level], (Metrics.game_width / 10) * (2 * index + 1), -SIZE, SIZE, SIZE, 10.0f, 0);
    }

    @Override
    public void update() {
        super.update();
        y += SPEED * BaseScene.frameTime;
        fixDstRect();

        if(dstRect.top > 16.0f) {
            BaseScene.getTopScene().remove(this);
        }

        collisionRect.set(dstRect);
        collisionRect.inset(0.11f, 0.11f);
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }
}