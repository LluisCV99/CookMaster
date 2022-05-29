package com.example.cookmaster.ui.NovaRecepta;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookmaster.MainActivity;
import com.example.cookmaster.R;
import com.example.cookmaster.databinding.NovaReceptaBinding;
import com.example.cookmaster.ui.classes.Receptes;
import com.example.cookmaster.ui.receptes.GestorReceptes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class NovaReceptaFragment extends Fragment {

    private NovaReceptaBinding binding;
    private NovaReceptaViewModel novaReceptaViewModel;
    private final int GALLERY_REQ_CODE = 1000;
    private final String api_id = "b90cfa64";
    private final String api_key = "2f495c17b1c094d789d18e1ae84870ae";
    GestorReceptes llista;
    ImageView imgGallery;
    Receptes recepta;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        novaReceptaViewModel = new ViewModelProvider(this).get(NovaReceptaViewModel.class);

        binding = NovaReceptaBinding.inflate(inflater, container, false);

        llista = ((MainActivity) requireActivity()).receptesDB;
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String[] s = new String[1];

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
        try {
        confirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomRecepta = nomText.getText().toString();
                String ingredientsRecepta = ingredientsText.getText().toString();
                String preparacioRecepta = preparacioText.getText().toString();

                if (android.os.Build.VERSION.SDK_INT > 9)
                {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
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
                    s[0] = StringUtils.substringBetween(response.body().string(), "calories\":", ",");


                    Toast.makeText(getContext(), s[0], Toast.LENGTH_LONG).show();
                    //recepta.setCalories(s);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "api bad", Toast.LENGTH_LONG).show();
                }



                recepta = new Receptes(nomRecepta,
                        ingredientsRecepta, preparacioRecepta, imgGallery, s[0]);
                llista.add(recepta);
                Toast.makeText(getContext(), "S'ha desat correctament!", Toast.LENGTH_LONG).show();
            }
        });
        }catch (Exception e){
            Toast.makeText(getContext(), "ha petat " + e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){


            if (requestCode == GALLERY_REQ_CODE){

                imgGallery.setImageURI(data.getData());
            }
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}