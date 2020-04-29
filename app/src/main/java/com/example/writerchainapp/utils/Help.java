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
        question2 = "Rules";
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
                String answer = "To read a story simply click genre from the home screen then choose one of the many genres available, there is a wide variety to suit everyone taste.\n" +
                        "From hear pick a chain or search through existing chains to see your options.\n" +
                        "Next select chapters as they rise to see what suits you best, follow one chains author or follow a different author each time maybe even make a few chapters of your own.\n";
                answerQuestion(getApplicationContext(), question1, answer);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = "Before you add content it is important to be aware of and follow the simple rules outlined below otherwise your chapter may be removed. \n" +
                        "1.\tEnsure your chain is in the correct genre after all you wouldn’t want to find a robot fight in the middle of a romance.\n" +
                        "2.\tDon’t give real life details, try to refrain from giving details about your personal life such as your name address or even your email.\n" +
                        "3.\tDon’t spam adding the same chapter or chain multiple times, this will result in a ban and deletion of your account \n" +
                        "4.\tKeep things clean as you can after all you don’t want to lower a stories overall quality pointlessly.\n";
                answerQuestion(getApplicationContext(), question2, answer);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = "To read a story simply click genre from the home screen then choose one of the many genres available, there is a wide variety to suit everyone taste.\n" +
                        "From hear pick a chain or search through existing chains to see your options.\n" +
                        "Next select chapters as they rise to see what suits you best, follow one chains author or follow a different author each time maybe even make a few chapters of your own.\n";
                answerQuestion(getApplicationContext(), question3, answer);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = "Writers’ Chain is an interactive community driven story creation app in which users can create new worlds or develop existing ones made by other users. This tool can be a powerful assistant for writers and readers alike.\n" +
                        "Please note that Writers’ Chain is at present a free to use app in the beta stage as such it is likely to experience any number of bugs or errors if you find one please contact an admin on discord at;\n" +
                        "https://discord.gg/F6H2pR\n" +
                        "or email at \n" +
                        "gerard.odwyer1@gmail.com\n";
                answerQuestion(getApplicationContext(), question4, answer);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = "Writers’ Chain is an interactive community driven story creation app in which users can create new worlds or develop existing ones made by other users. This tool can be a powerful assistant for writers and readers alike.\n" +
                        "Please note that Writers’ Chain is at present a free to use app in the beta stage as such it is likely to experience any number of bugs or errors if you find one please contact an admin on discord at;\n" +
                        "https://discord.gg/F6H2pR\n" +
                        "or email at \n" +
                        "gerard.odwyer1@gmail.com\n";
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
