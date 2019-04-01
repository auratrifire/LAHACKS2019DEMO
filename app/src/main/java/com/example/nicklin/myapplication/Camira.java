package com.example.nicklin.myapplication;

import java.util.List;
import java.io.ByteArrayOutputStream;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.util.Log;
import android.content.ContentValues;
import android.util.Base64;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Camira extends Activity implements OnClickListener {

    public static String TAG = "Translation";
    String choice = "";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String currentPhotoPath;
    static int TAKE_PIC = 1;
    Bitmap bitMap;
    ImageView ivThumbnailPhoto;
    Button picButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camira);

        Log.e(TAG, "ew");
        Intent intent = getIntent();
        choice = intent.getStringExtra("LANG");

        //ivThumbnailPhoto.findViewById(R.id.imageView3);

        if (hasCamera() && hasDefualtCameraApp(MediaStore.ACTION_IMAGE_CAPTURE)) {
            picButton = findViewById(R.id.button);
            picButton.setOnClickListener(this);
        }

        Button gallery_button = findViewById(R.id.galleryButton);
        gallery_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Camira.this, VisionActivity.class);
                intent.putExtra("LANG", choice);
                String emptyPic = "";
                intent.putExtra("PIC", emptyPic);
                startActivity(intent);
            }
        });


//        Intent i = new Intent(Camira.this, com.example.nicklin.myapplication.CameraFunction.class);
//        i.putExtra("LANG", choice);
//        startActivity(i);


    }

    @Override
    public void onClick(View v){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent, TAKE_PIC);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        if (requestCode == TAKE_PIC && resultCode== RESULT_OK && intent != null){
            // get bundle
            Bundle extras = intent.getExtras();

            // get bitmap
            bitMap = (Bitmap) extras.get("data");
            //ivThumbnailPhoto.setImageBitmap(bitMap);
            String bitmapString = BitMapToString(bitMap);
            Intent i = new Intent(Camira.this, VisionActivity.class);
            i.putExtra("LANG", choice);
            i.putExtra("PIC", bitmapString);
            startActivity(i);
        }
    }

    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    private boolean hasDefualtCameraApp(String action){
        final PackageManager packageManager = getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        return list.size() > 0;

    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    }


//    public void buttonOnClick(View v){
//        dispatchTakePictureIntent();
//    }
//
//    private void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // Ensure that there's a camera activity to handle the intent
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                Uri photoURI = FileProvider.getUriForFile(this,
//                        "com.example.android.fileprovider",
//                        photoFile);
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//            }
//        }
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            ImageView img = findViewById(R.id.imageView3);
//            img.setVisibility(View.VISIBLE);
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            img.setImageBitmap(imageBitmap);
//        }
//
//    }

//    private File createImageFile() throws IOException
//    {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date(0));
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        currentPhotoPath = image.getAbsolutePath();
//        return image;
//    }


