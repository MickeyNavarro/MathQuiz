package com.example.mathquiz;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_start, btn_answer0, btn_answer1, btn_answer2, btn_answer3;
    TextView tv_score, tv_questions, tv_timer, tv_bottomMessage;
    ProgressBar prog_timer;

    Game g = new Game();

    int secsRemaining = 30;

    CountDownTimer timer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            secsRemaining--;
            tv_timer.setText(Integer.toString(secsRemaining) + " sec");
            prog_timer.setProgress(30 - secsRemaining);
        }

        @Override
        public void onFinish() {
            //disable buttons to no longer be used
            btn_answer0.setEnabled(false);
            btn_answer1.setEnabled(false);
            btn_answer2.setEnabled(false);
            btn_answer3.setEnabled(false);

            tv_bottomMessage.setText("Time is up! " + g.getNumCorrect() + "/" + (g.getTotalQs() - 1));

            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btn_start.setVisibility(View.VISIBLE);
                }
            }, 4000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = findViewById(R.id.btn_start);
        btn_answer0 = findViewById(R.id.btn_answer0);
        btn_answer1 = findViewById(R.id.btn_answer1);
        btn_answer2 = findViewById(R.id.btn_answer2);
        btn_answer3 = findViewById(R.id.btn_answer3);

        tv_score = findViewById(R.id.tv_score);
        tv_questions = findViewById(R.id.tv_questions);
        tv_timer = findViewById(R.id.tv_timer);
        tv_bottomMessage = findViewById(R.id.tv_bottomMessage);

        prog_timer = findViewById(R.id.prog_timer);

        tv_timer.setText("0sec");
        tv_questions.setText("");
        tv_bottomMessage.setText("Press Go");
        tv_score.setText("0pts");
        prog_timer.setProgress(0);

         View.OnClickListener startButtonClickListener  = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button start_button = (Button) v;

                start_button.setVisibility(View.INVISIBLE);

                //reset timer
                secsRemaining = 30;

                //create new game
                g = new Game();

                //reset score
                g.setScore(0);
                tv_score.setText("0pts");

                nextTurn();
                timer.start();
            }
        };

        View.OnClickListener answerBtnCL  = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btnClicked = (Button) v;

                int answerSelected = Integer.parseInt(btnClicked.getText().toString());

                g.checkAnswer(answerSelected);

                tv_score.setText(Integer.toString(g.getScore()) + "pts");
                nextTurn();

            }
        };

        btn_start.setOnClickListener(startButtonClickListener);

        btn_answer0.setOnClickListener(answerBtnCL);
        btn_answer1.setOnClickListener(answerBtnCL);
        btn_answer2.setOnClickListener(answerBtnCL);
        btn_answer3.setOnClickListener(answerBtnCL);

    }

    private void nextTurn() {
        //create a new question
        g.makeNewQuestion();
        int [] answers = g.getCurrQ().getAnswerArray();

        //put text on answer button
        btn_answer0.setText(Integer.toString(answers[0]));
        btn_answer1.setText(Integer.toString(answers[1]));
        btn_answer2.setText(Integer.toString(answers[2]));
        btn_answer3.setText(Integer.toString(answers[3]));


        //enable buttons to be used
        btn_answer0.setEnabled(true);
        btn_answer1.setEnabled(true);
        btn_answer2.setEnabled(true);
        btn_answer3.setEnabled(true);


        tv_questions.setText(g.getCurrQ().getQuestionPhrase());
        tv_bottomMessage.setText(g.getNumCorrect() + "/" + (g.getTotalQs() - 1) );

    }
}
