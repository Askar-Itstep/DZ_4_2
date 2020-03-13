package com.example.dz_4_2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 111;
    private static final int REQUEST_SELECT = 222;
    private static final String TAG = "===MainActivity===";
    private Bitmap bmp;
    public final static String KEY_URI = "key_uri";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnImageCapture:{
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, MainActivity.REQUEST_CAMERA);
            }
            break;
            //--------- сохран. изображения-------------------------
            case R.id.btnImageSave:{
                if(bmp == null){
                    Toast.makeText(this, "ERROR. Bmp is NULL!", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "ERROR. Bmp is NULL!");
                    break;
                }
                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("bmp", bmp);    //здесь передача самого изображ.
                startActivity(intent);
            }
            break;
        //--------- выбор изображения-------------------------
            case R.id.btnChangeImage:{
                Intent intent = new Intent(this, SecondActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, MainActivity.REQUEST_SELECT);
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == MainActivity.REQUEST_SELECT && resultCode == RESULT_OK){
            Uri uri = (Uri) data.getExtras().get(MainActivity.KEY_URI);
            if(uri == null) {
                Log.e(TAG, "ERROR. uri is NULL!");
                return;
            }
            //Log.e(TAG, "URI return: " + uri.getPath());
            ImageView ivImage = findViewById(R.id.ivImage);
            ivImage.setImageURI(uri);
        }
        else if(requestCode == MainActivity.REQUEST_CAMERA && resultCode == RESULT_OK){
            bmp = (Bitmap) data.getExtras().get("data");
            if(bmp == null){
                Toast.makeText(this, "ERROR. Bmp is NULL!", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "ERROR. Bmp is NULL!");
                return;
            }
            Log.e(TAG, "Bmp: "+ bmp);
            ImageView ivImage = findViewById(R.id.ivImage);
            ivImage.setImageBitmap(bmp);
        }

    }
}
