package com.example.imageswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final int MIN_PAGE = 1;
    public int nPage = 1;
    private static final int[] IMG_RES_IDS = new int[] {
            R.mipmap.cat_1,
            R.mipmap.cat_2,
            R.mipmap.cat_3,
            R.mipmap.cat_4,
            R.mipmap.cat_5,
            R.mipmap.cat_6
    };
    private ImageView mainImageView;
    private TextView pageTextView;
    private Button prevButton, nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainImageView = findViewById(R.id.mainImageView);
        pageTextView = findViewById(R.id.mainText);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
    }

    private void setPage(int page){
        if (page < MIN_PAGE || page > IMG_RES_IDS.length) return;
        int resId = IMG_RES_IDS[page - 1];
        mainImageView.setImageResource(resId);
        this.nPage = page;
        setText();
    }

    private void setText(){
        pageTextView.setText(nPage + "/" + IMG_RES_IDS.length);
    }
    public void onBtnPrev(View view) {
        Log.d(TAG, "Prev pressed");
        setPage(nPage - 1);
        prevButton.setEnabled(nPage > IMG_RES_IDS.length);
        nextButton.setEnabled(nPage < MIN_PAGE);
    }

    public void onBtnNext(View view) {
        Log.d(TAG, "Next pressed");
        setPage(nPage + 1);
    }
}