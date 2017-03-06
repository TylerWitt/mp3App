package com.tmw.dev.mpfree;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final int READ_REQUEST_CODE = 42;
    public static final String MUSIC_URI = "com.tmw.dev.mpfree.MUSICURI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switchToMusic(View view){
        Intent findFiles = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        findFiles.addCategory(Intent.CATEGORY_OPENABLE);

        findFiles.setType("audio/*");

        startActivityForResult(findFiles, READ_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                Intent intent = new Intent(this, PlayMusicActivity.class);
                intent.putExtra(MUSIC_URI, uri);
                startActivity(intent);
            }
        }
    }
}
