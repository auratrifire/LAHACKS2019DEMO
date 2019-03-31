package com.example.nicklin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

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
                        startActivity(intent);
                        break;


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

