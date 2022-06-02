package com.example.fullopentrivia;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.text.StringEscapeUtils;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final private TriviaToken token = new TriviaToken();
    static final private String tokenString = "tokenString";
    private GetTriviaQuestion triviaClient = new GetTriviaQuestion();
    private TriviaQuestion triviaQuestion = new TriviaQuestion();
    private TextView textView, resultTextView;
    private Button button1, button2, button3, button4, nextButton;
    private List<String> Answers;
    private int pressed;
    private boolean isCorrect;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString(tokenString, token.getToken());

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        token.loadSavedToken(savedInstanceState.getString(tokenString));
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null) {
            Log.d("SavedInstanceState: ", "state is null: ");
            token.loadSavedToken(savedInstanceState.getString(tokenString));
        } else {
            Log.d("SavedInstanceState: ", "state is NOT null: ");
            Thread tok = new Thread() {
                @Override
                public void run() {
                    token.setToken();
                }
            };
            tok.start();
            try {
                tok.join();
            } catch( Exception e ) {
                Log.d("onCreate: ", "set new token: ");
            }
        }

        resultTextView = findViewById(R.id.textView);
        resultTextView.setVisibility(View.INVISIBLE);
        textView = findViewById(R.id.textView1);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        nextButton = findViewById(R.id.nextButton);

        Thread t = new Thread() {
            @Override
            public void run() {
                triviaQuestion = triviaClient.getTriviaQuestion(token.getToken());
                System.out.println("Main Activity Question:");
                System.out.println(triviaQuestion.toString());
                System.out.println("Token: " + token.getToken());
            }
        };
        t.start();
        try {
            t.join();
        } catch ( Exception e) {
            Log.d("onCreate: ", e.toString());
        }
        textView.setText(StringEscapeUtils.unescapeHtml4(triviaQuestion.getCategory())+ ":\n\n" + StringEscapeUtils.unescapeHtml4(triviaQuestion.getQuestion()));
        if( triviaQuestion.getType().equals("boolean") ) {
            button1.setText("True");
            button2.setText("False");
            button3.setVisibility(View.INVISIBLE);
            button4.setVisibility(View.INVISIBLE);

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Main: ", "Button1 pressed");
                    CheckAnswer runnable = new CheckAnswer("Button1");
                    pressed=1;
//                    new Thread(runnable).start();
                    runnable.t.start();
                    try {
                        runnable.t.join();
                    } catch (InterruptedException e) {
                        Log.d("onClick: button1: ", e.toString());
                    }
                    if(isCorrect) {
                        resultTextView.setText("Correct!");
                    } else {
                        resultTextView.setText("WRONG!!!");
                    }
                    resultTextView.setVisibility(View.VISIBLE);
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Main: ", "Button2 pressed");
                    CheckAnswer runnable = new CheckAnswer("Button2");
                    pressed=2;
//                    new Thread(runnable).start();
                    runnable.t.start();
                    try {
                        runnable.t.join();
                    } catch (InterruptedException e) {
                        Log.d("onClick: button2: ", e.toString());
                    }

                    if(isCorrect) {
                        resultTextView.setText("Correct!");
                    } else {
                        resultTextView.setText("WRONG!!!");
                    }
                    resultTextView.setVisibility(View.VISIBLE);
                }
            });
        }
        else {
            Answers = triviaQuestion.getIncorrectAnswers();
            Answers.add(triviaQuestion.getCorrectAnswer());
            Collections.sort(Answers);

            button1.setText(StringEscapeUtils.unescapeHtml4(Answers.get(0)));
            button2.setText(StringEscapeUtils.unescapeHtml4(Answers.get(1)));
            button3.setText(StringEscapeUtils.unescapeHtml4(Answers.get(2)));
            button4.setText(StringEscapeUtils.unescapeHtml4(Answers.get(3)));

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pressed=1;
                    Log.d("Main: ", "Button1 pressed");
                    CheckAnswer runnable = new CheckAnswer("Button1");
//                    new Thread(runnable).start();
                    runnable.t.start();
                    try {
                        runnable.t.join();
                    } catch (InterruptedException e) {
                        Log.d("onClick: button2: ", e.toString());
                    }

                    if(isCorrect) {
                        resultTextView.setText("Correct!");
                    } else {
                        resultTextView.setText("WRONG!!!");
                    }
                    resultTextView.setVisibility(View.VISIBLE);
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pressed=2;
                    Log.d("Main: ", "Button2 pressed");
                    CheckAnswer runnable = new CheckAnswer("Button2");
//                    new Thread(runnable).start();
                    runnable.t.start();
                    try {
                        runnable.t.join();
                    } catch (InterruptedException e) {
                        Log.d("onClick: button2: ", e.toString());
                    }

                    if(isCorrect) {
                        resultTextView.setText("Correct!");
                    } else {
                        resultTextView.setText("WRONG!!!");
                    }
                    resultTextView.setVisibility(View.VISIBLE);
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pressed=3;
                    Log.d("Main: ", "Button3 pressed");
                    CheckAnswer runnable = new CheckAnswer("Button3");
//                    new Thread(runnable).start();
                    runnable.t.start();
                    try {
                        runnable.t.join();
                    } catch (InterruptedException e) {
                        Log.d("onClick: button2: ", e.toString());
                    }

                    if(isCorrect) {
                        resultTextView.setText("Correct!");
                    } else {
                        resultTextView.setText("WRONG!!!");
                    }
                    resultTextView.setVisibility(View.VISIBLE);
                }
            });

            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pressed=4;
                    Log.d("Main: ", "Button4 pressed");
                    CheckAnswer runnable = new CheckAnswer("Button4");
//                    new Thread(runnable).start();
                    runnable.t.start();
                    try {
                        runnable.t.join();
                    } catch (InterruptedException e) {
                        Log.d("onClick: button2: ", e.toString());
                    }

                    if(isCorrect) {
                        resultTextView.setText("Correct!");
                    } else {
                        resultTextView.setText("WRONG!!!");
                    }
                    resultTextView.setVisibility(View.VISIBLE);
                }
            });
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Main: ", "Next Button pressed");
                recreate();
            }
        });
    }

    class CheckAnswer implements Runnable {
        String name; // name of thread

        Thread t;

        CheckAnswer(String threadname) {
            name = threadname;
            t = new Thread(this, name);
            System.out.println("New thread: " + t);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void run() {
            button1.setOnClickListener(null);
            button2.setOnClickListener(null);
            button3.setOnClickListener(null);
            button4.setOnClickListener(null);

            if( triviaQuestion.getType().equals("boolean")) {
                if( triviaQuestion.getCorrectAnswer().equals("True") && pressed==1 ) {
                    textView.setBackgroundColor(Color.GREEN);
                    button1.setBackgroundColor(Color.GREEN);
//                    button2.setBackgroundColor(Color.RED);
                    isCorrect=true;
                }
                else if(triviaQuestion.getCorrectAnswer().equals("False") && pressed==2) {
                    textView.setBackgroundColor(Color.GREEN);
                    button2.setBackgroundColor(Color.GREEN);
//                    button1.setBackgroundColor(Color.RED);
                    isCorrect=true;
                }
                else {
                    textView.setBackgroundColor(Color.RED);
                    isCorrect=false;
                    if( triviaQuestion.getCorrectAnswer().equals("True") ){
                        button1.setBackgroundColor(Color.GREEN);
                        button2.setBackgroundColor(Color.RED);
                    } else {
                        button2.setBackgroundColor(Color.GREEN);
                        button1.setBackgroundColor(Color.RED);
                    }
                }
                return;
            }

            switch (pressed) {
                case 1:
                    if( Answers.get(0).equals(triviaQuestion.getCorrectAnswer()) ) {
                        button1.setBackgroundColor(Color.GREEN);
                        textView.setBackgroundColor(Color.GREEN);
                        isCorrect=true;
                    }
                    else {
                        button1.setBackgroundColor(Color.RED);
                        textView.setBackgroundColor(Color.RED);
                        isCorrect=false;
                    }
                    break;
                case 2:
                    if( Answers.get(1).equals(triviaQuestion.getCorrectAnswer()) ) {
                        button2.setBackgroundColor(Color.GREEN);
                        textView.setBackgroundColor(Color.GREEN);
                        isCorrect=true;
                    }
                    else {
                        button2.setBackgroundColor(Color.RED);
                        textView.setBackgroundColor(Color.RED);
                        isCorrect=false;
                    }
                    break;
                case 3:
                    if( Answers.get(2).equals(triviaQuestion.getCorrectAnswer()) ) {
                        button3.setBackgroundColor(Color.GREEN);
                        textView.setBackgroundColor(Color.GREEN);
                        isCorrect=true;
                    }
                    else {
                        button3.setBackgroundColor(Color.RED);
                        textView.setBackgroundColor(Color.RED);
                        isCorrect=false;
                    }
                    break;
                case 4:
                    if( Answers.get(3).equals(triviaQuestion.getCorrectAnswer()) ) {
                        button4.setBackgroundColor(Color.GREEN);
                        textView.setBackgroundColor(Color.GREEN);
                        isCorrect=true;
                    }
                    else {
                        button4.setBackgroundColor(Color.RED);
                        textView.setBackgroundColor(Color.RED);
                        isCorrect=false;
                    }
                    break;
                default:
                    break;
            }

            Iterator<String> iterator = Answers.iterator();
            int x=0;
            while( iterator.hasNext() ) {
                if( iterator.next().equals(triviaQuestion.getCorrectAnswer()) ) {
                    break;
                }
                x++;
            }
            switch (x){
                case 0:
                    button1.setBackgroundColor(Color.GREEN);
                    break;
                case 1:
                    button2.setBackgroundColor(Color.GREEN);
                    break;
                case 2:
                    button3.setBackgroundColor(Color.GREEN);
                    break;
                case 3:
                    button4.setBackgroundColor(Color.GREEN);
                    break;
                default:
                    break;
            }
        }
    }
}