package com.example.cookmaster.data;

import android.content.Context;
import android.os.Environment;

import com.example.cookmaster.ui.classes.Receptes;
import com.example.cookmaster.ui.receptes.GestorReceptes;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import com.google.gson.Gson;

public class dataLoad {
    public static GestorReceptes loadReceptes(Context context) throws IOException, ClassNotFoundException {
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
        return gson.fromJson(contents, GestorReceptes.class);
    }
}
