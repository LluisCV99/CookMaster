package com.example.cookmaster.data;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cookmaster.ui.classes.Receptes;
import com.example.cookmaster.ui.receptes.GestorReceptes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class dataStore {
    public static void saveReceptes(GestorReceptes receptesDB, Context context) throws IOException {
        Gson gson = new Gson();
        String serializedMap = gson.toJson(receptesDB.get());
        FileOutputStream fos = context.openFileOutput("receptes.json", Context.MODE_PRIVATE);
        fos.write(serializedMap.getBytes());
    }

    public static String receptaDB(Receptes recepta) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference();
        String uploadId = ref.push().getKey();
        if (uploadId != null && uploadId.isEmpty()) {
            return "Empty";
        } else {
            ref.child(uploadId).setValue(recepta);
            return recepta.getNom() + " uploaded";
        }
    }


}
