package com.govst.zacharyexp.caregiverbuddy.braingame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.govst.zacharyexp.caregiverbuddy.R;

public class InsertQuestionsActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etQuestion, etOptionA, etOptionB, etOptionC, etOptionD,etAnswer;
    Button nextButton,doneButton, insertButton;
    String question, optiona, optionb, optionc,optiond,answer;
    String userName;
    //DatabaseHelper db;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_questions);
        userName=getIntent().getExtras().getString("username");
        //db= new DatabaseHelper(this);
        etQuestion= findViewById(R.id.question);
        etOptionA= findViewById(R.id.optiona);
        etOptionB= findViewById(R.id.optionb);
        etOptionC= findViewById(R.id.optionc);
        etOptionD= findViewById(R.id.optiond);
        etAnswer= findViewById(R.id.answer);
        nextButton= findViewById(R.id.nextbutton);
        doneButton= findViewById(R.id.donebutton);
        insertButton= findViewById(R.id.insertbutton);
        nextButton.setOnClickListener(this);
        doneButton.setOnClickListener(this);
        insertButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


        switch(view.getId()) {
            case R.id.insertbutton:
                question= etQuestion.getText().toString();
                optiona=etOptionA.getText().toString();
                optionb=etOptionB.getText().toString();
                optionc=etOptionC.getText().toString();
                optiond=etOptionD.getText().toString();
                answer=etAnswer.getText().toString();
                Toast.makeText(getApplicationContext(), "Successfully inserted", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nextbutton:
                Intent nextQuestion = new Intent(InsertQuestionsActivity.this, InsertQuestionsActivity.class);
                nextQuestion.putExtra("username", userName);
                startActivity(nextQuestion);
                break;

            case R.id.donebutton:
               // finish();
                Intent quizActivity =  new Intent(InsertQuestionsActivity.this, QuizMainActivity.class);
                quizActivity.putExtra("username", userName);
                startActivity(quizActivity);
                break;
        }
    }

}
