package com.govst.zacharyexp.caregiverbuddy.braingame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.govst.zacharyexp.caregiverbuddy.MainSelect;
import com.govst.zacharyexp.caregiverbuddy.R;

public class GameWon extends AppCompatActivity implements View.OnClickListener {

    Button playAgain, quit;
    TextView coinText, questionText;
    public String userName;
    public int coinValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_won);

        userName=getIntent().getExtras().getString("username");
        coinValue=getIntent().getExtras().getInt("CoinValue");
        playAgain = findViewById(R.id.playagainbutton);
        quit = findViewById(R.id.quitbutton);
        coinText= findViewById(R.id.coins);
        questionText = findViewById(R.id.questionText);
        setValue();
        playAgain.setOnClickListener(this);
        quit.setOnClickListener(this);

    }

    public void setValue()
    {
        coinText.setText("You have earned " + Integer.toString(coinValue) + " coins!!!");
        questionText.setText("Do you want to play again?");
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.playagainbutton:
                Intent intent = new Intent(GameWon.this, QuizActivity.class);
                intent.putExtra("username", userName);
                startActivity(intent);
                //finish();
                break;

            case R.id.quitbutton:
                Intent patientDashboardIntent = new Intent(GameWon.this, MainSelect.class);
                patientDashboardIntent.putExtra("username", userName);
                startActivity(patientDashboardIntent);
                break;
        }

    }
}
