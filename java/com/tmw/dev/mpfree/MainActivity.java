package com.tmw.dev.mpfree;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final int cintReadRequestCode = 42;
    private static final String cstrAudioMime = "audio/*";
    public static final String cstrMusicUri = "com.tmw.dev.mpfree.MUSICURI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ockSwitchToMusic(View pbtnView){
        Intent ittFindMusic = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        ittFindMusic.addCategory(Intent.CATEGORY_OPENABLE);
        ittFindMusic.setType(cstrAudioMime);

        startActivityForResult(ittFindMusic, cintReadRequestCode);
    }

    @Override
    public void onActivityResult(int pintRequestCode,
                                 int pintResultCode,
                                 Intent pittResultData) {
        if (pintRequestCode == cintReadRequestCode && pintResultCode == Activity.RESULT_OK) {
            Uri uriSong;
            if (pittResultData != null) {
                uriSong = pittResultData.getData();
                Intent ittPlayMusic = new Intent(this, PlayMusicActivity.class);
                ittPlayMusic.putExtra(cstrMusicUri, uriSong);
                startActivity(ittPlayMusic);
            }
        }
    }
}
