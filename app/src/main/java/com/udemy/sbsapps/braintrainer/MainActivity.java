package com.udemy.sbsapps.braintrainer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton, answerButton0, answerButton1, answerButton2, answerButton3, playAgainButton;
    TextView sumTextView, scoreTextView, timerTextView, resultTextView;
    ConstraintLayout gameLayout;

    ArrayList<Integer> answers = new ArrayList<>();

    int a, b, score = 0, numberOfQuestions = 0;
    int locationOfCorrectAnswer;

    Random rand;

    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(view);
    }

    public void chooseAnswer(View view) {

        numberOfQuestions++;
        String msg = Integer.toString(locationOfCorrectAnswer);
        if (msg.equals(view.getTag().toString())) {
            resultTextView.setText(R.string.correct);
            score++;
        } else {
            resultTextView.setText(R.string.incorrect);
        }

        resultTextView.setVisibility(View.VISIBLE);
        msg = Integer.toString(score) + "/" + Integer.toString(numberOfQuestions);
        scoreTextView.setText(msg);

    }

    @SuppressLint("SetTextI18n")
    public void playAgain(View view){
        score = numberOfQuestions = 0;

        timerTextView.setText("30s");

        String msg = Integer.toString(score) + "/" + Integer.toString(numberOfQuestions);
        sumTextView.setText(msg);

        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                resultTextView.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void newQuestion() {
        rand = new Random();

        resultTextView.setVisibility(View.INVISIBLE);

        a = rand.nextInt(21);
        b = rand.nextInt(21);

        String msg = Integer.toString(a) + " + " + Integer.toString(b);
        sumTextView.setText(msg);

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for (int i = 0; i < 4; i++) {
            if(locationOfCorrectAnswer == i)
                answers.add(a+b);
            else {
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == (a+b)) {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        msg = Integer.toString(answers.get(0));
        answerButton0.setText(msg);
        msg = Integer.toString(answers.get(1));
        answerButton1.setText(msg);
        msg = Integer.toString(answers.get(2));
        answerButton2.setText(msg);
        msg = Integer.toString(answers.get(3));
        answerButton3.setText(msg);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        sumTextView = findViewById(R.id.sumTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        resultTextView = findViewById(R.id.resultTextView);

        answerButton0 = findViewById(R.id.answerbutton1);
        answerButton1 = findViewById(R.id.answerbutton2);
        answerButton2 = findViewById(R.id.answerbutton3);
        answerButton3 = findViewById(R.id.answerbutton4);

        playAgainButton = findViewById(R.id.playAgainButton);

        gameLayout = findViewById(R.id.gameLayout);

        playAgainButton.setVisibility(View.INVISIBLE);
    }
}
