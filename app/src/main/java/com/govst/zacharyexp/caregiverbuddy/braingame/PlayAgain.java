package com.govst.zacharyexp.caregiverbuddy.braingame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.govst.zacharyexp.caregiverbuddy.R;

public class PlayAgain extends AppCompatActivity implements View.OnClickListener {

    Button playAgain;
    TextView wrongAnswerText;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_again);

        userName= getIntent().getExtras().getString("username");
        playAgain = findViewById(R.id.playAgainButton);
        wrongAnswerText = findViewById(R.id.wrongAns);

        playAgain.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {

        finish();
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.playAgainButton)
        {
            Intent intent = new Intent(PlayAgain.this, QuizActivity.class);
            intent.putExtra("username",userName);
            startActivity(intent);
            finish();
        }

    }
}
