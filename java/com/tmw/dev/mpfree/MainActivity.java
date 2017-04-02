package com.tmw.dev.mpfree;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final int READ_REQUEST_CODE = 42;
    private static final String SONG_TYPES = "audio/*";
    public static final String MUSIC_URI = "com.tmw.dev.mpfree.MUSICURI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ockSwitchToMusic(View pbtnView){
        Intent ittFindMusic = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        ittFindMusic.addCategory(Intent.CATEGORY_OPENABLE);
        ittFindMusic.setType(SONG_TYPES);

        startActivityForResult(ittFindMusic, READ_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int pintRequestCode,
                                 int pintResultCode,
                                 Intent pittResultData) {
        if (pintRequestCode == READ_REQUEST_CODE && pintResultCode == Activity.RESULT_OK) {
            Uri uriSong;
            if (pittResultData != null) {
                uriSong = pittResultData.getData();
                Intent ittPlayMusic = new Intent(this, PlayMusicActivity.class);
                ittPlayMusic.putExtra(MUSIC_URI, uriSong);
                startActivity(ittPlayMusic);
            }
        }
    }
}
