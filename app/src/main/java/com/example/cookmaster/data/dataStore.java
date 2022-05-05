package com.example.cookmaster.data;

import android.content.Context;

import com.example.cookmaster.ui.receptes.GestorReceptes;
import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;

public class dataStore {
    public static void saveReceptes(GestorReceptes receptesDB, Context context) throws IOException {
        Gson gson = new Gson();
        String serializedMap = gson.toJson(receptesDB.get());
        FileOutputStream fos = context.openFileOutput("receptes.json", Context.MODE_PRIVATE);
        fos.write(serializedMap.getBytes());
    }
}
