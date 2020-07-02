package com.example.projekt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    int objectsIndex = 0;
    int objectSizeIndex = 0;
    int objectSpeedIndex = 0;
    int objectDirectionIndex = 0;

    public boolean checkIfSelectionMissing(){
        RadioGroup r1 = (RadioGroup) findViewById(R.id.radioGroupObjects);
        RadioGroup r2 = (RadioGroup) findViewById(R.id.radioGroupSize);
        RadioGroup r3 = (RadioGroup) findViewById(R.id.radioGroupDirection);
        RadioGroup r4 = (RadioGroup) findViewById(R.id.radioGroupSpeed);
        // radio buttons aren't checked
        if((r1.getCheckedRadioButtonId() == -1)||(r2.getCheckedRadioButtonId() == -1)||
        (r3.getCheckedRadioButtonId() == -1)||(r4.getCheckedRadioButtonId() == -1)){
            return true;
        }
        return false;
    }
    public void goToMainActivity(View view){
        if(checkIfSelectionMissing()){
            Toast.makeText(this, "Please give a selection for every attribute!", Toast.LENGTH_SHORT).show();
        }
        // one of the radio buttons is checked
        else{
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("object type", objectsIndex);
            intent.putExtra("object size", objectSizeIndex);
            intent.putExtra("object direction", objectDirectionIndex);
            intent.putExtra("object speed", objectSpeedIndex);
            startActivity(intent);
        }
    }

    public void setRotatingObject(View view){
        RadioButton radioButton = (RadioButton) view;

        String radioButtonText = radioButton.getText().toString();
        switch(radioButtonText){
            case "Rectangle":
                objectsIndex = 1;
                break;
            case "Triangle":
                objectsIndex = 2;
                break;
            case "Star":
                objectsIndex = 3;
                break;
            case "small":
                objectSizeIndex = 0;
                break;
            case "medium":
                objectSizeIndex = 1;
                break;
            case "large":
                objectSizeIndex = 2;
                break;
            case "right":
                objectDirectionIndex = 0;
                break;
            case"left":
                objectDirectionIndex = 1;
                break;
            case "slow":
                objectSpeedIndex = 0;
                break;
            case "normal":
                objectSpeedIndex = 1;
                break;
            case "fast":
                objectSpeedIndex = 2;
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);
    }

}