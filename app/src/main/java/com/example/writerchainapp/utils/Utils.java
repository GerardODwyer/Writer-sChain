package com.example.writerchainapp.utils;

import com.example.writerchainapp.Constructors.Chain;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Utils {


    public static void saveDataToFirebase(Chain chain, DatabaseReference dbReference){
        DatabaseReference newDb = dbReference;
        String uuid = UUID.randomUUID().toString();
        Map<String, String> saveData = new HashMap<>();
        saveData.put("chainID", uuid);
        saveData.put("chainAuthor", chain.getChainAuthor());
        saveData.put("chainDesc", chain.getChainDescription());
        saveData.put("chainGenre", chain.getChainGenre());
        saveData.put("chainName", chain.getChainName());
        //saveData.put("chainChapterCount", String.valueOf(chain.getChapterCount()));
        newDb.child(uuid).setValue(saveData);

    }
}
