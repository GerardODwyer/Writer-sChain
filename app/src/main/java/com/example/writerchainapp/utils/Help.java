package com.example.writerchainapp.utils;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writerchainapp.R;

public class Help extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        question1 = "How to start reading a story?";
        question2 = "What is a Chain?";
        question3 = "What is right for me?";
        question4 = "How to become an admin user?";
        question5 = "How to join the Discord server?";


        button1 = findViewById(R.id.button1);
        button1.setText(question1);
        button2 = findViewById(R.id.button2);
        button2.setText(question2);
        button3 = findViewById(R.id.button3);
        button3.setText(question3);
        button4 = findViewById(R.id.button4);
        button4.setText(question4);
        button5 = findViewById(R.id.button5);
        button5.setText(question5);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = "Answer 1";
                answerQuestion(getApplicationContext(), question1, answer);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = "Answer 2";
                answerQuestion(getApplicationContext(), question2, answer);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = "Answer 3";
                answerQuestion(getApplicationContext(), question3, answer);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = "Answer 4";
                answerQuestion(getApplicationContext(), question4, answer);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = "Answer 5";
                answerQuestion(getApplicationContext(), question5, answer);
            }
        });

    }

    private void answerQuestion(Context applicationContext,String question, String answer) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.answer_dialog, null);
        alertDialog.setView(dialogView);
        Button button1 = dialogView.findViewById(R.id.buttonDelete);
        alertDialog.setTitle(question)
                .setMessage(answer);
        alertDialog.setView(dialogView);
        final AlertDialog dialog1 = alertDialog.create();
        dialog1.show();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });

    }
}
