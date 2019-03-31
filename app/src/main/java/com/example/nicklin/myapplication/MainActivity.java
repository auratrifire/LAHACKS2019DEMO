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
    private final String TRANSLATION_API_KEY = "AIzaSyDMGa7sbDrjFYtgCFG-CSzlDueXcFGmYy8";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
//        }


        Intent intent = new Intent(this, VisionActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
