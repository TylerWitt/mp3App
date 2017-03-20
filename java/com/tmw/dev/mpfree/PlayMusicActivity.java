package com.tmw.dev.mpfree;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class PlayMusicActivity extends AppCompatActivity {
    private static MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        mediaPlayer = new MediaPlayer();
        Intent ittPassedMusic = getIntent();
        Uri uriSong = ittPassedMusic.getParcelableExtra(MainActivity.MUSIC_URI);

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try{
            mediaPlayer.setDataSource(getApplicationContext(), uriSong);
            mediaPlayer.prepare();
        }
        catch (IOException e) {
            System.out.println(e.toString());
            System.out.println("File not valid: " + uriSong.toString());
        }
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
