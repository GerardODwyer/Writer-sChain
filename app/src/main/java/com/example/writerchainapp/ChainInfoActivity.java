package com.example.writerchainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ChainInfoActivity extends AppCompatActivity {

    private TextView ChainID;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chain_info);


        ChainID = (TextView)findViewById(R.id.text_chainID);
        Intent in= getIntent();
        Bundle b = in.getExtras();

        if(b!=null)
        {
            String j =(String) b.get("name");
      //      Textv.setText(j);
        }
    }
}
