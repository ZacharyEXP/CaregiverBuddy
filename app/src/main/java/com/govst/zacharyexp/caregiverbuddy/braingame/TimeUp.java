package com.govst.zacharyexp.caregiverbuddy.braingame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.govst.zacharyexp.caregiverbuddy.R;

public class TimeUp extends AppCompatActivity {

    Button playAgainButton;
    TextView timeUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_up);

        playAgainButton = findViewById(R.id.playAgainButton);
        timeUpText = findViewById(R.id.timeUpText);

        //play again button onclick listener
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimeUp.this, QuizActivity.class);
                startActivity(intent);
                finish();

            }

        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
