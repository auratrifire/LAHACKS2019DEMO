package com.example.nicklin.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class DrawActivity extends AppCompatActivity {

    private ImageView img1,img2;
    private EditText txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        img1=(ImageView) findViewById(R.id.img1);
        img2=(ImageView) findViewById(R.id.img2);

        txt1=(EditText) findViewById(R.id.txt1);
    }

    public void Convert(View v){

        String text=txt1.getText().toString();
        int width=txt1.getMeasuredWidth();

        //background color red
        int color= Color.RED;
        Bitmap img=drawText(text, width, color);
        img1.setImageBitmap(img);

        //background transparent

        int colorT=Color.TRANSPARENT;
        Bitmap img1=drawText(text,width,colorT);
        img2.setImageBitmap(img1);
    }

    public Bitmap drawText(String text, int textWidth, int color) {

        // Get text dimensions
        TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.parseColor("#ff00ff"));
        textPaint.setTextSize(30);

        StaticLayout mTextLayout = new StaticLayout(text, textPaint, textWidth, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);

        // Create bitmap and canvas to draw to
        Bitmap b = Bitmap.createBitmap(textWidth, mTextLayout.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas c = new Canvas(b);

        // Draw background
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        c.drawPaint(paint);

        // Draw text
        c.save();
        c.translate(0, 0);
        mTextLayout.draw(c);
        c.restore();

        return b;
    }
}
