package com.example.androidtest;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;


public class MainActivity extends Activity {

    private FrameLayout mFlContainer;
    private FrameLayout mFlCardBack;
    private FrameLayout mFlCardFront;

    private AnimatorSet mRightOutSet; // 右出动画
    private AnimatorSet mLeftInSet; // 左入动画

    private boolean mIsShowBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        mFlContainer = (FrameLayout) findViewById(R.id.main_fl_container);
        mFlCardBack = (FrameLayout) findViewById(R.id.main_fl_card_back);
        mFlCardFront = (FrameLayout) findViewById(R.id.main_fl_card_front);

        setAnimators(); // 设置动画
        setCameraDistance(); // 设置镜头距离
    }

    // 设置动画
    private void setAnimators() {
        mRightOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, +R.anim.anim_out);
        mLeftInSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, +R.anim.anim_in);

        // 设置点击事件
        mRightOutSet.addListener(new AnimatorListenerAdapter() {
            @Override public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mFlContainer.setClickable(false);
            }
        });
        mLeftInSet.addListener(new AnimatorListenerAdapter() {
            @Override public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mFlContainer.setClickable(true);
            }
        });
    }

    // 改变视角距离, 贴近屏幕
    private void setCameraDistance() {

        int distance = 16000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mFlCardFront.setCameraDistance(scale);
        mFlCardBack.setCameraDistance(scale);
    }

    // 翻转卡片
    public void flipCard(View view) {
        // 正面朝上
        if (!mIsShowBack) {
            mRightOutSet.setTarget(mFlCardFront);
            mLeftInSet.setTarget(mFlCardBack);
            mRightOutSet.start();
            mLeftInSet.start();
            mIsShowBack = true;
        } else { // 背面朝上
            mRightOutSet.setTarget(mFlCardBack);
            mLeftInSet.setTarget(mFlCardFront);
            mRightOutSet.start();
            mLeftInSet.start();
            mIsShowBack = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
