package com.example.sumitup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int score;
    int numberOfQuestions;
    TextView timerText;
    TextView scoreText;
    TextView greetingText;
    TextView questionText;
    TextView welcomeText;
    TextView timeUp;

    ImageView brainImage;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgainButton;
    int positionOfCorrectAnswer;
    GridLayout gridLayout;

    // ARRAY LIST TO STORE THE ANSWER OPTIONS
    ArrayList<Integer> answers = new ArrayList<>();

    // COUNTDOWN TIMER FOR THE APP
    CountDownTimer countDownTimer;

    ConstraintLayout layout2;
    Button startButton;

    // METHOD TO CHECK FOR THE ANSWERS
    @SuppressLint("SetTextI18n")
    public void answers(View view) {
        if (Integer.toString(positionOfCorrectAnswer).equals(view.getTag().toString())) {
            greetingText.setText("Correct :)");
            score++;
        } else {
            greetingText.setText("Wrong :/");
        }
        numberOfQuestions++;
        scoreText.setText(score + "/" + numberOfQuestions);
        nextQsn();
    }

    // METHOD FOR PLAY AGAIN BUTTON
    @SuppressLint("SetTextI18n")
    public void playAgain(View view) {
        timeUp.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        questionText.setVisibility(View.VISIBLE);
        score = 0;
        numberOfQuestions = 0;
        timerText.setText("30s");
        scoreText.setText(score + "/" + numberOfQuestions);
        nextQsn();
        playAgainButton.setVisibility(View.INVISIBLE);

        // COUNTDOWN TIMER CREATION
        countDownTimer = new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timerText.setText(l / 1000 + "s");
            }

            @Override
            public void onFinish() {
                gridLayout.setVisibility(View.INVISIBLE);
                questionText.setVisibility(View.INVISIBLE);
                timeUp.setVisibility(View.VISIBLE);
                greetingText.setText("DONE!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
        greetingText.setText("Let's Go!");
    }


    // METHOD TO CREATE OTHER QUESTIONS
    @SuppressLint("SetTextI18n")
    public void nextQsn() {
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        questionText.setText(a + " + " + b);

        positionOfCorrectAnswer = random.nextInt(4);
        answers.clear();
        for (int i = 0; i < 4; i++) {
            if (i == positionOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                int wrongAnswer = random.nextInt(41);
                while (wrongAnswer == (a + b)) {
                    wrongAnswer = random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }

    // METHOD FOR THE START GAME BUTTON
    public void startGame(View view) {
        startButton.setVisibility(View.INVISIBLE);
        brainImage.setVisibility(View.INVISIBLE);
        welcomeText.setVisibility(View.INVISIBLE);
        layout2.setVisibility(View.VISIBLE);
        playAgain(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerText = findViewById(R.id.timerTextView);
        scoreText = findViewById(R.id.scoreTextView);
        greetingText = findViewById(R.id.greetingTextView);
        questionText = findViewById(R.id.questionsTextView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        layout2 = findViewById(R.id.gameConstraintLayout);
        playAgainButton = findViewById(R.id.playAgainButton);
        startButton = findViewById(R.id.gameStartButton);
        welcomeText = findViewById(R.id.welcomeTextView);
        brainImage = findViewById(R.id.brainImageView);
        gridLayout = findViewById(R.id.gridLayout);
        timeUp = findViewById(R.id.timeUpTextView);
    }
}
