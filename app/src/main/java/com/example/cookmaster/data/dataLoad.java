package com.example.cookmaster.data;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import com.google.gson.Gson;

public class dataLoad {
    public static HashMap<String, String> loadReceptes(Context context) throws IOException, ClassNotFoundException {
        File file = new File(context.getFilesDir(), "receptes.json");
        if(!file.exists()){
            return null;
        }
        FileInputStream fis = context.openFileInput("receptes.json");
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder builder = new StringBuilder();
        BufferedReader br = new BufferedReader(isr);

        String line = br.readLine();
        while (line != null){
            builder.append(line).append("\n");
            line = br.readLine();
        }
        String contents = builder.toString();
        Gson gson = new Gson();
        HashMap<String, String> map = new HashMap<>();
        map = (HashMap<String, String>) gson.fromJson(contents, map.getClass());
        return map;
    }
}
