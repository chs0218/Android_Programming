package com.example.cookierun.spgp2023.cookierun.game;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.cookierun.R;
import com.example.cookierun.spgp2023.framework.interfaces.IBoxCollidable;
import com.example.cookierun.spgp2023.framework.objects.AnimSprite;
import com.example.cookierun.spgp2023.framework.scene.BaseScene;

public class Player extends AnimSprite implements IBoxCollidable {
    private final float ground;
    private float jumpSpeed;
    private static final float JUMP_POWER = 9.0f;
    private static final float GRAVITY = 17.0f;
    private RectF collisionRect = new RectF();
    public Player() {
        super(R.mipmap.cookie_player_sheet, 2.0f, 6.0f, 2.0f, 2.0f, 8, 1);
        this.ground = y;
    }

    public void update() {
        if (state == State.jump || state == State.doubleJump) {
            float dy = jumpSpeed * BaseScene.frameTime;
            jumpSpeed += GRAVITY * BaseScene.frameTime;
            if (y + dy >= ground) {
                dy = ground - y;
                state = State.running;
            }
            y += dy;
            fixDstRect();
            fixCollisionRect();
        }
    }

    private void fixCollisionRect() {
        float[] insets = edgeInsetRatios[state.ordinal()];
        collisionRect.set(
                dstRect.left + width * insets[0],
                dstRect.top + height * insets[1],
                dstRect.right - width * insets[2],
                dstRect.bottom - height * insets[3]);
    }

    protected enum State {
        running, jump, doubleJump, falling, COUNT
    }
    protected static Rect[][] srcRects = {
            makeRects(100, 101, 102, 103), // State.running
            makeRects(7, 8),               // State.jump
            makeRects(1, 2, 3, 4),         // State.doubleJump
            makeRects(0),                  // State.falling
    };

    protected static float[][] edgeInsetRatios = {
            { 0.1f, 0.0f, 0.1f, 0.0f }, // State.running
            { 0.1f, 0.2f, 0.1f, 0.0f }, // State.jump
            { 0.2f, 0.2f, 0.2f, 0.0f }, // State.doubleJump
            { 0.2f, 0.0f, 0.2f, 0.0f }, // State.falling
    };

    protected static Rect[] makeRects(int... indices) {
        Rect[] rects = new Rect[indices.length];
        for (int i = 0; i < indices.length; i++) {
            int idx = indices[i];
            int l = 72 + (idx % 100) * 272;
            int t = 132 + (idx / 100) * 272;
            rects[i] = new Rect(l, t, l + 140, t + 140);
        }
        return rects;
    }

    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        Rect[] rects = srcRects[state.ordinal()];
        int frameIndex = Math.round(time * fps) % rects.length;
        canvas.drawBitmap(bitmap, rects[frameIndex], dstRect, null);
    }
    protected State state = State.running;

    public void jump() {
        if (state == State.running) {
            state = State.jump;
            jumpSpeed = -JUMP_POWER;
        } else if (state == State.jump) {
            jumpSpeed = -JUMP_POWER;
            state = State.doubleJump;
        }
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }
}