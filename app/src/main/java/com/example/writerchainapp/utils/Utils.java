package com.example.writerchainapp.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.writerchainapp.Constructors.Chain;
import com.example.writerchainapp.Constructors.Chapters;
import com.example.writerchainapp.R;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class Utils {


    public static void saveDataToFirebase(Chain chain, DatabaseReference dbReference){
        DatabaseReference newDb = dbReference;
        String uuid = UUID.randomUUID().toString();
        Map<String, String> saveData = new HashMap<>();
        saveData.put("chainID", uuid);
        saveData.put("chainAuthor", chain.getChainAuthor());
        saveData.put("chainDescription", chain.getChainDescription());
        saveData.put("chainGenre", chain.getChainGenre());
        saveData.put("chainName", chain.getChainName());
        saveData.put("dateCreated", chain.getDateCreated());
        newDb.child(uuid).setValue(saveData);

    }

    public static void saveChaptersToFirebase(List<Chapters> chapterList, DatabaseReference dbReference, String chapterNumber){
        DatabaseReference newDb = dbReference;
        newDb.child("chapters").child(chapterNumber).setValue(chapterList);
        chapterList.clear();
    }

}
