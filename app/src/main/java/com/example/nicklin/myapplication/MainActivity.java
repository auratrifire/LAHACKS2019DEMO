package com.example.nicklin.myapplication;

import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.view.View;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.content.Intent;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.TranslateRequest;
import com.google.api.services.translate.TranslateRequestInitializer;
import com.google.api.services.translate.model.TranslateTextRequest;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "Translation";
    public String choice = "";
    private final String TRANSLATION_API_KEY = "AIzaSyDMGa7sbDrjFYtgCFG-CSzlDueXcFGmYy8";
    private View contentView;
    private View loadingView;
    private int shortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




      
      
        contentView = findViewById(R.id.landingPage);
        loadingView = findViewById(R.id.loadingText);

        // Initially hide the content view.
        contentView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);


        // Retrieve and cache the system's default "short" animation time.
        shortAnimationDuration = getResources().getInteger(
                android.R.integer.config_longAnimTime);

        crossfade();

        Spinner spinner = findViewById(R.id.spinner3);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        break;
                    default:
                        //start activity on selection of any item you want, here I am assuming first item.
                        Intent intent = new Intent(MainActivity.this, Camira.class);
                        choice = spinner.getSelectedItem().toString();
                        intent.putExtra("LANG", choice);
                        startActivity(intent);
                        break;


                }




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void sendMessage(View view) throws IOException {
//        Log.e(TAG, "About to create the HTTP Transport");
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
//        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
//        Log.e(TAG, "About to initialize a translation object");
//        TranslateRequestInitializer requestInitializer = new TranslateRequestInitializer(TRANSLATION_API_KEY);
//
//        Translate.Builder builder = new Translate.Builder(httpTransport, jsonFactory, null);
//
//        builder.setTranslateRequestInitializer(requestInitializer);
//        Translate translate = builder.build();
//
//        //List<TranslateTextRequest> translateList = new ArrayList<>();
//        TranslateTextRequest translateRequest = new TranslateTextRequest();
//        translateRequest.setQ(Arrays.asList("Hola"));
//        translateRequest.setFormat("String");
//        translateRequest.setModel("String");
//        translateRequest.setSource("SP");
//        translateRequest.setTarget("ES");
//
//        Log.e(TAG, translateRequest.toString());
//        Translate.Translations.TranslateOperation annotateRequest = translate.translations().translate(translateRequest);
//        TranslationsListResponse response = annotateRequest.execute();

//        for(TranslationsResource translationsResource: response.getTranslations()){
//            Log.e(TAG, translationsResource.getTranslatedText());

        Intent intent = new Intent(this, VisionActivity.class);
        startActivity(intent);
    }

    private void crossfade() {
        loadingView.setAlpha(1f);
        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        contentView.setAlpha(0f);
        contentView.setVisibility(View.VISIBLE);

        // Animate the content view to 100% opacity, and clear any animation
        // listener set on the view.
        contentView.animate().alpha(1f).setDuration(shortAnimationDuration).setListener(null);

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        loadingView.animate().alpha(0f).setDuration(shortAnimationDuration).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        loadingView.setVisibility(View.GONE);
                    }
                });
    }
}

