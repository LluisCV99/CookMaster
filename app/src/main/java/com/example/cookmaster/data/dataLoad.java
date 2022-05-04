package com.example.cookmaster.data;

import android.content.Context;
import android.os.Environment;

import com.example.cookmaster.ui.receptes.GestorReceptes;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

public class dataLoad {
    public static GestorReceptes loadReceptes(Context context) throws IOException, ClassNotFoundException {
        int i = 0;
        FileInputStream fis = new FileInputStream("receptes.bin");
        byte [] data = new byte[fis.available()];

        while (fis.available() != 0){
            data[i] = (byte) fis.read();
        }
        fis.close();

        return receptesDB;
    }
}
