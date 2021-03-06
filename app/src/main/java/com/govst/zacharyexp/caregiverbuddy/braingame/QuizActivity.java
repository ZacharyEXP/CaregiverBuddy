package com.govst.zacharyexp.caregiverbuddy.braingame;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.govst.zacharyexp.caregiverbuddy.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private Button buttonA, buttonB,buttonC, buttonD;
    TextView questionText, timeText, coinText, resultText,quizText;
    String userName;
    //DatabaseHelper databaseHelper;
    QuizQuestion currentQuestion;
    List<QuizQuestion> list;
    int timeValue=20, qid=0;
    int coinValue=0;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        list = new ArrayList<>();

        //userName = getIntent().getExtras().getString("username");
        buttonA= findViewById(R.id.buttonA);
        buttonB= findViewById(R.id.buttonB);
        buttonC= findViewById(R.id.buttonC);
        buttonD= findViewById(R.id.buttonD);
        questionText= findViewById(R.id.question);
        timeText = findViewById(R.id.timeText);
        coinText = findViewById(R.id.coinText);
        quizText = findViewById(R.id.quizText);
        resultText = findViewById(R.id.resultText);

        //databaseHelper = new DatabaseHelper(this);
        //databaseHelper.getWritableDatabase();

        //if(databaseHelper.getAllQuestions(userName).size()==0)
        //{
            //databaseHelper.Questions();
            //finish();
        //}

        //databaseHelper.updateQuestion();
        //list = databaseHelper.getAllQuestions(userName);
        list.add(new QuizQuestion("a", "15 * 10 = ?", "15", "45", "150", "0","150"));
        list.add(new QuizQuestion("a", "What word is most similar to the word 'infrequent'?", "Often", "Rarely", "Always", "Visit","Rarely"));
        list.add(new QuizQuestion("a", "220 - 72 = ?", "152", "68", "54", "148","148"));
        list.add(new QuizQuestion("a", "What was the answer to the previous question?", "23", "148", "54", "38","148"));

        list.add(new QuizQuestion("a", "Fill in the vowel(s): Ins_rt", "a", "e", "o", "u","e"));
        list.add(new QuizQuestion("a", "What word is most similar to the word 'hue'?", "Color", "Texture", "Opacity", "Laughter","Color"));
        list.add(new QuizQuestion("a", "Fill in the vowel(s): M_n_ge", "a",  "i", "o","u", "a"));
        list.add(new QuizQuestion("a", "What was the answer to the second question?", "Often", "Rarely", "Always", "Visit","Rarely"));

        list.add(new QuizQuestion("a", "9 + 99 = ?", "108", "90", "81", "999","108"));
        list.add(new QuizQuestion("a", "128 / 2 = ?", "2", "64", "256", "88","64"));
        list.add(new QuizQuestion("a", "What word is most similar to the word 'abstract'?", "Shape", "Drawing", "Person", "Vague","Vague"));
        list.add(new QuizQuestion("a", "What was the answer to the question before the previous one?", "2", "64", "256", "88","64"));


        list.add(new QuizQuestion("a", "Fill in the vowel(s): Q_irk", "a", "e", "o", "u","u"));
        list.add(new QuizQuestion("a", "82 - 85 = ?", "3", "-3", "167", "2","-3"));
        list.add(new QuizQuestion("a", "What word is most similar to the word 'eccentric'?", "Rare", "Peculiar", "Normal", "Central","Peculiar"));
        list.add(new QuizQuestion("a", "What word was the subject of the fifth question?", "Color", "Insert", "Opacity", "Laughter","Insert"));

        list.add(new QuizQuestion("a", "Fill in the vowel(s): W_uld", "a", "e", "i", "o","o"));
        list.add(new QuizQuestion("a", "What word is most similar to the word 'marvelous'?", "Amazing", "Boring", "Watching", "Laughter","Amazing"));
        list.add(new QuizQuestion("a", "Fill in the vowel(s): Br_in", "a", "e", "o", "u","a"));
        list.add(new QuizQuestion("a", "What was the answer to the first question?", "15", "45", "150", "0","150"));
        //Collections.shuffle(list);

        currentQuestion = list.get(qid);

        countDownTimer = new CountDownTimer(22000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timeText.setText(String.valueOf(timeValue) + "\"");
                timeValue -=1;

                if(timeValue==-1)
                {
                    resultText.setText("Time's Up!!!");
                    disableButton();
                }
            }

            @Override
            public void onFinish() {
                timeUp();

            }
        }.start();

        updateQuestionsAndOptions();

    }

    public void updateQuestionsAndOptions()
    {
        questionText.setText(currentQuestion.getQuestion());
        buttonA.setText(currentQuestion.getOptiona());
        buttonB.setText(currentQuestion.getOptionb());
        buttonC.setText(currentQuestion.getOptionc());
        buttonD.setText(currentQuestion.getOptiond());

        timeValue=20;

        countDownTimer.cancel();
        countDownTimer.start();

        coinText.setText(String.valueOf(coinValue));
        coinValue++;

    }

    public void buttonA(View view)
    {
        if (currentQuestion.getOptiona().equals(currentQuestion.getAnswer())) {
            buttonA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
            //Check if user has not exceeds the que limit
            if (qid < list.size() - 1) {

                //Now disable all the option button since user ans is correct so
                //user won't be able to press another option button after pressing one button
                disableButton();

                //Show the dialog that ans is correct
                correctDialog();
            }
            //If user has exceeds the que limit just navigate him to GameWon activity
            else {

                gameWon();

            }
        }
        //User ans is wrong then just navigate him to the PlayAgain activity
        else {

            gameLostPlayAgain();

        }

    }

    public void buttonB(View view)
    {
        if (currentQuestion.getOptionb().equals(currentQuestion.getAnswer())) {
            buttonB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
            //Check if user has not exceeds the que limit
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            }

            else {
                gameWon();
            }
        }
        else {

            gameLostPlayAgain();
        }
    }

    public void buttonC(View view)
    {
        if (currentQuestion.getOptionc().equals(currentQuestion.getAnswer())) {
            buttonC.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
            //Check if user has not exceeds the que limit
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            }

            else {

                gameWon();

            }
        }

        else {

            gameLostPlayAgain();

        }

    }

    public void buttonD(View view)
    {
        if (currentQuestion.getOptiond().equals(currentQuestion.getAnswer())) {
            buttonD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
            if (qid < list.size() - 1) {

                disableButton();
                correctDialog();
            }

            else {

                gameWon();

            }
        }

        else {

            gameLostPlayAgain();

        }

    }

    public void gameWon() {
        Intent intent = new Intent(QuizActivity.this, GameWon.class);
        intent.putExtra("username", userName );
        intent.putExtra("CoinValue", coinValue);
        startActivity(intent);
        finish();
    }

    public void gameLostPlayAgain() {
        Intent intent = new Intent(QuizActivity.this, PlayAgain.class);
        intent.putExtra("username", userName );
        startActivity(intent);
        finish();
    }

    public void timeUp() {
        Intent intent = new Intent(QuizActivity.this, TimeUp.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        countDownTimer.start();
    }


    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }


    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, QuizMainActivity.class);
        intent.putExtra("username",userName);
        startActivity(intent);
        finish();
    }


    public void correctDialog() {
        final Dialog correctAnswer = new Dialog(QuizActivity.this);
        correctAnswer.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (correctAnswer.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            correctAnswer.getWindow().setBackgroundDrawable(colorDrawable);
        }
        correctAnswer.setContentView(R.layout.correct_answer);
        correctAnswer.setCancelable(false);
        correctAnswer.show();


        onPause();


        TextView correctText = correctAnswer.findViewById(R.id.correctText);
        Button buttonNext = correctAnswer.findViewById(R.id.dialogNext);


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                correctAnswer.dismiss();

                qid++;

                currentQuestion = list.get(qid);

                updateQuestionsAndOptions();

                resetColor();

                enableButton();
            }
        });
    }

    public void resetColor() {
        buttonA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonC.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
    }


    public void disableButton() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
    }

    public void enableButton() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
    }


}
