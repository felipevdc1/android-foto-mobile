package com.zomercorporation.android_foto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView photo;
    ProfilePictureService profilePictureService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photo = findViewById(R.id.photo);

        profilePictureService = new ProfilePictureService(this, new ProfilePictureService.OnProfilePictureUploaded() {
            @Override
            public void run(Bitmap user) {
                photo.setImageBitmap(user);
                //  binder.profileCircleOnEditScreen.setImageBitmap(Utils.decodeBase64(user.photo));
            }
        }, new ProfilePictureService.OnFail() {
            @Override
            public void run() {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("oi");
                    }
                });

            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askForPermissionAndShowCamera();
            }
        });

    }

    private void askForPermissionAndShowCamera() {
        profilePictureService.askForPermissionAndShowCamera();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        profilePictureService.onActivityResult(requestCode, resultCode, data);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        profilePictureService.onActivityResult(requestCode, resultCode, data);
//    }
}
