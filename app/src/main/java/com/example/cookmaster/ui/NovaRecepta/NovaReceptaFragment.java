package com.example.cookmaster.ui.NovaRecepta;

import static android.app.Activity.RESULT_OK;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
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
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import com.example.cookmaster.MainActivity;
import com.example.cookmaster.R;
import com.example.cookmaster.data.dataStore;
import com.example.cookmaster.databinding.NovaReceptaBinding;
import com.example.cookmaster.ui.classes.Receptes;
import com.example.cookmaster.ui.receptes.GestorReceptes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class NovaReceptaFragment extends Fragment {

    private NovaReceptaBinding binding;

    //img
    private final int GALLERY_REQ_CODE = 1000;
    private ImageView imgGallery;

    //upload
    private Uri uri;
    private String url;

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
        llista = ((MainActivity) requireActivity()).receptesDB;
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText nomText = view.findViewById(R.id.nom_text);
        EditText ingredientsText = view.findViewById(R.id.ingredients_text);
        EditText preparacioText = view.findViewById(R.id.preparacio_text);
        Button ajuda = view.findViewById(R.id.help);
        ajuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Entrada d'aliments");
                alert.setMessage(
                        "Per poder calcular les calories del plat, " +
                                "els aliments s'han d'introduir en anglès i de la següent manera:\n" +
                                "\n100g peas,\n"+
                                "1 cup olive oil,\n"+
                                "1L soda"
                );
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                alert.create();
                alert.show();
            }
        });

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
                String url = "";
                String calories;


                if(nomRecepta.isEmpty()){
                    Toast.makeText(getContext(), "La recepta està buida.", Toast.LENGTH_SHORT).show();
                }else if(ingredientsRecepta.isEmpty()) {
                    Toast.makeText(getContext(), "Els ingredients estan buits.", Toast.LENGTH_SHORT).show();
                }else if(preparacioRecepta.isEmpty()){
                    Toast.makeText(getContext(), "La preparació està buida.", Toast.LENGTH_SHORT).show();
                }else if(nomRecepta.contains("\n")){
                    Toast.makeText(getContext(), "El nom de la recepta no pot contenir salts de linia", Toast.LENGTH_SHORT).show();
                }else {
                    calories = getNutrients(ingredientsRecepta);
                    if (calories.equals("")) {
                        Toast.makeText(getContext(), "Ingredients mal introduits", Toast.LENGTH_SHORT).show();
                    } else {
                        puja(nomRecepta, ingredientsRecepta, preparacioRecepta, calories);
                        Navigation.findNavController(view).navigate(R.id.nav_home);

                    }
                }


            }
        });
    }

    private void puja(String nomRecepta, String ingredientsRecepta, String preparacioRecepta, String calories){
        if(uri != null){
            //Penjar imatge a bdd
            String id = System.currentTimeMillis() + "." + getFileExtension(uri);
            uploadImg(uri, id);

        }else {
            Toast.makeText(getContext(), "Imatge no trobada", Toast.LENGTH_SHORT).show();
            url = "imatges/1654696473902.jpg";
        }

        SharedPreferences settings = getContext().getSharedPreferences("USER", 0);
        String uid = settings.getString("user",null);
        if(((MainActivity) requireActivity()).receptesDB.has(nomRecepta+uid)){
            Toast.makeText(getContext(), "Ja hi ha una recepta amb aquest nom", Toast.LENGTH_SHORT).show();
        }else {
            recepta = new Receptes(nomRecepta, ingredientsRecepta, preparacioRecepta, calories, uid, url, "");

            if (uri == null) {
                llista.add(recepta);
                String res = dataStore.receptaDB(recepta);
                if (!res.equals("")) {
                    Toast.makeText(getContext(), res, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Nova recepta " + recepta.getNom() + " creada", Toast.LENGTH_SHORT).show();
                }
            }
        }
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

    private String reconf(String ingredients){
        String[] tornada;
        String temp = "";
        if(ingredients.contains(",")){
            tornada = ingredients.split(",");
        }else if(ingredients.contains(";")){
            tornada = ingredients.split(";");
        }else if(ingredients.contains(".")){
            tornada = ingredients.split(".");
        } else if(ingredients.contains("\n")) {
            tornada = ingredients.split("\n");
        }else{
            return temp;
        }


        for(int i = 0; i < tornada.length-1; i++){
            temp += "\"" + tornada[i].trim() + "\",\n";
        }
        temp += "\"" + tornada[tornada.length-1].trim() + "\"";
        return temp;
    }

    public String getNutrients(String ingredientsRecepta){
        ingredientsRecepta = reconf(ingredientsRecepta);
        String ingredients = "";
        if(ingredientsRecepta.equals("")) return ingredients;


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        OkHttpClient client = new OkHttpClient();

        String json = "{\"ingr\": ["+ingredientsRecepta+"]}";

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url("https://api.edamam.com/api/nutrition-details?app_id=b90cfa64&app_key=2f495c17b1c094d789d18e1ae84870ae")
                .post(body)
                .build();
        try {
            Call call = client.newCall(request);
            Response response = call.execute();
            if(response.code() != 200){
                ingredients = "";
            }else {
                ingredients = StringUtils.substringBetween(response.body().string(), "calories\":", ",");
            }

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "api bad: "+ e.getMessage(), Toast.LENGTH_LONG).show();
            ingredients = "Error";
        }
        return ingredients;
    }

    public void uploadImg(Uri uri, String id){
        StorageReference fileRef = mStorageRef.child(id);

        mUploadTask = fileRef.putFile(uri);

        mUploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                uploadSuccesful(taskSnapshot, id);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "img upload Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void uploadSuccesful(UploadTask.TaskSnapshot taskSnapshot, String id){
        if(taskSnapshot.getMetadata() != null){
            if(taskSnapshot.getMetadata().getReference() != null){
                url = "imatges/" + id;

                recepta.setImageUrl(url);
                llista.add(recepta);
                String res = dataStore.receptaDB(recepta);
                if(!res.equals("")){
                    Toast.makeText(getContext(), res, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Nova recepta " + recepta.getNom() + " creada", Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(getContext(), "Error reference", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getContext(), "Error metadata", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}