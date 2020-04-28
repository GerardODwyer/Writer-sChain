package com.example.writerchainapp.utils;

import androidx.annotation.NonNull;

import com.example.writerchainapp.Constructors.Chain;
import com.example.writerchainapp.Constructors.Chapters;
import com.example.writerchainapp.Constructors.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Utils {


    public static void saveDataToFirebase(Chain chain, DatabaseReference dbReference){
        DatabaseReference newDb = dbReference;
        String uuid = UUID.randomUUID().toString();
        Map<String, String> saveData = new HashMap<>();
        saveData.put("chainID", uuid);
        saveData.put("chainAuthor", chain.getChainAuthor());
        saveData.put("chainAuthorID", chain.getChainAuthorID());
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

    public static void increaseUserChainInfo(DatabaseReference dbReference, Users users) {
        users.increaseUserChainCount();
        dbReference.setValue(users);
    }

    public static void increaseUserChapterInfo(DatabaseReference dbReference, Users users) {
        users.increaseUserChapterCount();
        dbReference.setValue(users);
    }

    public static void decreaseUserChapterInfo(DatabaseReference dbReference, Users users) {
        users.decreaseUserChapterCount();
        dbReference.setValue(users);
    }
}
