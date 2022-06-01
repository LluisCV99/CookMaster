package com.example.cookmaster.ui.NovaRecepta;

import static android.app.Activity.RESULT_OK;

import static java.lang.Thread.sleep;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cookmaster.MainActivity;
import com.example.cookmaster.R;
import com.example.cookmaster.data.imgUpload;
import com.example.cookmaster.databinding.NovaReceptaBinding;
import com.example.cookmaster.ui.classes.Receptes;
import com.example.cookmaster.ui.receptes.GestorReceptes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class NovaReceptaFragment extends Fragment {

    private NovaReceptaBinding binding;

    //img
    private final int GALLERY_REQ_CODE = 1000;
    private ImageView imgGallery;

        //upload
        private Uri uri;
        private String url;
        private imgUpload uploader;
        private FirebaseDatabase database;
        private DatabaseReference mDatabaseRef;
        private StorageReference mStorageRef;
        private StorageTask<UploadTask.TaskSnapshot> mUploadTask;

    //Receptes
    private GestorReceptes llista;
    private Receptes recepta;

    //API
    private final String api_id = "b90cfa64";
    private final String api_key = "2f495c17b1c094d789d18e1ae84870ae";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mStorageRef = FirebaseStorage.getInstance().getReference("imatges");
        binding = NovaReceptaBinding.inflate(inflater, container, false);
        uploader = new imgUpload();
        llista = ((MainActivity) requireActivity()).receptesDB;
        database = FirebaseDatabase.getInstance();
        mDatabaseRef = database.getReference("receptes");
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText nomText = view.findViewById(R.id.nom_text);
        EditText ingredientsText = view.findViewById(R.id.ingredients_text);
        EditText preparacioText = view.findViewById(R.id.preparacio_text);

        imgGallery = view.findViewById(R.id.image_recepta);
        Button btnGallery = view.findViewById(R.id.btnGallery);


        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galeria = new Intent(Intent.ACTION_PICK);
                galeria.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galeria, GALLERY_REQ_CODE);
            }
        });

        FloatingActionButton confirma = view.findViewById(R.id.floatingActionButton2);

        confirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomRecepta = nomText.getText().toString();
                String ingredientsRecepta = ingredientsText.getText().toString();
                String preparacioRecepta = preparacioText.getText().toString();

                 String ingredients = noSeriaMillorTenirUnMetodePerLAPI(ingredientsRecepta);

                if(uri != null){


                    //Penjar imatge a bdd, moure a un metode quan xuti
                    String id = System.currentTimeMillis() + "." + getFileExtension(uri);
                    upup(uri, id);

                    SharedPreferences settings = getContext().getSharedPreferences("USER", 0);
                    String uid = settings.getString("user",null);
                    recepta = new Receptes(nomRecepta, ingredientsRecepta, preparacioRecepta,
                            imgGallery, ingredients, uid, "");



                }else{
                    Toast.makeText(getContext(), "Imatge no trobada", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private String getFileExtension(Uri uri){
        ContentResolver cr = requireContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){


            if (requestCode == GALLERY_REQ_CODE && data != null && data.getData() != null){
                uri = data.getData();
                imgGallery.setImageURI(uri);
            }
        }

    }

    public String noSeriaMillorTenirUnMetodePerLAPI(String ingredientsRecepta){
        String ingredients;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        OkHttpClient client = new OkHttpClient();

                /*RequestBody formBody = new FormBody.Builder()
                        .add("body", "100g chicken, 100g rice, 100g curry")
                        .build();*/

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.edamam.com/api/nutrition-data").newBuilder();
        urlBuilder.addQueryParameter("app_id", api_id);
        urlBuilder.addQueryParameter("app_key", api_key);
        urlBuilder.addQueryParameter("ingr", ingredientsRecepta);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                //.header("app_id", api_id)
                //.header("app_key", api_key)
                .url(url)
                //.post(formBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            ingredients = StringUtils.substringBetween(response.body().string(), "calories\":", ",");


            Toast.makeText(getContext(), ingredients, Toast.LENGTH_LONG).show();
            //recepta.setCalories(s);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "api bad: "+ e.getMessage(), Toast.LENGTH_LONG).show();
            ingredients = "Error";
        }
        return ingredients;
    }


    public boolean checkUploadInProgress(){
            return mUploadTask != null && mUploadTask.isInProgress();
        }

    public void upup(Uri uri, String id){
        StorageReference fileRef = mStorageRef.child(id);

        mUploadTask = fileRef.putFile(uri);

        mUploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               uploadSuccesful(taskSnapshot);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "img upload Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void uploadSuccesful(UploadTask.TaskSnapshot taskSnapshot){
        if(taskSnapshot.getMetadata() != null){
            if(taskSnapshot.getMetadata().getReference() != null){
                url = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();

                recepta.setUrl(url);
                llista.add(recepta);
                receptaDB(recepta);
            }else {
                Toast.makeText(getContext(), "Error reference", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getContext(), "Error metadata", Toast.LENGTH_SHORT).show();
        }
    }
    private void receptaDB(Receptes recepta) {
        String uploadId = mDatabaseRef.push().getKey();
        if (uploadId != null && uploadId.isEmpty()) {
            Toast.makeText(getContext(), "mt", Toast.LENGTH_SHORT).show();
        } else {
            mDatabaseRef.child(uploadId).setValue(recepta);
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}