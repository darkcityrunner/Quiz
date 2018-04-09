package com.example.android.iqquiz2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by michaeldegroff on 3/21/18.
 */

public class SplashPage extends AppCompatActivity {

    LinearLayout layoutUpToDown, layoutDownToUp;
    Animation uptodown, downtoup;
    private Button untimedQuizButton;
    private Button timedQuizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_page);

        layoutUpToDown = (LinearLayout) findViewById(R.id.layoutUpToDown);
        layoutDownToUp = (LinearLayout) findViewById(R.id.layoutDownToUp);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        layoutUpToDown.setAnimation(uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        layoutDownToUp.setAnimation(downtoup);

        untimedQuizButton = (Button)findViewById(R.id.takeItSlowButton);
        untimedQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashPage.this, MainActivity.class);
                intent.putExtra("quizTimer",false);
                startActivity(intent);
            }
        });

        timedQuizButton = (Button)findViewById(R.id.raceTheClock);
        timedQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashPage.this, MainActivity.class);
                intent.putExtra("quizTimer",true);
                startActivity(intent);
            }
        });

    }
}