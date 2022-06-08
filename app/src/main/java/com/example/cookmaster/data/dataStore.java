package com.example.cookmaster.data;



import com.example.cookmaster.ui.classes.Receptes;
import com.example.cookmaster.ui.home.GestorHomeReceptes;
import com.example.cookmaster.ui.receptes.GestorReceptes;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class dataStore {

    public static String saveReceptes(GestorReceptes receptesDB) {
        String s = "";
        for(Receptes r : receptesDB.getAll()){
            s = receptaDB(r);
            if(s.isEmpty()){
                break;
            }
        }
        return s;
    }

    public static String receptaDB(Receptes recepta) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("receptes");
        String uploadId = recepta.getId();
        if (uploadId != null && uploadId.isEmpty()) {
            return "Error";
        } else {
            ref.child(uploadId).setValue(recepta);
            return recepta.getNom() + " uploaded";
        }
    }

    public static String calendarDB(GestorHomeReceptes ghr){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("calendaris");
        String uploadId = FirebaseAuth.getInstance().getUid();
        if(uploadId != null && uploadId.isEmpty()){
            return "Error";
        }else {
            ref.child(uploadId).setValue(ghr);
            return "";
        }
    }


}
