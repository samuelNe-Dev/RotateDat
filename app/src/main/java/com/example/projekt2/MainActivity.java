package com.example.projekt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    /* {rectangle, triangle} */
    private int[] objects = {R.drawable.questionmark, R.drawable.rectangle, R.drawable.triangle, R.drawable.star};

    /* {small, medium , large} */
    private int[] objectSize = {200, 400, 600};

    /* {slow, normal, fast} */
    private int[] objectSpeed = {5000, 2500, 1250};

    /* {right,left} */
    private int[] objectDirection = {360,-360};

    /**
     * This Function sets the values for the rotating object and starts the animation.
     * @param i
     */
    public void setAndStartRotatingObject(Intent i){
        int[] values = new int[4];
        values[0] = i.getIntExtra("object type", 0);
        values[1] = i.getIntExtra("object size", 0);
        values[2] = i.getIntExtra("object direction",0);
        values[3] = i.getIntExtra("object speed",0);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        imageView.setImageResource(objects[values[0]]);
        imageView.getLayoutParams().width = objectSize[values[1]];

        RotateAnimation rotate = new RotateAnimation(
                0, objectDirection[values[2]],
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(objectSpeed[values[3]]);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setRepeatCount(Animation.INFINITE);

        imageView.startAnimation(rotate);
        
    }

    /**
     * This function starts the SettingsActivity.
     * @param view
     */
    public void goToSettings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        /* Fullscreen for MainActivity */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        setAndStartRotatingObject(i);

    }
}
