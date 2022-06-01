package com.example.cookmaster.data;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class imgUpload {


    private final StorageReference mStorageRef;

    private StorageTask mUploadTask;


    public imgUpload(){
        mStorageRef = FirebaseStorage.getInstance().getReference("imatges");
    }

    public imgUpload(String name, String imageUrl){
        if(name.trim().equals("")){
            name = "No name";
        };
        mStorageRef = FirebaseStorage.getInstance().getReference("imatges");
    }

    public boolean checkUploadInProgress(){
        return mUploadTask != null && mUploadTask.isInProgress();
    }

    public String uploadImg(Uri uri, String id, Context context){
        StorageReference fileRef = mStorageRef.child(id);

        String[] ret = new String[1];
        mUploadTask = fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                if(taskSnapshot.getMetadata() != null){
                    if(taskSnapshot.getMetadata().getReference() != null){
                        ret[0] = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                    }else {
                        ret[0] = "reference";
                    }
                }else{
                    ret[0] = "metadata";
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                ret[0] = "img upload Error: " + e.getMessage();
            }
        });
        return ret[0];
    }
}
