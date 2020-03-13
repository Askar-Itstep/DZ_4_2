package com.example.dz_4_2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class ThirdActivity extends AppCompatActivity {

    private final String TAG = "===ThirdActivity===";
    public final static String KEY_ONE_BMP = "key_one_bmp";
    public final static String KEY_TWO_TITLE = "key_two_title";
    public final static String KEY_THIRD_URI = "key_third_uri";
//Активность отображения рисунка
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        String title = this.getIntent().getStringExtra(ThirdActivity.KEY_TWO_TITLE);
        TextView tvImageName = this.findViewById(R.id.tvImageName);
        tvImageName.setText(title);

        Intent intent = this.getIntent();
        Uri uri = (Uri) intent.getExtras().get(ThirdActivity.KEY_THIRD_URI);
        Log.e(TAG, "uri: "+uri);

        ImageView imageView = this.findViewById(R.id.ivImage);
        imageView.setImageURI(uri);


//        Bitmap bmp = (Bitmap) intent.getParcelableExtra(ThirdActivity.KEY_ONE_BMP);
//        ImageView imageView = this.findViewById(R.id.ivImage);
//        imageView.setImageBitmap(bmp);

    }

}
