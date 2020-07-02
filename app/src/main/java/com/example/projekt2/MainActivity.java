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

    int[] objects = {R.drawable.questionmark, R.drawable.rectangle, R.drawable.triangle, R.drawable.star};    // {rectangle, triangle}

    int[] objectSize = {200, 400, 600};                             // {small, medium , large}

    int[] objectSpeed = {5000, 2500, 1250};                         // {slow, normal, fast}

    int[] objectDirection = {360,-360};                             // {right,left}

    RotateAnimation rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        Intent i = getIntent();

        int object_type = i.getIntExtra("object type", 0);
        int object_size = i.getIntExtra("object size", 0);
        int object_direction = i.getIntExtra("object direction",0);
        int object_speed = i.getIntExtra("object speed",0);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        imageView.setImageResource(objects[object_type]);
        imageView.getLayoutParams().width = objectSize[object_size];

        rotate = new RotateAnimation(
                0, objectDirection[object_direction],
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(objectSpeed[object_speed]);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setRepeatCount(Animation.INFINITE);
        imageView.startAnimation(rotate);
    }

    public void goToSettings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }


}