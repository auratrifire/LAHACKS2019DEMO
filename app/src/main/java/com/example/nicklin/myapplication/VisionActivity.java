package com.example.nicklin.myapplication;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
//import com.google.api.services.vision.v1.Vision;
import com.google.api.client.json.GenericJson;
//import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.vision.v1.model.*;
import com.google.api.services.vision.v1.*;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.TranslationsListResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;


public class VisionActivity extends AppCompatActivity {
    private static final String CLOUD_VISION_API_KEY = "AIzaSyDMGa7sbDrjFYtgCFG-CSzlDueXcFGmYy8";
    public static final String FILE_NAME = "~/picture.jpg";
    static final int REQUEST_GALLERY_IMAGE = 100;
    static final int REQUEST_PERMISSIONS = 13;
    static final int REQUEST_CODE_PICK_ACCOUNT = 101;
    static final int REQUEST_ACCOUNT_AUTHORIZATION = 102;

    private static final String ANDROID_CERT_HEADER = "X-Android-Cert";
    private static final String ANDROID_PACKAGE_HEADER = "X-Android-Package";
    private static final int MAX_LABEL_RESULTS = 10;
    private static final int MAX_DIMENSION = 1200;

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String LANGUAGE = "ru";
    private static final int GALLERY_PERMISSIONS_REQUEST = 0;
    private static final int GALLERY_IMAGE_REQUEST = 1;
    public static final int CAMERA_PERMISSIONS_REQUEST = 2;
    public static final int CAMERA_IMAGE_REQUEST = 3;


    private TextView textResults;
    private TextView labelResults;
    private TextView localResults;
    private ImageView img;

    public String choice = "";

    private String translateAccro = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision);

        Intent i = getIntent();
        choice = i.getStringExtra("LANG");

        switch(choice){
            case "Afrikaans":
                translateAccro = "af";
                break;
            case "Albanian":
                translateAccro = "sq";
                break;
            case "Aranic":
                translateAccro = "ar";
                break;
            case "Azerbaijani":
                translateAccro = "az";
                break;
            case "Basque":
                translateAccro = "eu";
                break;
            case "Bengali":
                translateAccro = "bn";
                break;
            case "Belarusian":
                translateAccro = "be";
                break;
            case "Bulgarian":
                translateAccro = "bg";
                break;
            case "Catalan":
                translateAccro = "ca";
                break;
            case "Chinese Simplified":
                translateAccro = "zh-CN";
                break;
            case "Chinese Traditional":
                translateAccro = "zh-TW";
                break;
            case "Croatian":
                translateAccro = "hr";
                break;
            case "Czech":
                translateAccro = "cs";
                break;
            case "Danish":
                translateAccro = "da";
                break;
            case "Dutch":
                translateAccro = "nl";
                break;
            case "English":
                translateAccro = "en";
                break;
            case "Esperanto":
                translateAccro = "eo";
                break;
            case "Estonian":
                translateAccro = "et";
                break;
            case "Filipino":
                translateAccro = "tl";
                break;
            case "Finnish":
                translateAccro = "fi";
                break;
            case "French":
                translateAccro = "fr";
                break;
            case "Galician":
                translateAccro = "gl";
                break;
            case "Georgian":
                translateAccro = "ka";
                break;
            case "German":
                translateAccro = "de";
                break;
            case "Greek":
                translateAccro = "el";
                break;
            case "Gujarati":
                translateAccro = "gu";
                break;
            case "Haitian Creole":
                translateAccro = "ht";
                break;
            case "Hebrew":
                translateAccro = "iw";
                break;
            case "Hindi":
                translateAccro = "hi";
                break;
            case "Hungarian":
                translateAccro = "hu";
                break;
            case "Icelandic":
                translateAccro = "is";
                break;
            case "Indonesian":
                translateAccro = "id";
                break;
            case "Irish":
                translateAccro = "ga";
                break;
            case "Italian":
                translateAccro = "it";
                break;
            case "Japanese":
                translateAccro = "ja";
                break;
            case "Kannada":
                translateAccro = "kn";
                break;
            case "Korean":
                translateAccro = "ko";
                break;
            case "Latin":
                translateAccro = "la";
                break;
            case "Latvian":
                translateAccro = "lv";
                break;
            case "Lithuanian":
                translateAccro = "lt";
                break;
            case "Macedonian":
                translateAccro = "mk";
                break;
            case "Malay":
                translateAccro = "ms";
                break;
            case "Maltese":
                translateAccro = "mt";
                break;
            case "Norwegian":
                translateAccro = "no";
                break;
            case "Persian":
                translateAccro = "fa";
                break;
            case "Polish":
                translateAccro = "pl";
                break;
            case "Portuguese":
                translateAccro = "pt";
                break;
            case "Romanian":
                translateAccro = "ro";
                break;
            case "Russian":
                translateAccro = "ru";
                break;
            case "Serbian":
                translateAccro = "sr";
                break;
            case "Slovak":
                translateAccro = "sk";
                break;
            case "Slovenian":
                translateAccro = "sl";
                break;
            case "Spanish":
                translateAccro = "es";
                break;
            case "Swahili":
                translateAccro = "sw";
                break;
            case "Swedish":
                translateAccro = "sv";
                break;
            case "Tamil":
                translateAccro = "ta";
                break;
            case "Telugu":
                translateAccro = "te";
                break;
            case "Thai":
                translateAccro = "th";
                break;
            case "Turkish":
                translateAccro = "tr";
                break;
            case "Ukrainian":
                translateAccro = "uk";
                break;
            case "Urdu":
                translateAccro = "ur";
                break;
            case "Vietnamese":
                translateAccro = "vi";
                break;
            case "Welsh":
                translateAccro = "cy";
                break;
            case "Yiddish":
                translateAccro = "yi";
                break;
            //default:
               // translateAccro = "ru";
        }

        Button selectImageButton = findViewById(R.id.select_image_button);
        img = findViewById(R.id.selected_image);
        labelResults = findViewById(R.id.tv_label_results);
        //textResults = findViewById(R.id.tv_texts_results);
        //localResults = findViewById(R.id.tv_local_results);

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ActivityCompat.requestPermissions(VisionActivity.this,
//                        new String[]{Manifest.permission.GET_ACCOUNTS},
//                        REQUEST_PERMISSIONS);
                //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkPermissions();
                //}
            }
        });
    }


    public void checkPermissions(){

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED||
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                    1052);

        }else{
            launchImagePicker();
        }

    }

    private void launchImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select an image"),
                REQUEST_GALLERY_IMAGE);
    }


//    private void launchCloudVision(){
//        String FILENAME = "picture.jpg";
//        String PATH = "drawable/"+ FILENAME;
//        File f = new File(PATH);
//        Uri uri = Uri.fromFile(f);
//        Log.e(TAG,  uri.toString());
//        uploadImage(uri);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY_IMAGE && resultCode == RESULT_OK && data != null) {
            uploadImage(data.getData());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1052:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    launchImagePicker();
                } else {
                    Toast.makeText(VisionActivity.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    public void uploadImage(Uri uri){
        try{
            Log.e(TAG, "About to enter the bitmap func");
            Bitmap bitmap = scaleBitmapDown(MediaStore.Images.Media.getBitmap(getContentResolver(), uri), MAX_DIMENSION);
            //Bitmap bitmap =  MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            Log.e(TAG, "Successfully exited the bitmap func");
            Log.e(TAG, "About to call CloudVision");
            callCloudVision(bitmap);
            img.setImageBitmap(bitmap);
        }catch(IOException e){
            Log.e(TAG, e.toString());
        }

    }

    @SuppressLint("StaticFieldLeak")
    private void callCloudVision(final Bitmap bitmap){

       new AsyncTask<Object, Void, BatchAnnotateImagesResponse>(){
           @Override
           protected BatchAnnotateImagesResponse doInBackground(Object... params){
               try{
                   HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
                   JsonFactory jsonFactory = GsonFactory.getDefaultInstance();


                   VisionRequestInitializer requestInitializer =
                           new VisionRequestInitializer(CLOUD_VISION_API_KEY);


                   Vision.Builder builder = new Vision.Builder
                           (httpTransport, jsonFactory, null);
                   builder.setVisionRequestInitializer(requestInitializer);
                   Vision vision = builder.build();

                   List<Feature> featureList = new ArrayList<>();
                   Feature labelDetection = new Feature();
                   labelDetection.setType("LABEL_DETECTION");
                   labelDetection.setMaxResults(10);
                   featureList.add(labelDetection);

                   Feature objectLocalization = new Feature();
                   objectLocalization.setType("OBJECT_LOCALIZATION");
                   objectLocalization.setMaxResults(10);
                   featureList.add(objectLocalization);

                   Feature textDetection = new Feature();
                   textDetection.setType("TEXT_DETECTION");
                   textDetection.setMaxResults(10);
                   featureList.add(textDetection);

                   List<AnnotateImageRequest> imageList = new ArrayList<>();
                   AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest();
                   Image base64EncodedImage = getBase64EncodedJpeg(bitmap);
                   annotateImageRequest.setImage(base64EncodedImage);
                   annotateImageRequest.setFeatures(featureList);
                   imageList.add(annotateImageRequest);

                   BatchAnnotateImagesRequest batchAnnotateImagesRequest =
                           new BatchAnnotateImagesRequest();
                   batchAnnotateImagesRequest.setRequests(imageList);

                   Vision.Images.Annotate annotateRequest =
                           vision.images().annotate(batchAnnotateImagesRequest);
                   annotateRequest.setDisableGZipContent(true);
                   Log.d(TAG, "Sending request to Google Cloud");

                   BatchAnnotateImagesResponse response = annotateRequest.execute();
                   return response;
               } catch (GoogleJsonResponseException e) {
                 Log.e(TAG, "Request error: " + e.getContent());
               } catch (IOException e) {
               Log.d(TAG, "Request error: " + e.getMessage());
            }
            return null;
           }
           protected void onPostExecute(BatchAnnotateImagesResponse response) {
               //textResults.setText(getDetectedTexts(response));
               labelResults.setText(getDetectedLabels(response));
               //localResults.setText(getDetectedLocals(response));

           }
       }.execute();

    }


    private String getDetectedLabels(BatchAnnotateImagesResponse response){
        StringBuilder message = new StringBuilder("");
        if (response == null){
            return ("empty response\n");
        }
        List<EntityAnnotation> labels = response.getResponses().get(0).getLabelAnnotations();
        ArrayList<String> words = new ArrayList<>();
        if (labels != null) {
            for (EntityAnnotation label : labels) {
//                message.append(String.format(Locale.getDefault(), "%.3f: %s",
//                        label.getScore(), label.getDescription()));
//                message.append("\n");
                words.add(label.getDescription());
            }
        } else {
            return ("nothing\n");
        }
        Translator t = new Translator();
        try{
            ArrayList<String> translations = t.translate(words, translateAccro);
            Log.e(TAG, translations.toString());
            message.append(String.format(Locale.getDefault(), "%s\t -> \t %s", "English: ", choice + ": "));
            message.append('\n');
            message.append('\n');
            if (labels != null) {
                int index = 0;
                for (EntityAnnotation label : labels) {
                    message.append(String.format(Locale.getDefault(), "%s\t -> \t%s",
                            label.getDescription(), translations.get(index)));
                    message.append("\n");
                    index++;
                }
            } else {
                message.append("Nothing Detected\n");
            }
        } catch (IOException e){
            Log.e(TAG, e.toString());
        }




        return message.toString();
    }

    private String getDetectedLocals(BatchAnnotateImagesResponse response){
        StringBuilder message = new StringBuilder("");
        if(response ==  null){
            return ("nothing\n");
        }
        double[] x_indices = new double[4];
        double[] y_indices = new double[4];
        List<LocalizedObjectAnnotation> locals = response.getResponses().get(0).getLocalizedObjectAnnotations();
        Log.e(TAG, locals.toString());
        if (locals != null){
            for (LocalizedObjectAnnotation label : locals) {


                Log.e(TAG, label.toString());
                message.append(String.format(Locale.getDefault(), "%.3f: %s",
                        label.getScore(), label.getName()));
                message.append("Normalized Vertices:\n");

                Iterator<NormalizedVertex> l = label.getBoundingPoly().getNormalizedVertices().iterator();
                int index = 0;
                while (l.hasNext()) {
                    NormalizedVertex vertex = l.next();
                    x_indices[index] = (double) vertex.getX();
                    y_indices[index] = (double) vertex.getY();
                    index++;
                    message.append(String.format(Locale.getDefault(), " - (%s, %s)\n", vertex.getX(), vertex.getY()));
                }
                message.append(String.format(Locale.getDefault(), "Center: (%s, %s)\n", find_average(x_indices), find_average(y_indices)));

            }
        }

        return message.toString();
    }

    private double find_average(double[] a){
        double sum = 0.0;
        for(int i = 0; i < a.length; i++){
            sum += a[i];
        }
        return sum/4.0;
    }

    private String getDetectedTexts(BatchAnnotateImagesResponse response){
        StringBuilder message = new StringBuilder("");
        List<EntityAnnotation> texts = response.getResponses().get(0)
                .getTextAnnotations();
        if (texts != null) {
            for (EntityAnnotation text : texts) {
                message.append(String.format(Locale.getDefault(), "%s: %s",
                        text.getLocale(), text.getDescription()));
                message.append("\n");
            }
        } else {
            message.append("nothing\n");
        }

        return message.toString();
    }


    private Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {

        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }


    public Image getBase64EncodedJpeg(Bitmap bitmap) {
        Image image = new Image();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        image.encodeContent(imageBytes);
        return image;
    }


    private class Translator {

        public void try_translation(){
            try {
                ArrayList<String> ans = new ArrayList<> ();
                ArrayList<String> arr = new ArrayList<String>();
                arr.add("a ;dskfa;sdkf;adsk");
                arr.add("World");
                arr.add("Mom");
                String tar = "ru";
                ans = translate(arr, tar);
                Log.e(TAG, ans.toString());
            }
            catch (IOException e) {
                Log.e(TAG, e.toString());
            }

        }

        private ArrayList<String> translate(ArrayList<String> arr, String tar) throws IOException {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String key = "AIzaSyBRoIxDjqFtCcrvkwkmqE6Hd2vmQt7-8Oo";
            // Set up the HTTP transport and JSON factory
            HttpTransport httpTransport = new NetHttpTransport();
            JsonFactory jsonFactory = AndroidJsonFactory.getDefaultInstance();
            Translate.Builder translateBuilder = new Translate.Builder(httpTransport, jsonFactory, null);
            translateBuilder.setApplicationName(getString(R.string.app_name));
            Translate translate = translateBuilder.build();
            ArrayList<String> q = arr;
            ArrayList<String> responseList = new ArrayList<>();
            for(int i = 0; i < q.size(); i++)
            {
                Translate.Translations.List list = translate.translations().list(q, tar);
                list.setKey(key);
                list.setSource("en");
                TranslationsListResponse translateResponse = list.execute();
                String response = translateResponse.getTranslations().get(i).getTranslatedText();
                responseList.add(response);
            }
            for (int i = 0; i < responseList.size(); i++)
            {
                Log.d(TAG, responseList.get(i));
            }
            return responseList;
        }
    }




}
