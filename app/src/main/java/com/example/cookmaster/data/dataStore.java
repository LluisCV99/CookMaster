package com.example.cookmaster.data;

import android.content.Context;

import com.example.cookmaster.ui.receptes.GestorReceptes;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class dataStore {
    public static void saveReceptes(GestorReceptes receptesDB, Context context) throws IOException {

        FileOutputStream fileOut = context.openFileOutput("receptes.bin", Context.MODE_PRIVATE);
        /*
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(receptesDB);
        oos.flush();

        fileOut.write(bos.toByteArray());
         */

        byte[] data = SerializationUtils.serialize()
    }
}
