package com.example.cookierun.spgp2023.cookierun.game;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.cookierun.R;
import com.example.cookierun.spgp2023.framework.scene.RecycleBin;

import java.util.Random;

public class JellyItem extends MapObject {
    public static final int JELLY_COUNT = 60;
    private static final int ITEMS_IN_A_ROW = 30;
    private static final int SIZE = 66;
    private static final int BORDER = 2;
    public static final float INSET = 0.20f;
    protected Rect srcRect = new Rect();
    protected RectF collisionRect = new RectF();
    public int index;
    protected int[] SOUND_IDS = {
            R.raw.jelly,
            R.raw.jelly_alphabet,
            R.raw.jelly_item,
            R.raw.jelly_gold,
            R.raw.jelly_coin,
            R.raw.jelly_big_coin,
    };
    public int soundId() {
        return SOUND_IDS[index % SOUND_IDS.length];
    }

    JellyItem() {
        super(MainScene.Layer.item);
        setBitmapResource(R.mipmap.jelly);
        width = height = 1;
    }

    public static JellyItem get(int index, float left, float top) {
        JellyItem item = (JellyItem) RecycleBin.get(JellyItem.class);
        if (item == null) {
            item = new JellyItem();
        }
        item.init(index, left, top);
        return item;
    }

    public static int getRandomIndex(Random random) {
        return random.nextInt(JELLY_COUNT);
    }

    private void init(int index, float left, float top) {
        this.index = index;
        setSrcRect(index);
        dstRect.set(left, top, left + width, top + height);
    }

    private void setSrcRect(int index) {
        int x = index % ITEMS_IN_A_ROW;
        int y = index / ITEMS_IN_A_ROW;
        int left = x * (SIZE + BORDER) + BORDER;
        int top = y * (SIZE + BORDER) + BORDER;
        srcRect.set(left, top, left + SIZE, top + SIZE);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }

    @Override
    public void update() {
        super.update();
        collisionRect.set(
                dstRect.left + width * INSET,
                dstRect.top + height * INSET,
                dstRect.right - width * INSET,
                dstRect.bottom - height * INSET);
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }
}